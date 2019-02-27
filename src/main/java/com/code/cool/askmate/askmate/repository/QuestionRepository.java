package com.code.cool.askmate.askmate.repository;

import com.code.cool.askmate.askmate.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface QuestionRepository extends JpaRepository<Question, String> {

    @Override
    List<Question> findAll();

    Question getQuestionById(long id);

    List<Question> findAllByQuestionTitleContaining(String search);

    List<Question> findAllByOrderByQuestionTitleAsc();

    List<Question> findAllByOrderByQuestionTitleDesc();

    //List<Question> findAllByQuestionTitleOrQuestionDescriptionContaining(String search);

    List<Question> findAllByOrderByVoteNumberAsc();

    List<Question> findAllByOrderByVoteNumberDesc();
}