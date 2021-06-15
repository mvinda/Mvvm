package com.example.base.mvvm.model;

import java.lang.ref.WeakReference;

/**
 * create by WHD
 * on 2021-03-28
 */
public abstract class BasePagingModel<T> extends SuperBaseModel<T> {

    protected boolean isRefresh = true;
    protected int pageNumber = 0;


    public interface IModelListener<T> extends IBaseModelListener {
        void onLoadFinish(BasePagingModel modelm, T data, boolean isEmpty, boolean isFirstPage, boolean hasNextPage);

        void onLoadFail(BasePagingModel model, String prompt, boolean isFirstPage);
    }


    protected void loadSuccess(T data, final boolean isEmpty, final boolean isFirstPage,
                               final boolean hasNextPage) {
        synchronized (this) {
            mUiHandler.postDelayed(() -> {
                for (WeakReference<IBaseModelListener> weakListener : mWeakListenerArrayList) {
                    if (weakListener.get() instanceof IModelListener) {
                        IModelListener listenerItem = (IModelListener) weakListener.get();
                        if (listenerItem != null) {
                            listenerItem.onLoadFinish(BasePagingModel.this, data, isEmpty, isFirstPage, hasNextPage);
                        }
                    }
                }
                if (getCachedPreferenceKey() != null && isFirstPage) {
                    saveDataToPreference(data);
                }
            }, 0);
        }
    }

    protected void loadFail(final String prompt, final boolean isFirstPage) {
        synchronized (this) {
            mUiHandler.postDelayed(() -> {
                for (WeakReference<IBaseModelListener> weakListener : mWeakListenerArrayList) {
                    if (weakListener.get() instanceof IModelListener) {
                        IModelListener listenerItem = (IModelListener) weakListener.get();
                        if (listenerItem != null) {
                            listenerItem.onLoadFail(BasePagingModel.this, prompt, isFirstPage);
                        }
                    }
                }
            }, 0);
        }
    }


    @Override
    protected void notifyCachedData(T data) {
        loadSuccess(data, false, true, true);
    }


}
