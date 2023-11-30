package com.example.crm.services;

import com.example.crm.models.User;
import com.example.crm.models.UserPhoto;
import com.example.crm.repositories.UserPhotoRepository;
import com.example.crm.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPhotoService {

    private final UserPhotoRepository userPhotoRepository;

    private final UserRepository userRepository;

    public UserPhoto getByUserId(User user){
        return userPhotoRepository.findUserPhotoByUser(user);
    }

    public String savePhoto(User user,String message){
        if(userRepository.existsById(user.getUserId())){
            if(userPhotoRepository.existsUserPhotoByUser(userRepository.findUserByUserId(user.getUserId()))){
                UserPhoto userPhoto = userPhotoRepository.findUserPhotoByUser(user);
                userPhoto.setImage(message);
                userPhotoRepository.save(userPhoto);
                return "Фото збережено";
            } else {
                userPhotoRepository.save(UserPhoto.of(user,message));
                return "Фото збережено";
            }
        } else {
            return "Такого користувача не існує";
        }
    }
}
