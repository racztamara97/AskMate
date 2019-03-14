package com.code.cool.askmate.askmate.model;

import javax.persistence.*;


@Entity(name = "Comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String commentText;
    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private User user;
    //private long userId;
    private long questionId;
    private int voteNumber = 0;

    public Comment() {
    }

    public Comment(String commentText, User user, long questionId, int voteNumber) {
        this.commentText = commentText;
        this.user = user;
        this.questionId = questionId;
        this.voteNumber = voteNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public int getVoteNumber() {
        return voteNumber;
    }

    public void setVoteNumber(int voteNumber) {
        this.voteNumber = voteNumber;
    }
}
