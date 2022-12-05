package com.example.MiniServerApp04WebSocket04;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@ServerEndpoint("/WebSocketDemoPathParam/{parameter}")
public class WebSocketDemoPathParam {
    private static final Queue<Session> sessions = new ConcurrentLinkedQueue<>();

    static {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleWithFixedDelay(WebSocketDemo::broadcast, 5, 5, TimeUnit.SECONDS);
    }

    @OnMessage
    public String onMessage(String message, @PathParam("parameter") String parameter) {
        System.out.println("WebSocketで受信したメッセージ/ " + message);
        System.out.println("受け取ったパスパラメータ " + parameter);
        return "WebSocketでメッセージを正常に受信しました！";
    }

    @OnError
    public void onError(Throwable th, @PathParam("parameter") String parameter) {
        System.out.println("WebSocketエラーが発生/ " + th.getMessage());
        System.out.println("受け取ったパスパラメータ " + parameter);
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("parameter") String parameter) {
        System.out.println("WebSocketセッション確立");
        System.out.println("受け取ったパスパラメータ " + parameter);
        sessions.add(session);
    }

    @OnClose
    public void onClose(Session session, @PathParam("parameter") String parameter) {
        System.out.println("WebSocketセッション終了");
        System.out.println("受け取ったパスパラメータ " + parameter);
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