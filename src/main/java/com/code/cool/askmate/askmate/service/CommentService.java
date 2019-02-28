package com.code.cool.askmate.askmate.service;

import com.code.cool.askmate.askmate.model.Comment;
import com.code.cool.askmate.askmate.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public void createNewComment(long userId, long questionId, Comment comment) {
        Comment newComment = new Comment();
        newComment.setUserId(userId);
        newComment.setQuestionId(questionId);
        newComment.setCommentText(comment.getCommentText());
        newComment.setVoteNumber(comment.getVoteNumber());
        commentRepository.save(newComment);
    }
}
