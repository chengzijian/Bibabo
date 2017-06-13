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

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bibabo.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by SamuelGjk on 2017/3/22.
 * <p>
 * 参考 dinuscxj/RecyclerRefreshLayout @{https://github.com/dinuscxj/RecyclerRefreshLayout/blob/master/app/src/main/java/com/dinuscxj/example/adapter/BaseRecyclerListAdapter.java}
 */

public abstract class BaseRecyclerListAdapter<T, VH extends ViewHolder> extends RecyclerView.Adapter<ViewHolder> {
    protected List<T> data = new ArrayList<>();

    @Override
    public int getItemCount() {
        return data.size();
    }

    public List<T> getData() {
        return data;
    }

    protected OnItemClickListener mOnItemClickListener;

    public abstract int getItemViewLayoutId();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(getItemViewLayoutId(), parent, false);

        //给所有列表增加点击效果。
        if(setSelectableItemBackground()){
            TypedValue typedValue = new TypedValue();
            parent.getContext().getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue, true);
            itemView.setBackgroundResource(typedValue.resourceId);
        }

        ViewHolder holder = new ViewHolder(parent.getContext(), itemView);
        onViewHolderCreated((VH) holder, holder.getConvertView());
        setListener(parent, holder, viewType);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int pos) {
        convert((VH) holder, data.get(pos), pos);
    }

    public void onViewHolderCreated(VH holder, View itemView) {

    }

    /**
     * 是否有点击效果
     * @return
     */
    public boolean setSelectableItemBackground() {
        return true;
    }

    protected abstract void convert(VH holder, T t, int position);

    protected void setListener(final ViewGroup parent, final ViewHolder holder, int viewType) {
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, holder, data.get(holder.getLayoutPosition()));
                }
            }
        });

        holder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemClickListener != null) {
                    return mOnItemClickListener.onItemLongClick(v, holder, data.get(holder.getLayoutPosition()));
                }
                return false;
            }
        });
    }

    @UiThread
    public void setData(List<T> data) {
        this.data.clear();
        this.data.addAll(data);

        notifyDataSetChanged();
    }

    public T getItem(int position) {
        return data.get(position);
    }

    @UiThread
    public BaseRecyclerListAdapter<T, VH> add(@NonNull T item) {
        data.add(item);
        notifyItemInserted(data.size() - 1);
        return this;
    }

    @UiThread
    public BaseRecyclerListAdapter<T, VH> addAll(@NonNull Collection<T> items) {
        data.addAll(items);
        notifyItemRangeInserted(data.size() - items.size(), items.size());
        return this;
    }

    @UiThread
    public BaseRecyclerListAdapter<T, VH> add(int position, @NonNull T item) {
        data.add(position, item);
        notifyItemInserted(position);
        return this;
    }

    @UiThread
    public BaseRecyclerListAdapter<T, VH> remove(int position) {
        data.remove(position);
        notifyItemRemoved(position);
        return this;
    }

    @UiThread
    public BaseRecyclerListAdapter<T, VH> remove(@NonNull T item) {
        return remove(data.indexOf(item));
    }

    @UiThread
    public BaseRecyclerListAdapter<T, VH> clear() {
        data.clear();
        notifyDataSetChanged();
        return this;
    }

    public boolean isEmpty() {
        return data == null || data.isEmpty();
    }

    public interface OnItemClickListener<VH extends ViewHolder, T> {
        void onItemClick(View view, VH holder, T data);

        boolean onItemLongClick(View view, VH holder, T data);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
