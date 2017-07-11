package com.bibabo.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.bibabo.R;
import com.bibabo.base.list.BaseRecyclerListAdapter;
import com.bibabo.base.list.ViewHolder;
import com.bibabo.base.list.delegate.DividerItemDecoration;
import com.bibabo.entity.QQListInfoResult;
import com.bibabo.framework.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 可伸缩的view
 * Created by zijian.cheng on 2017/7/11.
 */

public class FlexRecyclerView extends FrameLayout implements View.OnClickListener {

    private RecyclerView recyclerView;
    private View mToggleBtn;
    private PagingAdapter mAdapter;

    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;

    public FlexRecyclerView(Context context) {
        this(context, null);
    }

    public FlexRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.flex_recycler_view, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        recyclerView = (RecyclerView) findViewById(R.id.id_flex_recycler_view);
        mToggleBtn = findViewById(R.id.id_toggle_btn);
        mToggleBtn.setOnClickListener(this);
        initRecyclerView();
    }

    private void initRecyclerView() {
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext()
                , Color.TRANSPARENT, DisplayUtils.dp2px(4), DisplayUtils.dp2px(4));
        dividerItemDecoration.setDrawBorderTopAndBottom(true);
        dividerItemDecoration.setDrawBorderLeftAndRight(true);
        recyclerView.addItemDecoration(dividerItemDecoration);
        gridLayoutManager = new GridLayoutManager(getContext(), 4);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(null);
        recyclerView.setFadingEdgeLength(0);
        recyclerView.setVerticalScrollBarEnabled(false);
        recyclerView.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        recyclerView.setAdapter(mAdapter = new PagingAdapter());

        mAdapter.setOnItemClickListener(getOnItemClickListener());

        List<String> str = new ArrayList<>();
        str.add("1-120");
        str.add("121-240");
        str.add("241-360");
        str.add("361-480");
        str.add("361-480");
        str.add("361-480");
        str.add("361-480");
        str.add("361-480");
        mAdapter.setData(str);
    }

    @Override
    public void onClick(View v) {
        if(recyclerView.getLayoutManager() instanceof GridLayoutManager){
            recyclerView.setLayoutManager(linearLayoutManager);
        } else {
            recyclerView.setLayoutManager(gridLayoutManager);
        }
    }

    private BaseRecyclerListAdapter.OnItemClickListener getOnItemClickListener() {
        return new BaseRecyclerListAdapter.OnItemClickListener<ViewHolder, String>() {

            @Override
            public void onItemClick(View view, ViewHolder holder, String data) {
//                playVideoForVid(data.getVideoItem().getVid());
//                ImageLoader.loadStringRes(previewImageView, "http:" + data.getVideoItem().getPreview());
            }

            @Override
            public boolean onItemLongClick(View view, ViewHolder holder, String data) {
                return false;
            }
        };
    }

    private static class PagingAdapter extends BaseRecyclerListAdapter<String, ViewHolder> {

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_paging;
        }

        @Override
        protected void convert(final ViewHolder holder, String str, int position) {
            holder.setText(R.id.id_paging_item_text, str);
        }
    }
}
