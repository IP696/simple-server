package com.pavel;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;

public class MainHandler implements HttpHandler {

    public void handle(HttpExchange exchange) throws IOException {

        InputStream resourceAsStream = getClass().getResourceAsStream("/templates/index.html");
        InputStreamReader isReader = new InputStreamReader(resourceAsStream);
        BufferedReader reader = new BufferedReader(isReader);

        StringBuilder sb = new StringBuilder();
        String str;
        while ((str = reader.readLine()) != null) {
            sb.append(str);
        }

        String response = sb.toString();

        exchange.sendResponseHeaders(200, sb.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
