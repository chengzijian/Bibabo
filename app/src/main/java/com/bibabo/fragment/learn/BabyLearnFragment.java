package com.bibabo.fragment.learn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.bibabo.R;
import com.bibabo.base.MVPBaseViewPagerFragment;
import com.bibabo.entity.CategoryData;
import com.bibabo.fragment.watch.child.BabyWatchItemFragment;

/**
 *
 * Created by zijian.cheng on 2017/6/1.
 */

public class BabyLearnFragment extends MVPBaseViewPagerFragment<BabyLearnContract.View, BabyLearnPresenter>
        implements BabyLearnContract.View, Toolbar.OnMenuItemClickListener {

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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void setCategoryList() {
        childCategoryList.clear();
        childCategoryList.add(new CategoryData("test1",""));
        childCategoryList.add(new CategoryData("test2",""));
    }

    @Override
    protected Fragment getFragment(int position) {
        Fragment frag = new Fragment();
        switch (position) {
            case 0:
            default:
                frag = BabyWatchItemFragment.newInstance("");
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
