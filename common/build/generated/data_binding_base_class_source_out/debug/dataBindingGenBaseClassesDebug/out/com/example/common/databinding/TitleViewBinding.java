package com.example.common.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.example.common.views.titleview.TitleViewViewModel;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class TitleViewBinding extends ViewDataBinding {
  @NonNull
  public final TextView itemFileName;

  @Bindable
  protected TitleViewViewModel mViewModel;

  protected TitleViewBinding(Object _bindingComponent, View _root, int _localFieldCount,
      TextView itemFileName) {
    super(_bindingComponent, _root, _localFieldCount);
    this.itemFileName = itemFileName;
  }

  public abstract void setViewModel(@Nullable TitleViewViewModel viewModel);

  @Nullable
  public TitleViewViewModel getViewModel() {
    return mViewModel;
  }

  @NonNull
  public static TitleViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root,
      boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.title_view, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static TitleViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root,
      boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<TitleViewBinding>inflateInternal(inflater, com.example.common.R.layout.title_view, root, attachToRoot, component);
  }

  @NonNull
  public static TitleViewBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.title_view, null, false, component)
   */
  @NonNull
  @Deprecated
  public static TitleViewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<TitleViewBinding>inflateInternal(inflater, com.example.common.R.layout.title_view, null, false, component);
  }

  public static TitleViewBinding bind(@NonNull View view) {
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
  public static TitleViewBinding bind(@NonNull View view, @Nullable Object component) {
    return (TitleViewBinding)bind(component, view, com.example.common.R.layout.title_view);
  }
}
