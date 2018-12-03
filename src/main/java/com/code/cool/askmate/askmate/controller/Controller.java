package com.code.cool.askmate.askmate.controller;

import com.code.cool.askmate.askmate.model.Question;
import com.code.cool.askmate.askmate.model.User;
import com.code.cool.askmate.askmate.repository.QuestionRepository;
import com.code.cool.askmate.askmate.repository.UserRepository;
import com.code.cool.askmate.askmate.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@org.springframework.stereotype.Controller
@Scope("session")
@SessionAttributes({"userToLogin", "question"})
public class Controller {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private LoginService loginService;

    @GetMapping("/")
    public String index(Model model) {
        if(!model.containsAttribute("userToLogin")){
            model.addAttribute("userToLogin", new User());
        }
        return "index";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("newUser", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationForm(@ModelAttribute("newUser") User user) {
        userRepository.save(user);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String loginForm(HttpSession session, @ModelAttribute("userToLogin") User user){
        if(loginService.checkUserExists(user.getUsername(), user.getPassword())){
            session.setAttribute("user", user);
            System.out.println(user);

            return "redirect:/";
        } else {
            return "redirect:/registration";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/";
    }

}
