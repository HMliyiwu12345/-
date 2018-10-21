package com.example.wuye.zhbj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.example.wuye.bean.WaitEvent;
import com.example.wuye.util.ConstantUtil;
import com.example.wuye.util.SpUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SplashActivity extends AppCompatActivity {

    private RelativeLayout rl_splash;
    private AnimationSet animationSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        EventBus.getDefault().register(this);//注册EventBus事件


        rl_splash = findViewById(R.id.rl_splash);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(3000);//设置动画时间
        rotateAnimation.setFillAfter(true);//设置动画结束状态

        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(3000);//设置动画时间
        scaleAnimation.setFillAfter(true);//设置动画结束状态

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(3000);//设置动画时间
        alphaAnimation.setFillAfter(true);//设置动画结束状态

        //添加动画类到动画集合中
        animationSet = new AnimationSet(true);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);


        towait();//线程休眠3000


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void nextActivity(WaitEvent waitEvent) {
        boolean key= SpUtil.getBoolean(getApplicationContext(), ConstantUtil.ENTERMAIN,true);
        Intent intent = null;
        if (key) {
            intent = new Intent(SplashActivity.this, GuideActivity.class);
            startActivity(intent);

        } else {
            intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        finish();
    }

    private void towait() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    rl_splash.startAnimation(animationSet);//开启动画
                    Thread.sleep(5000);
                    EventBus.getDefault().post(new WaitEvent());

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }
}
