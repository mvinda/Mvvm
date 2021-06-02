package com.example.base.mvp.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Henry on 2016/3/13.
 * RecyclerView适配器的抽象基类
 */
public abstract class BaseAdapter<T, H extends BaseRecyclerViewHolder> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    protected static final String TAG = BaseAdapter.class.getSimpleName();

    protected final Context context;

    protected int layoutResId;

    protected List<T> datas;

    protected OnItemClickListener mOnItemClickListener = null;

    private OnItemLongClickListener mOnLongClickListener = null;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(View view, int position);
    }

    public BaseAdapter(Context context, int layoutResId) {
        this(context, layoutResId, null);
    }

    public BaseAdapter(Context context, int layoutResId, List<T> datas) {
        this.datas = datas == null ? new ArrayList<T>() : datas;
        this.context = context;
        this.layoutResId = layoutResId;
    }


    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutResId, viewGroup, false);
        BaseRecyclerViewHolder vh = new BaseRecyclerViewHolder(view, mOnItemClickListener, mOnLongClickListener);
        onCreateView(vh, viewType);
        return vh;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder viewHolder, int position) {
        T item = getItem(position);
        convert((H) viewHolder, item, position);
    }

    @Override
    public int getItemCount() {
        if (datas == null || datas.isEmpty())
            return 0;

        return datas.size();
    }

    public T getItem(int position) {
        if (position >= datas.size()) return null;
        return datas.get(position);
    }

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
        if (datas != null) {
            int position = datas.indexOf(t);
            if (position >= 0 && position < datas.size()) {
                datas.remove(position);
                notifyItemRemoved(position);
            }
        }
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void addData(List<T> datas) {
        addData(0, datas);
    }

    public void addData(int position, List<T> list) {
        if (list != null && !list.isEmpty()) {
            for (T t : list) {
                datas.add(position, t);
                notifyItemInserted(position);
            }
        }
    }

    public void refreshData(List<T> list) {
        if (list != null && !list.isEmpty()) {
            clear();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                datas.add(i, list.get(i));
                notifyItemInserted(i);
            }
        }
    }

    public void loadMoreData(List<T> list) {
        if (list != null && !list.isEmpty()) {
            int size = list.size();
            int begin = datas.size();
            for (int i = 0; i < size; i++) {
                datas.add(list.get(i));
                notifyItemInserted(i + begin);
            }
        }
    }

    @Override
    public void onViewAttachedToWindow(BaseRecyclerViewHolder holder) {
        super.onViewAttachedToWindow(holder);
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
     * create view holder
     *
     * @param viewHolder
     * @param viewType
     */
    protected abstract void onCreateView(BaseRecyclerViewHolder viewHolder, int viewType);

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void setOnLongClickListener(OnItemLongClickListener listener) {
        this.mOnLongClickListener = listener;
    }
}
