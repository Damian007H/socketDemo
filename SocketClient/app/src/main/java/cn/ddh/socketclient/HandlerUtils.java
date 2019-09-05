package cn.ddh.socketclient;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.Serializable;

/**
 * 文件描述：
 * 作者：黄继栋
 * 创建时间：2019/9/5
 */
public class HandlerUtils {


    /**
     * 通过handler发送   更新UI的   消息
     *
     * @param handler
     */
    public static void sendUpDateHandlerMsg(Handler handler, int flag, String msg) {
        Bundle bundle = new Bundle();
        Message message = new Message();

        HandlerDate handlerDate = new HandlerDate();
        handlerDate.setFlag(flag);
        handlerDate.setMsg(msg);


        bundle.putSerializable("handlerDate", handlerDate);
        message.setData(bundle);
        message.what = MessageHandlerDate.UpdateUI.getHandlerWhat();
        handler.sendMessage(message);
    }





    public static void sendConnectionHandlerMsg(Handler handler,ConnectionState connectionState) {
        Message message = new Message();
        message.what = MessageHandlerDate.ConnectionSateChange.getHandlerWhat();
        Bundle bundle=new Bundle();
        bundle.putInt("state",connectionState.getState());
        message.setData(bundle);
        handler.sendMessage(message);


    }


}
