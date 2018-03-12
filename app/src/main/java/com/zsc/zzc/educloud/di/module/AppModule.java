package com.zsc.zzc.educloud.di.module;


import com.zsc.zzc.educloud.app.App;
import com.zsc.zzc.educloud.model.DataManager;
import com.zsc.zzc.educloud.model.db.DBHelper;
import com.zsc.zzc.educloud.model.db.RealmHelper;
import com.zsc.zzc.educloud.model.http.HttpHelper;
import com.zsc.zzc.educloud.model.http.RetrofitHelper1;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by codeest on 16/8/7.
 */

@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper1 retrofitHelper) {
        return retrofitHelper;
    }

    @Provides
    @Singleton
    DBHelper provideDBHelper(RealmHelper realmHelper) {
        return realmHelper;
    }


    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, DBHelper DBHelper) {
        return new DataManager(httpHelper, DBHelper);
    }
}
