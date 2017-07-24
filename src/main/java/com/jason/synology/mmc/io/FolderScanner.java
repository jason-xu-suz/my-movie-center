package com.jason.synology.mmc.io;

import com.jason.synology.mmc.config.ScannerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Copyright with Industics company.
 * Author: jason.xu
 * Date: 2017/7/24
 * Time: 下午3:18
 */
@Service
public class FolderScanner {
    @Autowired
    private ScannerProperties scannerProperties;

    public File[] scanRoot() {
        File scanRoot = new File(scannerProperties.getRootPath());
        if (scanRoot.exists() && scanRoot.isDirectory()) {
            return scanRoot.listFiles();
        }
        return null;
    }
}
