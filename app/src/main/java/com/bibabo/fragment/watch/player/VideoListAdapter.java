package com.bibabo.fragment.watch.player;

import com.bibabo.R;
import com.bibabo.base.list.BaseRecyclerListAdapter;
import com.bibabo.base.list.ViewHolder;
import com.bibabo.entity.QQListInfoResult;

public class VideoListAdapter extends BaseRecyclerListAdapter<QQListInfoResult.DataBean, ViewHolder> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_video_list;
    }

    @Override
    protected void convert(final ViewHolder holder, QQListInfoResult.DataBean userInfo, int position) {
        holder.setText(R.id.id_video_name, userInfo.getVideoItem().getTitle());
        holder.setImageByUrl(R.id.id_image_pic, "http:" + userInfo.getVideoItem().getPreview());
    }
}