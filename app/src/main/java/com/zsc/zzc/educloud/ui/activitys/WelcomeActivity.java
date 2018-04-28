package com.zsc.zzc.educloud.ui.activitys;

import android.widget.ImageView;

import com.zsc.zzc.educloud.R;
import com.zsc.zzc.educloud.base.BaseMvpActivity;
import com.zsc.zzc.educloud.component.ImageLoader;
import com.zsc.zzc.educloud.model.bean.User;
import com.zsc.zzc.educloud.model.db.RealmHelper;
import com.zsc.zzc.educloud.presenter.WelcomePresenter;
import com.zsc.zzc.educloud.presenter.contract.WelcomeContract;
import com.zsc.zzc.educloud.utils.EventUtil;
import com.zsc.zzc.educloud.utils.StringUtils;

import java.util.List;

import butterknife.BindView;

public class WelcomeActivity extends BaseMvpActivity<WelcomePresenter> implements WelcomeContract.View {

    @BindView(R.id.iv_welcome_bg)
    ImageView ivWelcomeBg;

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        mPresenter.getWelcomeData();
    }

    @Override
    public void showError(String msg) {
        EventUtil.showToast(mContext, msg);
    }

    @Override
    public void showContent(List<String> list) {
        if (list != null) {
            int page = StringUtils.getRandomNumber(0, list.size() - 1);
            ImageLoader.load(mContext, list.get(page), ivWelcomeBg);
            ivWelcomeBg.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).start();
        }

    }

    @Override
    public void jumpToMain() {
        User user= RealmHelper.getInstance().getUserInfo();
        if(user!=null) {
            MainActivity.start(this);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }else {
            LoginActivity.start(mContext,"");
        }

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}