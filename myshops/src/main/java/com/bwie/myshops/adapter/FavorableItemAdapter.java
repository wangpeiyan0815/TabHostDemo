package com.bwie.myshops.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.myshops.R;
import com.bwie.myshops.bean.DiscountBean;
import com.bwie.myshops.utils.ImageLoaderUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 首页左侧适配
 */

public class FavorableItemAdapter extends RecyclerView.Adapter<FavorableItemAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private List<DiscountBean
            .DataBeanHeadline.ProductsBeanHeadline> list;
    private OnFavorChildClickListener listener;
    private RecyclerView recyclerView;
    private DisplayImageOptions options;

    public void setOnFavorClickChildListener(OnFavorChildClickListener listener) {
        this.listener = listener;
        options = ImageLoaderUtils.initOptions();
    }

    public FavorableItemAdapter(Context context, List<DiscountBean
            .DataBeanHeadline.ProductsBeanHeadline> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.favorable_item_pager, parent, false);
        ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //进行赋值
        ImageLoader.getInstance().displayImage(list.get(position).getImage_small()
                , holder.image, options);
        holder.nameTv.setText(list.get(position).getName());
        if (TextUtils.isEmpty(list.get(position).getFeatured_price())) {
            //当没有优惠价时
            holder.JianTv.setVisibility(View.INVISIBLE);
            holder.yuanTv.setText(list.get(position).getPrice());
            holder.desTv.setText(list.get(position).getShort_description()+"更多精彩.....");
            holder.chaTv.setText("行动起来！");
        }else{
            holder.yuanTv.setText("￥" + list.get(position).getFeatured_price());
            holder.chaTv.setText("立减￥"+pricedifference(list.get(position).getPrice(),list.get(position).getFeatured_price())+"元");
            if(TextUtils.isEmpty(list.get(position).getShort_description())){
                holder.desTv.setText(list.get(position).getShort_description()+"更多精彩.....");
            }else{
                holder.desTv.setText(list.get(position).getShort_description());
            }
            holder.JianTv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            holder.JianTv.setText("￥" + list.get(position).getPrice());
        }
    }

    //计算差价的方法
    private double pricedifference(String price, String featured_price) {

        if (!TextUtils.isEmpty(featured_price)) {
            double i = Double.parseDouble(price);
            double i2 = Double.parseDouble(featured_price);
            return i - i2;
        }
        return 0;
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
        if (recyclerView != null && listener != null) {
            int position = recyclerView.getChildAdapterPosition(view);
            listener.onRightChildClick(recyclerView, view, position, list.get(position).getId());
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTv, yuanTv, JianTv, chaTv,desTv;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.favor_item__nameTv);
            yuanTv = (TextView) itemView.findViewById(R.id.favor_item__yuanpriceTv);
            JianTv = (TextView) itemView.findViewById(R.id.favor_item__jianpriceTv);
            chaTv = (TextView) itemView.findViewById(R.id.favor_item__chapriceTv);
            desTv = (TextView) itemView.findViewById(R.id.favor_item_desTv);
            image = (ImageView) itemView.findViewById(R.id.favor_item_imageView);
        }
    }

    //定义一个接口
    public interface OnFavorChildClickListener {
        void onRightChildClick(RecyclerView recyclerView, View view, int position, int data);
    }
}
