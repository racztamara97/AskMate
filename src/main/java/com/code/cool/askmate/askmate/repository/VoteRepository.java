package com.code.cool.askmate.askmate.repository;

import com.code.cool.askmate.askmate.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, String> {
}
