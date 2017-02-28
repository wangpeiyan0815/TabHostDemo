package com.bwie.myshops.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.bwie.myshops.R;
import com.bwie.myshops.fragment.FavorableFragment;
import com.bwie.myshops.fragment.MoonshineFragment;
import com.bwie.myshops.fragment.MyFragment;
import com.bwie.myshops.fragment.ShopcartFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private int mImages[] = {
            R.drawable.tab_moonshine,
            R.drawable.tab_favorable,
            R.drawable.tab_shopcart,
            R.drawable.tab_my
    };
    // 标题
    private String mFragmentTags[] = {
            "商品",
            "优惠",
            "购物车",
            "我的"
    };
    private List<Fragment> listFrag = new ArrayList<>();
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title=(TextView)findViewById(R.id.title);
        //获取视图
        MyFragmentTabHost mTabhost = (MyFragmentTabHost) findViewById(R.id.tabhost);
        //绑定fragment
        mTabhost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        //去掉分割线
        mTabhost.getTabWidget().setDividerDrawable(null);
        mTabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Log.d(getLocalClassName(),tabId);
                title.setText(tabId);
            }
        });
        intiFragment();
        //初始化TAB
        Bundle bundle = null;
        for (int i = 0; i < mFragmentTags.length; i++) {
            // Tab按钮添加文字和图片
            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(mFragmentTags[i]).setIndicator(getImageView(i, mFragmentTags[i]));
            // 添加Fragment
            bundle = new Bundle();
            mTabhost.addTab(tabSpec, listFrag.get(i).getClass(), bundle);
            // 设置Tab按钮的背景
        }

    }
    //初始化fragment
    private void intiFragment(){
        MoonshineFragment moonshineFragment = new MoonshineFragment();
        FavorableFragment favorableFragment = new FavorableFragment();
        ShopcartFragment shopcartFragment  = new ShopcartFragment();
        MyFragment myFragment = new MyFragment();
        listFrag.add(moonshineFragment);
        listFrag.add(favorableFragment);
        listFrag.add(shopcartFragment);
        listFrag.add(myFragment);
    }
    // 获得图片资源 设置初始化指示器
    private View getImageView(int index, String str) {
        @SuppressLint("InflateParams")
        View view = getLayoutInflater().inflate(R.layout.view_tab_indicator, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.tab_iv_image);
        TextView label=(TextView)view.findViewById(R.id.tab_label);
        label.setText(str);
        imageView.setImageResource(mImages[index]);
        return view;
    }
}
