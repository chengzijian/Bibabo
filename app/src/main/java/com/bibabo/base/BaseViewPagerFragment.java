package com.bibabo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.bibabo.R;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import butterknife.BindView;

/**
 *
 * Created by zijian.cheng on 2017/6/2.
 */

public abstract class BaseViewPagerFragment extends BaseFragment {

    private static final String SINGLE_DAY_FRAGMENTS_TAGS = "single_day_fragments_tags";

    private static final String CURRENT_SINGLE_DAY_FRAGMENT_POSITION = "current_single_day_fragments_position";

    @Nullable
    @BindView(R.id.id_top_tab_layout)
    protected SlidingTabLayout mTabLayout;

    @Nullable
    @BindView(R.id.view_pager)
    protected ViewPager mViewPager;

    private ChildViewPageAdapter mViewPagerAdapter;
    protected String[] categoryNames;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mViewPagerAdapter != null && mViewPagerAdapter.getFragments() != null) {
            Fragment[] singleDayFragments = mViewPagerAdapter.getFragments();
            String[] tags = new String[singleDayFragments.length];
            for (int i = 0; i < tags.length; i++) {
                tags[i] = singleDayFragments[i].getTag();
            }
            outState.putStringArray(SINGLE_DAY_FRAGMENTS_TAGS, tags);
            outState.putInt(CURRENT_SINGLE_DAY_FRAGMENT_POSITION, mViewPager.getCurrentItem());
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mViewPager != null) {
            initViewPage(savedInstanceState);
        }
    }

    private void initViewPage(Bundle savedInstanceState) {
        String[] singleDayFragmentsTags = null;
        int currentSingleDayFragment = 0;

        if (savedInstanceState != null && savedInstanceState.containsKey(SINGLE_DAY_FRAGMENTS_TAGS)) {
            singleDayFragmentsTags = savedInstanceState.getStringArray(SINGLE_DAY_FRAGMENTS_TAGS);
        }
        if (savedInstanceState != null && savedInstanceState.containsKey(CURRENT_SINGLE_DAY_FRAGMENT_POSITION)) {
            currentSingleDayFragment = savedInstanceState.getInt(CURRENT_SINGLE_DAY_FRAGMENT_POSITION);
        }

        setCategoryTitles();
        setUpViewPagerForNarrowMode(singleDayFragmentsTags, currentSingleDayFragment);
    }

    private void setUpViewPagerForNarrowMode(String[] singleDayFragmentsTags, int currentSingleDayFragment) {
        mViewPagerAdapter = new ChildViewPageAdapter(getChildFragmentManager());
        mViewPagerAdapter.setRetainedFragmentsTags(singleDayFragmentsTags);
        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setViewPager(mViewPager, categoryNames);
        mViewPager.setCurrentItem(currentSingleDayFragment);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                }
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setPageMargin(getResources()
                .getDimensionPixelSize(R.dimen.padding_normal));
        mViewPager.setPageMarginDrawable(R.drawable.page_margin);
    }

    protected abstract void setCategoryTitles();

    protected abstract Fragment getFragment(int position);

    protected class ChildViewPageAdapter extends FragmentPagerAdapter {

        private FragmentManager mFragmentManager;
        private Fragment[] mFragments;

        public ChildViewPageAdapter(FragmentManager fm) {
            super(fm);
            mFragmentManager = fm;
        }

        @Override
        public Fragment getItem(int position) {
            if (mFragments != null && mFragments.length > position && mFragments[position] != null) {
                return mFragments[position];
            }
            Fragment frag = getFragment(position);
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
}
