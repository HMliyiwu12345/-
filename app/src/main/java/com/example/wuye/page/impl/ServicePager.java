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

public class ServicePager extends BasePager {
    public ServicePager(Activity activity) {
        super(activity);
        initData();
    }

    @Override
    public void initData() {
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSlideMenu.openMenu();

            }
        });

        mTextView.setText("智慧服务");
    }
}
