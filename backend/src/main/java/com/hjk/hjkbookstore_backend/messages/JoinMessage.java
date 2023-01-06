package com.hjk.hjkbookstore_backend.messages;

import lombok.Data;

@Data
public class JoinMessage extends Message {
    private String username;

    public JoinMessage(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "[JoinMessage] " + username;
    }
}
