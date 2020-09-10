package com.example.bffa_rahmatsaputra.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    @SerializedName("users")
    private List<UsersItem> users;

    public List<UsersItem> getUsers() {
        return users;
    }

    public void setUsers(List<UsersItem> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return
                "Response{" +
                        "users = '" + users + '\'' +
                        "}";
    }
}