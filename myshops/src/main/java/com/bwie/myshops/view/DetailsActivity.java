/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.bwie.myshops.view;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import com.bwie.myshops.R;
import com.bwie.myshops.bean.DetailsBean;
import com.bwie.myshops.utils.ImageLoaderUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;

/**
 *    Viewpagaer  图片放大缩小展示页面
 */
public class DetailsActivity extends AppCompatActivity {
    List<String> listsStr = new ArrayList<>();
    private DisplayImageOptions options;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        ViewPager mViewPager = (HackyViewPager) findViewById(R.id.view_pager);
        setContentView(mViewPager);
        options = ImageLoaderUtils.initOptions();
        //进行接值
        Bundle bundle = new Bundle();
        bundle = this.getIntent().getExtras();
        int index = bundle.getInt("index");
        DetailsBean.DataBean.ProductBean dui = (DetailsBean.DataBean.
                ProductBean) bundle.get("photo");
        listsStr.add(dui.getApp_long_image1());
        listsStr.add(dui.getApp_long_image2());
        listsStr.add(dui.getApp_long_image3());
        listsStr.add(dui.getApp_long_image4());
        mViewPager.setAdapter(new SamplePagerAdapter());
        mViewPager.setCurrentItem(2);
    }

    class SamplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return listsStr.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            //进行赋值
            Picasso.with(DetailsActivity.this).load(listsStr.get(position)).into(photoView);
            container.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }
}
