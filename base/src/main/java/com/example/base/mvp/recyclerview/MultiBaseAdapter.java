package com.example.base.mvp.recyclerview;

import android.content.Context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Henry on 2016/3/13.
 * RecyclerView适配器的抽象基类
 */
public abstract class MultiBaseAdapter<T, H extends BaseRecyclerViewHolder> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    protected static final String TAG = MultiBaseAdapter.class.getSimpleName();

    protected final Context context;

    protected List<T> datas;

    /**
     * 先绑定数据，使用不同的View用于填充特定数据
     *
     * @param context
     * @param datas
     */
    public MultiBaseAdapter(Context context, List<T> datas) {
        this.datas = datas == null ? new ArrayList<T>() : datas;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder viewHoder, int position) {
        T item = getItem(position);
        convert((H) viewHoder, item, position);
    }

    @Override
    public int getItemCount() {
        if (datas == null || datas.size() <= 0)
            return 0;

        return datas.size();
    }

    public T getItem(int position) {
        if (position >= datas.size()) return null;
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 清空数据，逐项清空，触发item移除动画效果
     */
    public void clear() {
        for (Iterator it = datas.iterator(); it.hasNext(); ) {
            T t = (T) it.next();
            int position = datas.indexOf(t);
            it.remove();
            notifyItemRemoved(position);
        }
    }

    /**
     * 从列表中删除某项
     *
     * @param t
     */
    public void removeItem(T t) {
        int position = datas.indexOf(t);
        datas.remove(position);
        notifyItemRemoved(position);
    }

    public List<T> getDatas() {
        return datas;
    }

    public void addItem(T item) {
        datas.add(item);
    }

    /**
     * 原数据为空，新添加数据
     *
     * @param list
     */
    public void addData(List<T> list) {
        datas.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 某个位置后面添加数据
     *
     * @param position
     * @param list
     */
    public void addData(int position, List<T> list) {
        if (list != null && list.size() > 0) {
            for (T t : list) {
                datas.add(position, t);
                notifyItemInserted(position);
            }
        }
    }

    /**
     * 刷新数据
     *
     * @param list
     */
    public void refreshData(List<T> list) {
        if (list != null && list.size() > 0) {
            clear();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                datas.add(i, list.get(i));
                notifyItemInserted(i);
            }
        }
    }

    /**
     * 加载更多数据
     *
     * @param list
     */
    public void loadMoreData(List<T> list) {
        if (list != null && list.size() > 0) {
            int size = list.size();
            int begin = datas.size();
            if (begin == 0) {
                addData(list);
            } else {
                for (int i = 0; i < size; i++) {
                    datas.add(list.get(i));
                    notifyItemInserted(i + begin);
                }
            }
        }
    }

    @Override
    public void onViewAttachedToWindow(BaseRecyclerViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }

    @Override
    public void onViewRecycled(BaseRecyclerViewHolder holder) {
        holder.clear();
        super.onViewRecycled(holder);
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param viewHolder A fully initialized helper.
     * @param item       The item that needs to be displayed.
     */
    protected abstract void convert(H viewHolder, T item, int position);

    /**
     * 初始化item view完成，可方便view进行初始化操作
     *
     * @param viewHolder
     */
    protected abstract void onCreateView(H viewHolder, int viewType);

}
