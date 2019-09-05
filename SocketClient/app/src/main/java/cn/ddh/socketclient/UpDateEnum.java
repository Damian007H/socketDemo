package cn.ddh.socketclient;

/**
 * 文件描述：
 * 作者：黄继栋
 * 创建时间：2019/9/5
 */
public enum UpDateEnum {
    Toast(102),
    ReciveMsg(103),
    SendMsg(104);


    int type;


    UpDateEnum(int i) {
        this.type = i;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
