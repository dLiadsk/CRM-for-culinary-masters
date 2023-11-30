package com.example.crm.services;

import com.example.crm.models.Menu;
import com.example.crm.models.Recipe;
import com.example.crm.models.User;
import com.example.crm.repositories.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    public List<Menu> getMenus() {
        return menuRepository.findAll();
    }

    public List<Menu> getUserMenus(User user) {
        List<Menu> menuList = new ArrayList<>();
        getMenus().stream().filter(menu -> menu.getUser().getUserId().equals(user.getUserId())).forEach(menuList::add);
        return menuList;
    }

    public String deleteById(Long id){
        if(menuRepository.existsById(id)){
            menuRepository.deleteById(id);
            return "Видалено";
        } else
            return "Такого рецепту не існує";
    }


    public Optional<Menu> findById(Long id){
        return menuRepository.findById(id);
    }


    public String updateRecipe(Long id,Menu recipe){
        if(menuRepository.existsById(id)){
            Menu recipeToEdit = menuRepository.findByMenuId(id);
            recipeToEdit.setName(recipe.getName());
            recipeToEdit.setRecipes(recipe.getRecipes());
            recipeToEdit.setNotes(recipe.getNotes());
            recipeToEdit.setImage(recipe.getImage());
            recipeToEdit.setPreparationTime(recipe.getPreparationTime());
            recipeToEdit.setComplexity(recipe.getComplexity());
            recipeToEdit.setUser(recipe.getUser());
            menuRepository.save(recipeToEdit);
            return "Зміни успішно збережено";
        } else {
            return "Такого меню не існує";
        }

    }

    public Menu createMenu(User user,
                           String name,
                           String notes,
                           String image,
                           String preparationTime,
                           String complexity,
                           List<Recipe> recipes
    ) {


        return menuRepository.save(Menu.of(
                user,
                name,
                notes,
                image,
                preparationTime,
                complexity,
                recipes));
    }

}
