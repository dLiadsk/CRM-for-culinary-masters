// package com.example.crm.controllers;

// import com.example.crm.models.User;
// import com.example.crm.services.UserService;
// import lombok.RequiredArgsConstructor;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// @Controller
// @RequiredArgsConstructor
// public class UserController {
//    private final UserService userService;

//    @GetMapping("/login")
//    public String toLogin() {
//        return "login";
//    }


//    @GetMapping("/sing_up")
//    public String singUpForm() {
//        return "sing_up";
//    }

//    @PostMapping("/sing_up")
//    public String singUp(User user, Model model, RedirectAttributes redirectAttributes) {
//        if (!userService.createUser(user)) {
//            model.addAttribute("error", "Користувач з такою поштою вже існує.");
//            return "sing_up";
//        }
//        redirectAttributes.addFlashAttribute("success", "Реєстрація пройшла успішно.");
//        return "redirect:/";
//    }
// }
