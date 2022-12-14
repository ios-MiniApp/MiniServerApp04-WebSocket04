package com.example.MiniServerApp04WebSocket04;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/*
 WebSocketだけを使うなら、このConfigファイルは必要ない。
 RestAPIとWebSocketを併用すると必要。
 このサンプルには無意味だけど一様書いている。
 */
@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        packages("com.example.MiniServerApp04WebSocket04");
    }

}
