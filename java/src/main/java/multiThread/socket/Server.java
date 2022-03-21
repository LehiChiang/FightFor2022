package multiThread.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            //1、创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
            ServerSocket serverSocket = new ServerSocket(10086);
            //2、调用accept()方法开始监听，等待客户端的连接
            Socket socket = serverSocket.accept();
            //3、获取输入流，并读取客户端信息
            BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String info;
            while (!(info = bf.readLine()).equals("bye")) {
                System.out.println(info);
            }
            socket.shutdownInput();
//            //4、获取输出流，响应客户端的请求
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//            bw.write("欢迎连接服务器！");
//            bw.flush();
            //5、关闭资源
//            bw.close();
            bf.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
