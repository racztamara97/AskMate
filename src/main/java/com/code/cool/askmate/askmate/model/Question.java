package com.code.cool.askmate.askmate.model;

import javax.persistence.*;

@Entity(name = "Questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private User user;
    private String questionTitle;
    private String questionDescription;
    private int voteNumber = 0;

    public Question() {
    }

    public Question(User user, String questionTitle, String questionDescription, int vote_number) {
        this.user = user;
        this.questionTitle = questionTitle;
        this.questionDescription = questionDescription;
        this.voteNumber = vote_number;
    }

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

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public int getVoteNumber() {
        return voteNumber;
    }

    public void setVoteNumber(int voteNumber) {
        this.voteNumber = voteNumber;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", user=" + user +
                ", questionTitle=" + questionTitle +
                ", question1_description=" + questionDescription +
                ", voteNumber=" + voteNumber +
                '}';
    }
}
