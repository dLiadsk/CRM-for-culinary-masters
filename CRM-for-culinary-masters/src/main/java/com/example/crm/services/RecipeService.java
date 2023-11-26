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
   public boolean createRecipe(Recipe recipe){
       recipeRepository.save(recipe);
       return true;
   }
}
