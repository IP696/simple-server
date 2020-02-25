package com.pavel;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ResourcesHandler implements HttpHandler {

    public void handle(HttpExchange exchange) throws IOException {

        String name = exchange.getRequestURI().toString();
        name = name.replaceFirst("/+", "");
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(name);

        byte[] targetArray = new byte[0];

        if (resourceAsStream != null) {
            targetArray = new byte[resourceAsStream.available()];
            resourceAsStream.read(targetArray);
        }

        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, targetArray.length);
            os.write(targetArray);
        }
    }
}
