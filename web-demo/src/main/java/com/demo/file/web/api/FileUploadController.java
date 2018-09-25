/*
 * Copyright (c) 2015-2015, Zhejiang QiZhi Technologies Co., Ltd.
 * <http://www.shterm.com/>
 */
package com.demo.file.web.api;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class FileUploadController {

    @Autowired
    private ServletConfig sc;

    private String fileRoot;

    private String shareRoot;

    @PostConstruct
    public void init() {
        fileRoot = sc.getInitParameter("FILE_ROOT");
        shareRoot = sc.getInitParameter("SHARE_ROOT");
    }

    /*@RequestMapping("/")
    public String index() {
        return "index";
    }*/

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public List<UploadedFile> upload(MultipartHttpServletRequest request,
        HttpServletResponse response) throws IOException {
        File path = new File(fileRoot, "files");
        if (!path.exists()) {
            path.mkdirs();
        }
        List<UploadedFile> uploadedFiles = new ArrayList<>();
        for (MultipartFile multipartFile : request.getFileMap().values()) {
            saveFileToLocalDisk(multipartFile, path);
            UploadedFile fileInfo = getUploadedFileInfo(multipartFile, "files");
            uploadedFiles.add(fileInfo);
        }
        return uploadedFiles;
    }

    @RequestMapping("/list")
    public String listFiles(Map<String, Object> map) {
        File[] files = new File(fileRoot, "files").listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
        List<UploadedFile> list = new ArrayList<>(files.length);
        for (File file : files) {
            UploadedFile fileInfo = new UploadedFile();
            fileInfo.setName(file.getName());
            fileInfo.setSize(file.length());
            list.add(fileInfo);
        }
        map.put("fileList", list);
        return "/listFiles";
    }

    @RequestMapping("/delete/{fileName:.+}")
    public String delete(@PathVariable("fileName") String fileName) {
        File file = new File(fileRoot + "/files", fileName);
        file.delete();
        return "redirect:/list";
    }

    private void saveFileToLocalDisk(MultipartFile multipartFile, File path) throws IOException {
        FileCopyUtils.copy(new BufferedInputStream(multipartFile.getInputStream()),
            new BufferedOutputStream(
                new FileOutputStream(new File(path, multipartFile.getOriginalFilename()))));
    }

    private UploadedFile getUploadedFileInfo(MultipartFile multipartFile, String location) {
        UploadedFile fileInfo = new UploadedFile();
        fileInfo.setName(multipartFile.getOriginalFilename());
        fileInfo.setSize(multipartFile.getSize());
        fileInfo.setType(multipartFile.getContentType());
        fileInfo.setLocaton(shareRoot + '\\' + location + '\\');
        return fileInfo;
    }
}
