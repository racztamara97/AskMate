package com.code.cool.askmate.askmate.repository;

import com.code.cool.askmate.askmate.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, String> {

    public List<Vote> findVotesByUserId(long userId);

    public Vote findVoteByUserIdAndQuestionId(long userId, long questionId);
}
