package com.hjk.hjkbookstore_backend.messages;

import lombok.Data;

@Data
public class ChatMessage extends Message {
    private String content;
    private String username;

    public ChatMessage(String content, String username) {
        this.content = content;
        this.username = username;
    }

    @Override
    public String toString() {
        return "[ChatMessage] " + content + "-" + username;
    }
}
