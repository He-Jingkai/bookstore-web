package com.hjk.hjkbookstore_backend.controller;

import com.hjk.hjkbookstore_backend.decoders.MessageDecoder;
import com.hjk.hjkbookstore_backend.encoders.ChatMessageEncoder;
import com.hjk.hjkbookstore_backend.encoders.JoinMessageEncoder;
import com.hjk.hjkbookstore_backend.encoders.LeaveMessageEncoder;
import com.hjk.hjkbookstore_backend.encoders.UsersMessageEncoder;
import com.hjk.hjkbookstore_backend.messages.*;
import org.springframework.stereotype.Component;

import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;


@Component
@ServerEndpoint(
        value = "/chatRoom",
        decoders = { MessageDecoder.class },
        encoders = { JoinMessageEncoder.class, ChatMessageEncoder.class,
                LeaveMessageEncoder.class, UsersMessageEncoder.class}
)
public class ChatEndPoint {
    private static final Logger logger = Logger.getLogger("BotEndpoint");
    private static final Queue<Session> mySession = new ConcurrentLinkedQueue<>();

    @OnOpen
    public void openConnection(Session session) {
        mySession.add(session);
        logger.log(Level.INFO, "Connection opened.");
    }

    @OnMessage
    public void message(final Session session, Message msg) {
        logger.log(Level.INFO, "Received: {0}", msg.toString());
        if (msg instanceof JoinMessage) {
            JoinMessage jmsg = (JoinMessage) msg;
            session.getUserProperties().put("name", jmsg.getUsername());
            session.getUserProperties().put("active", true);
            logger.log(Level.INFO, "Received: {0}", jmsg.toString());
            sendAll(session,new JoinMessage(jmsg.getUsername()));
            sendAll(session, new UsersMessage(this.getUserList()));
        } else if (msg instanceof ChatMessage) {
            final ChatMessage cmsg = (ChatMessage) msg;
            logger.log(Level.INFO, "Received: {0}", cmsg.toString());
            sendAll(session,cmsg);
        }
    }

    @OnClose
    public void closedConnection(Session session) {
        session.getUserProperties().put("active", false);
        if (session.getUserProperties().containsKey("name")) {
            String name = session.getUserProperties().get("name").toString();
            sendAll(session,new LeaveMessage(name));
            sendAll(session, new UsersMessage(this.getUserList()));
        }
        logger.log(Level.INFO, "Connection closed.");
    }

    @OnError
    public void error(Throwable t) {
        logger.log(Level.INFO, "Connection error ({0})", t.toString());
    }

    public synchronized void sendAll(Session session, Object msg) {
        try {
            for (Session s : mySession){
                if(Objects.equals(s.getId(), session.getId()))continue;
                if (s.isOpen()) {
                    s.getBasicRemote().sendObject(msg);
                    logger.log(Level.INFO, "Sent: {0}", msg.toString());
                }
            }
        } catch (IOException | EncodeException e) {
            logger.log(Level.INFO, e.toString());
        }
    }

    public List<String> getUserList() {
        List<String> users = new ArrayList<>();
        for (Session s : mySession){
            if (s.isOpen() && (boolean) s.getUserProperties().get("active"))
                users.add(s.getUserProperties().get("name").toString());
        }
        return users;
    }
}
