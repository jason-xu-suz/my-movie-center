package com.jason.synology.mmc.io;

import java.io.File;

/**
 * Copyright with Industics company.
 * Author: jason.xu
 * Date: 2017/7/24
 * Time: 下午3:18
 */
public class FolderScanner {
    public File[] scan(File file) {
        if (file.exists() && file.isDirectory()) {
            return file.listFiles();
        }
        return null;
    }
}
