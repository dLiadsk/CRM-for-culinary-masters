 package com.example.crm.services;

 import com.example.crm.models.User;
 import com.example.crm.repositories.UserRepository;
 import com.fasterxml.jackson.annotation.JsonProperty;
 import lombok.RequiredArgsConstructor;
 import org.springframework.beans.factory.annotation.Value;
 import lombok.extern.java.Log;
 import org.springframework.data.crossstore.ChangeSetPersister;
 import org.springframework.http.HttpStatus;
 import org.springframework.security.core.userdetails.UsernameNotFoundException;
 import org.springframework.security.crypto.password.PasswordEncoder;
 import org.springframework.stereotype.Service;
 import org.springframework.web.bind.annotation.ResponseStatus;
 import org.springframework.web.server.ResponseStatusException;

 @Service
 public class UserService {
    private final UserRepository userRepository;

    private final String accessTokenSecret;
    private final String refreshTokenSecret;

    public UserService(@Value("${application.security.access-token.secret}") String accessTokenSecret,
                       @Value("${application.security.refresh-token.secret}") String refreshToken,
                       UserRepository userRepository,
                       PasswordEncoder passwordEncoder){
        this.accessTokenSecret = accessTokenSecret;
        this.refreshTokenSecret = refreshToken;
        this.userRepository = userRepository;
        this.passwordEncoder =passwordEncoder;
    }

    private final PasswordEncoder passwordEncoder;

    public User findUserById(long id) {
        return userRepository.findUserByUserId(id);
    }

    public User createUser(String username,
                              String email,
                              String password,
                              String firstName,
                              String lastName,
                              String date,
                              String phoneNumber,
                              String father,
                              String gender) {

        return userRepository.save(User.of(
                username,
                email,
                passwordEncoder.encode(password),
                firstName,
                lastName,
                date,
                father,
                gender,
                phoneNumber
        ));
    }

     public Login login(String email, String password) {
        var user = userRepository.findUserByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
        if (!passwordEncoder.matches(password, user.getPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password not match");
        }
        return Login.of(user.getUserId(),accessTokenSecret,refreshTokenSecret);
     }

     public User getUserFromToken(String token){
        return userRepository.findUserByUserId(Token.from(token, accessTokenSecret));
     }

     public Login refreshAccess(String refreshToken) {
        var userId = Token.from(refreshToken, refreshTokenSecret);

        return Login.of(userId,accessTokenSecret, Token.of(refreshToken));
     }

     public User findUserByUsername(String username){
        if(userRepository.existsUserByUsername(username)){
            return userRepository.findUserByUsername(username);
        } else {
            return new User();
        }
     }
 }
