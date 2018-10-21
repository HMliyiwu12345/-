package com.example.wuye.page.impl;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.wuye.bean.CloseMenu;
import com.example.wuye.bean.NewsMenu;
import com.example.wuye.bean.SelectNews;
import com.example.wuye.bean.wuye;
import com.example.wuye.page.BasePager;
import com.example.wuye.page.MenuBasePager;
import com.example.wuye.page.menuimpl.InteractMenuPager;
import com.example.wuye.page.menuimpl.NewMenuPager;
import com.example.wuye.page.menuimpl.PhoteMenuPager;
import com.example.wuye.page.menuimpl.TopicMenuPager;
import com.example.wuye.view.SlideMenu;
import com.example.wuye.zhbj.R;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by WUYE on 2018/10/10.
 */

public class NewPager extends BasePager {
    private List<MenuBasePager>  list=null;


    public NewPager(Activity activity) {
        super(activity);

    }



    @Override
    public void initData() {
        list=new ArrayList<MenuBasePager>();
        list.add( new NewMenuPager(mActivity));
        list.add( new TopicMenuPager(mActivity));
        list.add( new PhoteMenuPager(mActivity));
        list.add( new InteractMenuPager(mActivity));


        EventBus.getDefault().register(this);
        mTextView.setText("新闻中心");
        fl_content.addView( list.get(0).RootView);


        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSlideMenu.openMenu();


            }
        });

        getNetWork();

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setMenu(wuye wuye) {
        int positon=wuye.value;
        if (list.get(positon).RootView != null) {
            fl_content.removeAllViews();
            fl_content.addView(list.get(positon).RootView);
        }
    }

    /**
     * 请求网络数据
     */
    private void getNetWork() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("http://192.168.43.100:8080/zhbj/categories.json").build();
                    Response response = client.newCall(request).execute();
                    Reader reader = response.body().charStream();


                    gsonParser(reader);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).

                start();
    }

    /**
     * 解析json数据
     *
     * @param reader
     */
    public void gsonParser(Reader reader) {
        Gson gson = new Gson();
        newsMenu = gson.fromJson(reader, NewsMenu.class);
        Log.d("newspager", newsMenu.toString());
        EventBus.getDefault().post(new SelectNews(newsMenu));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void closedMenu(CloseMenu closeMenu) {
        mSlideMenu.closeMenu();
    }

    @Override
    public void destory() {
        EventBus.getDefault().unregister(this);

    }
}
