package com.example.crm.repositories;


import com.example.crm.models.Recipe;
import com.example.crm.models.RecipeReaction;
import com.example.crm.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeReactionsRepository extends JpaRepository<RecipeReaction,Long> {

    List<RecipeReaction> findAllByRecipe(Recipe recipe);

    boolean existsByUser(User user);

    RecipeReaction findByUser(User user);

}
