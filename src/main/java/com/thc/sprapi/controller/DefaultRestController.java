package com.thc.sprapi.controller;

import com.thc.sprapi.util.FileUpload;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "0. 기본 API 안내",
        description = "기본 기능 정의한 RestController.")
@RequestMapping("/api/default")
@RestController
public class DefaultRestController {
    @Operation(summary = "파일업로드",
            description = "파일을 서버에 업로드(일반) \n"
                    + "@param MultipartFile multipartFile \n"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<String\\> \n"
                    + "@exception \n"
    )
    @PostMapping("/uploadFile")
    public ResponseEntity<String> uploadFile(@Valid @RequestParam MultipartFile file, HttpServletRequest request) {
        String returnValue = null;
        try {
            //returnValue = FileUpload.s3(file);
            returnValue = FileUpload.local(file, request);
        } catch (IOException e) {
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

    @GetMapping("/test")
    public String test() throws Exception {
        return "";
    }
}
