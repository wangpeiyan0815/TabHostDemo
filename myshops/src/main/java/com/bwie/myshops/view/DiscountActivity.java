package com.bwie.myshops.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bwie.myshops.R;
import com.bwie.myshops.bean.DetailsBean;
import com.bwie.myshops.utils.GlideImageLoader;
import com.bwie.myshops.utils.OkHttpUtil;
import com.bwie.myshops.utils.ToastUtil;
import com.bwie.myshops.utils.UrlUtils;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 详情页面
 */

public class DiscountActivity extends Activity implements OnBannerClickListener, View.OnClickListener {

    private int index;
    private Banner banner;
    private DetailsBean.DataBean.ProductBean product;
    private TextView discoun_nameTv;
    private TextView discoun_youhuiTv;
    private TextView discoun_yuanTv;
    private TextView discoun_desTv;
    private TextView discpunt_liao_xuanTv;
    private TextView discpunt_tang_xuanTv;
    private TextView discpunt_liaoTv;
    private TextView discpunt_wen_xuanTv;
    private TextView discpunt_wenTv;
    private TextView discpunt_tangTv;
    private TextView textView2;
    private RelativeLayout discount_rela_liao;
    private RelativeLayout discount_rela_wen;
    private RelativeLayout discount_rela_tang;
    private int num = 0;
    ArrayList<String> listLiao = new ArrayList<>();
    ArrayList<String> listWen = new ArrayList<>();
    ArrayList<String> listTang = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discount_activiyt);
        intiView();
        banner = (Banner) findViewById(R.id.banner);
        //进行接值
        Intent intent = getIntent();
        index = intent.getIntExtra("index", 0);
        OkHttpUtil okHttpUtil = new OkHttpUtil();
        okHttpUtil.getJson(UrlUtils.URL_DETAILS + index, new HttpData());
    }

    /**
     * 初始化id
     */
    private void intiView() {
        discoun_nameTv = (TextView) findViewById(R.id.Discoun_nameTv);
        discoun_youhuiTv = (TextView) findViewById(R.id.Discoun_youhuiTv);
        discoun_yuanTv = (TextView) findViewById(R.id.Discoun_yuanTv);
        discoun_desTv = (TextView) findViewById(R.id.Discoun_desTv);
        textView2 = (TextView) findViewById(R.id.textView2);

        discpunt_liaoTv = (TextView) findViewById(R.id.discpunt_liaoTv);
        discpunt_wenTv = (TextView) findViewById(R.id.discpunt_wenTv);
        discpunt_tangTv = (TextView) findViewById(R.id.discpunt_tangTv);


        discpunt_liao_xuanTv = (TextView) findViewById(R.id.discpunt_liao_xuanTv);
        discpunt_tang_xuanTv = (TextView) findViewById(R.id.discpunt_tang_xuanTv);
        discpunt_wen_xuanTv = (TextView) findViewById(R.id.discpunt_wen_xuanTv);
        discpunt_liao_xuanTv.setOnClickListener(this);
        discpunt_tang_xuanTv.setOnClickListener(this);
        discpunt_wen_xuanTv.setOnClickListener(this);
        discount_rela_liao = (RelativeLayout) findViewById(R.id.discount_rela_liao);
        discount_rela_wen = (RelativeLayout) findViewById(R.id.discount_rela_wen);
        discount_rela_tang = (RelativeLayout) findViewById(R.id.discount_rela_tang);
    }

    /**
     * 添加数据
     */
    private void getData(DetailsBean.DataBean.ProductBean product) {
        List<String> listStr = new ArrayList<>();
        listStr.add(product.getApp_long_image1());
        listStr.add(product.getApp_long_image2());
        listStr.add(product.getApp_long_image3());
        listStr.add(product.getApp_long_image4());
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(listStr);
        banner.setDelayTime(3000);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        //监听
        banner.setOnBannerClickListener(this);
    }

    @Override
    public void OnBannerClick(int position) {
        //进行跳转传值,进入图片放大缩小界面
        Intent intent = new Intent(DiscountActivity.this, DetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("index", position);
        bundle.putSerializable("photo", product);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 介绍方法
     */
    private void introduce(DetailsBean.DataBean.ProductBean product) {
        //进行添加赋值
        discoun_nameTv.setText(product.getName());
        discoun_youhuiTv.setText("￥" + product.getFeatured_price());
        discoun_yuanTv.setText("￥" + product.getPrice());
        discoun_desTv.setText(product.getShort_description());
    }

    /**
     * 加料方法
     */
    private void charge(DetailsBean.DataBean.ProductBean product) {
        List<DetailsBean.DataBean.ProductBean.ProductAttrsBean> productAttrs = product.getProductAttrs();
        if (productAttrs.size() == 3) {
            discount_rela_liao.setVisibility(View.VISIBLE);
            discount_rela_wen.setVisibility(View.VISIBLE);
            discount_rela_tang.setVisibility(View.VISIBLE);
            //清空以前
            listLiao.clear();
            listTang.clear();
            listWen.clear();
            int item_id = productAttrs.get(0).getItem_id();
            int item_id1 = productAttrs.get(1).getItem_id();
            int item_id2 = productAttrs.get(2).getItem_id();
            if (item_id == 1) {
                discpunt_liaoTv.setText(productAttrs.get(0).getProductAttrItem().getPrint_name());
                discpunt_liao_xuanTv.setText("标准");
                List<DetailsBean.DataBean.ProductBean.ProductAttrsBean.ProductAttrItemBean.
                        ProductAttrItemValuesBean> productAttrItemValues = productAttrs.get(0).
                        getProductAttrItem().getProductAttrItemValues();
                for (int i = 0; i < productAttrItemValues.size(); i++) {
                    listLiao.add(productAttrItemValues.get(i).getName() + "+￥" +
                            productAttrItemValues.get(i).getPrice() + "元");
                }
            }
            if (item_id1 == 2) {
                discpunt_wenTv.setText(productAttrs.get(1).getProductAttrItem().getPrint_name());
                discpunt_wen_xuanTv.setText("标准");
                List<DetailsBean.DataBean.ProductBean.ProductAttrsBean.ProductAttrItemBean.
                        ProductAttrItemValuesBean> productAttrItemValues = productAttrs.get(1).
                        getProductAttrItem().getProductAttrItemValues();
                for (int i = 0; i < productAttrItemValues.size(); i++) {
                    listWen.add(productAttrItemValues.get(i).getName() + "+￥" +
                            productAttrItemValues.get(i).getPrice() + "元");
                }
            }
            if (item_id2 == 3) {
                discpunt_tangTv.setText(productAttrs.get(2).getProductAttrItem().getPrint_name());
                discpunt_tang_xuanTv.setText("标准");
                List<DetailsBean.DataBean.ProductBean.ProductAttrsBean.ProductAttrItemBean.
                        ProductAttrItemValuesBean> productAttrItemValues = productAttrs.get(2).
                        getProductAttrItem().getProductAttrItemValues();
                for (int i = 0; i < productAttrItemValues.size(); i++) {
                    listTang.add(productAttrItemValues.get(i).getName() + "+￥" +
                            productAttrItemValues.get(i).getPrice() + "元");
                }
            }
        }
        if (productAttrs.size() == 2) {
         /*   discount_rela_wen.setVisibility(View.VISIBLE);
            discount_rela_tang.setVisibility(View.VISIBLE);
            discount_rela_liao.setVisibility(View.GONE);*/
            //清空以前
            listLiao.clear();
            listTang.clear();
            listWen.clear();
            int item_id = productAttrs.get(0).getItem_id();
            int item_id1 = productAttrs.get(1).getItem_id();
            if(productAttrs.get(0).getItem_id() == 1 && productAttrs.get(1).getItem_id() == 2){
                //加料
                discpunt_wenTv.setText(productAttrs.get(0).getProductAttrItem().getPrint_name());
                discpunt_wen_xuanTv.setText("标准");
                List<DetailsBean.DataBean.ProductBean.ProductAttrsBean.ProductAttrItemBean.
                        ProductAttrItemValuesBean> productAttrItemValues = productAttrs.get(0).
                        getProductAttrItem().getProductAttrItemValues();
                for (int i = 0; i < productAttrItemValues.size(); i++) {
                    listLiao.add(productAttrItemValues.get(i).getName() + "+￥" +
                            productAttrItemValues.get(i).getPrice() + "元");
                }
                //温度
                discpunt_tangTv.setText(productAttrs.get(1).getProductAttrItem().getPrint_name());
                discpunt_tang_xuanTv.setText("标准");
                List<DetailsBean.DataBean.ProductBean.ProductAttrsBean.ProductAttrItemBean.
                        ProductAttrItemValuesBean> productAttrItemValues1 = productAttrs.get(1).
                        getProductAttrItem().getProductAttrItemValues();
                for (int i = 0; i < productAttrItemValues1.size(); i++) {
                    listWen.add(productAttrItemValues1.get(i).getName() + "+￥" +
                            productAttrItemValues1.get(i).getPrice() + "元");
                }
                discount_rela_wen.setVisibility(View.VISIBLE);
                discount_rela_tang.setVisibility(View.GONE);
                discount_rela_liao.setVisibility(View.VISIBLE);
            }
            if(productAttrs.get(0).getItem_id() == 2 && productAttrs.get(1).getItem_id() == 3){
                //温度
                discpunt_wenTv.setText(productAttrs.get(0).getProductAttrItem().getPrint_name());
                discpunt_wen_xuanTv.setText("标准");
                List<DetailsBean.DataBean.ProductBean.ProductAttrsBean.ProductAttrItemBean.
                        ProductAttrItemValuesBean> productAttrItemValues = productAttrs.get(0).
                        getProductAttrItem().getProductAttrItemValues();
                for (int i = 0; i < productAttrItemValues.size(); i++) {
                    listWen.add(productAttrItemValues.get(i).getName() + "+￥" +
                            productAttrItemValues.get(i).getPrice() + "元");
                }
                //加糖
                discpunt_tangTv.setText(productAttrs.get(1).getProductAttrItem().getPrint_name());
                discpunt_tang_xuanTv.setText("标准");
                List<DetailsBean.DataBean.ProductBean.ProductAttrsBean.ProductAttrItemBean.
                        ProductAttrItemValuesBean> productAttrItemValues1 = productAttrs.get(1).
                        getProductAttrItem().getProductAttrItemValues();
                for (int i = 0; i < productAttrItemValues1.size(); i++) {
                    listTang.add(productAttrItemValues1.get(i).getName() + "+￥" +
                            productAttrItemValues1.get(i).getPrice() + "元");
                }
                discount_rela_wen.setVisibility(View.VISIBLE);
                discount_rela_tang.setVisibility(View.VISIBLE);
                discount_rela_liao.setVisibility(View.GONE);
            }
            if(productAttrs.get(0).getItem_id() == 1 && productAttrs.get(1).getItem_id() == 3){
                //加料
                discpunt_wenTv.setText(productAttrs.get(0).getProductAttrItem().getPrint_name());
                discpunt_wen_xuanTv.setText("标准");
                List<DetailsBean.DataBean.ProductBean.ProductAttrsBean.ProductAttrItemBean.
                        ProductAttrItemValuesBean> productAttrItemValues = productAttrs.get(0).
                        getProductAttrItem().getProductAttrItemValues();
                for (int i = 0; i < productAttrItemValues.size(); i++) {
                    listLiao.add(productAttrItemValues.get(i).getName() + "+￥" +
                            productAttrItemValues.get(i).getPrice() + "元");
                }
                //加糖
                discpunt_tangTv.setText(productAttrs.get(1).getProductAttrItem().getPrint_name());
                discpunt_tang_xuanTv.setText("标准");
                List<DetailsBean.DataBean.ProductBean.ProductAttrsBean.ProductAttrItemBean.
                        ProductAttrItemValuesBean> productAttrItemValues1 = productAttrs.get(1).
                        getProductAttrItem().getProductAttrItemValues();
                for (int i = 0; i < productAttrItemValues1.size(); i++) {
                    listTang.add(productAttrItemValues1.get(i).getName() + "+￥" +
                            productAttrItemValues1.get(i).getPrice() + "元");
                }
                discount_rela_wen.setVisibility(View.GONE);
                discount_rela_tang.setVisibility(View.VISIBLE);
                discount_rela_liao.setVisibility(View.VISIBLE);
            }
           /* if (item_id == 2) {
                //温度
                discpunt_wenTv.setText(productAttrs.get(0).getProductAttrItem().getPrint_name());
                discpunt_wen_xuanTv.setText("标准");
                List<DetailsBean.DataBean.ProductBean.ProductAttrsBean.ProductAttrItemBean.
                        ProductAttrItemValuesBean> productAttrItemValues = productAttrs.get(0).
                        getProductAttrItem().getProductAttrItemValues();
                for (int i = 0; i < productAttrItemValues.size(); i++) {
                    listWen.add(productAttrItemValues.get(i).getName() + "+￥" +
                            productAttrItemValues.get(i).getPrice() + "元");
                }

            }
            if (item_id1 == 3) {
                //糖量
                discpunt_tangTv.setText(productAttrs.get(1).getProductAttrItem().getPrint_name());
                discpunt_tang_xuanTv.setText("标准");
                List<DetailsBean.DataBean.ProductBean.ProductAttrsBean.ProductAttrItemBean.
                        ProductAttrItemValuesBean> productAttrItemValues = productAttrs.get(1).
                        getProductAttrItem().getProductAttrItemValues();
                for (int i = 0; i < productAttrItemValues.size(); i++) {
                    listTang.add(productAttrItemValues.get(i).getName() + "+￥" +
                            productAttrItemValues.get(i).getPrice() + "元");
                }
            }*/
        }
        if(productAttrs.size() == 0) {
            textView2.setVisibility(View.GONE);
            discount_rela_liao.setVisibility(View.GONE);
            discount_rela_wen.setVisibility(View.GONE);
            discount_rela_tang.setVisibility(View.GONE);
        }
    }

    //点击事件处理
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.discpunt_liao_xuanTv:
                num = 1;
                setPickerView(listLiao);
                break;
            case R.id.discpunt_tang_xuanTv:
                num = 3;
                setPickerView(listTang);
                break;
            case R.id.discpunt_wen_xuanTv:
                num = 2;
                setPickerView(listWen);
                break;
        }
    }

    /**
     * 轮播选择器
     */
    public void setPickerView(ArrayList<String> list1) {

        final OptionsPickerView<String> mOptionsPickerView = new OptionsPickerView<>(this);
        // 设置数据
        mOptionsPickerView.setPicker(list1);
        mOptionsPickerView.setCyclic(false);
        mOptionsPickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                if (num == 1) {
                    discpunt_liao_xuanTv.setText(listLiao.get(options1));
                } else if (num == 2) {
                    discpunt_wen_xuanTv.setText(listWen.get(options1));
                } else if (num == 3) {
                    discpunt_tang_xuanTv.setText(listTang.get(options1));
                }
                num = 0;
                mOptionsPickerView.dismiss();
            }
        });
        mOptionsPickerView.show();
    }

    /**
     * 网络请求
     */
    class HttpData extends OkHttpUtil.HttpCallBack {

        @Override
        public void onError(String meg) {
            super.onError(meg);
            ToastUtil.showNetworkError(DiscountActivity.this);
        }

        @Override
        public void onSusscess(String data) {
            //拿数据，进行解析
            Gson gson = new Gson();
            DetailsBean detailsBean = gson.fromJson(data, DetailsBean.class);
            product = detailsBean.getData().getProduct();
            getData(product);
            introduce(product);
            charge(product);
        }
    }
}
