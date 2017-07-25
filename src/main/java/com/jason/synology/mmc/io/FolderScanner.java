package com.jason.synology.mmc.io;

import com.jason.synology.mmc.config.ScannerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<File> scanRoot() {
        File scanRoot = new File(scannerProperties.getRootPath());
        if (scanRoot.exists() && scanRoot.isDirectory()) {
            File[] files = scanRoot.listFiles();
            if (files != null) {
                return Arrays.stream(files)
                        .filter(file -> scannerProperties.isIgnoreDotFolder() && !file.getName().startsWith("."))
                        .collect(Collectors.toList());
            }
        }
        return null;
    }
}
