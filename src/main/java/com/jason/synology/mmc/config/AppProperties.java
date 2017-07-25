package com.jason.synology.mmc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Copyright with Industics company.
 * Author: jason.xu
 * Date: 2017/7/25
 * Time: 下午3:20
 */
@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private String name;
    private String version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
