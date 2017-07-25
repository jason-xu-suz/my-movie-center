package com.jason.synology.mmc.endpoint;

import com.jason.synology.mmc.io.FolderScanner;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

/**
 * Copyright with Industics company.
 * Author: jason.xu
 * Date: 2017/7/24
 * Time: 下午4:43
 */
@RestController
@RequestMapping("api")
@Api("IO APIs")
public class FolderEndpoint {
    @Autowired
    private FolderScanner folderScanner;

    @ResponseBody
    @GetMapping("/scan_root_folder")
    public List<File> scanRootFolder() {
        return folderScanner.scanRoot();
    }
}
