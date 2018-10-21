package com.example.wuye.page.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.wuye.page.BasePager;

/**
 * Created by WUYE on 2018/10/10.
 */

public class SettingPager extends BasePager {
    public SettingPager(Activity activity) {
        super(activity);
        initData();
    }

    @Override
    public void initData() {

//        TextView textView=new TextView(mActivity);
//        textView.setText("setting");
//        textView.setTextColor(Color.RED);
//        textView.setTextSize(22);
//        textView.setGravity(Gravity.CENTER);

        mTextView.setText("设置中心");
        mImageButton.setVisibility(View.GONE);
    }
}
