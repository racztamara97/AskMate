package com.code.cool.askmate.askmate.model;

import javax.persistence.*;

@Entity(name = "Questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    private int user_id;
    private String question_title;
    private String question_description;
    private int vote_number;

    public Question() {
    }

    public Question(int user_id, String question_name, String question_description, int vote_number) {
        this.user_id = user_id;
        this.question_title = question_name;
        this.question_description = question_description;
        this.vote_number = vote_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getQuestion_title() {
        return question_title;
    }

    public void setQuestion_title(String question_title) {
        this.question_title = question_title;
    }

    public String getQuestion_description() {
        return question_description;
    }

    public void setQuestion_description(String question_description) {
        this.question_description = question_description;
    }

    public int getVote_number() {
        return vote_number;
    }

    public void setVote_number(int vote_number) {
        this.vote_number = vote_number;
    }

}
