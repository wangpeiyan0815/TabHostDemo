package com.bwie.myshops.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.bwie.myshops.fragment.Favorable_pagerFragment;

/**
 *  优惠fragment 适配期
 */

public class Favorable_PagerAdapter extends FragmentStatePagerAdapter {
    private Context context;
    private String[] str;

    public Favorable_PagerAdapter(FragmentManager fm, Context context, String[] str) {
        super(fm);
        this.context = context;
        this.str = str;
    }

    @Override
    public Fragment getItem(int position) {
        Favorable_pagerFragment fragment = new Favorable_pagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("index",position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return str.length;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return str[position];
    }
}
