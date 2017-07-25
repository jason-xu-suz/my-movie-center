package com.jason.synology.mmc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Copyright with Industics company.
 * Author: jason.xu
 * Date: 2017/7/24
 * Time: 下午5:30
 */
@Component
@ConfigurationProperties(prefix = "scan")
public class ScannerProperties {
    private String rootPath;
    private boolean ignoreDotFolder;

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public boolean isIgnoreDotFolder() {
        return ignoreDotFolder;
    }

    public void setIgnoreDotFolder(boolean ignoreDotFolder) {
        this.ignoreDotFolder = ignoreDotFolder;
    }
}
