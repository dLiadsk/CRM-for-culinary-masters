package com.example.crm.controllers;


import com.example.crm.models.Recipe;
import com.example.crm.models.User;
import com.example.crm.services.RecipeReactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api")
public class LikeController {
    @Autowired
    private RecipeReactionService recipeReactionService;

    record ReactionRequest(
            User user,
            Recipe recipe,
            String reaction,
            String disReaction
    ){}

    @PostMapping("/saveReaction")
    public String saveReaction(@RequestBody ReactionRequest request){
        recipeReactionService.saveReaction(
                request.user(),
                request.recipe(),
                request.reaction(),
                request.disReaction()
                );
        return request.disReaction();
    }

    @GetMapping("/getReactions/{id}")
    public String getLikes(@PathVariable Long id){
        return recipeReactionService.getReactionsById(id);
    }




}
