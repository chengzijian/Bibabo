package com.bibabo.fragment.watch.child;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bibabo.R;
import com.bibabo.base.list.BaseRecyclerListAdapter;
import com.bibabo.base.list.ListBaseFragment;
import com.bibabo.entity.MainListDto;
import com.bibabo.framework.utils.StringUtils;

import java.util.List;

/**
 *
 * Created by zijian.cheng on 2017/6/1.
 */
public class BabyWatchItemFragment extends ListBaseFragment<BabyWatchItemContract.View
        , BabyWatchItemPresenter, MainListDto> implements BabyWatchItemContract.View {

    private String httpUrl;

    public static BabyWatchItemFragment newInstance(String url) {
        Bundle args = new Bundle();
        BabyWatchItemFragment fragment = new BabyWatchItemFragment();
        args.putString("url", url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int provideViewLayoutId() {
        return R.layout.layout_recycler_view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        httpUrl = getArguments().getString("url");
    }

    @NonNull
    @Override
    public BaseRecyclerListAdapter<MainListDto, ?> createAdapter() {
        return new BabyWatchItemAdapter();
    }

    @Override
    public RecyclerView.LayoutManager createLayoutManager() {
        return new GridLayoutManager(getContext(), 2);
    }

    @Override
    protected void loadData() {
        if(!StringUtils.isEmpty(httpUrl)){
            presenter.fetchList(String.format(httpUrl, clear ? 1 : getNextPage()));
        }
    }

    @Override
    public void updateCurrentList(List<MainListDto> items) {
        updateList(items);
        updateViewState();
    }

}