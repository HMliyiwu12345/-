package com.example.wuye.page.menuimpl;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.wuye.adapter.GoogleMusicAdapter;
import com.example.wuye.page.MenuBasePager;
import com.example.wuye.zhbj.R;
import com.viewpagerindicator.TabPageIndicator;
import com.viewpagerindicator.TitlePageIndicator;

/**
 * Created by WUYE on 2018/10/12.
 */

public class NewMenuPager extends MenuBasePager {


    private ViewPager pager;
    private FragmentPagerAdapter adapter;
    private View view;
    private TabPageIndicator indicator;

    public NewMenuPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
//        TextView view = new TextView(mActivity);
//        view.setText("菜单详情页-新闻");
//        view.setTextColor(Color.RED);
//        view.setTextSize(22);
//        view.setGravity(Gravity.CENTER);
        view = View.inflate(mActivity, R.layout.simple_tabs,null);
        adapter = new GoogleMusicAdapter(getSupportFragmentManager());
        pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(adapter);
        indicator = (TabPageIndicator) view.findViewById(R.id.indicator);
        indicator.setViewPager(pager);
        return view;
    }

    @Override
    public void initData() {

    }
}
