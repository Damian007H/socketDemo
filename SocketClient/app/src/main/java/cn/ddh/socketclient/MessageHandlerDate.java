package cn.ddh.socketclient;

/**
 * 文件描述：
 * 作者：黄继栋
 * 创建时间：2019/9/5
 */
public enum MessageHandlerDate {
    ConnectionSateChange(1),//当连接状态发生变化时的通知
    UpdateUI(2);//更新uI的操作

    private int handlerWhat;

    MessageHandlerDate(int i) {
        this.handlerWhat = i;
    }

    public int getHandlerWhat() {
        return handlerWhat;
    }

    public void setHandlerWhat(int handlerWhat) {
        this.handlerWhat = handlerWhat;
    }
}
