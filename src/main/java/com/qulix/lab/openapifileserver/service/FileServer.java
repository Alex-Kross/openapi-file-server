package com.qulix.lab.openapifileserver.service;


import com.qulix.lab.openapifileserver.entity.FileEntity;

public interface FileServer {
    FileEntity getFiles(String path) throws Exception;
    boolean createFolder(String path, String name);
    boolean deleteFolder(String path, String name);
}
