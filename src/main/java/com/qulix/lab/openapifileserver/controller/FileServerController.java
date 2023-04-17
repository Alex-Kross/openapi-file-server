package com.qulix.lab.openapifileserver.controller;

//import com.qulix.lab.SwaggerCodgen.model.Book;
import com.qulix.lab.SwaggerCodgen.api.FileApi;
import com.qulix.lab.openapifileserver.service.impl.FileServerImpl;
import org.springframework.http.ResponseEntity;
//import com.qulix.lab.SwaggerCodgen.api.BookApi;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
//import com.qulix.lab.SwaggerCodgen.api.BookApi;

import java.io.FileNotFoundException;

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
    public ResponseEntity<Void> uploadFile(MultipartFile fileName) {
        return FileApi.super.uploadFile(fileName);
    }

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
