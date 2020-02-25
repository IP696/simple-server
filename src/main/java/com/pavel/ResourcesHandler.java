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

        if (resourceAsStream == null) return;

        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, resourceAsStream.available());
            byte[] buffer = new byte[1024];
            int len;
            while ((len = resourceAsStream.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
        }
    }
}
