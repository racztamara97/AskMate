package com.code.cool.askmate.askmate.service;

import com.code.cool.askmate.askmate.model.Question;
import com.code.cool.askmate.askmate.model.Vote;
import com.code.cool.askmate.askmate.repository.QuestionRepository;
import com.code.cool.askmate.askmate.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VoteService {

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    QuestionRepository questionRepository;

    public void createNewVote(long userId, long questionId) {

        Vote newVote = new Vote();
        newVote.setUserId(userId);
        newVote.setQuestionId(questionId);
        voteRepository.save(newVote);
    }


    public boolean checkVoteExsits(long userId, long questionId) {
        List<Vote> votesOfThisUser = voteRepository.findVotesByUserId(userId);
        if (votesOfThisUser != null) {
            for (Vote actual : votesOfThisUser) {
                if (actual.getQuestionId() == questionId) {
                    return true;
                }
            }
            //createNewVote(userId, questionId);
            //voteUp(questionId);
            return false;
        }
        //createNewVote(userId, questionId);
        //voteUp(questionId);
        return false;
    }

    public void voteUp(long questionId) {
        Question actual = questionRepository.getQuestionById(questionId);
        int voteBefore = actual.getVoteNumber();
        actual.setVoteNumber(voteBefore + 1);
        questionRepository.save(actual);
    }

    public void voteDown(long questionId) {
        Question actual = questionRepository.getQuestionById(questionId);
        int voteBefore = actual.getVoteNumber();
        if (voteBefore != 0) {
            actual.setVoteNumber(voteBefore - 1);
            questionRepository.save(actual);
        }
    }
}
