package SocketNet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpClient01 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8802);
        while (true){
            final Socket socket = serverSocket.accept();
            //每进来一个请求创建一个线程去进行处理
            new Thread(() -> {
                service(socket);
            }).start();
        }
    }

    private static void service(Socket socket){
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "hello socket and Nio";
            printWriter.println("Content-length:"+body.getBytes().length);
            //先输出头
            printWriter.println();
            //输出请求中的body，也就是数据
            printWriter.println(body);
            printWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
