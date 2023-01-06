package com.hjk.hjkbookstore_backend.decoders;

import com.hjk.hjkbookstore_backend.messages.ChatMessage;
import com.hjk.hjkbookstore_backend.messages.JoinMessage;
import com.hjk.hjkbookstore_backend.messages.LeaveMessage;
import com.hjk.hjkbookstore_backend.messages.Message;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.StringReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MessageDecoder implements Decoder.Text<Message> {
    private Map<String,String> messageMap;

    @Override
    public void init(EndpointConfig ec) { }

    @Override
    public void destroy() { }

    @Override
    public Message decode(String string) throws DecodeException {
        Message msg = null;
        if (willDecode(string)) {
            switch (messageMap.get("type")) {
                case "join":
                    msg = new JoinMessage(messageMap.get("username"));
                    break;
                case "leave":
                    msg = new LeaveMessage(messageMap.get("username"));
                    break;
                case "text":
                    msg = new ChatMessage(messageMap.get("content"),
                                          messageMap.get("username"));
            }
        } else {
            throw new DecodeException(string, "[Message] Can't decode.");
        }
        return msg;
    }

    @Override
    public boolean willDecode(String string) {
        boolean decodes = false;
        messageMap = new HashMap<>();
        JsonParser parser = Json.createParser(new StringReader(string));
        while (parser.hasNext()) {
            if (parser.next() == JsonParser.Event.KEY_NAME) {
                String key = parser.getString();
                parser.next();
                String value = parser.getString();
                messageMap.put(key, value);
            }
        }
        Set<String> keys = messageMap.keySet();
        if (keys.contains("type")) {
            switch (messageMap.get("type")) {
                case "join":
                case "leave":
                    if (keys.contains("username"))
                        decodes = true;
                    break;
                case "text":
                    String[] chatMsgKeys = {"content", "username"};
                    if (keys.containsAll(Arrays.asList(chatMsgKeys)))
                        decodes = true;
                    break;
            }
        }
        return decodes;
    }
}
