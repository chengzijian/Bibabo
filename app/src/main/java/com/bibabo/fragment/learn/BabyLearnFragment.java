package com.bibabo.fragment.learn;

import android.os.Bundle;

import com.bibabo.R;
import com.bibabo.base.BaseFragment;

/**
 * Created by zijian.cheng on 2017/6/1.
 */

public class BabyLearnFragment extends BaseFragment {

    public static BabyLearnFragment newInstance() {
        Bundle args = new Bundle();
        BabyLearnFragment fragment = new BabyLearnFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    protected int provideViewLayoutId() {
        return R.layout.fragment_main;
    }
}
