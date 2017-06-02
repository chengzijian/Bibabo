package com.bibabo.fragment.watch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.bibabo.R;
import com.bibabo.base.MVPBaseViewPagerFragment;
import com.bibabo.fragment.BabyWatchItemFragment;

/**
 * 宝宝看
 * Created by zijian.cheng on 2017/6/1.
 */

public class BabyWatchFragment extends MVPBaseViewPagerFragment<BabyWatchContract.View, BabyWatchPresenter>
        implements BabyWatchContract.View, Toolbar.OnMenuItemClickListener {

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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void setCategoryTitles() {
        categoryNames = new String[]{
                "精选","动画"
        };
    }

    @Override
    protected Fragment getFragment(int position) {
        Fragment frag = new Fragment();
        switch (position) {
            case 0:
            default:
                frag = BabyWatchItemFragment.newInstance("BabyWatchFragment:"+position);
                break;
        }
        return frag;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @Override
    public void error() {

    }
}
