package com.bwie.myshops.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.myshops.R;
import com.bwie.myshops.adapter.FavorableItemAdapter;
import com.bwie.myshops.bean.DiscountBean;
import com.bwie.myshops.custom.MyDecoration;
import com.bwie.myshops.utils.OkHttpUtil;
import com.bwie.myshops.utils.ToastUtil;
import com.bwie.myshops.utils.UrlUtils;
import com.bwie.myshops.view.DiscountActivity;
import com.google.gson.Gson;

import java.util.List;

/**
 * 优惠显示界面
 */

public class Favorable_pagerFragment extends Fragment implements FavorableItemAdapter.OnFavorChildClickListener{

    private RecyclerView favorPagerRecycler;
    private DiscountBean.DataBeanHeadline data1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favoravle_pager, container, false);
        favorPagerRecycler = (RecyclerView) view.findViewById(R.id.favor_pager_recycler);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //进行接值
        Bundle arguments = getArguments();
        int index = arguments.getInt("index");
        OkHttpUtil okHttpUtil = new OkHttpUtil();
        if (index == 0) {
            okHttpUtil.getJson(UrlUtils.URL_FAVORABLE_DISCOUNT, new HttpData());
        } else if (index == 1) {
            okHttpUtil.getJson(UrlUtils.URL_FAVORABLE_SIFT, new HttpData());
        }
    }

    //适配的方法
    private void getData(List<DiscountBean
            .DataBeanHeadline.ProductsBeanHeadline> products) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        favorPagerRecycler.setLayoutManager(layoutManager);
        //设置下划线
        MyDecoration decoration = new MyDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        favorPagerRecycler.addItemDecoration(decoration);
        //获取adapter
        FavorableItemAdapter itemAdapter = new FavorableItemAdapter(getActivity(),products);
        favorPagerRecycler.setAdapter(itemAdapter);
        itemAdapter.setOnFavorClickChildListener(this);
    }

    @Override
    public void onRightChildClick(RecyclerView recyclerView, View view, int position, int data) {
        //进行点击跳转
        //进行跳转传值,进入图片放大缩小界面
        Intent intent = new Intent(getActivity(),DiscountActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("index",data);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    class HttpData extends OkHttpUtil.HttpCallBack {

        @Override
        public void onError(String meg) {
            super.onError(meg);
            ToastUtil.showNetworkError(getActivity());
        }

        @Override
        public void onSusscess(String data) {
            //拿数据，进行解析
            Gson gson = new Gson();
            DiscountBean discountBean = gson.fromJson(data, DiscountBean.class);
            data1 = discountBean.getData();
            List<DiscountBean.DataBeanHeadline.ProductsBeanHeadline> products = discountBean.getData().getProducts();
            //进行适配
            getData(products);
        }
    }
}
