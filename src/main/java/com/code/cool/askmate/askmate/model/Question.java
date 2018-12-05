package com.code.cool.askmate.askmate.model;

import javax.persistence.*;

@Entity(name = "Questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    private User user;
    private String question_title;
    private String question_description;
    private int vote_number = 0;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

   @Override
    public String toString(){
       return "Question{" +
               "id=" + id +
               ", user=" + user +
               ", question_title=" + question_title +
               ", question1_description=" + question_description +
               ", vote_number=" + vote_number +
               '}';
   }
}
