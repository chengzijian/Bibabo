package com.bibabo.base;

import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.view.View;

import me.yokeyword.fragmentation.SwipeBackLayout;
import me.yokeyword.fragmentation_swipeback.core.ISwipeBackFragment;
import me.yokeyword.fragmentation_swipeback.core.SwipeBackFragmentDelegate;

public abstract class BaseBackFragment extends BaseFragment implements ISwipeBackFragment {

    final SwipeBackFragmentDelegate mDelegate = new SwipeBackFragmentDelegate(this);

    public BaseBackFragment() {
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mDelegate.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setParallaxOffset(0.5f);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mDelegate.onViewCreated(view, savedInstanceState);
    }

    public View attachToSwipeBack(View view) {
        return this.mDelegate.attachToSwipeBack(view);
    }

    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        this.mDelegate.onHiddenChanged(hidden);
    }

    public SwipeBackLayout getSwipeBackLayout() {
        return this.mDelegate.getSwipeBackLayout();
    }

    public void setSwipeBackEnable(boolean enable) {
        this.mDelegate.setSwipeBackEnable(enable);
    }

    public void setParallaxOffset(@FloatRange(from = 0.0D, to = 1.0D) float offset) {
        this.mDelegate.setParallaxOffset(offset);
    }

    public void onDestroyView() {
        this.mDelegate.onDestroyView();
        super.onDestroyView();
    }
}
