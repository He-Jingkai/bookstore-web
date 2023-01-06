package com.hjk.hjkbookstore_backend.encoders;

import com.hjk.hjkbookstore_backend.messages.JoinMessage;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.io.StringWriter;


public class JoinMessageEncoder implements Encoder.Text<JoinMessage> {
    @Override
    public void init(EndpointConfig ec) { }

    @Override
    public void destroy() { }

    @Override
    public String encode(JoinMessage joinMessage) throws EncodeException {
        StringWriter swriter = new StringWriter();
        try (JsonGenerator jsonGen = Json.createGenerator(swriter)) {
            jsonGen.writeStartObject()
                .write("type", "join")
                .write("username", joinMessage.getUsername())
            .writeEnd();
        }
        return swriter.toString();
    }
}
