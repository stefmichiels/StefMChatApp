package controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Feedback;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;

@ServerEndpoint(value = "/feedback")
public class Feedbacks {
    private static final List<Feedback> feedbackList = new ArrayList<>();

    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session session) throws IOException, EncodeException {
        sessions.add(session);
        ObjectMapper mapper = new ObjectMapper();
        session.getBasicRemote().sendText(mapper.writeValueAsString(feedbackList));
    }

    @OnMessage
    public void onMessage(Session session, String feedback) throws IOException, EncodeException {
        ObjectMapper mapper = new ObjectMapper();
        Feedback feedbackObject = mapper.readValue(feedback, Feedback.class);
        sendAll(feedbackObject);
        System.out.println(mapper.toString() + " ah " + feedbackObject.getFeedback());
        feedbackList.add(feedbackObject);
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    private void sendAll(Feedback feedback) throws IOException, EncodeException {
        for (Session s : sessions) {
            List<Feedback> result = new ArrayList<>();
            result.add(feedback);
            ObjectMapper mapper = new ObjectMapper();
            s.getBasicRemote().sendText(mapper.writeValueAsString(result));
        }
    }
}