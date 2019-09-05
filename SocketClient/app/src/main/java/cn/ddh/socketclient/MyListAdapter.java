package cn.ddh.socketclient;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * 文件描述：
 * 作者：黄继栋
 * 创建时间：2019/9/5
 */
public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{
    private List<MsgData> list;



    public MyListAdapter(List l) {
        list = l;
    }

    @Override
    public MyListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_msg, parent, false);
        ViewHolder h = new ViewHolder(v);
        Log.d("MainActivity", "onCreate");
        return h;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {//滑动RecyclerView出发的事件
        MsgData msgData = list.get(position);
        holder.rlLeftContent.setVisibility(View.GONE);
        holder.rlRightContent.setVisibility(View.GONE);

        if (msgData.zuo) {//判断该信息该信息是显示在左边还是右边，如果要在左边显示则把右边的部分隐藏
            holder.rlLeftContent.setVisibility(View.VISIBLE);
            holder.rlRightContent.setVisibility(View.GONE);//把右边的隐藏
            holder.ivLeftHead.setImageResource(R.drawable.left_head);
            holder.tvLeftText.setText(msgData.getText());
        }else{
            holder.rlRightContent.setVisibility(View.VISIBLE);
            holder.rlLeftContent.setVisibility(View.GONE);//把左边的隐藏
            holder.ivRightHead.setImageResource(R.drawable.right_head);
            holder.tvRightText.setText(msgData.getText());
        }
        Log.d("MainActivity", "onBind");
    }

    @Override
    public int getItemCount() {//这里要重写一下 不然不会显示任何信息
        Log.d("asdasd", "" + list.size());
        return list.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivLeftHead, ivRightHead;
        TextView tvLeftText, tvRightText;
        RelativeLayout rlLeftContent, rlRightContent;

        public ViewHolder(View itemView) {
            super(itemView);
            ivLeftHead = itemView.findViewById(R.id.iv_left_head);
            ivRightHead = itemView.findViewById(R.id.iv_right_head);
            tvLeftText = itemView.findViewById(R.id.tv_left_text);
            tvRightText = itemView.findViewById(R.id.tv_right_text);
            rlLeftContent = itemView.findViewById(R.id.rl_left_content);
            rlRightContent = itemView.findViewById(R.id.rl_right_content);
        }
    }
}


