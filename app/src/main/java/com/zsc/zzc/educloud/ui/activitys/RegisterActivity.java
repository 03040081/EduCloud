package com.zsc.zzc.educloud.ui.activitys;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zsc.zzc.educloud.R;
import com.zsc.zzc.educloud.base.SwipeBackActivity;
import com.zsc.zzc.educloud.presenter.RegisterPresenter;
import com.zsc.zzc.educloud.presenter.contract.RegisterContract;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 21191 on 2018/3/1.
 */

public class RegisterActivity extends SwipeBackActivity<RegisterPresenter> implements RegisterContract.View {

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.cv_add)
    CardView cvAdd;
    @BindView(R.id.et_username)
    EditText userName;
    @BindView(R.id.et_password)
    EditText password;
    @BindView(R.id.et_repeatpassword)
    EditText repeatpassword;
    @BindView(R.id.et_confirm)
    EditText confirmtxt;
    @BindView(R.id.et_useraccount)
    EditText userAccount;
    @BindView(R.id.bt_postcomfirm)
    Button btpostconfirm;
    @BindView(R.id.bt_gosignup)
    Button bt_signup;

    @Override
    protected void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ShowEnterAnimation();
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    animateRevealClose();
                } else {
                    RegisterActivity.super.onBackPressed();
                }
            }
        });
        userAccount.setInputType(InputType.TYPE_CLASS_PHONE);

    }

    @Override
    protected void initEvent(){
        userAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                btpostconfirm.setEnabled(Boolean.FALSE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                    btpostconfirm.setEnabled(Boolean.TRUE);
            }
        });

    }


    final CountDownTimer timer=new CountDownTimer(60000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            btpostconfirm.setClickable(false);
            btpostconfirm.setText((millisUntilFinished/1000)+"秒后可重发");
            btpostconfirm.setTextColor(0x000000);
        }

        @Override
        public void onFinish() {
            btpostconfirm.setClickable(true);
            btpostconfirm.setText("重新获取验证码");
            btpostconfirm.setTextColor(0xf0f7f4);
        }
    };



    @OnClick({R.id.bt_gosignup, R.id.bt_postcomfirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_gosignup:
                String username=String.valueOf(userName.getText());
                String useraccount=String.valueOf(userAccount.getText());
                String userPass=String.valueOf(password.getText());
                String userPassConfirm=String.valueOf(repeatpassword.getText());
                String code=String.valueOf(confirmtxt.getText());
                if(userPass.equals(userPassConfirm)){
                    Log.e("参数 ",username+" @ "+useraccount+" @ "+userPass+" @ "+code);
                    mPresenter.register(username,useraccount,userPass,code);

                    //LoginActivity.start(mContext,useraccount);
                }else{
                    Toast.makeText(mContext,"密码不一致",Toast.LENGTH_SHORT);
                }
                break;

            case R.id.bt_postcomfirm:
                String phone=String.valueOf(userAccount.getText());
                mPresenter.confirm(phone);
                timer.start();
                break;

        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void onBackPressed() {
        animateRevealClose();
    }

    @Override
    public void showError(String msg) {
        if(msg!=null&&msg.equals("")){
            Toast.makeText(mContext,"注册失败",Toast.LENGTH_LONG);
        }
    }

    @Override
    public void showResult(String res) {
        Toast.makeText(mContext,res,Toast.LENGTH_LONG);
    }


    private void ShowEnterAnimation() {
        Transition transition = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition);

            getWindow().setSharedElementEnterTransition(transition);

            transition.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {
                    cvAdd.setVisibility(View.GONE);
                }

                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onTransitionEnd(Transition transition) {
                    transition.removeListener(this);
                    animateRevealShow();
                }

                @Override
                public void onTransitionCancel(Transition transition) {

                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }


            });
        }
    }

    public void animateRevealShow() {

        Animator mAnimator = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth() / 2, 0, fab.getWidth() / 2, cvAdd.getHeight());

            mAnimator.setDuration(500);
            mAnimator.setInterpolator(new AccelerateInterpolator());
            mAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    cvAdd.setVisibility(View.VISIBLE);
                    super.onAnimationStart(animation);
                }
            });
            mAnimator.start();
        }
    }

    public void animateRevealClose() {
        Animator mAnimator = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth() / 2, 0, cvAdd.getHeight(), fab.getWidth() / 2);

            mAnimator.setDuration(500);
            mAnimator.setInterpolator(new AccelerateInterpolator());
            mAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    cvAdd.setVisibility(View.INVISIBLE);
                    super.onAnimationEnd(animation);
                    fab.setImageResource(R.drawable.plus);
                    RegisterActivity.super.onBackPressed();
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                }
            });
            mAnimator.start();
        }
    }


}
