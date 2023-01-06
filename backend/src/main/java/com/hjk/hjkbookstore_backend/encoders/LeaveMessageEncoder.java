package com.hjk.hjkbookstore_backend.encoders;

import com.hjk.hjkbookstore_backend.messages.LeaveMessage;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.io.StringWriter;

public class LeaveMessageEncoder implements Encoder.Text<LeaveMessage>{
    @Override
    public void init(EndpointConfig ec) { }

    @Override
    public void destroy() { }

    @Override
    public String encode(LeaveMessage leaveMessage) throws EncodeException {
        StringWriter swriter = new StringWriter();
        try (JsonGenerator jsonGen = Json.createGenerator(swriter)) {
            jsonGen.writeStartObject()
                    .write("type", "leave")
                    .write("username", leaveMessage.getUsername())
                    .writeEnd();
        }
        return swriter.toString();
    }
}
