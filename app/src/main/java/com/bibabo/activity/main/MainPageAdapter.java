package com.bibabo.activity.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bibabo.R;
import com.bibabo.fragment.learn.BabyLearnFragment;
import com.bibabo.fragment.music.BabyMusicFragment;
import com.bibabo.fragment.watch.BabyWatchFragment;

/**
 *
 * Created by zijian.cheng on 2017/6/2.
 */

public class MainPageAdapter extends FragmentPagerAdapter {

    private FragmentManager mFragmentManager;
    private Fragment[] mFragments;
    private String[] categoryNames;

    public MainPageAdapter(Context context, FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
        categoryNames = context.getResources().getStringArray(R.array.main_page_titles);
    }

    @Override
    public Fragment getItem(int position) {
        if (mFragments != null && mFragments.length > position && mFragments[position] != null) {
            return mFragments[position];
        }

        Fragment frag = new Fragment();
        switch (position) {
            case 0:
                frag = BabyWatchFragment.newInstance();
                break;
            case 1:
                frag = BabyMusicFragment.newInstance();
                break;
            case 2:
                frag = BabyLearnFragment.newInstance();
                break;
        }

        if (mFragments == null) {
            mFragments = new Fragment[getCount()];
        }
        mFragments[position] = frag;
        return frag;
    }

    @Override
    public int getCount() {
        return categoryNames.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return categoryNames[position];
    }

    public Fragment[] getFragments() {
        if (mFragments == null) {
            int count = getCount();
            for (int i = 0; i < count; i++) {
                getItem(i);
            }
        }
        return mFragments;
    }

    public void setRetainedFragmentsTags(String[] tags) {
        if (tags != null && tags.length > 0) {
            mFragments = new Fragment[tags.length];
            for (int i = 0; i < tags.length; i++) {
                Fragment fragment = mFragmentManager.findFragmentByTag(tags[i]);
                mFragments[i] = fragment;
                if (fragment == null) {
                    getItem(i);
                }
            }
        }
    }
}
