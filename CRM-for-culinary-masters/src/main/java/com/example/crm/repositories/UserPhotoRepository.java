package com.example.crm.repositories;

import com.example.crm.models.User;
import com.example.crm.models.UserPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPhotoRepository extends JpaRepository<UserPhoto,Long> {

    UserPhoto findUserPhotoByUser(User user);

    boolean existsUserPhotoByUser(User user);
}
