package com.zsc.zzc.educloud.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.zsc.zzc.educloud.R;
import com.zsc.zzc.educloud.app.App;
import com.zsc.zzc.educloud.utils.KL;
import com.zsc.zzc.educloud.utils.ScreenUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Description: Activity基类
 * Creator: yxc
 * date: 17/9/14
 */
public abstract class BaseActivity extends SupportActivity {

    protected Activity mContext;
    private Unbinder mUnBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KL.d(this.getClass(), this.getClass().getName() + "------>onCreate");
        init();

        setContentView(getLayout());
        getIntentData();
        mContext = this;
        mUnBinder = ButterKnife.bind(this);
        initView();
        initEvent();
    }
    /*
    *******************错误根源**************************** onPreCreate();
     */
    protected void init() {
        setTranslucentStatus(true);
        setTheme(R.style.BlackTheme);
        App.getInstance().registerActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        KL.d(this.getClass(), this.getClass().getName() + "------>onStart");
        setTitleHeight(getRootView(this));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        KL.d(this.getClass(), this.getClass().getName() + "------>onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        KL.d(this.getClass(), this.getClass().getName() + "------>onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        KL.d(this.getClass(), this.getClass().getName() + "------>onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        KL.d(this.getClass(), this.getClass().getName() + "------>onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KL.d(this.getClass(), this.getClass().getName() + "------>onDestroy");
        if (mUnBinder != null)
            mUnBinder.unbind();
        App.getInstance().unregisterActivity(this);
    }

    /**
     * 设置沉浸式
     *
     * @param on
     */
    protected void setTranslucentStatus(boolean on) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }
    }

    private void setTitleHeight(View view) {
        if (view != null) {
            RelativeLayout title = (RelativeLayout) view.findViewById(R.id.title);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                if (title != null) {
                    ViewGroup.LayoutParams lp = title.getLayoutParams();
                    lp.height = ScreenUtil.dip2px(this, 48);
                    title.setLayoutParams(lp);
                    title.setPadding(0, 0, 0, 0);
                }
            }
        }
    }

    protected static View getRootView(Activity context) {
        return ((ViewGroup) context.findViewById(android.R.id.content)).getChildAt(0);
    }

    protected abstract int getLayout();

    protected void initView() {
    }

    protected void initEvent() {
    }

    protected void getIntentData() {
    }
}
