package com.zsc.zzc.educloud.base;

import android.os.Bundle;

import com.zsc.zzc.educloud.app.App;
import com.zsc.zzc.educloud.di.component.ActivityComponent;
import com.zsc.zzc.educloud.di.component.DaggerActivityComponent;
import com.zsc.zzc.educloud.di.module.ActivityModule;

import javax.inject.Inject;


public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements BaseView {

    @Inject
    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    protected abstract void initInject();

}
