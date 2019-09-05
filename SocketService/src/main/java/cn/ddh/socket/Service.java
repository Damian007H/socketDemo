package cn.ddh.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 文件描述：
 * 作者：黄继栋
 * 创建时间：2019/9/5
 */
public class Service {

    public static ArrayList<Socket> list = new ArrayList<Socket>();
//    public static Executor ex = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        ServerSocket soo = null;
        Socket so = null;
        try {
            soo = new ServerSocket(61666);
            System.out.println("创建服务器成功...");
            while (true) {
                so = soo.accept();
                list.add(so);

                new Thread(new Jie(so)).start();
            }
        } catch (IOException e) {
            System.out.println("创建服务器失败...");
            try {
                soo.close();
                so.close();
            } catch (IOException e1) {
            }

        }
    }




}
