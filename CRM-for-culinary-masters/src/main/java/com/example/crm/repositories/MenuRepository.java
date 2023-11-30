package com.example.crm.repositories;

import com.example.crm.models.Menu;
import com.example.crm.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu findByMenuId(Long id);

}
