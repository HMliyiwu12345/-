package com.example.wuye.page;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.wuye.bean.NewsMenu;
import com.example.wuye.view.SlideMenu;
import com.example.wuye.zhbj.R;

/**
 * Created by WUYE on 2018/10/10.
 */

public class BasePager {
    public Activity mActivity;
    public TextView mTextView;
    public View RootView;
    public ImageButton mImageButton;
    public SlideMenu mSlideMenu;
    public NewsMenu newsMenu;
    public FrameLayout fl_content;


    public BasePager(Activity activity){
        mActivity=activity;
        RootView=initView();

    }

    public View initView(){
        View view =View.inflate(mActivity, R.layout.base_pager_layout,null);
        mTextView=view.findViewById(R.id.tv_tittle);
        mSlideMenu =mActivity.findViewById(R.id.sm_slidemenu);
        fl_content=view.findViewById(R.id.fl_content);


        mImageButton=(ImageButton)view.findViewById(R.id.ib_tittle);

        return view;
    }

    public void initData(){

    }

    public void destory(){

    }
}
