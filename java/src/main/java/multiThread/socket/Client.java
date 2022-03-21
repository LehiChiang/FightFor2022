package multiThread.socket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            //1、创建客户端Socket，指定服务器地址和端口
            Socket socket = new Socket("127.0.0.1", 10086);
            //2、获取输出流，向服务器端发送信息
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            Scanner scanner = new Scanner(System.in);
            String info;
            while (!(info = scanner.nextLine()).equals("bye")) {
                bw.write(info);
                bw.flush();
            }

//            //3、获取输入流，并读取服务器端的响应信息
//            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            String info = null;
//            while ((info = br.readLine()) != null) {
//                System.out.println(info);
//            }
            //4、关闭资源
//            br.close();
            bw.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
