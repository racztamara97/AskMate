package com.code.cool.askmate.askmate.repository;

import com.code.cool.askmate.askmate.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuestionRepository extends JpaRepository<Question, String>{
}
