package com.zsc.zzc.educloud.base;


public interface BasePresenter<T extends BaseView> {
    void attachView(T view);

    void detachView();
}
