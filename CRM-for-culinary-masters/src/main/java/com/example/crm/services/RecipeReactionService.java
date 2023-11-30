package com.example.crm.services;

import com.example.crm.models.Recipe;
import com.example.crm.models.RecipeReaction;
import com.example.crm.models.User;
import com.example.crm.repositories.RecipeReactionsRepository;
import com.example.crm.repositories.RecipeRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeReactionService {

    private final RecipeReactionsRepository recipeReactionReactionsRepository;

    private final RecipeRepository recipeRepository;

    public String saveReaction(User user, Recipe recipe, String like,String dislike){
        if(recipeReactionReactionsRepository.existsByUser(user)){
        if(like.equals("1") & dislike.equals("0"))
            if(recipeReactionReactionsRepository.existsByUser(user)){
                RecipeReaction reaction = recipeReactionReactionsRepository.findByUser(user);
                reaction.setReaction("1");
                reaction.setDisReaction("0");
                recipeReactionReactionsRepository.save(reaction);
        }
        if(like.equals("0") & dislike.equals("1")){
                if(recipeReactionReactionsRepository.existsByUser(user)){
                    RecipeReaction reaction = recipeReactionReactionsRepository.findByUser(user);
                    reaction.setReaction("0");
                    reaction.setDisReaction("1");
                    recipeReactionReactionsRepository.save(reaction);
            }
        }
        }
        else{
            recipeReactionReactionsRepository.save(RecipeReaction.of(user,recipe,like,dislike));
        }
        return "Вашу реакцію збережено";
    }

    public String getReactionsById(Long id){
        if(recipeRepository.existsById(id)){
            String reactions = null;
            String disReactions = null;
            int likes = 0;
            int disLikes = 0;
            Recipe recipe = recipeRepository.findByRecipeId(id);
            if(recipeReactionReactionsRepository.findAllByRecipe(recipe) != null){
                List<RecipeReaction> reactionList = recipeReactionReactionsRepository.findAllByRecipe(recipe);
                for (RecipeReaction react : reactionList){
                    likes = likes + Integer.parseInt(react.getReaction());
                    disLikes = disLikes + Integer.parseInt(react.getDisReaction());
                }
                reactions = String.valueOf(likes);
                disReactions = String.valueOf(disLikes);
            }
            return reactions + "#" + disReactions;
        } else {
            return "Такого рецепту не існує";
        }
    }

}
