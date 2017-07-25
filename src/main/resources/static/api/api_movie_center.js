import requestObj from './request';

import {SCAN_ROOT_FOLDER} from './urls'

let request = requestObj.request;

export function scanRootFolder() {
    return request(SCAN_ROOT_FOLDER);
}