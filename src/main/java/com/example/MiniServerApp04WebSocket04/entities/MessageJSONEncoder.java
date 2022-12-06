package com.example.MiniServerApp04WebSocket04.entities;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageJSONEncoder implements Encoder.Text<MessageJSON>{

    @Override
    public void init(EndpointConfig config) {
        System.out.println("init");
    }

    @Override
    public String encode(MessageJSON messageJSON) throws EncodeException {
        System.out.println("encode");
        return messageJSON.message;
    }

    @Override
    public void destroy() {
        System.out.println("destroy");

    }

}
