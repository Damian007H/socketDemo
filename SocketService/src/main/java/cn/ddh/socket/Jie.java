package cn.ddh.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 文件描述：
 * 作者：黄继栋
 * 创建时间：2019/9/5
 */
public class Jie implements Runnable{

    private Socket so;

    Jie(Socket so) {
        this.so = so;
        String lianjie;
        lianjie = "已连接 用户IP：" + so.getInetAddress().getHostAddress() + "当前连接数：" + Service.list.size();
        System.out.println(lianjie);
    }


    public void run() {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(so.getInputStream(), "UTF-8"));
            String s;
            while ((s = in.readLine()) != null) {
                new Thread(new Fa(s, so)).start();// 收到消息之后 就把收到的消息发送给除了发送者之外在所有人
            }
            Service.list.remove(so);
            String tuichu;
            tuichu = "已退出 用户IP：" + so.getInetAddress().getHostAddress() + "当前连接数：" + Service.list.size();
            System.out.println(tuichu);
            in.close();
            so.close();
        } catch (IOException e) {
            Service.list.remove(so);
            String tuichu;
            tuichu = "已退出 用户IP：" + so.getInetAddress().getHostAddress() + "当前连接数：" + Service.list.size();
            System.out.println(tuichu);
            try {
                in.close();
                so.close();
            } catch (IOException e1) {
            }
        }
    }
}
