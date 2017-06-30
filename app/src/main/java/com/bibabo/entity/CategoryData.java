package com.bibabo.entity;

import java.io.Serializable;

/**
 * 分类和链接地址
 * Created by zijian.cheng on 2017/6/30.
 */

public class CategoryData implements Serializable {
    private static final long serialVersionUID = -2864689024433827268L;

    private String title;
    private String itype;

    public CategoryData(){}

    public CategoryData(String t, String u){
        this.title = t;
        this.itype = u;
    }

    public String getTitle() {
        return title;
    }

    public String getItype() {
        return itype;
    }
}
