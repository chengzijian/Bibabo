package com.bibabo.fragment.watch;

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
    protected void setCategoryList() {
        childCategoryList.clear();
//
//        childCategoryList.add(new CategoryData("儿童历险", "4"));
//        childCategoryList.add(new CategoryData("儿童音乐", "1"));
//        childCategoryList.add(new CategoryData("儿童教育", "3"));
//        childCategoryList.add(new CategoryData("儿童竞技", "7"));
//        childCategoryList.add(new CategoryData("少儿经典", "8"));
//        childCategoryList.add(new CategoryData("海外精选", "10"));

        childCategoryList.add(new CategoryData("少年", "1"));
        childCategoryList.add(new CategoryData("少女", "27"));
        childCategoryList.add(new CategoryData("萌系", "26"));
        childCategoryList.add(new CategoryData("搞笑", "2"));
        childCategoryList.add(new CategoryData("惊悚", "7"));
        childCategoryList.add(new CategoryData("魔幻", "4"));
        childCategoryList.add(new CategoryData("推理", "5"));
        childCategoryList.add(new CategoryData("原创", "11"));
        childCategoryList.add(new CategoryData("竞技", "9"));
    }

    @Override
    protected Fragment getFragment(int position) {
        return BabyWatchItemFragment.newInstance(childCategoryList.get(position).getItype());
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @Override
    public void error() {

    }
}
