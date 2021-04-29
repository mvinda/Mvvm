package com.example.common.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.example.common.views.picturetitleview.PictureTitleViewViewModel;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class PictureTitleViewBinding extends ViewDataBinding {
  @NonNull
  public final TextView itemFileName;

  @NonNull
  public final ImageView itemImage;

  @Bindable
  protected PictureTitleViewViewModel mViewModel;

  protected PictureTitleViewBinding(Object _bindingComponent, View _root, int _localFieldCount,
      TextView itemFileName, ImageView itemImage) {
    super(_bindingComponent, _root, _localFieldCount);
    this.itemFileName = itemFileName;
    this.itemImage = itemImage;
  }

  public abstract void setViewModel(@Nullable PictureTitleViewViewModel viewModel);

  @Nullable
  public PictureTitleViewViewModel getViewModel() {
    return mViewModel;
  }

  @NonNull
  public static PictureTitleViewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.picture_title_view, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static PictureTitleViewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<PictureTitleViewBinding>inflateInternal(inflater, com.example.common.R.layout.picture_title_view, root, attachToRoot, component);
  }

  @NonNull
  public static PictureTitleViewBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.picture_title_view, null, false, component)
   */
  @NonNull
  @Deprecated
  public static PictureTitleViewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<PictureTitleViewBinding>inflateInternal(inflater, com.example.common.R.layout.picture_title_view, null, false, component);
  }

  public static PictureTitleViewBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static PictureTitleViewBinding bind(@NonNull View view, @Nullable Object component) {
    return (PictureTitleViewBinding)bind(component, view, com.example.common.R.layout.picture_title_view);
  }
}
