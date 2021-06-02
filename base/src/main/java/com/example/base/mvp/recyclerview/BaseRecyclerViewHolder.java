package com.example.base.mvp.recyclerview;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Henry on 2016/3/13.
 * RecyclerView的基础ViewHolder
 */
public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener, View.OnLongClickListener {

    private final BaseAdapter.OnItemLongClickListener mOnItemLongClickListener;
    private SparseArray<View> views;

    private BaseAdapter.OnItemClickListener mOnItemClickListener;

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    private int viewType;

    public BaseRecyclerViewHolder(View itemView, BaseAdapter.OnItemClickListener onItemClickListener,
                                  BaseAdapter.OnItemLongClickListener onItemLongClickListener) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        this.mOnItemClickListener = onItemClickListener;
        mOnItemLongClickListener = onItemLongClickListener;
        this.views = new SparseArray<View>();
    }

    public TextView getTextView(int viewId) {
        return retrieveView(viewId);
    }

    public Button getButton(int viewId) {
        return retrieveView(viewId);
    }

    public ImageView getImageView(int viewId) {
        return retrieveView(viewId);
    }

    public <T extends View> T getView(int viewId) {
        return retrieveView(viewId);
    }

    public View getConvertView() {
        return itemView;
    }

    protected <T extends View> T retrieveView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, getLayoutPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (mOnItemLongClickListener != null) {
            return mOnItemLongClickListener.onItemLongClick(v, getLayoutPosition());
        }
        return false;
    }

    /**
     * 设置TextView的值
     *
     * @param viewId
     * @param text
     * @return
     */
    public BaseRecyclerViewHolder setText(int viewId, CharSequence text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public BaseRecyclerViewHolder setImageResource(int viewId, int resId) {
        View view = getView(viewId);
        if (view instanceof ImageView) {
            ((ImageView) view).setImageResource(resId);
        } else if (view instanceof TextView) {
            TextView textview = (TextView) view;

            Drawable[] compoundDrawables = textview.getCompoundDrawables();
            for (int i = 0; i < compoundDrawables.length - 1; i++) {
                Drawable compoundDrawable = compoundDrawables[i];
                if (compoundDrawable != null) {
                    Drawable drawable = view.getContext().getResources().getDrawable(resId);
                    Rect bounds = compoundDrawable.getBounds();
                    drawable.setBounds(0, 0, bounds.width(), bounds.height());
                    compoundDrawables[i] = drawable;
                    break;
                }
                if (i == compoundDrawables.length - 1) {//最后一个也为空说明没有设置textview的drawable,所以默认添加左边drawable
                    Drawable drawable = view.getContext().getResources().getDrawable(resId);
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                    compoundDrawables[0] = drawable;
                }
            }

            textview.setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], compoundDrawables[2], compoundDrawables[3]);
        }
        return this;
    }

    public BaseRecyclerViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    public BaseRecyclerViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public BaseRecyclerViewHolder setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public BaseRecyclerViewHolder setBackgroundRes(int viewId, int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public BaseRecyclerViewHolder setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public BaseRecyclerViewHolder setTextColorRes(int viewId, int textColorRes) {
        TextView view = getView(viewId);
        view.setTextColor(textColorRes);
        return this;
    }

    @SuppressLint("NewApi")
    public BaseRecyclerViewHolder setAlpha(int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView(viewId).setAlpha(value);
        } else {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }

    public BaseRecyclerViewHolder setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public BaseRecyclerViewHolder linkify(int viewId) {
        TextView view = getView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    public BaseRecyclerViewHolder setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    public BaseRecyclerViewHolder setProgress(int viewId, int progress) {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    public BaseRecyclerViewHolder setProgress(int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    public BaseRecyclerViewHolder setMax(int viewId, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        return this;
    }

    public BaseRecyclerViewHolder setRating(int viewId, float rating) {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    public BaseRecyclerViewHolder setRating(int viewId, float rating, int max) {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    public BaseRecyclerViewHolder setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    public BaseRecyclerViewHolder setTag(int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    public BaseRecyclerViewHolder setChecked(int viewId, boolean checked) {
        Checkable view = (Checkable) getView(viewId);
        view.setChecked(checked);
        return this;
    }

    /**
     * 给视图添加选中放大的效果
     *
     * @param viewId 视图id， 一般为条目的根布局
     * @param ratioX x方向的缩放比例
     * @param ratioY y方向的缩放比例
     * @return BaseRecyclerViewHolder
     */
    public BaseRecyclerViewHolder setScaleEffect(@IdRes int viewId, final float ratioX, final float ratioY) {
        final View view = getView(viewId);
        // 因为要设置的FocusChangeListener与已有的功能相同，所以没必要再重复设置
        if (view.getOnFocusChangeListener() != null) {
            return this;
        }
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                float scaleX = hasFocus ? ratioX : 1.0f;
                float scaleY = hasFocus ? ratioY : 1.0f;
                if (Build.VERSION.SDK_INT >= 21) {
                    //抬高Z轴
                    ViewCompat.animate(v).scaleX(scaleX).scaleY(scaleY).start();
                } else {
                    ViewCompat.animate(v).scaleX(scaleX).scaleY(scaleY).start();
                    ViewGroup parent = (ViewGroup) v.getParent();
                    parent.requestLayout();
                    parent.invalidate();
                }
            }
        });
        return this;
    }

    /**
     * 关于事件的
     */
    public BaseRecyclerViewHolder setOnClickListener(int viewId,
                                                     View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public BaseRecyclerViewHolder setOnTouchListener(int viewId,
                                                     View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    public BaseRecyclerViewHolder setOnLongClickListener(int viewId,
                                                         View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    /**
     * 释放占用
     */
    public void clear() {
        if (views != null) {
            views.clear();
        }
    }

}
