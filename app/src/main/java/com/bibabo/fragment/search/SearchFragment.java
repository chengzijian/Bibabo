package com.bibabo.fragment.search;

import android.os.Bundle;

import com.bibabo.R;
import com.bibabo.base.BaseFragment;

/**
 * Created by zijian.cheng on 2017/6/1.
 */

public class SearchFragment extends BaseFragment {

    public static SearchFragment newInstance() {
        Bundle args = new Bundle();
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int provideViewLayoutId() {
        return R.layout.fragment_main;
    }
}
