package com.example.wuye.page.impl;

import android.app.Activity;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.wuye.bean.CloseMenu;
import com.example.wuye.bean.FirstItem;
import com.example.wuye.bean.NewsMenu;
import com.example.wuye.bean.SelectNews;
import com.example.wuye.bean.wuye;
import com.example.wuye.page.BasePager;
import com.example.wuye.page.MenuBasePager;
import com.example.wuye.page.menuimpl.InteractMenuPager;
import com.example.wuye.page.menuimpl.NewMenuPager;
import com.example.wuye.page.menuimpl.PhoteMenuPager;
import com.example.wuye.page.menuimpl.TopicMenuPager;
import com.example.wuye.util.CacheDataUtil;
import com.example.wuye.util.ConstantUtil;
import com.example.wuye.util.SpUtil;
import com.example.wuye.view.SlideMenu;
import com.example.wuye.zhbj.R;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by WUYE on 2018/10/10.
 */

public class NewPager extends BasePager {
    private List<MenuBasePager> list = null;
    private int mPositon;


    public NewPager(Activity activity) {
        super(activity);

    }


    @Override
    public void initData() {
        list = new ArrayList<MenuBasePager>();
        list.add(new NewMenuPager(mActivity));
        list.add(new TopicMenuPager(mActivity));
        list.add(new PhoteMenuPager(mActivity));
        list.add(new InteractMenuPager(mActivity));

        SpUtil.putBoolean(mActivity,ConstantUtil.NEWKEY,true);

        EventBus.getDefault().post(new FirstItem());

        EventBus.getDefault().register(this);
        mTextView.setText("新闻中心");
        int selectMenuItem = SpUtil.getInt(mActivity, ConstantUtil.SELECTMENUITEM, 0);
        fl_content.removeAllViews();
        fl_content.addView(list.get(0).RootView);


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
        mPositon = wuye.value;
        if (list.get(mPositon).RootView != null) {
            fl_content.removeAllViews();
            fl_content.addView(list.get(mPositon).RootView);
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
//                    OkHttpClient client = new OkHttpClient();
//                    Request request = new Request.Builder().url(ConstantUtil.CATEGORY_URL).build();
//                    Response response = client.newCall(request).execute();
//                    Reader reader = response.body().charStream();
//                    String responseData = response.body().string();
                    final Gson gson = new Gson();
                    String cache = CacheDataUtil.getCache(mActivity, ConstantUtil.CATEGORY_URL);
                    if (!TextUtils.isEmpty(cache)) {
                        newsMenu = gson.fromJson(cache, NewsMenu.class);
                    } else {
                        OkHttpUtils.get().url(ConstantUtil.CATEGORY_URL).build().execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.d("newspager", "失败");
                            }
                            @Override
                            public void onResponse(String response, int id) {
                                Log.d("newspager", "成功");
                                // gsonParser(response);
                                CacheDataUtil.setCache(mActivity, ConstantUtil.CATEGORY_URL, response);
                                newsMenu = gson.fromJson(response, NewsMenu.class);
                            }
                        });
                    }
                    EventBus.getDefault().post(new SelectNews(newsMenu));

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
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
