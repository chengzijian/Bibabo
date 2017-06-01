package com.bibabo.base;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.bibabo.R;
import com.bibabo.framework.fragmentation.SupportActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *
 * Created by zijian.cheng on 2017/5/08.
 */

public abstract class BaseActivity extends SupportActivity {

    protected Unbinder unbinder;

    @Nullable
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideContentViewId());
        unbinder = ButterKnife.bind(this);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setNavigationIconColor(Color.WHITE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    protected abstract int provideContentViewId();

    protected void setToolBarTitle(CharSequence title) {
        if (toolbar != null) {
            toolbar.setTitle(title);
        }
    }

    protected void setDisplayShowTitleEnabled(boolean enabled) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(enabled);
        }
    }

    protected void setNavigationIconColor(@ColorInt int color) {
        if (toolbar != null) {
            Drawable icon = toolbar.getNavigationIcon();
            if (icon != null) {
                DrawableCompat.setTint(icon, color);
            }
        }
    }
}
