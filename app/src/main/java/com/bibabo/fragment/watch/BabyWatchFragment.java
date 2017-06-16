package com.bibabo.fragment.watch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.bibabo.R;
import com.bibabo.base.MVPBaseViewPagerFragment;
import com.bibabo.fragment.watch.child.BabyWatchItemFragment;

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
                "儿歌", "故事", "成语故事", "好性格", "胎教故事"
                , "新年故事", "冬季温暖故事", "睡前故事", "益智童话故事"
        };
        categoryLinks = new String[]{
                "erge-c715-s2-p%d.html",
                "story-c701-s2-p%d.html",
                "story-c12-s2-p%d.html",
                "story-t1183-s1-p%d.html",
                "story-t2311-s1-p%d.html",
                "story-t2298-s1-p%d.html",
                "story-t2221-s1-p%d.html",
                "story-t1627-s1-p%d.html",
                "story-t800-s1-p%d.html"
        };
    }

    @Override
    protected Fragment getFragment(int position) {
        return BabyWatchItemFragment.newInstance(categoryLinks[position]);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @Override
    public void error() {

    }
}
