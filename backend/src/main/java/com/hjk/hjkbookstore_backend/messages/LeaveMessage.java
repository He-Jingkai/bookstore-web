package com.hjk.hjkbookstore_backend.messages;

import lombok.Data;

@Data
public class LeaveMessage extends Message{
    private String username;

    public LeaveMessage(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "[LeaveMessage] " + username;
    }
}
