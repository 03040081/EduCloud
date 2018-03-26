package com.zsc.zzc.educloud.ui.activitys;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zsc.zzc.educloud.R;
import com.zsc.zzc.educloud.base.SwipeBackActivity;
import com.zsc.zzc.educloud.model.bean.User;
import com.zsc.zzc.educloud.presenter.LoginPresenter;
import com.zsc.zzc.educloud.presenter.contract.LoginContract;
import com.zsc.zzc.educloud.utils.ActivityUtils;

import org.simple.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends SwipeBackActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_go)
    Button btLogin;
    @BindView(R.id.cv)
    CardView cardView;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    String userAccount=null;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT);
    }

    @Override
    public void saveContent(User user) {
        //Toast.makeText(mContext,user.getUserName(),Toast.LENGTH_SHORT);
        Log.e("用户：",user.getUserName());
    }
    @Override
    protected void getIntentData() {
        userAccount=getIntent().getStringExtra("userAccount");
    }
    @Override
    public void initView(){
        EventBus.getDefault().register(this);
        if(userAccount!=null&&!userAccount.equals("")){
            etUsername.setText(userAccount);
        }
    }
    @OnClick({R.id.bt_go, R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setExitTransition(null);
                    getWindow().setEnterTransition(null);
                    ActivityOptions options =
                            ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
                    startActivity(new Intent(this, RegisterActivity.class), options.toBundle());
                } else {
                    startActivity(new Intent(this, RegisterActivity.class));
                }
                break;
            case R.id.bt_go:
                /*Explode explode=new Explode();
                explode.setDuration(500);
                getWindow().setExitTransition(explode);
                getWindow().setEnterTransition(explode);*/
                //ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
                //Intent i2 = new Intent(this,LoginSuccessActivity.class);
                //startActivity(i2, oc2.toBundle());
                userAccount= String.valueOf(etUsername.getText());
                String userPass= String.valueOf(etPassword.getText());
                mPresenter.login(userAccount,userPass);
                ActivityUtils.finishAll();
                MainActivity.start(mContext);
                break;
        }
    }
    public static void start(Context context, String userAccount) {
        Intent starter = new Intent(context, LoginActivity.class);
        starter.putExtra("userAccount", userAccount);
        context.startActivity(starter);
    }
}
