package com.bibabo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.bibabo.R;
import com.bibabo.base.BaseFragment;

import butterknife.BindView;

/**
 *
 * Created by zijian.cheng on 2017/6/1.
 */

public class BabyWatchItemFragment extends BaseFragment {

    @BindView(R.id.id_text_view)
    TextView mTextView;

    public static BabyWatchItemFragment newInstance(String str) {
        Bundle args = new Bundle();
        BabyWatchItemFragment fragment = new BabyWatchItemFragment();
        args.putString("str",str);
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    protected int provideViewLayoutId() {
        return R.layout.fragment_item;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String str = getArguments().getString("str");
        mTextView.setText(str);
    }
}
