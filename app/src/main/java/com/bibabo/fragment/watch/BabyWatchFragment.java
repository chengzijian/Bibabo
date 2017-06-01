package com.bibabo.fragment.watch;

import android.os.Bundle;

import com.bibabo.R;
import com.bibabo.base.BaseFragment;

/**
 * Created by zijian.cheng on 2017/6/1.
 */

public class BabyWatchFragment extends BaseFragment {

    public static BabyWatchFragment newInstance() {
        Bundle args = new Bundle();
        BabyWatchFragment fragment = new BabyWatchFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    protected int provideViewLayoutId() {
        return R.layout.fragment_main;
    }
}
