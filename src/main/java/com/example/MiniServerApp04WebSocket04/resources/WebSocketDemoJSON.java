package com.example.MiniServerApp04WebSocket04.resources;
import com.example.MiniServerApp04WebSocket04.entities.MessageJSON;
import com.example.MiniServerApp04WebSocket04.entities.MessageJSONEncoder;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
 下の３種類のをサンプルで用意しており、このファイルは「2」のサンプル。
 Jsonをクライアントに送信するサンプルプログラム。
 １．http://localhost:8080/WebSocketDemo,
 ２．http://localhost:8080/WebSocketDemoJSON
 ３．http://localhost:8080/WebSocketDemoPathParam,
 */

@ServerEndpoint(value = "/WebSocketDemoJSON", encoders = MessageJSONEncoder.class)
public class WebSocketDemoJSON {
    private static final Queue<Session> sessions = new ConcurrentLinkedQueue<>();

    static {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleWithFixedDelay(WebSocketDemoJSON::broadcast, 5, 5, TimeUnit.SECONDS);
    }

    @OnMessage
    public MessageJSON onMessage(String message, Session session) {
        System.out.println("WebSocketで受信したメッセージ/ " + message);
        MessageJSON messageJSON = new MessageJSON();
        messageJSON.setMessageJSON("これはJSONのメッセージ");
        return messageJSON;
    }

    @OnError
    public void onError(Throwable th) {
        System.out.println("WebSocketエラーが発生/ " + th.getMessage());
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("WebSocketセッション確立");
        sessions.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("WebSocketセッション終了");
        sessions.remove(session);
    }

    public static void broadcast() {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        sessions.forEach(session -> {
            session.getAsyncRemote().sendText("Broadcast : " + formatter.format(now));
        });
    }

}