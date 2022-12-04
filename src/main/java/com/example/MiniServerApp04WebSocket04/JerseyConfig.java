package com.example.MiniServerApp04WebSocket04;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        packages("com.example.MiniServerApp04WebSocket04");
    }

}
