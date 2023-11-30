package com.example.crm.controllers;

import com.example.crm.models.Menu;
import com.example.crm.models.Recipe;
import com.example.crm.models.User;
import com.example.crm.services.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @PostMapping("/myMenus")
    public List<Menu> getMenuByUser(@RequestBody User user){
        return menuService.getUserMenus(user);
    }
    @GetMapping("/deleteMenu/{id}")
    public String deleteMenu(@PathVariable Long id){
        return menuService.deleteById(id);
    }
    @GetMapping("/menu/{id}")
    public Menu getMenuById(@PathVariable Long id){
        Optional<Menu> menu = menuService.findById(id);
        if(menu.isPresent()){
            return menu.get();
        } else {
            throw new RuntimeException("Такого меню не існує");
        }
    }
    @PostMapping("/updateMenu/{id}")
    public String updateMenu(@PathVariable Long id,@RequestBody MenuController.MenuRequest request){
        Menu menu = Menu.of(
                request.user(), request.name(), request.notes(), request.image(), request.preparationTime(), request.complexity(), request.recipes());
        return menuService.updateRecipe(id,menu);
    }


    @GetMapping("/menus")
    public List<Menu> getRecipes() {
        return menuService.getMenus();
    }



}
