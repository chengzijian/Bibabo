package com.bibabo.fragment.watch.player.detail;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bibabo.R;
import com.bibabo.base.MVPBaseFragment;
import com.bibabo.base.list.BaseRecyclerListAdapter;
import com.bibabo.base.list.ListBaseView;
import com.bibabo.base.list.ViewHolder;
import com.bibabo.base.list.delegate.DividerItemDecoration;
import com.bibabo.entity.QQListInfoResult;
import com.bibabo.entity.QQListInfoResult.DataBean;
import com.bibabo.entity.VideoDetailsInfo;
import com.bibabo.event.PlayVideoEvent;
import com.bibabo.event.PlayVideoListEvent;
import com.bibabo.fragment.watch.player.VideoCommonData;
import com.bibabo.framework.utils.DisplayUtils;
import com.bibabo.framework.utils.PromptUtils;
import com.bibabo.framework.utils.StringUtils;
import com.bibabo.widget.FlexRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 * Created by zijian.cheng on 2017/7/10.
 */

public class VideoDetailFragment extends MVPBaseFragment<VideoDetailContract.View, VideoDetailPresenter>
        implements ListBaseView, VideoDetailContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.id_movie_title)
    TextView mMovieTitle;

    @BindView(R.id.id_movie_score)
    TextView mMovieScore;

    @BindView(R.id.id_flex_view)
    FlexRecyclerView mFlexRecyclerView;

    private GridLayoutManager mGridLayoutManager;
    private LinearLayoutManager mLinearLayoutManager;

    private String vid;

    private CatalogAdapter mAdapter;

    public static VideoDetailFragment newInstance(String vid) {
        Bundle args = new Bundle();
        VideoDetailFragment fragment = new VideoDetailFragment();
        args.putString("vid", vid);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int provideViewLayoutId() {
        return R.layout.fragment_detail;
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
        recyclerView.setLayoutManager(mGridLayoutManager);
        recyclerView.setItemAnimator(null);
        recyclerView.setFadingEdgeLength(0);
        recyclerView.setVerticalScrollBarEnabled(false);
        recyclerView.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        recyclerView.setAdapter(mAdapter = new CatalogAdapter());

        mAdapter.setOnItemClickListener(getOnItemClickListener());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGridLayoutManager = new GridLayoutManager(getContext(), 3);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        initRecyclerView();
        if (mAppBarLayout != null) {
            mAppBarLayout.setBackgroundColor(Color.TRANSPARENT);
            mAppBarLayout.setVisibility(View.GONE);
        }
        vid = getArguments().getString("vid");
        if (!StringUtils.isEmpty(vid)) {
            presenter.fetchVideoList(vid);
        }
    }

    @Override
    public void fetchVideoInfoSuccess(VideoDetailsInfo result) {
        QQListInfoResult listInfo = result.getListInfo();
        if (listInfo != null) {
            List<QQListInfoResult.DataBean> dataBeans = listInfo.getData();
            if (dataBeans != null && dataBeans.size() > 0) {
                mAdapter.setData(dataBeans);
                VideoCommonData.setVideoList(dataBeans);
            }
        }

        mMovieTitle.setText(result.getTitle());
        mMovieScore.setText(String.format("%1$s分・%2$s・全%3$s集・%4$s次播放"
                , result.getScore()
                , result.getTypeName(), "-", "-"));

        //设置播放器视频播放列表
        EventBus.getDefault().post(new PlayVideoListEvent());
    }

    private BaseRecyclerListAdapter.OnItemClickListener getOnItemClickListener() {
        return new BaseRecyclerListAdapter.OnItemClickListener<ViewHolder, DataBean>() {

            @Override
            public void onItemClick(View view, ViewHolder holder, DataBean data) {
                //播放视频
                EventBus.getDefault().post(new PlayVideoEvent(holder.getLayoutPosition()));
            }

            @Override
            public boolean onItemLongClick(View view, ViewHolder holder, QQListInfoResult.DataBean data) {
                return false;
            }
        };
    }

    @OnClick({R.id.id_grid_layer_btn, R.id.id_linear_layer_btn})
    public void changeLayoutManager(View v){
        if(v.getId() == R.id.id_grid_layer_btn){
            recyclerView.setLayoutManager(mGridLayoutManager);
        } else if(v.getId() == R.id.id_linear_layer_btn){
            recyclerView.setLayoutManager(mLinearLayoutManager);
        }
    }

    /**
     * 显示简介
     */
    @OnClick(R.id.id_video_introduce)
    public void showIntroduce(){
        start(IntroduceFragment.newInstance());
    }

    @Override
    public void setEnd(boolean end) {

    }

    @Override
    public void setRefresh(boolean refresh) {

    }

    @Override
    public void notifyLoadingStarted() {
        PromptUtils.getInstance().showProgressDialog(getContext(), "数据加载中…");
    }

    @Override
    public void notifyLoadingFinished() {
        PromptUtils.getInstance().dismissProgressDialog();
    }

    @Override
    public void error() {
        PromptUtils.getInstance().dismissProgressDialog();
    }
}
