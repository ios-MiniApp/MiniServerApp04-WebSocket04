package com.example.MiniServerApp04WebSocket04;
import com.example.MiniServerApp04WebSocket04.resources.WebSocketDemo;
import com.example.MiniServerApp04WebSocket04.resources.WebSocketDemoJSON;
import com.example.MiniServerApp04WebSocket04.resources.WebSocketDemoPathParam;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/*
 WebSocketに必要なConfigファイル。
 新しいWebSocketのファイルを作成したら、24〜27行目みたいに、@Beanでファイル名を書いてあげる必要がある。
 これを書かないと動かなない。
 （このファイルを作るのにめちゃめちゃつまずいた...）
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //registry.addHandler(unitWebSocketHandler, "/unit").setAllowedOrigins("*");
    }

    @Bean
    public WebSocketDemo webSocketDemo() {
        return new WebSocketDemo();
    }

    @Bean
    public WebSocketDemoPathParam webSocketDemoPathParam() {
        return new WebSocketDemoPathParam();
    }

    @Bean
    public WebSocketDemoJSON webSocketDemoJSON() {
        return new WebSocketDemoJSON();
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
