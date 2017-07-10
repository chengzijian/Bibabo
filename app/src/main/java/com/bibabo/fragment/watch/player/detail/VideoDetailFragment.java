package com.bibabo.fragment.watch.player.detail;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bibabo.R;
import com.bibabo.base.MVPBaseFragment;
import com.bibabo.base.list.BaseRecyclerListAdapter;
import com.bibabo.base.list.ListBaseView;
import com.bibabo.base.list.ViewHolder;
import com.bibabo.entity.QQListInfoResult;
import com.bibabo.entity.QQListInfoResult.DataBean;
import com.bibabo.entity.VideoDetailsInfo;
import com.bibabo.framework.utils.StringUtils;

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
        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 5);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(mLayoutManager);
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
            }
        }

        //播放视频
//        playVideoForVid(result.getCurrVideoVid());
//        ImageLoader.loadStringRes(videoImage, "http:" + result.getPic());
//        ImageLoader.loadStringRes(previewImageView, "http:" + result.getPic());
//        videoDesc.setText(result.getSecTitle());
//        videoTitle.setText(result.getTitle());

    }

    private BaseRecyclerListAdapter.OnItemClickListener getOnItemClickListener() {
        return new BaseRecyclerListAdapter.OnItemClickListener<ViewHolder, DataBean>() {

            @Override
            public void onItemClick(View view, ViewHolder holder, DataBean data) {
//                playVideoForVid(data.getVideoItem().getVid());
//                ImageLoader.loadStringRes(previewImageView, "http:" + data.getVideoItem().getPreview());
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

        } else if(v.getId() == R.id.id_linear_layer_btn){

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

    }

    @Override
    public void notifyLoadingFinished() {

    }

    @Override
    public void error() {

    }
}
