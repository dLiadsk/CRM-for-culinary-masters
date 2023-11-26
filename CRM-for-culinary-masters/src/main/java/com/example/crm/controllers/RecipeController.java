package com.example.crm.controllers;


import com.example.crm.models.Recipe;
import com.example.crm.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;
    @GetMapping("/test")
    public String text(){
        String message = "Привіт світ";
        return message;
    }

    @GetMapping("/recipes")
    public List<Recipe> getRecipes(){
        return recipeRepository.findAll();
    }

}
