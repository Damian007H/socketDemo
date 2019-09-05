package cn.ddh.socketclient;

/**
 * 文件描述：
 * 作者：黄继栋
 * 创建时间：2019/9/5
 */
public enum SendState {

    Success(10000, "success"),
    Fail(10004, "fail");


    private int code;
    private String msg;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

     private SendState(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
