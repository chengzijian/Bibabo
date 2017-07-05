package com.bibabo.activity.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;

import com.bibabo.R;
import com.bibabo.activity.StandardActivity;
import com.bibabo.activity.playvideo.PlayMovieActivity;
import com.bibabo.base.MVPBaseActivity;
import com.bibabo.entity.TabEntity;
import com.bibabo.framework.BaseApplication;
import com.bibabo.framework.fragmentation.anim.DefaultHorizontalAnimator;
import com.bibabo.framework.fragmentation.anim.FragmentAnimator;
import com.bibabo.framework.utils.PromptUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends MVPBaseActivity<MainContract.View, MainPresenter>
        implements MainContract.View, NavigationView.OnNavigationItemSelectedListener {

    private static final String SINGLE_DAY_FRAGMENTS_TAGS = "single_day_fragments_tags";

    private static final String CURRENT_SINGLE_DAY_FRAGMENT_POSITION = "current_single_day_fragments_position";


    private long exitTime = 0;
    private String[] mTitles = {"宝宝看", "宝宝听", "宝宝学"};

    private int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select};

    private int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect};

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private MainPageAdapter mMainPageAdapter;

    @BindView(R.id.vp_2)
    public ViewPager mViewPager;

    @BindView(R.id.tl_2)
    public CommonTabLayout mTabLayout;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initViewPage(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        if (mMainPageAdapter != null && mMainPageAdapter.getFragments() != null) {
//            Fragment[] singleDayFragments = mMainPageAdapter.getFragments();
//            String[] tags = new String[singleDayFragments.length];
//            for (int i = 0; i < tags.length; i++) {
//                tags[i] = singleDayFragments[i].getTag();
//            }
//            outState.putStringArray(SINGLE_DAY_FRAGMENTS_TAGS, tags);
//            outState.putInt(CURRENT_SINGLE_DAY_FRAGMENT_POSITION, mViewPager.getCurrentItem());
//        }
    }

    private void initViewPage(Bundle savedInstanceState) {
        String[] singleDayFragmentsTags = null;
        int currentSingleDayFragment = 0;

        if (savedInstanceState != null && savedInstanceState.containsKey(SINGLE_DAY_FRAGMENTS_TAGS)) {
            singleDayFragmentsTags = savedInstanceState.getStringArray(SINGLE_DAY_FRAGMENTS_TAGS);
        }
        if (savedInstanceState != null && savedInstanceState.containsKey(CURRENT_SINGLE_DAY_FRAGMENT_POSITION)) {
            currentSingleDayFragment =  savedInstanceState.getInt(CURRENT_SINGLE_DAY_FRAGMENT_POSITION);
        }

        setUpViewPagerForNarrowMode(singleDayFragmentsTags, currentSingleDayFragment);
    }

    private void setUpViewPagerForNarrowMode(String[] singleDayFragmentsTags, int currentSingleDayFragment) {
        mMainPageAdapter = new MainPageAdapter(getContext(), this.getSupportFragmentManager());
        mMainPageAdapter.setRetainedFragmentsTags(singleDayFragmentsTags);
        mViewPager.setAdapter(mMainPageAdapter);
        mViewPager.setCurrentItem(currentSingleDayFragment);

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mTabLayout.setTabData(mTabEntities);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
//                    mTabLayout.showMsg(0, 1);
//                    UnreadMsgUtils.show(mTabLayout_2.getMsgView(0), mRandom.nextInt(100) + 1);
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

    @Override
    public void onBackPressedSupport() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (System.currentTimeMillis() - exitTime > 2000) {
            PromptUtils.getInstance().showLongToast("再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            //两次按键小于2秒时，退出应用
            BaseApplication.exitInMillSeconds(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if(id == R.id.nav_vip_video){
            PlayMovieActivity.launch(getContext());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void updateMe() {

    }

    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
