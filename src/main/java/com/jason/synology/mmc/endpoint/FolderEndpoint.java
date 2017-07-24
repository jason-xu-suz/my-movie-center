package com.jason.synology.mmc.endpoint;

import com.jason.synology.mmc.io.FolderScanner;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * Copyright with Industics company.
 * Author: jason.xu
 * Date: 2017/7/24
 * Time: 下午4:43
 */
@RestController
@RequestMapping("api")
@Api("IO Apis")
public class FolderEndpoint {
    @Autowired
    private FolderScanner folderScanner;

    @GetMapping("/listFolder")
    public File[] scanFile() {
        return folderScanner.scan(new File("/User/json"));

    }
}
