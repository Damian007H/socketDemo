package cn.ddh.socketclient;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import static cn.ddh.socketclient.HandlerUtils.sendUpDateHandlerMsg;

/**
 * 文件描述：
 * 作者：黄继栋
 * 创建时间：2019/9/5
 */
public class Lianjie implements Runnable {

    private MainActivity main;
    private Socket so = null;

    public Lianjie(MainActivity m) {
        main = m;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            so = new Socket("192.168.3.60", 61666);//第一个参数是你服务器的ip，第二个参数是端口号
            //服务器连接成功的话则发一个Message给UI线程 跳到MainActivity.java的第24行

            HandlerUtils.sendConnectionHandlerMsg(main.hand, ConnectionState.Success);

        } catch (IOException e) {
            HandlerUtils.sendConnectionHandlerMsg(main.hand, ConnectionState.error);

            e.printStackTrace();
            return;
        }

        //连接服务器成功之后开始接受消息
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(so.getInputStream(), "UTF-8"));
            while (true) {
                String s = in.readLine();
                if (s == null || s.equals("")) break;
                //收到消息之后便new一个xx类，第一个参数是信息内容，第二个参数是头像ID
                //第三个参数显示在左边还是右边，没有第三个参数的话默认显示在左边
                //我事先准备了两张头像
                //最后将他添加到list里
                main.list.add(new MsgData(s, true));
                //收到消息后就要更新RecyclerView将他们显示出来，跳到MainActivity.java的第24行
                sendUpDateHandlerMsg(main.hand, UpDateEnum.ReciveMsg.getType(), null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fa(final String s, final Listener listener) {//发送消息
        new Thread(new Runnable() {
            @Override
            public void run() {//我发现有的手机在UI线程发消息会崩溃，而有的不会
                // 这里就在一个新的线程发消息
                BufferedWriter out = null;
                try {

                    if (so == null) {//连接未建立
                        HandlerUtils.sendConnectionHandlerMsg(main.hand, ConnectionState.error);

                        SendState failSend = SendState.Fail;
                        failSend.setMsg("已断开连接，请重新连接");

                        listener.sendState(failSend);

                        return;
                    }
                    out = new BufferedWriter(new OutputStreamWriter(so.getOutputStream(), "UTF-8"));
                    out.write(s + "\r\n");
                    out.flush();
                    listener.sendState(SendState.Success);
                } catch (IOException e) {

                    e.printStackTrace();

                    SendState failSend = SendState.Fail;
                    failSend.setMsg(e.getMessage());

                    listener.sendState(failSend);


                }
            }
        }).start();
    }


}
