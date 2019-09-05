package cn.ddh.socketclient;

/**
 * 文件描述：
 * 作者：黄继栋
 * 创建时间：2019/9/5
 */
public enum ConnectionState {
    Success(10000), error(10004);

    private int state;//服务器连接状态

    ConnectionState(int i) {

        this.state = i;


    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
