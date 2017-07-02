package com.bibabo.fragment.watch.child;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bibabo.R;
import com.bibabo.base.list.BaseRecyclerListAdapter;
import com.bibabo.base.list.ListBaseFragment;
import com.bibabo.base.list.ViewHolder;
import com.bibabo.entity.MainListDto;
import com.bibabo.entity.QVMovieInfo;
import com.bibabo.fragment.watch.player.BabyVideoDetailActivity;
import com.bibabo.framework.config.ShowConfig;
import com.bibabo.framework.utils.StringUtils;

import java.util.List;

/**
 *
 * Created by zijian.cheng on 2017/6/1.
 */
public class BabyWatchItemFragment extends ListBaseFragment<BabyWatchItemContract.View
        , BabyWatchItemPresenter, QVMovieInfo> implements BabyWatchItemContract.View {

    private String mItype;

    public static BabyWatchItemFragment newInstance(String type) {
        Bundle args = new Bundle();
        BabyWatchItemFragment fragment = new BabyWatchItemFragment();
        args.putString("type", type);
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
        mItype = getArguments().getString("type");
    }

    @NonNull
    @Override
    public BaseRecyclerListAdapter<QVMovieInfo, ?> createAdapter() {
        return new BabyWatchItemAdapter();
    }

    @Override
    public RecyclerView.LayoutManager createLayoutManager() {
        return new GridLayoutManager(getContext(), 2);
    }

    @Override
    protected BaseRecyclerListAdapter.OnItemClickListener getOnItemClickListener() {
        return new BaseRecyclerListAdapter.OnItemClickListener<ViewHolder, QVMovieInfo>() {

            @Override
            public void onItemClick(View view, ViewHolder holder, QVMovieInfo data) {
                BabyVideoDetailActivity.launch(getContext(), data.getVid());
            }

            @Override
            public boolean onItemLongClick(View view, ViewHolder holder, QVMovieInfo data) {
                return false;
            }
        };
    }

    @Override
    protected void loadData() {
        if(!StringUtils.isEmpty(mItype)){
            int page = clear ? 0 : getNextPage();
            int offset = page * ShowConfig.DEFAULT_PAGE_SIZE;
            presenter.fetchQVChildrenVideoList(mItype, String.valueOf(offset));
        }
    }

    @Override
    public void updateCurrentList(List<QVMovieInfo> items) {
        updateList(items);
        updateViewState();
    }

}
