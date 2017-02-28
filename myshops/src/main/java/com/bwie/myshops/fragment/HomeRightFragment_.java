package com.bwie.myshops.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.myshops.R;
import com.bwie.myshops.adapter.HomeRightAdapter;
import com.bwie.myshops.bean.Bean;
import com.bwie.myshops.custom.MyDecoration;
import com.bwie.myshops.view.DiscountActivity;

import java.util.List;

/**
 *  右边展示页
 */

public class HomeRightFragment_ extends Fragment implements HomeRightAdapter.OnRightChildClickListener{

    private RecyclerView rifht_recycler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        rifht_recycler = (RecyclerView) view.findViewById(R.id.right_recycler);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        int position = bundle.getInt("position");
        Bean.DataBeanHeadline shop =
                (Bean.DataBeanHeadline) bundle.getSerializable("shop");
        List<Bean.DataBeanHeadline.CategoriesBeanHeadline.ProductsBeanHeadline> products =
                shop.getCategories().get(position).getProducts();
        //进行添加数据
        setAdapter(products);
    }
    private void setAdapter(List<Bean.DataBeanHeadline.
            CategoriesBeanHeadline.ProductsBeanHeadline> products){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rifht_recycler.setLayoutManager(layoutManager);
        //垂直
        MyDecoration myDecoration = new MyDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        rifht_recycler.addItemDecoration(myDecoration);
        rifht_recycler.setItemAnimator(new DefaultItemAnimator());
        HomeRightAdapter homeRightAdapter = new HomeRightAdapter(getActivity(),products);
        rifht_recycler.setAdapter(homeRightAdapter);
        homeRightAdapter.setOnRightClickChildListener(this);
    }
    public static HomeRightFragment_ setNew(Bean.DataBeanHeadline str, int num) {
        HomeRightFragment_ fragment_num = new HomeRightFragment_();
        Bundle bundle = new Bundle();
        bundle.putInt("position",num);
        bundle.putSerializable("shop", str);
        fragment_num.setArguments(bundle);
        return fragment_num;
    }
    @Override
    public void onRightChildClick(RecyclerView recyclerView, View view, int position, int data) {
         //点击跳转
        Intent in = new Intent(getActivity(), DiscountActivity.class);
        in.putExtra("index",data);
        startActivity(in);
    }
}
