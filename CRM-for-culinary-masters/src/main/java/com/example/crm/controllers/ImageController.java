package com.example.crm.controllers;

import com.example.crm.models.PhotoImage;
import com.example.crm.repositories.PhotoRepository;
import com.example.crm.services.FileStorageService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8081", allowCredentials = "true", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class ImageController {

    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            // Зберегти файл на сервері та записати інформацію в базу даних
            byte[] bytes = file.getBytes();
            String fileName = file.getOriginalFilename();
            String filePath = "D:/photos/recipes/" + fileName;
            fileStorageService.saveBytesToFile(bytes, filePath);

            return fileName;
        } catch (IOException e) {
            return "Failed to upload file";
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable Long id) {
        Optional<PhotoImage> optionalPhoto = photoRepository.findById(id);

        if (optionalPhoto.isPresent()) {
            PhotoImage photo = optionalPhoto.get();

            try {
                // Зчитати байти з файлу
                byte[] bytes = fileStorageService.readBytesFromFile("D:/photos/"+photo.getFileName());

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // або MediaType.IMAGE_PNG

                return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
            } catch (IOException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
