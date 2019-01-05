package com.icheung.stackoverflow.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Wrapper {
    @SerializedName("items")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
