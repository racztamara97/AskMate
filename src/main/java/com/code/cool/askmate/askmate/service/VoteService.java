package com.code.cool.askmate.askmate.service;

import com.code.cool.askmate.askmate.VoteType;
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

    public void createNewVote(long userId, long questionId, VoteType type) {

        Vote newVote = new Vote();
        newVote.setUserId(userId);
        newVote.setQuestionId(questionId);
        newVote.setType(type);
        voteRepository.save(newVote);
    }

    public boolean checkVoteExists(long questionId, long userId, VoteType type) {
        List<Vote> votesOfThisUser = voteRepository.findVotesByUserId(userId);
        if (votesOfThisUser != null) {
            for (Vote actual : votesOfThisUser) {
                if (actual.getQuestionId() == questionId) {
                    if (actual.getType() != type) {
                        if (actual.getType() == VoteType.UP) {
                            voteDown(questionId, userId);
                        } else {
                            voteUp(questionId, userId);
                        }
                        voteRepository.delete(actual);
                        Vote delete = voteRepository.findVoteByUserIdAndQuestionId(userId, questionId);
                        voteRepository.delete(delete);
                        return false;

                    }
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public void voteUp(long questionId, long userId) {
        Question actual = questionRepository.getQuestionById(questionId);
        int voteBefore = actual.getVoteNumber();
        actual.setVoteNumber(voteBefore + 1);
        createNewVote(userId, questionId, VoteType.UP);
        questionRepository.save(actual);
    }

    public void voteDown(long questionId, long userId) {
        Question actual = questionRepository.getQuestionById(questionId);
        int voteBefore = actual.getVoteNumber();
        if (voteBefore != 0) {
            actual.setVoteNumber(voteBefore - 1);
            createNewVote(userId, questionId, VoteType.DOWN);
            questionRepository.save(actual);
        }
    }
}
