package com.example.crm.controllers;

import com.example.crm.models.User;
import com.example.crm.models.UserPhoto;
import com.example.crm.services.UserPhotoService;
import com.example.crm.services.UserService;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8081", allowCredentials = "true", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RequiredArgsConstructor
public class UserController {

   @Autowired
   private UserService userService;

   @Autowired
   private UserPhotoService userPhotoService;

    @GetMapping("/login")
    public String login(){
        return "success";
    }


   record RegisterRequest(String username,
                          String email,
                          String password,
                          String firstName,
                          String lastName,
                          String date,
                          String phoneNumber,
                          String father,
                          String gender
   ){}
//    record RegisterResponse(Long id,
//                            @JsonProperty("first_name") String firstName,
//                            @JsonProperty("last_name") String lastName,
//                            String email
//                            ){}
   @PostMapping("/registration")
    public String register(@RequestBody RegisterRequest registerRequest){
        String message = null;
         try {
             userService.createUser(
                     registerRequest.username(),
                     registerRequest.email(),
                     registerRequest.password(),
                     registerRequest.firstName(),
                     registerRequest.lastName(),
                     registerRequest.date(),
                     registerRequest.phoneNumber(),
                     registerRequest.father(),
                     registerRequest.gender()
             );
         } catch (Error e){
             message = "Користувач з таким username вже існує";
         }
    return message;
   }

    record LoginRequest(String email, String password){}
    record LoginResponse(String token){}
   @PostMapping("/newLogin")
    public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpServletResponse response){
        var login = userService.login(loginRequest.email(),loginRequest.password());

       Cookie cookie = new Cookie("refresh_token",login.getRefreshToken().getToken());
       cookie.setMaxAge(3600);
       cookie.setHttpOnly(true);
       cookie.setSecure(true);
       cookie.setPath("/api");
       response.addCookie(cookie);

        return new LoginResponse(login.getAccessToken().getToken());
   }

       record UserResponse(User user
       ){}
        @GetMapping("/user")
        public UserResponse user(HttpServletRequest request, HttpServletResponse response){
            var user = (User) request.getAttribute("user");

            response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");

            return new UserResponse(user);
        }


        record RefreshResponse(String message){}
        @PostMapping("/refresh")
        public RefreshResponse refresh(@CookieValue("refresh_token")String refreshToken){
            return new RefreshResponse(userService.refreshAccess(refreshToken).getAccessToken().getToken());
        }

        record LogoutResponse(String message){}
        @PostMapping("/logout")
        public LogoutResponse logout(HttpServletResponse response){
            Cookie cookie = new Cookie("refresh_token",null);
            cookie.setMaxAge(0);
            cookie.setValue(null);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setPath("/api");

            response.addCookie(cookie);

            return new LogoutResponse("Logout success");
        }

        @GetMapping("/profile/{username}")
        public User findUserByUsername(@PathVariable String username){
            return userService.findUserByUsername(username);
        }

        @PostMapping("/profile/updateUser")
        public String updateUser(@RequestBody User user){
            return userService.updateUser(user);
        }

        record photoRequest(User user, String image){}
        @PostMapping("/profile/upload")
        public String uploadPhoto(@RequestBody photoRequest photoRequest){
            return userPhotoService.savePhoto(photoRequest.user(),photoRequest.image());
        }

        @GetMapping("/checkEmail/{email}")
        public boolean email(@PathVariable String email){
            return userService.email(email);
        }

        @GetMapping("/checkUsername/{username}")
        public boolean username(@PathVariable String username){
            return userService.username(username);
        }

}
