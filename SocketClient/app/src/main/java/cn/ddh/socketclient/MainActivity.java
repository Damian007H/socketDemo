package cn.ddh.socketclient;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import static cn.ddh.socketclient.HandlerUtils.sendUpDateHandlerMsg;

public class MainActivity extends AppCompatActivity {
    private EditText text;
    private Button bt, btConnection;
    private RecyclerView recy;


    private MyListAdapter adapter;
    public List list;
    private Lianjie lj;
    @SuppressLint("HandlerLeak")
    public Handler hand = new Handler() {//用于在子线程更新UI，收到信息和连接服务器成功时用到
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);


            //连接状态的改变
            if (msg.what == MessageHandlerDate.ConnectionSateChange.getHandlerWhat()) {
                int state = msg.getData().getInt("state");
                btConnection.setVisibility(state == ConnectionState.Success.getState() ? View.GONE : View.VISIBLE);
                //更新UI
            } else if (msg.what == MessageHandlerDate.UpdateUI.getHandlerWhat()) {
                HandlerDate handlerDate = (HandlerDate) msg.getData().getSerializable("handlerDate");


                //toast
                if (handlerDate.getFlag() == UpDateEnum.Toast.getType()) {
                    showToast(handlerDate.getMsg());
                    //收到消息
                } else if (handlerDate.getFlag() == UpDateEnum.ReciveMsg.getType()) {

                    overSend(false);
                }else if (handlerDate.getFlag() == UpDateEnum.SendMsg.getType()){

                    overSend(true);


                }else {
                    showToast("未识别的更新类型");


                }

                //未识别的通知
            } else {
                showToast("未识别的handler消息");

            }


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<MsgData>();
        text = findViewById(R.id.text);
        btConnection = findViewById(R.id.bt_reconnction);
        bt = findViewById(R.id.bt);
        recy = findViewById(R.id.recy);
        LinearLayoutManager lin = new LinearLayoutManager(this);
        recy.setLayoutManager(lin);
        adapter = new MyListAdapter(list);
        recy.setAdapter(adapter);
        lj = new Lianjie(this);

        btConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lj = new Lianjie(MainActivity.this);

            }
        });
        bt.setOnClickListener(new View.OnClickListener() {//给发送按钮设置监听事件
            @Override
            public void onClick(View v) {
                String s = text.getText().toString();
                if (s.equals("")) {
                    sendUpDateHandlerMsg(hand, UpDateEnum.Toast.getType(), "发送消息不能为空");

                } else {
                    list.add(new MsgData(s, false));
                    //new一个xx类，第一个参数的信息的内容，第二个参数是头像的图片id，第三个参数表示左右
                    //true为左边，false为右
                    lj.fa(s, new Listener() {
                        @Override
                        public void sendState(SendState sendState) {
                            int sendStateCode = sendState.getCode();

                            if (sendStateCode == SendState.Fail.getCode()) {
                                sendUpDateHandlerMsg(hand, UpDateEnum.Toast.getType(), sendState.getMsg());
                            } else {
                                sendUpDateHandlerMsg(hand,UpDateEnum.SendMsg.getType(),null);


                            }
                        }
                    });//发送消息
                }
            }
        });
    }


    public void overSend(boolean haveCleanText) {
        if (haveCleanText) {
            text.setText(null);
        }
        recy.scrollToPosition(list.size() - 1);//将屏幕移动到RecyclerView的底部

    }

    private void showToast(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();


    }

}
