package com.example.wuye.page.menuimpl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.wuye.page.MenuBasePager;

/**
 * Created by WUYE on 2018/10/12.
 */

public class PhoteMenuPager extends MenuBasePager {
    public PhoteMenuPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        TextView view = new TextView(mActivity);
        view.setText("菜单详情页-组图");
        view.setTextColor(Color.RED);
        view.setTextSize(22);
        view.setGravity(Gravity.CENTER);
        return view;

    }
}
