package com.example.crm.controllers;


import com.example.crm.models.Recipe;
import com.example.crm.models.User;
import com.example.crm.repositories.RecipeRepository;
import com.example.crm.repositories.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/test")
    public String text() {
        String message = "Привіт світ";
        return message;
    }

    @PostMapping("/createRecipe")
    public String createRecipes(@RequestBody RecipeRequest recipe) {

        recipeService.createRecipe(recipe.name(), recipe.ingredients(), recipe.steps(), recipe.notes(), recipe.image(), recipe.preparationTime(), recipe.complexity(), recipe.user());
        return "success";
    }

    record RecipeRequest(String name,
                         String ingredients,
                         String steps,
                         String notes,
                         String image,
                         String preparationTime,
                         String complexity,

                         User user
    ) {
    }
    /*    @GetMapping("/recipes")
        public List<Recipe> getRecipes(){
            return recipeRepository.findAll();
        }*/


}
