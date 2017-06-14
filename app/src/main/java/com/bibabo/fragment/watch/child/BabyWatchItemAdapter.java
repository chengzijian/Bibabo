package com.bibabo.fragment.watch.child;

import com.bibabo.R;
import com.bibabo.base.list.BaseRecyclerListAdapter;
import com.bibabo.base.list.ViewHolder;
import com.bibabo.entity.MainListDto;

/**
 * Created by zijian.cheng on 2017/6/13.
 */

public class BabyWatchItemAdapter extends BaseRecyclerListAdapter<MainListDto, ViewHolder> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.list_item;
    }

    @Override
    protected void convert(ViewHolder holder, MainListDto s, int position) {
        holder.setText(R.id.id_text_view, s.getName());
    }
}
