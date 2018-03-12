package com.zsc.zzc.educloud.di.component;


import com.zsc.zzc.educloud.app.App;
import com.zsc.zzc.educloud.di.module.AppModule;
import com.zsc.zzc.educloud.di.module.HttpModule;
import com.zsc.zzc.educloud.model.DataManager;
import com.zsc.zzc.educloud.model.db.RealmHelper;
import com.zsc.zzc.educloud.model.http.RetrofitHelper1;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    App getContext();  // 提供App的Context

    DataManager getDataManager(); //数据中心

    RetrofitHelper1 retrofitHelper();  //提供http的帮助类

    RealmHelper realmHelper();    //提供数据库帮助类

}
