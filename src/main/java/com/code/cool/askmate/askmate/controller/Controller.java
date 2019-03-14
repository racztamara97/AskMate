package com.code.cool.askmate.askmate.controller;

import com.code.cool.askmate.askmate.VoteType;
import com.code.cool.askmate.askmate.model.Comment;
import com.code.cool.askmate.askmate.model.Question;
import com.code.cool.askmate.askmate.model.User;
import com.code.cool.askmate.askmate.repository.CommentRepository;
import com.code.cool.askmate.askmate.repository.QuestionRepository;
import com.code.cool.askmate.askmate.repository.UserRepository;
import com.code.cool.askmate.askmate.service.CommentService;
import com.code.cool.askmate.askmate.service.LoginService;
import com.code.cool.askmate.askmate.service.QuestionService;
import com.code.cool.askmate.askmate.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@org.springframework.stereotype.Controller
@Scope("session")
@SessionAttributes({"userToLogin", "question", "searchForThis", "actualQuestion"})
public class Controller {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private LoginService loginService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;


    @ModelAttribute("question")
    public Question createQuestion() {
        return new Question();
    }

    public User getUserFromSession(HttpSession session){
        User userInSession = (User) session.getAttribute("user");
        String usernameInSession = userInSession.getUsername();
        return userRepository.getUserByUsername(usernameInSession);
    }

    @GetMapping("/")
    public String index(Model model) {
        if (!model.containsAttribute("userToLogin")) {
            model.addAttribute("userToLogin", new User());
        }
        model.addAttribute("questions", questionRepository.findAll());
        return "index";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("newUser", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationForm(Model model, HttpSession session, @ModelAttribute("newUser") User user) {
        if (!model.containsAttribute("userToLogin")) {
            model.addAttribute("userToLogin", new User());
        }
        userRepository.save(user);
        session.setAttribute("user", user);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String loginForm(HttpSession session, @ModelAttribute("userToLogin") User user) {
        if (loginService.checkUserExists(user.getUsername(), user.getPassword())) {
            session.setAttribute("user", user);
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
        User userInSession = (User) session.getAttribute("user");
        String username = userInSession.getUsername();
        User realUser = userRepository.getUserByUsername(username);
        questionService.createNewQuestion(realUser, question);
        return "redirect:/";
    }

    @GetMapping("/question")
    public String getQuestion(HttpSession session, Model model, @RequestParam(value = "questionButton") long id) {
        if (!model.containsAttribute("userToLogin")) {
            model.addAttribute("userToLogin", new User());
        }
        model.addAttribute("actualQuestion", questionRepository.getQuestionById(id));
        session.setAttribute("actualQuestion", questionRepository.getQuestionById(id));
        model.addAttribute("newComment", new Comment());
        model.addAttribute("comments", commentRepository.findAllByQuestionId(id));
        return "question";
    }

    @PostMapping("/search")
    public String search(@RequestParam(value = "search") String search, Model model) {
        model.addAttribute("questions", questionRepository.findAllByQuestionTitleContaining(search));
        return "index";
    }

    @PostMapping("/vote")
    public String vote(HttpSession session, @RequestParam("voteType") String vote) {
        Question actualQuestion = (Question) session.getAttribute("actualQuestion");
        User realUser = getUserFromSession(session);
        long userId = realUser.getId();
        long questionId = actualQuestion.getId();
        if (vote.equals("up")) {
            if (!voteService.checkVoteExists(questionId, userId, VoteType.UP)) {
                voteService.voteUp(questionId, userId);
            }
        } else {
            if (!voteService.checkVoteExists(questionId, userId, VoteType.DOWN)) {
                voteService.voteDown(questionId, userId);
            }
        }
        return "redirect:/question?questionButton=" + questionId;
    }

    @PostMapping("/sort")
    public String sort(Model model, @RequestParam("sortType") String sort){
        if (sort.equals("titleAsc")){
            model.addAttribute("questions", questionRepository.findAllByOrderByQuestionTitleAsc());
        }
        if (sort.equals("titleDesc")){
            model.addAttribute("questions", questionRepository.findAllByOrderByQuestionTitleDesc());
        }
        if (sort.equals("voteAsc")){
            model.addAttribute("questions", questionRepository.findAllByOrderByVoteNumberAsc());
        }
        if (sort.equals("voteDesc")) {
            model.addAttribute("questions", questionRepository.findAllByOrderByVoteNumberDesc());
        }
        return "index";
    }

/*    @GetMapping("/add_comment")
    public String addComment(Model model){
        model.addAttribute("newComment", new Comment());
        return "new_comment";
    }*/

    @PostMapping("/add_comment")
    public String addCommentForm(HttpSession session, @ModelAttribute("newComment") Comment comment){
        User realUser = getUserFromSession(session);
        Question actualQuestion = (Question) session.getAttribute("actualQuestion");
        long questionId = actualQuestion.getId();
        commentService.createNewComment(realUser, questionId, comment);
        return "redirect:/question?questionButton=" + questionId;
    }

}
