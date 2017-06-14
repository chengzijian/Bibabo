package com.bibabo.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class DataListResult<T> extends BaseResult {

    private static final long serialVersionUID = -7112139280586736629L;

    @SerializedName("count")
    private int mCount;


    @SerializedName("data")
    private List<T> mDataList;

    private int mAllPage;
    private int mCurrPage;
    private int mSize;

    /**
     * 是否全都加载完成
     * @return 是否全部加载完成
     */
    public boolean isAllDataLoaded() {
        return mCurrPage >= mAllPage;
    }

    public void setCurrPage(int page) {
        mCurrPage = page;
    }

    public int getCurrPage() {
        return mCurrPage;
    }

    public int getSize() {
        return mSize;
    }

    public void setSize(int size) {
        mSize = size;
    }

    public int getCount() {
        return mCount;
    }

    public int getAllPage() {
        return mAllPage;
    }

    public void setAllPage(int mAllPage) {
        this.mAllPage = mAllPage;
    }

    public List<T> getDataList() {
        if (mDataList == null) {
            mDataList = new ArrayList<T>();
        }
        return mDataList;
    }

    public void setDataList(List<T> dataList) {
        mDataList = dataList;
    }

}
