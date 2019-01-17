package com.code.cool.askmate.askmate.repository;

import com.code.cool.askmate.askmate.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface QuestionRepository extends JpaRepository<Question, String> {

    @Override
    List<Question> findAll();

    Question getQuestionById(long id);

   // Question findByQuestionTitle(String questionTitle);

    List<Question> findAllByQuestionTitleContaining(String search);
    // List<Question> findAllByOrderByQuestion_titleAsc();

    // List<Question> findAllOrderByTitleDesc();

    //List<Question> findAllByQuestion_titleOrQuestion_descriptionIsContainingSearch(String search);
}