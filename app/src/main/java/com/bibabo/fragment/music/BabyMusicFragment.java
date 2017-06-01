package com.bibabo.fragment.music;

import android.os.Bundle;

import com.bibabo.R;
import com.bibabo.base.BaseFragment;

/**
 * Created by zijian.cheng on 2017/6/1.
 */

public class BabyMusicFragment extends BaseFragment {

    public static BabyMusicFragment newInstance() {
        Bundle args = new Bundle();
        BabyMusicFragment fragment = new BabyMusicFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    protected int provideViewLayoutId() {
        return R.layout.fragment_main;
    }
}
