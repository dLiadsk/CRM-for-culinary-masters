package com.example.crm.repositories;

import com.example.crm.models.Recipe;
import com.example.crm.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Recipe findByRecipeId(Long id);
}
