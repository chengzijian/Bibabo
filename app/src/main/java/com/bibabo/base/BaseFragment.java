package com.bibabo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.AppBarLayout;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bibabo.R;
import com.bibabo.framework.fragmentation.SupportFragment;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zijian.cheng on 2017/5/08.
 */

public abstract class BaseFragment extends SupportFragment {

    protected Unbinder unbinder;

    @Nullable
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @Nullable
    @BindView(com.bibabo.R.id.appbar)
    protected AppBarLayout mAppBarLayout;

    @Nullable
    @BindView(com.bibabo.R.id.title)
    protected TextView mBarTitle;

    @BindColor(com.bibabo.R.color.navigation)
    int navigationColor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(provideViewLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, root);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            IMMLeaks.fixFocusedViewLeak(_mActivity.getApplication()); // 修复 InputMethodManager 引发的内存泄漏
//        }
        if (toolbar != null) {
            DrawerArrowDrawable navigationIcon = new DrawerArrowDrawable(getContext());
            navigationIcon.setColor(navigationColor);
            toolbar.setNavigationIcon(navigationIcon);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNavigationClick();
                }
            });
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    protected void setToolBarTitle(@StringRes int resId) {
        if (toolbar != null) {
            toolbar.setTitle(resId);
        }
    }

    protected void setToolBarTitle(String resStr) {
        if (toolbar != null) {
            toolbar.setTitle(resStr);
        }
    }

    protected void onNavigationClick() {
        _mActivity.onBackPressed();
    }

    protected abstract int provideViewLayoutId();
}
