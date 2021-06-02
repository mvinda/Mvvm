package com.example.base.mvp.proguard;

/**
 * Created by Henry on 2018/11/5.
 * 保留类名不混淆，属性与方法若无其他声明依旧会被混淆，
 * 如果需要配套保留方法需要组合使用
 * {@link KeepClass 将keep配置到指定的方法}
 */
public @interface KeepClassName {
}
