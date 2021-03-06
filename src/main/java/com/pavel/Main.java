package com.pavel;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/index", new MainHandler());
        server.createContext("/static", new ResourcesHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }
}
