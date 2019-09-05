package cn.ddh.socketclient;

import android.app.Service;

import java.io.Serializable;

/**
 * 文件描述：  更新UI的  消息对象
 * 作者：黄继栋
 * 创建时间：2019/9/5
 */
public class HandlerDate implements Serializable {

//    @UpDateEnum
    private int flag;//消息类型  更新哪类的消息

    private String msg;//消息内容


    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
