package com.example.crm.controllers;

import com.example.crm.models.Recipe;
import com.example.crm.models.User;
import com.example.crm.services.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class MenuController {

    @Autowired
    MenuService menuService;

    @PostMapping("/createMenu")
    public String createMenus(@RequestBody MenuRequest menu){

        menuService.createMenu(menu.user(), menu.name(), menu.notes(), menu.image(), menu.preparationTime(), menu.complexity(), menu.recipes());
        return "success";

    }

    record MenuRequest(User user,
                       String name,
                       String notes,
                       String image,
                       String preparationTime,
                       String complexity,
                       List<Recipe> recipes
    ) {
    }
}
