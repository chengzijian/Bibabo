package com.bibabo.fragment.watch.player.detail;

import android.os.Bundle;

import com.bibabo.R;
import com.bibabo.base.BaseBackFragment;

/**
 * 显示简介
 * Created by zijian.cheng on 2017/7/10.
 */

public class IntroduceFragment extends BaseBackFragment {

    public static IntroduceFragment newInstance() {
        Bundle args = new Bundle();
        IntroduceFragment fragment = new IntroduceFragment();
        //args.putString("vid", vid);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int provideViewLayoutId() {
        return R.layout.fragment_introduce;
    }
}
