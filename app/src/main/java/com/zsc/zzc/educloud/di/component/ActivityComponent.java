package com.zsc.zzc.educloud.di.component;

import android.app.Activity;

import com.zsc.zzc.educloud.di.module.ActivityModule;
import com.zsc.zzc.educloud.di.scope.ActivityScope;
import com.zsc.zzc.educloud.ui.activitys.CollectionActivity;
import com.zsc.zzc.educloud.ui.activitys.HistoryActivity;
import com.zsc.zzc.educloud.ui.activitys.LoginActivity;
import com.zsc.zzc.educloud.ui.activitys.SearchActivity;
import com.zsc.zzc.educloud.ui.activitys.VideoInfoActivity;
import com.zsc.zzc.educloud.ui.activitys.VideoListActivity;
import com.zsc.zzc.educloud.ui.activitys.WelcomeActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {
    Activity getActivity();

    void inject(VideoInfoActivity videoInfoActivity);

    void inject(WelcomeActivity welcomeActivity);

    void inject(CollectionActivity collectionActivity);

    void inject(HistoryActivity historyActivity);

    void inject(SearchActivity searchActivity);

    void inject(VideoListActivity videoListActivity);

    void inject(LoginActivity loginActivity);

   /* void inject(RegisterActivity registerActivity);*/

    //void inject(WelfareActivity welfareActivity);

}
