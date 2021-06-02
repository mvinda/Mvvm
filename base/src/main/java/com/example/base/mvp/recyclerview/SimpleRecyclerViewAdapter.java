package com.example.base.mvp.recyclerview;

import android.content.Context;

import java.util.List;

/**
 * Created by Henry on 2016/3/13.
 *
 * RecyclerView简单适配器的抽象类,适用于每个Item都是相同Layout布局的场景
 */
public abstract class SimpleRecyclerViewAdapter<T> extends BaseAdapter<T, BaseRecyclerViewHolder> {

    public SimpleRecyclerViewAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    public SimpleRecyclerViewAdapter(Context context, int layoutResId, List<T> datas) {
        super(context, layoutResId, datas);
    }

    @Override
    protected void onCreateView(BaseRecyclerViewHolder viewHolder, int viewType) {

    }
}
