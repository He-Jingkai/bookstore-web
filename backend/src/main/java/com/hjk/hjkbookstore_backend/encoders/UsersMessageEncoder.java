package com.hjk.hjkbookstore_backend.encoders;

import com.hjk.hjkbookstore_backend.messages.UsersMessage;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.io.StringWriter;

public class UsersMessageEncoder implements Encoder.Text<UsersMessage> {
    @Override
    public void init(EndpointConfig ec) { }

    @Override
    public void destroy() { }

    @Override
    public String encode(UsersMessage usersMessage) throws EncodeException {
        StringWriter swriter = new StringWriter();
        try (JsonGenerator jsonGen = Json.createGenerator(swriter)) {
            jsonGen.writeStartObject()
                .write("type", "users")
                .writeStartArray("userList");
            for (String user : usersMessage.getUserList())
                jsonGen.write(user);
            jsonGen.writeEnd().writeEnd();
        }
        return swriter.toString();
    }
}
