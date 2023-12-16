package com.metepod.http.server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;

public class App {
    public static void main(String[] args) throws IOException {
        App app = new App();
        app.run();
    }

    private void run() throws IOException {
        InetSocketAddress address = new InetSocketAddress(8080);
        HttpServer httpserver = HttpServer.create(address, 0);

        httpserver.createContext("/", exchange -> {
            // 1. Request
            // method
            String method = exchange.getRequestMethod();
            System.out.println("Method: " + method);

            // uri
            URI uri = exchange.getRequestURI();
            System.out.println("URI: " + uri);

            // header
            Headers headers = exchange.getRequestHeaders();
            for (String key : headers.keySet()) {
                System.out.println(key + ": " + headers.get(key));
            }

            //body
            InputStream inputStream = exchange.getRequestBody();
            String body = new String(inputStream.readAllBytes());
            System.out.println(body);

            //2. Response

            String content = "Hello, world!\n";
            byte[] bytes = content.getBytes();
            // 헤더 먼저 지정
            exchange.sendResponseHeaders(200, bytes.length);

            //전송 - 이때 바디전송
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(bytes);
            outputStream.flush();
        });

        httpserver.start();
    }
}
