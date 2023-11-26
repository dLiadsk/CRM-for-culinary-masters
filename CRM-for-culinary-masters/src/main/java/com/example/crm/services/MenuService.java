package com.example.crm.services;

import com.example.crm.models.Menu;
import com.example.crm.repositories.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuService {
   private final MenuRepository menuRepository;

   public boolean createRecipe(Menu menu) {
       menuRepository.save(menu);
       return true;
   }

}
