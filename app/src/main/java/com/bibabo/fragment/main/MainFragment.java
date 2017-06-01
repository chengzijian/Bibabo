package com.bibabo.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;

import com.bibabo.R;
import com.bibabo.base.BaseFragment;
import com.bibabo.framework.fragmentation.SupportFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zijian.cheng on 17/5/08
 *
 * @version V1.0
 */

public class MainFragment extends BaseFragment {

//    @BindView(R.id.fl_container)
//    FrameLayout flContainer;
//    @BindView(R.id.bottomBar)
//    BottomNavigationBar bottomNavigationBar;
//
//    @BindView(R.id.fab) View mCreateFamily;

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOUR = 3;

    private SupportFragment[] mFragments = new SupportFragment[4];

    //BadgeItem badgeItem;
    int lastSelectedPosition = 0;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
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
        // Register
//        EventBus.getDefault().register(this);
//        if (savedInstanceState == null) {
//            mFragments[FIRST] = new SupportFragment();
//            mFragments[SECOND] = new SupportFragment();
//            mFragments[THIRD] = MessageMainFragment.newInstance();
//            mFragments[FOUR] = WorldListFragment.newInstance();//世界
//
//            loadMultipleRootFragment(R.id.fl_container, FIRST,
//                    mFragments[FIRST],
//                    mFragments[SECOND],
//                    mFragments[THIRD],
//                    mFragments[FOUR]);
//        } else {
//            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
//
//            // 这里我们需要拿到mFragments的引用,也可以通过getSupportFragmentManager.getFragments()自行进行判断查找(效率更高些),用下面的方法查找更方便些
//            mFragments[FIRST] = findChildFragment(SupportFragment.class);
//            mFragments[SECOND] = findChildFragment(SupportFragment.class);
//            mFragments[THIRD] = findChildFragment(MessageMainFragment.class);
//            mFragments[FOUR] = findChildFragment(WorldListFragment.class);
//        }
//        bottomNavigationBar.setTabSelectedListener(this);
//        //bottomNavigationBar.setFab(mCreateFamily);
//        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
//        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
//        //底部bar上的计数小圆点
////        badgeItem = new BadgeItem()
////                .setBorderWidth(4)
////                .setBackgroundColorResource(R.color.colorPrimary)
////                .setHideOnSelect(true);
//        bottomNavigationBar
//                .addItem(new BottomNavigationItem(R.drawable.ic_tab_bar_card).setActiveColorResource(R.color.tab_bar_card_color))
//                .addItem(new BottomNavigationItem(R.drawable.ic_tab_bar_weapon).setActiveColorResource(R.color.tab_bar_weapon_color))
//                .addItem(new BottomNavigationItem(R.drawable.ic_tab_bar_massage).setActiveColorResource(R.color.tab_bar_massage_color))
//                .addItem(new BottomNavigationItem(R.drawable.ic_tab_bar_world).setActiveColorResource(R.color.tab_bar_world_color))
//                .setFirstSelectedPosition(lastSelectedPosition > 3 ? 3 : lastSelectedPosition)
//                .initialise();
    }

//    /**
//     * 创建家族
//     */
//    @OnClick(R.id.fab)
//    public void onClickCreateFamily(){
//        if(UserUtils.isAlreadyLogin()){
//            CreateFamilyActivity.launch(getContext());
//        }
//    }
//
//    /**
//     * 创建家族成功
//     * @param event
//     */
//    @Subscribe
//    public void onCreateFamilySuccess(CreateFamilyResultEvent event){
//        mCreateFamily.setVisibility(View.GONE);
//        if (UserUtils.isAlreadyLogin()) {
//            FamilyActivity.launch(_mActivity, event.familyRoomId);
//        }
//    }
//
//    @Override
//    public void onSupportVisible() {
//        super.onSupportVisible();
//    }
//
//    @Override
//    public void onTabSelected(int position) {
////        setMessageText(position + " Tab Selected");
////        setScrollableText(position);
//        showHideFragment(mFragments[position], mFragments[lastSelectedPosition]);
//        lastSelectedPosition = position;
//    }
//
//    @Override
//    public void onTabUnselected(int position) {
//
//    }
//
//    @Override
//    public void onTabReselected(int position) {
//
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        // Unregister
//        EventBus.getDefault().unregister(this);
//    }
}
