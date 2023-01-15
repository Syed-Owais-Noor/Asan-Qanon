package com.example.mad_project;

public class ReplyModel {
    private String email, userEmail, query, reply;

    public ReplyModel() {
    }

    public ReplyModel(String email, String userEmail, String query, String reply) {
        this.email = email;
        this.userEmail = userEmail;
        this.query = query;
        this.reply = reply;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
