package com.code.cool.askmate.askmate.service;

import com.code.cool.askmate.askmate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;

    public boolean checkUserExists(String username, String password) {
        if(username.equals("") || password.equals("")){
            return false;
        }
        if ((userRepository.getUserByUsername(username)) == (userRepository.getUserByPassword(password))) {
            return true;
        } else {
            return false;
        }
    }

}
