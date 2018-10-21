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

public class TablePager extends BasePager {
    public TablePager(Activity activity) {
        super(activity);
        initData();
    }

    @Override
    public void initData() {

        mTextView.setText("首页");
        mImageButton.setVisibility(View.GONE);
    }
}
