package com.code.cool.askmate.askmate.repository;

import com.code.cool.askmate.askmate.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, String> {

    @Override
    List<Comment> findAll();

    List<Comment> findAllByQuestionId(long questionId);
}
