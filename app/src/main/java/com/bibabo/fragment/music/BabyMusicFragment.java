package com.bibabo.fragment.music;

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
 *
 * Created by zijian.cheng on 2017/6/1.
 */

public class BabyMusicFragment extends MVPBaseViewPagerFragment<BabyMusicContract.View, BabyMusicPresenter>
        implements BabyMusicContract.View, Toolbar.OnMenuItemClickListener {

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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void setCategoryTitles() {
        categoryNames = new String[]{
                "儿歌","英文","故事"
        };
    }

    @Override
    protected Fragment getFragment(int position) {
        Fragment frag = new Fragment();
        switch (position) {
            case 0:
            default:
                frag = BabyWatchItemFragment.newInstance("BabyMusicFragment:"+position);
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
