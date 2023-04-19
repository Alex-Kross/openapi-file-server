package com.qulix.lab.openapifileserver.service.impl;


import com.qulix.lab.openapifileserver.entity.FileAttribute;
import com.qulix.lab.openapifileserver.entity.FileEntity;
import com.qulix.lab.openapifileserver.service.FileServer;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
@PropertySource("classpath:application.properties")
public class FileServerImpl implements FileServer {
    @Value("${path.root}")
    private String rootPath = "C:\\Super";

    //get root folder from properties
    {
//        PropertiesService propertiesService = new PropertiesService();
//        // first variant
//        rootPath = propertiesService.getRootPathWithUtilProp();
//        // second variant
//        rootPath = propertiesService.getRootPathWithRestEasyProp();
    }
//    @Autowired
    public FileEntity getFiles(String path) throws Exception {

        //get file with this union path
        File file = new File(rootPath + path);
        if (file.exists()) {
            // initialize FileEntity object
            FileEntity fileInfo = new FileEntity();
            //transfer file data in object
            fileInfo.setName(file.getName());
            fileInfo.setLength(file.length());
            fileInfo.setPath(path);
            //find suitable attribute
            List<FileAttribute> fileAttributes = new ArrayList<>();
            if (file.isDirectory()) {
                fileAttributes.add(FileAttribute.DIRECTORY);

                fileInfo.setInnerFiles(file.list());
            } else {
                fileAttributes.add(FileAttribute.FILE);
            }
            if (file.isHidden()) {
                fileAttributes.add(FileAttribute.HIDDEN);
            }
            if (file.canExecute()) {
                fileAttributes.add(FileAttribute.EXECUTABLE);
            }
            if (file.canWrite()) {
                fileAttributes.add(FileAttribute.WRITABLE);
            }
            if (file.canRead()) {
                fileAttributes.add(FileAttribute.READABLE);
            }
            fileInfo.setFileAttributes(fileAttributes.toArray(new FileAttribute[fileAttributes.size()]));

            return fileInfo;
        } else {
            throw new FileNotFoundException("File not found");
        }
    }
    @Override
    public boolean createFolder(String path, String name){
        File file = new File(rootPath + path + "\\" + name);
        if (file.exists()) {
            throw new RuntimeException("Folder exist");
        }
        return file.mkdir();
    }
    @Override
    public boolean deleteFolder(String path, String name){
        File file = new File(rootPath + path + "\\" + name);
        if (!file.exists()) {
            throw new RuntimeException("Folder doesn't exist");
        }
        return file.delete();
    }


    public String uploadFile(String path, MultipartFile file) throws IOException{
        String fileName = file.getOriginalFilename();
//        String fileList = "";
        File newFile = new File(rootPath + "\\" + path + "\\" + fileName);

//        File currentFile = file.getResource().getFile();
        FileUtils.writeByteArrayToFile(newFile, file.getBytes());
//        copyDataFileInAnotherFile(currentFile, newFile);
//        byte [] bytes = IOUtils.toByteArray(file.getInputStream());
//        fileList = String.valueOf(bytes);

        return fileName;
    }
    public void copyDataFileInAnotherFile(File file, File anotherFile) throws IOException {
        InputStream inputStream = new FileInputStream(file);
        OutputStream outputStream = new FileOutputStream(anotherFile);
        outputStream.write(inputStream.readAllBytes());
    }

    public boolean deleteFile(String path) {
        File file = new File(rootPath + "\\" + path);
        if (!file.exists()) {
            throw new RuntimeException("File doesn't exist");
        }
        return file.delete();
    }

    public File downloadFile(String path) {
        File file = new File(rootPath + "\\" + path);
        return file;
    }
}
