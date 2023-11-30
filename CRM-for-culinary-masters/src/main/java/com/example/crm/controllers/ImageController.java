package com.example.crm.controllers;

import com.example.crm.models.Menu;
import com.example.crm.models.Recipe;
import com.example.crm.models.User;
import com.example.crm.models.UserPhoto;
import com.example.crm.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8081", allowCredentials = "true", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class ImageController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private UserService userService;
    @Autowired
    private UserPhotoService userPhotoService;

    @Autowired
    private RecipeService recipeService;

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
        Optional<Recipe> recipePhoto = recipeService.findById(id);

        if (recipePhoto.isPresent()) {
            Recipe recipe = recipePhoto.get();

            try {
                // Зчитати байти з файлу
                byte[] bytes = fileStorageService.readBytesFromFile("D:/photos/recipes/"+recipe.getImage());

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

    @GetMapping("/menus/{id}")
    public ResponseEntity<byte[]> getPhotoMenus(@PathVariable Long id) {
        Optional<Menu> menuPhoto = menuService.findById(id);

        if (menuPhoto.isPresent()) {
            Menu menu = menuPhoto.get();

            try {
                // Зчитати байти з файлу
                byte[] bytes = fileStorageService.readBytesFromFile("D:/photos/recipes/"+menu.getImage());

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

    @GetMapping("/userPhoto/{username}")
    public ResponseEntity<byte[]> getPhotoUser(@PathVariable String username) {
        UserPhoto userPhoto = userPhotoService.getByUserId(userService.findUserByUsername(username));

        if (userPhoto != null) {
            try {
                // Зчитати байти з файлу
                byte[] bytes = fileStorageService.readBytesFromFile("D:/photos/recipes/"+userPhoto.getImage());

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
