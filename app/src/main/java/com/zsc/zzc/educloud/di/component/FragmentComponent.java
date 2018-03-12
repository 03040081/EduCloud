package com.zsc.zzc.educloud.di.component;

import android.app.Activity;

import com.zsc.zzc.educloud.di.module.FragmentModule;
import com.zsc.zzc.educloud.di.scope.FragmentScope;
import com.zsc.zzc.educloud.ui.fragments.ClassificationFragment;
import com.zsc.zzc.educloud.ui.fragments.CommentFragment;
import com.zsc.zzc.educloud.ui.fragments.ForumFragment;
import com.zsc.zzc.educloud.ui.fragments.MineFragment;
import com.zsc.zzc.educloud.ui.fragments.RecommendFragment;
import com.zsc.zzc.educloud.ui.fragments.ScheduleFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();


    void inject(ClassificationFragment dailyFragment);

    void inject(RecommendFragment recommendFragment);

    void inject(MineFragment mineFragment);

    void inject(CommentFragment commentFragment);

    void inject(ScheduleFragment scheduleFragment);

//    void inject(VideoIntroFragment videoIntroFragment);
    void inject(ForumFragment forumFragment);
}
