package com.bibabo.fragment.watch.child;

import com.bibabo.R;
import com.bibabo.base.list.BaseRecyclerListAdapter;
import com.bibabo.base.list.ViewHolder;
import com.bibabo.entity.MainListDto;
import com.bibabo.entity.QVMovieInfo;

/**
 *
 * Created by zijian.cheng on 2017/6/13.
 */

public class BabyWatchItemAdapter extends BaseRecyclerListAdapter<QVMovieInfo, ViewHolder> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.list_item;
    }

    @Override
    protected void convert(ViewHolder holder, QVMovieInfo item, int position) {
        holder.setImageByUrl(R.id.id_image_view, item.getVpic());
        holder.setText(R.id.id_text_view, item.getVtitle());
        holder.setText(R.id.id_click_number, String.format("播放量：%s", item.getPlayNum()));
    }
}
