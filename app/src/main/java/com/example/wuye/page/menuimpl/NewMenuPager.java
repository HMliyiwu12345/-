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

    private static final String[] CONTENT = new String[] { "Recent", "Artists", "Albums", "Songs", "Playlists", "Genres" };


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
        View view=View.inflate(mActivity, R.layout.simple_tabs,null);
        FragmentPagerAdapter adapter = new GoogleMusicAdapter(getSupportFragmentManager());
        ViewPager pager = (ViewPager)view.findViewById(R.id.pager);
        pager.setAdapter(adapter);

        TabPageIndicator indicator = (TabPageIndicator)view.findViewById(R.id.indicator);
        indicator.setViewPager(pager);

        return view;
    }
}
