package cn.ddh.socket;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * 文件描述：
 * 作者：黄继栋
 * 创建时间：2019/9/5
 */
class Fa implements Runnable{


    private String s;
    private Socket ss;

    Fa(String s, Socket so) {
        this.s = s;
        ss = so;
    }

    public void run() {
        Socket so;

        try {
            for (int i = 0; i < Service.list.size(); i++) {
                so = Service.list.get(i);
                if (so == ss)
                    continue;// 收到消息之后 就把收到的消息发送给除了发送者之外在所有人。ss为发送者
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(so.getOutputStream(), "UTF-8"));
                out.write(s + "\r\n");
                out.flush();
            }
        } catch (IOException e) {
            System.out.println("群发异常");

        }

    }

}
