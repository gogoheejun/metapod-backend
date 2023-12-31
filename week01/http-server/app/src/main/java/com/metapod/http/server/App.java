package com.metapod.http.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.CharBuffer;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        App app = new App();
        app.run();
    }

    private void run() throws IOException, InterruptedException {
        //1. Listen
        //서버소켓은 소켓과 똑같은거지만, 걍 accept이런 용도로 안쓰고 오직 리슨만 하는 용도로 쓰기 위해 구분한거임
        //new 하는 순간 listen알아서 됨.
        ServerSocket listener = new ServerSocket(8090, 0); //backlog – requested maximum length of the queue of incoming connections. 0이면 자동

        System.out.println("Listen!");
        //2. Accept
        while (true) {

            // curl localhost:8080을 하지 않으면 더이상 코드가 진행되지 않는데,
            // 이것을 Blocking이라고 함. -> 비동기, 멀티쓰레드가 필요한 이유임.
            Socket socket = listener.accept();
            System.out.println("Accept!" + socket.getLocalPort());


            Thread.sleep(10000);

            //3. 처리
            Reader reader = new InputStreamReader(socket.getInputStream());
            CharBuffer charBuffer = CharBuffer.allocate(1_000_000);
            reader.read(charBuffer);
            charBuffer.flip();

            System.out.println(charBuffer.toString());

            //4. Response
//            String message = """
//                    HTTP/1.1 200 OK
//
//                    Hello, world!
//                    """;

            String body = "Hello, world!";
            byte[] bytes = body.getBytes();
            String message = "" +
                    "HTTP/1.1 200 OK\n" +
                    "Content-Type: text/html; charset=UTF-8\n" +
                    "Content-Length: " + bytes.length + "\n" +
                    "\n" +
                    body;

            Writer writer = new OutputStreamWriter(socket.getOutputStream());
            writer.write(message);
            writer.flush();

            // 5. Close
            socket.close();
        }
//        listener.close();
    }
}
