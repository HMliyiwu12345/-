package com.example.wuye.page;

import android.app.Activity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.wuye.bean.NewsMenu;
import com.example.wuye.view.SlideMenu;
import com.example.wuye.zhbj.R;

/**
 * Created by WUYE on 2018/10/12.
 */

public abstract class MenuBasePager {
    public Activity mActivity;
    public View RootView;


    public  MenuBasePager(Activity activity){
        mActivity=activity;
        RootView=initView();
    }

    public abstract View initView();

    public void initData(){

    }

    public void destory(){

    }
}
