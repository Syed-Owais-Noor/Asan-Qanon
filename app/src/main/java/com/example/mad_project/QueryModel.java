package com.example.mad_project;

public class QueryModel {
    private String email;
    private String query;

    public QueryModel() { }

    public QueryModel(String email, String query) {
        this.email = email;
        this.query = query;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}