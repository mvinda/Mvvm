package com.example.base.mvp.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Henry on 2016/3/13.
 *
 * RecyclerView简单适配器的抽象类,适用于列表中,包含多种Item的结构
 */
public abstract class MultiRecyclerViewAdapter<T> extends MultiBaseAdapter<T, BaseRecyclerViewHolder> {

    private BaseAdapter.OnItemClickListener mOnItemClickListener = null;

    private BaseAdapter.OnItemLongClickListener mOnItemLongClickListener = null;

    private MultiItemTypeSupport multiItemTypeSupport;

    private LayoutInflater mLayoutInflater;

    public MultiRecyclerViewAdapter(Context context, List<T> datas) {
        super(context, datas);
    }

    /**
     * 先绑定数据，使用不同的View用于填充特定数据
     *
     * @param context
     * @param datas
     * @param multiItemTypeSupport
     */
    public MultiRecyclerViewAdapter(Context context, List<T> datas, MultiItemTypeSupport multiItemTypeSupport) {
        super(context, datas);
        this.multiItemTypeSupport = multiItemTypeSupport;
        mLayoutInflater = LayoutInflater.from(context);
    }

    private View createViewByType(ViewGroup viewGroup, int viewType) {
        int layoutId = multiItemTypeSupport.getLayoutId(viewType);
        View view = null;
        if (layoutId > 0) {
            view = mLayoutInflater.inflate(layoutId, viewGroup, false);
        } else {
            view = new View(viewGroup.getContext());
        }
        return view;
    }

    @Override
    public int getItemViewType(int position) {
        if (multiItemTypeSupport != null) {
            return multiItemTypeSupport.getItemViewType(position, super.getItem(position));
        }
        return super.getItemViewType(position);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        //调用不同的布局, 通过复写getItemViewType方法，可以使viewType变成不同的值，在根据这个值创建View
        View view = createViewByType(viewGroup, viewType);
        BaseRecyclerViewHolder vh = new BaseRecyclerViewHolder(view, mOnItemClickListener, mOnItemLongClickListener);
        vh.setViewType(viewType);
        onCreateView(vh, viewType);
        return vh;
    }

    @Override
    protected void onCreateView(BaseRecyclerViewHolder viewHolder, int viewType) {
    }

    public void setOnItemClickListener(BaseAdapter.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void setOnItemLongClickListener(BaseAdapter.OnItemLongClickListener listener) {
        this.mOnItemLongClickListener = listener;
    }

    public MultiItemTypeSupport getMultiItemTypeSupport() {
        return multiItemTypeSupport;
    }

    public void setMultiItemTypeSupport(MultiItemTypeSupport multiItemTypeSupport) {
        this.multiItemTypeSupport = multiItemTypeSupport;
    }

    public interface MultiItemTypeSupport<T> {
        int getLayoutId(int viewType);

        int getItemViewType(int postion, T t);
    }
}
