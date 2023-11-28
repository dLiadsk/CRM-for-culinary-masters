package com.example.crm.controllers;


import com.example.crm.models.Recipe;
import com.example.crm.repositories.RecipeRepository;
import com.example.crm.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;
    @GetMapping("/test")
    public String text(){
        String message = "Привіт світ";
        return message;
    }

/*    @GetMapping("/recipes")
    public List<Recipe> getRecipes(){
        return recipeRepository.findAll();
    }*/
    @PostMapping("/createRecipe")
    public String createRecipes(@RequestBody Recipe recipe){
        recipeService.createRecipe(recipe);
        return "success";
    }


}
