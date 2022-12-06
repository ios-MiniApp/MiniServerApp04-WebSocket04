package com.example.MiniServerApp04WebSocket04.resources;
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
 下の３種類のをサンプルで用意しており、このファイルは「1」のサンプル。
 ５秒に１回現在時刻をクライアントに送信するサンプルプログラム。
 １．http://localhost:8080/WebSocketDemo,
 ２．http://localhost:8080/WebSocketDemoJSON
 ３．http://localhost:8080/WebSocketDemoPathParam,
 */

@ServerEndpoint("/WebSocketDemo")
public class WebSocketDemo {
    private static final Queue<Session> sessions = new ConcurrentLinkedQueue<>();

    static {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleWithFixedDelay(WebSocketDemo::broadcast, 5, 5, TimeUnit.SECONDS);
    }

    @OnMessage
    public String onMessage(String message) {
        System.out.println("WebSocketで受信したメッセージ/ " + message);
        return "WebSocketでメッセージを正常に受信しました！";
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
