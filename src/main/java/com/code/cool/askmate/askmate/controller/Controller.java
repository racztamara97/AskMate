package com.code.cool.askmate.askmate.controller;

import com.code.cool.askmate.askmate.model.Question;
import com.code.cool.askmate.askmate.model.User;
import com.code.cool.askmate.askmate.repository.QuestionRepository;
import com.code.cool.askmate.askmate.repository.UserRepository;
import com.code.cool.askmate.askmate.service.LoginService;
import com.code.cool.askmate.askmate.service.QuestionService;
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

    @Autowired
    private QuestionService questionService;

    @ModelAttribute("question")
    public Question createQuestion() {
        return new Question();
    }

    @GetMapping("/")
    public String index(Model model) {
        if (!model.containsAttribute("userToLogin")) {
            model.addAttribute("userToLogin", new User());
        }
       /* if (model.containsAttribute("orderedQuestionsAsc")) {
            model.addAttribute("questions", questionRepository.findAllByOrderByQuestion_titleAsc());
        }*//*
        else if (model.containsAttribute("orderedQuestionsDesc")){
            model.addAttribute("questions", questionRepository.findAllOrderByTitleDesc());
        }*/
        model.addAttribute("questions", questionRepository.findAll());
        return "index";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("newUser", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationForm(HttpSession session, @ModelAttribute("newUser") User user) {
        userRepository.save(user);
        session.setAttribute("user", user);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String loginForm(HttpSession session, @ModelAttribute("userToLogin") User user) {
        if (loginService.checkUserExists(user.getUsername(), user.getPassword())) {
            session.setAttribute("user", user);
            System.out.println(user);

            return "redirect:/";
        } else {
            return "redirect:/registration";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

    @GetMapping("/new_question")
    public String newQuestionForm(Model model) {
        model.addAttribute("newQuestion", new Question());
        return "new_question";
    }

    @PostMapping("/new_question")
    public String addNewQuestion(HttpSession session, @ModelAttribute("question") Question question) {
        User user = (User) session.getAttribute("user");
        questionService.createNewQuestion(user, question);
        return "redirect:/";
    }


    @GetMapping("/question")
    public String getQuestion(Model model, @RequestParam(value = "questionButton") long id) {
        model.addAttribute("actualQuestion", questionRepository.getQuestionById(id));
        return "question";
    }

    @PostMapping("/title_ASC")
    public String getQuestionsOrderedByTitleAsc(Model model) {
        model.addAttribute("orderedQuestionsAsc");
        return "index";
    }

    @PostMapping("/title_DESC")
    public String getQuestionsOrderedByTitleDesc(Model model) {
        model.addAttribute("orderedQuestionDesc");
        return "index";
    }


}
