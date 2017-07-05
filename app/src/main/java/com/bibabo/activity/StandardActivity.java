package com.bibabo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.bibabo.R;
import com.bibabo.base.BaseActivity;
import com.bibabo.framework.fragmentation.anim.DefaultHorizontalAnimator;
import com.bibabo.framework.fragmentation.anim.FragmentAnimator;

/**
 *
 * Created by zijian.cheng on 2017/7/5.
 */

public class StandardActivity extends BaseActivity {

    public static void launch(Context context) {
        Intent intent = new Intent(context, StandardActivity.class);
        //intent.putExtra(ShowConfig.INTENT_CLICK_URL, url);
        context.startActivity(intent);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_base;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
//            loadRootFragment(R.id.fl_container, PlayMovieFragment.newInstance());
        }

    }

    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
