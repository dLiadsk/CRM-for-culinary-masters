package com.example.crm.repositories;


import com.example.crm.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuReactionsRepository extends JpaRepository<MenuReaction,Long> {

    List<MenuReaction> findAllByMenu(Menu menu);

    boolean existsByUser(User user);

    MenuReaction findByUser(User user);

}
