/*
 * Copyright (c) 2017 SamuelGjk <samuel.alva@outlook.com>
 *
 * This file is part of DiyCode
 *
 * DiyCode is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DiyCode is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with DiyCode. If not, see <http://www.gnu.org/licenses/>.
 */

package com.bibabo.base.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bibabo.R;
import com.bibabo.base.MVPBaseFragment;
import com.bibabo.base.list.delegate.LoadMoreDelegate;
import com.bibabo.base.list.delegate.SwipeRefreshDelegate;
import com.kennyc.view.MultiStateView;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.kennyc.view.MultiStateView.VIEW_STATE_CONTENT;
import static com.kennyc.view.MultiStateView.VIEW_STATE_EMPTY;
import static com.kennyc.view.MultiStateView.VIEW_STATE_ERROR;
import static com.kennyc.view.MultiStateView.VIEW_STATE_LOADING;

/**
 * Created by SamuelGjk on 2017/3/22.
 * <p>
 * 参考 drakeet/rebase-android @{https://github.com/drakeet/rebase-android/blob/master/app/src/main/java/com/drakeet/rebase/fragment/ListBaseFragment.java}
 */

public abstract class ListBaseFragment<V extends ListBaseView, T extends ListBasePresenterImpl<V, MODEL>
        , MODEL> extends MVPBaseFragment<V, T>
        implements ListBaseView, SwipeRefreshDelegate.OnSwipeRefreshListener, LoadMoreDelegate.LoadMoreSubject {

    @Nullable
    @BindView(R.id.multi_state_view)
    protected MultiStateView multiStateView;

    @BindView(R.id.recycler_view)
    protected RecyclerView recyclerView;

    private SwipeRefreshDelegate refreshDelegate;
    private LoadMoreDelegate loadMoreDelegate;

    private AtomicInteger loadingCount;
    private boolean isEnd;
    private int page;

    protected boolean clear = true;

    protected BaseRecyclerListAdapter<MODEL, ?> adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = createAdapter();
        refreshDelegate = new SwipeRefreshDelegate(this);
        loadMoreDelegate = new LoadMoreDelegate(this);
        loadingCount = new AtomicInteger(0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);
        recyclerView.setLayoutManager(createLayoutManager());
        adapter.setOnItemClickListener(getOnItemClickListener());
        recyclerView.setAdapter(adapter);
        loadMoreDelegate.attach(recyclerView);
        refreshDelegate.attach(root);
        if (multiStateView != null) {
            View errorView = multiStateView.getView(VIEW_STATE_ERROR);
            if (errorView != null) {
                ButterKnife.findById(errorView, R.id.text_error)
                           .setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                                   switchViewState(VIEW_STATE_LOADING);
                                   loadData();
                               }
                           });
            }
        }
        loadData();
        return root;
    }

    @NonNull
    public abstract BaseRecyclerListAdapter<MODEL, ?> createAdapter();

    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
    }

    protected BaseRecyclerListAdapter.OnItemClickListener getOnItemClickListener(){
        return null;
    }

    protected abstract void loadData();

    protected boolean onInterceptLoadMore() {
        return false;
    }

    @Override
    public void setRefresh(boolean refresh) {
        refreshDelegate.setRefresh(refresh);
    }

    @Override
    public void onSwipeRefresh() {
        clear = true;
        loadData();
    }


    @Override
    public final void onLoadMore() {
        if (!isEnd()) {
            if (!onInterceptLoadMore()) {
                clear = false;
                loadData();
            }
        }
    }

    protected boolean isShowingRefresh() {
        return refreshDelegate.isShowingRefresh();
    }

    @Override
    public void setEnd(boolean end) {
        isEnd = end;
        if(!isEnd){//如果不是最后一些，page + 1
            page += 1;
        } else {
            page = 0;
        }
    }

    public int getNextPage(){
        return page;
    }

    public boolean isEnd() {
        return isEnd;
    }

    protected void setSwipeToRefreshEnabled(boolean enable) {
        refreshDelegate.setEnabled(enable);
    }

    public void smoothScrollToPosition(int position) {
        recyclerView.smoothScrollToPosition(position);
    }

    @Override
    public boolean isLoading() {
        return loadingCount.get() > 0;
    }

    @Override
    public void notifyLoadingStarted() {
        loadingCount.getAndIncrement();
    }

    @Override
    public void notifyLoadingFinished() {
        loadingCount.decrementAndGet();
    }

    @Override
    public void error() {
        if (adapter.getItemCount() == 0) {
            switchViewState(VIEW_STATE_ERROR);
        }
    }

    protected void switchViewState(int state) {
        if (multiStateView != null) {
            multiStateView.setViewState(state);
        }
    }

    protected void updateList(List<MODEL> items) {
        if (clear) {
            adapter.setData(items);
        } else {
            adapter.addAll(items);
        }
    }

    protected void clearList(){
        clear = true;
        adapter.clear();
    }

    protected void updateViewState() {
        if (adapter.getItemCount() > 0) {
            switchViewState(VIEW_STATE_CONTENT);
        } else {
            switchViewState(VIEW_STATE_EMPTY);
        }
    }
}
