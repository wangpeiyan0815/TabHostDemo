package com.bwie.myshops.bean;

import java.io.Serializable;
import java.util.List;

/**
 *  打折商品分装类
 */

public class DiscountBean implements Serializable{

    private boolean status;
    private int code;
    private String message;
    private DataBeanHeadline data;
    private ShareBeanHeadline share;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBeanHeadline getData() {
        return data;
    }

    public void setData(DataBeanHeadline data) {
        this.data = data;
    }

    public ShareBeanHeadline getShare() {
        return share;
    }

    public void setShare(ShareBeanHeadline share) {
        this.share = share;
    }

    public static class DataBeanHeadline implements Serializable{
        private List<ProductsBeanHeadline> products;

        public List<ProductsBeanHeadline> getProducts() {
            return products;
        }

        public void setProducts(List<ProductsBeanHeadline> products) {
            this.products = products;
        }

        public static class ProductsBeanHeadline implements Serializable{

            private int id;
            private int category_id;
            private String image;
            private Object featured_image;
            private String image_small;
            private String name;
            private int sort;
            private int created_at;
            private int created_by;
            private int updated_at;
            private int updated_by;
            private String price;
            private String featured_price;
            private Object featured_position;
            private int featured_position_sort;
            private Object app_featured_home;
            private int app_featured_home_sort;
            private Object app_featured_image;
            private String short_description;
            private String meta_keywords;
            private String meta_description;
            private Object is_audit;
            private Object remarks;
            private Object featured;
            private String description;
            private String image_medium;
            private String image_large;
            private Object app_featured_topic;
            private int app_featured_topic_sort;
            private String app_long_image1;
            private String app_long_image2;
            private String app_long_image3;
            private int type_id;
            private String app_long_image4;
            private String app_long_image5;
            private int status;
            private Object specs_json;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public Object getFeatured_image() {
                return featured_image;
            }

            public void setFeatured_image(Object featured_image) {
                this.featured_image = featured_image;
            }

            public String getImage_small() {
                return image_small;
            }

            public void setImage_small(String image_small) {
                this.image_small = image_small;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getCreated_at() {
                return created_at;
            }

            public void setCreated_at(int created_at) {
                this.created_at = created_at;
            }

            public int getCreated_by() {
                return created_by;
            }

            public void setCreated_by(int created_by) {
                this.created_by = created_by;
            }

            public int getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(int updated_at) {
                this.updated_at = updated_at;
            }

            public int getUpdated_by() {
                return updated_by;
            }

            public void setUpdated_by(int updated_by) {
                this.updated_by = updated_by;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getFeatured_price() {
                return featured_price;
            }

            public void setFeatured_price(String featured_price) {
                this.featured_price = featured_price;
            }

            public Object getFeatured_position() {
                return featured_position;
            }

            public void setFeatured_position(Object featured_position) {
                this.featured_position = featured_position;
            }

            public int getFeatured_position_sort() {
                return featured_position_sort;
            }

            public void setFeatured_position_sort(int featured_position_sort) {
                this.featured_position_sort = featured_position_sort;
            }

            public Object getApp_featured_home() {
                return app_featured_home;
            }

            public void setApp_featured_home(Object app_featured_home) {
                this.app_featured_home = app_featured_home;
            }

            public int getApp_featured_home_sort() {
                return app_featured_home_sort;
            }

            public void setApp_featured_home_sort(int app_featured_home_sort) {
                this.app_featured_home_sort = app_featured_home_sort;
            }

            public Object getApp_featured_image() {
                return app_featured_image;
            }

            public void setApp_featured_image(Object app_featured_image) {
                this.app_featured_image = app_featured_image;
            }

            public String getShort_description() {
                return short_description;
            }

            public void setShort_description(String short_description) {
                this.short_description = short_description;
            }

            public String getMeta_keywords() {
                return meta_keywords;
            }

            public void setMeta_keywords(String meta_keywords) {
                this.meta_keywords = meta_keywords;
            }

            public String getMeta_description() {
                return meta_description;
            }

            public void setMeta_description(String meta_description) {
                this.meta_description = meta_description;
            }

            public Object getIs_audit() {
                return is_audit;
            }

            public void setIs_audit(Object is_audit) {
                this.is_audit = is_audit;
            }

            public Object getRemarks() {
                return remarks;
            }

            public void setRemarks(Object remarks) {
                this.remarks = remarks;
            }

            public Object getFeatured() {
                return featured;
            }

            public void setFeatured(Object featured) {
                this.featured = featured;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getImage_medium() {
                return image_medium;
            }

            public void setImage_medium(String image_medium) {
                this.image_medium = image_medium;
            }

            public String getImage_large() {
                return image_large;
            }

            public void setImage_large(String image_large) {
                this.image_large = image_large;
            }

            public Object getApp_featured_topic() {
                return app_featured_topic;
            }

            public void setApp_featured_topic(Object app_featured_topic) {
                this.app_featured_topic = app_featured_topic;
            }

            public int getApp_featured_topic_sort() {
                return app_featured_topic_sort;
            }

            public void setApp_featured_topic_sort(int app_featured_topic_sort) {
                this.app_featured_topic_sort = app_featured_topic_sort;
            }

            public String getApp_long_image1() {
                return app_long_image1;
            }

            public void setApp_long_image1(String app_long_image1) {
                this.app_long_image1 = app_long_image1;
            }

            public String getApp_long_image2() {
                return app_long_image2;
            }

            public void setApp_long_image2(String app_long_image2) {
                this.app_long_image2 = app_long_image2;
            }

            public String getApp_long_image3() {
                return app_long_image3;
            }

            public void setApp_long_image3(String app_long_image3) {
                this.app_long_image3 = app_long_image3;
            }

            public int getType_id() {
                return type_id;
            }

            public void setType_id(int type_id) {
                this.type_id = type_id;
            }

            public String getApp_long_image4() {
                return app_long_image4;
            }

            public void setApp_long_image4(String app_long_image4) {
                this.app_long_image4 = app_long_image4;
            }

            public String getApp_long_image5() {
                return app_long_image5;
            }

            public void setApp_long_image5(String app_long_image5) {
                this.app_long_image5 = app_long_image5;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public Object getSpecs_json() {
                return specs_json;
            }

            public void setSpecs_json(Object specs_json) {
                this.specs_json = specs_json;
            }
        }
    }

    public static class ShareBeanHeadline implements Serializable{
    }
}
