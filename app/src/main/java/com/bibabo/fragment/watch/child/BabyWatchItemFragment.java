package com.bibabo.fragment.watch.child;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.bibabo.R;
import com.bibabo.base.list.BaseRecyclerListAdapter;
import com.bibabo.base.list.ListBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zijian.cheng on 2017/6/1.
 */
public class BabyWatchItemFragment extends ListBaseFragment<BabyWatchItemContract.View
        , BabyWatchItemPresenter, String>
        implements BabyWatchItemContract.View {

    public static BabyWatchItemFragment newInstance(String str) {
        Bundle args = new Bundle();
        BabyWatchItemFragment fragment = new BabyWatchItemFragment();
        args.putString("str", str);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int provideViewLayoutId() {
        return R.layout.layout_recycler_view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String str = getArguments().getString("str");
    }

    @NonNull
    @Override
    public BaseRecyclerListAdapter<String, ?> createAdapter() {
        return new BabyWatchItemAdapter();
    }

    @Override
    protected void loadData() {
        List<String> items = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            items.add("this is :" + i);
        }
        setRefresh(false);
        notifyLoadingFinished();


        updateCurrentList(items);
    }

    public void updateCurrentList(List<String> items) {
        updateList(items);
        updateViewState();
    }
}
