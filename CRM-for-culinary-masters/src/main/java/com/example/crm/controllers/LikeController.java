package com.example.crm.controllers;


import com.example.crm.models.Menu;
import com.example.crm.models.Recipe;
import com.example.crm.models.User;
import com.example.crm.services.MenuReactionService;
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

    @Autowired
    private MenuReactionService menuReactionService;

    record ReactionRequest(
            User user,
            Recipe recipe,
            String reaction,
            String disReaction
    ){}

    @PostMapping("/saveReaction")
    public String saveReaction(@RequestBody ReactionRequest request){
        return recipeReactionService.saveReaction(
                request.user(),
                request.recipe(),
                request.reaction(),
                request.disReaction()
                );

    }


    record MenuReactionRequest(
            User user,
            Menu menu,
            String reaction,
            String disReaction
    ){}
    @GetMapping("/getReactions/menus/{id}")
    public String getMenusLikes(@PathVariable Long id){
        return menuReactionService.getReactionsById(id);
    }

    @PostMapping("/saveReaction/menus")
    public String saveMenusReaction(@RequestBody MenuReactionRequest request){
        return menuReactionService.saveReaction(
                request.user(),
                request.menu(),
                request.reaction(),
                request.disReaction()
        );

    }


    @GetMapping("/getReactions/{id}")
    public String getLikes(@PathVariable Long id){
        return recipeReactionService.getReactionsById(id);
    }




}
