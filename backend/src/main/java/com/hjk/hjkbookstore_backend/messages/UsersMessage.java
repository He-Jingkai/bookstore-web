package com.hjk.hjkbookstore_backend.messages;

import java.util.List;

public class UsersMessage extends Message {
    private List<String> userList;

    public UsersMessage(List<String> userList) {
        this.userList = userList;
    }

    public List<String> getUserList() {
        return userList;
    }

    @Override
    public String toString() {
        return "[UsersMessage] " + userList.toString();
    }
}
