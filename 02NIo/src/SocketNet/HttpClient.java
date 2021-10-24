package SocketNet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpClient {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8801);
        while (true){
            //获取连接
            Socket socket = serverSocket.accept();
            service(socket);
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
