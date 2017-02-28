package com.bwie.myshops.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.bwie.myshops.R;
import com.bwie.myshops.adapter.HomeLeftAdapter;
import com.bwie.myshops.app.MyApp;
import com.bwie.myshops.bean.Bean;
import com.bwie.myshops.utils.OkHttpUtil;
import com.bwie.myshops.utils.ToastUtil;
import com.bwie.myshops.utils.UrlUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


/**
 *   首页
 */

public class MoonshineFragment extends Fragment implements HomeLeftAdapter.OnChildClickListener{
    private RecyclerView homeRecycler;
    private FrameLayout homeFrame;
    private List<String> list = new ArrayList<>();
    private HomeLeftAdapter leftAdapter;
    private FragmentManager manager;
    private Bean bean;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.moonshine_fragment,container,false);
        homeRecycler = (RecyclerView) view.findViewById(R.id.home_recycler);
        homeFrame = (FrameLayout) view.findViewById(R.id.home_frame);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        OkHttpUtil okHttpUtil = new OkHttpUtil();
        okHttpUtil.getJson(UrlUtils.URL_HOME_PRODUCT,new HttpData());
    }
    //回调接口
    @Override
    public void onChildClick(RecyclerView recyclerView, View view, int position, String data) {
        MyApp.bgNum = position;
        leftAdapter.notifyDataSetChanged();
        //进行创建fragment,进行传值
        HomeRightFragment_ fragment = HomeRightFragment_.setNew(bean.getData(),position);
        manager.beginTransaction().replace(R.id.home_frame,fragment).commit();
    }
    //添加数据源
    private void getData(List<Bean.DataBeanHeadline.CategoriesBeanHeadline> categories){
        for (int i = 0; i < categories.size(); i++) {
            list.add(categories.get(i).getName());
        }
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        homeRecycler.setLayoutManager(layoutManager);
        leftAdapter = new HomeLeftAdapter(getActivity(),list);
        homeRecycler.setAdapter(leftAdapter);
        leftAdapter.setOnClickChildListener(this);
        manager = getActivity().getSupportFragmentManager();
        HomeRightFragment_ fragment = HomeRightFragment_.setNew(bean.getData(),0);
        manager.beginTransaction().add(R.id.home_frame,fragment).commit();
    }
    class HttpData extends OkHttpUtil.HttpCallBack{

        @Override
        public void onError(String meg) {
            super.onError(meg);
            ToastUtil.showNetworkError(getActivity());
        }
        @Override
        public void onSusscess(String data) {
            //拿数据，进行解析
            Gson gson = new Gson();
            bean = gson.fromJson(data, Bean.class);
            List<Bean.DataBeanHeadline.CategoriesBeanHeadline> categories = bean.getData().getCategories();
            getData(categories);
        }
    }
}
