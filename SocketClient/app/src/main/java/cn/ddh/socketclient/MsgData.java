package cn.ddh.socketclient;

/**
 * 文件描述：
 * 作者：黄继栋
 * 创建时间：2019/9/5
 */
public class MsgData {
    public MsgData() {
    }

    public boolean isZuo() {
        return zuo;
    }

    public void setZuo(boolean zuo) {
        this.zuo = zuo;
    }

    private String text;//信息内容
//    private int head;//头像的图片id

    public MsgData(String text, boolean zuo) {
        this.text = text;
//        this.head = head;
        this.zuo = zuo;
    }

    public boolean zuo = true;//控制信息显示在左边还有右边，默认左边
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

//    public int getHead() {
//        return head;
//    }
//
//    public void setHead(int head) {
//        this.head = head;
//    }
}
