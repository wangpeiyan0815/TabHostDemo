package com.bwie.myshops.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.myshops.R;
import com.bwie.myshops.adapter.Favorable_PagerAdapter;


/**
 *  优惠界面
 */

public class FavorableFragment extends Fragment {

    private TabLayout favorable_tabs;
    private ViewPager favorable_viewpager;
    private String[] str = {"天天优惠", "为你精选", "亲的最爱"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.favorable_fragment,container,false);
        favorable_tabs = (TabLayout) view.findViewById(R.id.favorable_tabs);
        favorable_viewpager = (ViewPager) view.findViewById(R.id.favorable_viewpager);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setPager();
    }
    //适配Viewpager
    private void setPager(){
        FragmentManager manager = getActivity().getSupportFragmentManager();
        //进行适配
        Favorable_PagerAdapter pagerAdapter = new Favorable_PagerAdapter(manager,getActivity(),str);
        favorable_viewpager.setAdapter(pagerAdapter);
        //与viewpager关联起来
        favorable_tabs.setupWithViewPager(favorable_viewpager);
    }

}
