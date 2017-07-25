/*global Promise*/
import _ from 'lodash';
import axios from 'axios';
import qs from 'qs';
import setting from 'config/setting';
import moment from 'moment';
import {trimAll, camelCaseAll, snakeCaseAll} from 'util';

let prefix = '';
let errorHandler = function() {};
const templateRegular = /{{([\s\S]+?)}}/;
_.templateSettings.interpolate = templateRegular;

let cache = {};

// override Date toJSON fix dateFormat
// eslint-disable-next-line
Date.prototype.toJSON = function toJSON() {
    return moment(this).format('YYYY-MM-DDTHH:mm:ss');
};

function request(urlOption, options = {}) {
    let user = setting.getCurrentUser();
    if (user) {
        options.urlData = _.merge({}, options.urlData, {
            tenantId: user.tenantId,
            userId: user.userId
        });
    }

    options.config = options.config || {};
    var method = urlOption.method || 'get';
    var url = urlOption.url;
    var isFormSubmit = urlOption.isFormSubmit;
    var isTemplate = templateRegular.test(url);
    if (isTemplate) {
        let urlData = trimAll(options.urlData);
        url = _.template(url)(_.merge({}, urlData, {prefix}));
    }

    let cacheKey = `${method}$$${url}$$${JSON.stringify(_.get(options, 'config', {}))}`;

    let headers = {
        'X-Requested-With': 'XMLHttpRequest',
        'Accept': 'application/json'
    };
    if (_.toLower(method) === 'get') {
        headers['Cache-Control'] = 'no-cache';
        headers['Pragma'] = 'no-cache';

        if (options.cache === true && cache[cacheKey]) {
            return new Promise((resolve) => {
                resolve(cache[cacheKey]);
            });
        }
    }

    if (_.toLower(method) === 'post' || _.toLower(method) === 'put') {
        headers['Content-Type'] = 'application/json;charset=UTF-8';
        headers['Accept'] = 'application/json';
    }

    let data = _.get(options, 'config.data');
    if (data) {
        options.config.data = trimAll(snakeCaseAll(data));
    }

    let params = _.get(options, 'config.params');
    if (params) {
        options.config.params = trimAll(snakeCaseAll(params));
    }

    let transformRequest = [function(data) {
        if (_.isObject(data)) {
            return JSON.stringify(data, function(key, value) {
                if (value === undefined || value === '') {
                    return null;
                }
                return value;

            });
        } else {
            return data;
        }
    }];

    if (isFormSubmit) {
        headers['Content-Type'] = 'application/x-www-form-urlencoded';
        transformRequest = [];
        let data = _.get(options, 'config.data');
        if (data) {
            options.config.data = qs.stringify(data);
        }
    }
    // add cache
    return axios(_.merge(options.config, {
        url,
        method,
        headers,
        timeout: 30000,
        transformRequest: transformRequest,
        paramsSerializer: function(params) {
            return qs.stringify(params, {arrayFormat: 'repeat'});
        }
    })).then((response)=>{
        // error handle
        let data = camelCaseAll(response.data);
        if (options.cache === true) {
            cache[cacheKey] = data;
        }

        return data;
    }, (error) =>{
        console.log(_.get(error, 'response.data.message'));
        if (options.showError !== false) {
            let data = _.get(error, 'response.data');
            data = camelCaseAll(data);
            _.set(error, 'response.data', data);
            errorHandler(error);
        }
        throw error;
    });
}

function setPrefix(newPrefix) {
    prefix = newPrefix;
}

function setErrorHandler(newErrorHandler) {
    errorHandler = newErrorHandler;
}

function getUrl(urlOption, options = {}) {
    let user = setting.getCurrentUser();
    if (user) {
        options.urlData = _.merge({}, options.urlData, {
            tenantId: user.tenantId,
            userId: user.userId
        });
    }
    var url = urlOption.url;
    var isTemplate = templateRegular.test(url);
    if (isTemplate) {
        let urlData = trimAll(options.urlData);
        url = _.template(url)(_.merge({}, urlData, {prefix}));
    }
    return url;
}

let result = {
    request: request,
    setPrefix: setPrefix,
    setErrorHandler: setErrorHandler,
    getUrl: getUrl
};

export default result;
