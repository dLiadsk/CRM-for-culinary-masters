package com.example.crm.services;

import com.example.crm.models.Menu;
import com.example.crm.models.Recipe;
import com.example.crm.models.User;
import com.example.crm.repositories.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
   private final MenuRepository menuRepository;

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
