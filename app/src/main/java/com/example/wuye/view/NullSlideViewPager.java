package com.example.wuye.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by WUYE on 2018/10/9.
 */

public class NullSlideViewPager extends ViewPager {
    public NullSlideViewPager(Context context) {
        super(context);
    }

    public NullSlideViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
