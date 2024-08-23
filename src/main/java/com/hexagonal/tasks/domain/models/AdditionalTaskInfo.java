package com.hexagonal.tasks.domain.models;

public class AdditionalTaskInfo {

    private final Long id;
    private final String userName;
    private final String userEmail;

    public AdditionalTaskInfo(Long id, String userName, String userEmail) {
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
