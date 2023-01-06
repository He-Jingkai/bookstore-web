package com.hjk.hjkbookstore_backend.encoders;

import com.hjk.hjkbookstore_backend.messages.ChatMessage;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.io.StringWriter;


public class ChatMessageEncoder implements Encoder.Text<ChatMessage> {

    @Override
    public void init(EndpointConfig ec) { }

    @Override
    public void destroy() { }

    @Override
    public String encode(ChatMessage chatMessage) throws EncodeException {
        StringWriter swriter = new StringWriter();
        try (JsonGenerator jsonGen = Json.createGenerator(swriter)) {
            jsonGen.writeStartObject()
                .write("type", "text")
                .write("content", chatMessage.getContent())
                .write("username", chatMessage.getUsername())
            .writeEnd();
        }
        return swriter.toString();
    }
}
