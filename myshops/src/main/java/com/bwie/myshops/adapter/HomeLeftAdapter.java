package com.bwie.myshops.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwie.myshops.R;
import com.bwie.myshops.app.MyApp;

import java.util.List;

/**
 *   首页左侧适配
 */

public class HomeLeftAdapter extends RecyclerView.Adapter<HomeLeftAdapter.ViewHolder> implements View.OnClickListener{
    private Context context;
    private List<String> list;
    private OnChildClickListener listener;
    private RecyclerView recyclerView;

    public void setOnClickChildListener(OnChildClickListener listener) {
        this.listener = listener;
    }

    public HomeLeftAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_left_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (MyApp.bgNum == position) {
            holder.linearLayout.setBackgroundColor(Color.WHITE);
        }else{
            holder.linearLayout.setBackgroundColor(Color.parseColor("#F5F5F5"));
        }
        holder.textView.setText(list.get(position));
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }
    //解绑
    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View view) {
        //recyclerView != null && listener != null.if  快捷生成
        if (recyclerView != null && listener != null) {
            //快捷  recyclerView.getChildAdapterPosition(view).var
            int position = recyclerView.getChildAdapterPosition(view);
            listener.onChildClick(recyclerView,view,position,list.get(position));
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
         TextView textView;
         LinearLayout linearLayout;
         public ViewHolder(View itemView) {
             super(itemView);
             textView  = (TextView) itemView.findViewById(R.id.item_left_text);
             linearLayout = (LinearLayout) itemView.findViewById(R.id.home_left_liner);
         }
     }
    //定义一个接口
    public interface OnChildClickListener {
        void onChildClick(RecyclerView recyclerView, View view, int position, String data);
    }
}
