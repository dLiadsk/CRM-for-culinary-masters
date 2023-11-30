package com.example.crm.services;

import com.example.crm.models.*;
import com.example.crm.repositories.MenuReactionsRepository;
import com.example.crm.repositories.MenuRepository;
import com.example.crm.repositories.RecipeReactionsRepository;
import com.example.crm.repositories.RecipeRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuReactionService {

    private final MenuReactionsRepository menuReactionsRepository;

    private final MenuRepository menuRepository;

    public String saveReaction(User user, Menu menu, String like, String dislike){
        if(menuReactionsRepository.existsByUser(user)){
            if(like.equals("1") & dislike.equals("0"))
                if(menuReactionsRepository.existsByUser(user)){
                    MenuReaction reaction = menuReactionsRepository.findByUser(user);
                    reaction.setReaction("1");
                    reaction.setDisReaction("0");
                    menuReactionsRepository.save(reaction);
                }
            if(like.equals("0") & dislike.equals("1")){
                if(menuReactionsRepository.existsByUser(user)){
                    MenuReaction reaction = menuReactionsRepository.findByUser(user);
                    reaction.setReaction("0");
                    reaction.setDisReaction("1");
                    menuReactionsRepository.save(reaction);
                }
            }
        }
        else{
            menuReactionsRepository.save(MenuReaction.of(user,menu,like,dislike));
        }
        return "Вашу реакцію збережено";
    }

    public String getReactionsById(Long id){
        if(menuRepository.existsById(id)){
            String reactions = null;
            String disReactions = null;
            int likes = 0;
            int disLikes = 0;
            Menu menu = menuRepository.findByMenuId(id);
            if(menuReactionsRepository.findAllByMenu(menu) != null){
                List<MenuReaction> reactionList = menuReactionsRepository.findAllByMenu(menu);
                for (MenuReaction react : reactionList){
                    likes = likes + Integer.parseInt(react.getReaction());
                    disLikes = disLikes + Integer.parseInt(react.getDisReaction());
                }
                reactions = String.valueOf(likes);
                disReactions = String.valueOf(disLikes);
            }
            return reactions + "#" + disReactions;
        } else {
            return "Такого меню не існує";
        }
    }

}
