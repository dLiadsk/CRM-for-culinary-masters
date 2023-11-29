package com.example.crm.services;

import com.example.crm.models.Recipe;
import com.example.crm.models.User;
import com.example.crm.repositories.RecipeRepository;
import com.example.crm.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RecipeService {
   private final RecipeRepository recipeRepository;


    public String deleteById(Long id){
        if(recipeRepository.existsById(id)){
            recipeRepository.deleteById(id);
            return "Видалено";
        } else
            return "Такого рецепту не існує";
    }

    public String updateRecipe(Long id,Recipe recipe){
        if(recipeRepository.existsById(id)){
            Recipe recipeToEdit = recipeRepository.findByRecipeId(id);
            recipeToEdit.setName(recipe.getName());
            recipeToEdit.setIngredients(recipe.getIngredients());
            recipeToEdit.setSteps(recipe.getSteps());
            recipeToEdit.setNotes(recipe.getNotes());
            recipeToEdit.setImage(recipe.getImage());
            recipeToEdit.setPreparationTime(recipe.getPreparationTime());
            recipeToEdit.setComplexity(recipe.getComplexity());
            recipeToEdit.setUser(recipe.getUser());
            recipeRepository.save(recipeToEdit);
            return "Зміни успішно збережено";
        } else {
            return "Такого рецепту не існує";
        }

    }

    public Optional<Recipe> findById(Long id){
        return recipeRepository.findById(id);
    }

    public List<Recipe> getRecipes(){
        return recipeRepository.findAll();
    }

    public Recipe createRecipe(String name,
                             String ingredients,
                             String steps,
                             String notes,
                             String image,
                             String preparationTime,
                             String complexity,
                             User user) {


        return recipeRepository.save(Recipe.of(
                name,
               ingredients,
                steps,
                notes,
                image,
                preparationTime,
                complexity,
                user));
    }
    }

