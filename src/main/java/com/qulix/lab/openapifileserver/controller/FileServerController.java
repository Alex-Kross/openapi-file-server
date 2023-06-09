package com.qulix.lab.openapifileserver.controller;

//import com.qulix.lab.SwaggerCodgen.model.Book;
import com.qulix.lab.SwaggerCodgen.api.FileApi;
import com.qulix.lab.openapifileserver.service.impl.FileServerImpl;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
//import com.qulix.lab.SwaggerCodgen.api.BookApi;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
//import com.qulix.lab.SwaggerCodgen.api.BookApi;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class FileServerController implements FileApi {
    private FileServerImpl fileServerService = new FileServerImpl();
    @Override
    public ResponseEntity<String> getFiles(String pathToFiles) {
        String answer = "";
//        Properties properties = System.getProperties();
        try {

            answer = fileServerService.getFiles(pathToFiles).toString();
//            logger.info("request for get all files is success");
        } catch (FileNotFoundException e) {
//            logger.error("request for get all files is fail: " + e);
            answer = e.getMessage();
        } catch (Exception e) {
            answer = e.getMessage();
//            logger.error("request for get all files is fail: " + e);
        }
        return ResponseEntity.ok(answer);
    }

    @Override
    public ResponseEntity<String> createFolder(String pathToFiles, String fileName) {
//        logger.info("request for create the folder");
        return ResponseEntity.ok(String.valueOf(fileServerService.createFolder(pathToFiles, fileName)));
    }

    @Override
    public ResponseEntity<String> deleteFolder(String pathToFiles, String fileName) {
//        logger.info("request for create the folder");
        return ResponseEntity.ok(String.valueOf(fileServerService.deleteFolder(pathToFiles, fileName)));
    }

    @Override
    public ResponseEntity<String> upload(String pathToFiles, MultipartFile file) {
        String result = "";
        Resource resource = file.getResource();
        String name = file.getName();
        String originalFilename = file.getOriginalFilename();
        String contentType = file.getContentType();
        try {
//            File file2 = resource.getFile();
            result = fileServerService.uploadFile(pathToFiles, file);
//            File testFile = new File("C:\\Super" + pathToFiles + "\\" + fileName.getName());
//
//            FileUtils.writeByteArrayToFile(testFile, fileName.getBytes());
//            result = fileName.getBytes().toString();
////        fileName.getInputStream();
        } catch (IOException e) {
//
        }
        return ResponseEntity.ok("uploadFile is called, Uploaded file name : " + result);
    }

    @Override
    public ResponseEntity<Resource> download(String pathToFiles) {
        File file = fileServerService.downloadFile(pathToFiles);

        try {
//            FileInputStream fileInputStream = new FileInputStream(file);
//            Path path = Paths.get("C:\\Super\\" + pathToFiles + "\\" + file.toPath());
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(file.toPath()));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                    .body(resource);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<String> delete(String pathToFiles) {
        return ResponseEntity.ok(String.valueOf(fileServerService.deleteFile(pathToFiles)));
    }

    //    @Override
//    public ResponseEntity<UploadFileRequest> uploadFile(MultipartFile fileName) {
//        return FileApi.super.uploadFile(fileName);
//    }
//    @Override
//    public ResponseEntity<Void> uploadFile(MultipartFile fileName) {
//        return FileApi.super.uploadFile(fileName);
//    }

    //    @Override
//    public ResponseEntity<List<Book>> getBooks() {
//        List<Book> bookList = new ArrayList<>();
//        bookList.add(new Book()
//                .title("Name of the Wind")
//                .author("Patrick"));
//        bookList.add(new Book()
//                .title("Mistborn")
//                .author("Brandon"));
//        return ResponseEntity.ok(bookList);
//    }
}
