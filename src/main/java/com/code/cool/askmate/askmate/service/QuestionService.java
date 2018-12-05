package com.code.cool.askmate.askmate.service;

import com.code.cool.askmate.askmate.model.Question;
import com.code.cool.askmate.askmate.model.User;
import com.code.cool.askmate.askmate.repository.QuestionRepository;
import com.code.cool.askmate.askmate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserRepository userRepository;

    public void createNewQuestion(User myUser, Question question){

        Question newQuestion = new Question();
        newQuestion.setUser(myUser);
        newQuestion.setQuestion_title(question.getQuestion_title());
        newQuestion.setQuestion_description(question.getQuestion_description());
        newQuestion.setVote_number(question.getVote_number());
        questionRepository.save(newQuestion);
    }
}
