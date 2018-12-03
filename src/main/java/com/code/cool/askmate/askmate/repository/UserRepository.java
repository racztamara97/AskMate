package com.code.cool.askmate.askmate.repository;

import com.code.cool.askmate.askmate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String>{

    }
