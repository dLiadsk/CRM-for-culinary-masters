package com.example.crm.services;

import com.example.crm.models.Recipe;
import com.example.crm.models.User;
import com.example.crm.repositories.RecipeRepository;
import com.example.crm.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeService {
   private final RecipeRepository recipeRepository;
//   public boolean createRecipe(Recipe recipe){
//       recipeRepository.save(recipe);
//       return true;
//   }
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
