package com.example.crm.controllers;


import com.example.crm.models.Recipe;
import com.example.crm.models.User;
import com.example.crm.repositories.RecipeRepository;
import com.example.crm.repositories.UserRepository;
import com.example.crm.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
        @GetMapping("/recipes")
        public List<Recipe> getRecipes() {
            return recipeService.findAll();
        }

        @GetMapping("/recipes/{id}")
        public Recipe getRecipeById(@PathVariable Long id){
            Optional<Recipe> recipe = recipeService.findById(id);
            if(recipe.isPresent()){
                return recipe.get();
            } else {
                throw new RuntimeException("Такого рецепту не існує");
            }
        }

        @PostMapping("/myRecipes")
        public List<Recipe> getRecipeByUser(@RequestBody User user){
            List<Recipe> recipes = recipeService.findAll();
            List<Recipe> userRecipes = new ArrayList<>();
            for (Recipe rep: recipes){
                if(rep.getUser().getUserId().equals(user.getUserId())){
                    userRecipes.add(rep);
                }
            }
            return userRecipes;
        }

        @GetMapping("/deleteRecipe/{id}")
        public String deleteRecipe(@PathVariable Long id){
            return recipeService.deleteById(id);
        }

        @PostMapping("/updateRecipe/{id}")
        public String updateRecipe(@PathVariable Long id,@RequestBody RecipeRequest request){
            Recipe recipe = Recipe.of(
                    request.name(),
                    request.ingredients(),
                    request.steps(),
                    request.notes(),
                    request.image(),
                    request.preparationTime(),
                    request.complexity(),
                    request.user());
            return recipeService.updateRecipe(id,recipe);
        }

}
