package com.example.wuye.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wuye.bean.CloseMenu;
import com.example.wuye.bean.NewsMenu;
import com.example.wuye.bean.SelectNews;
import com.example.wuye.bean.wuye;
import com.example.wuye.page.MenuBasePager;
import com.example.wuye.page.impl.NewPager;
import com.example.wuye.page.menuimpl.InteractMenuPager;
import com.example.wuye.page.menuimpl.NewMenuPager;
import com.example.wuye.page.menuimpl.PhoteMenuPager;
import com.example.wuye.page.menuimpl.TopicMenuPager;
import com.example.wuye.zhbj.MainActivity;
import com.example.wuye.zhbj.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by WUYE on 2018/10/9.
 */

public class SlideFragment extends BaseFragment {
    private ListView mListVIew;
    private NewsMenu mNewsMenu;
    private MyAdapter myAdapter;
    private int currentPosition;
    private NewPager newPager;



    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.slide_fragment_layout, null);
        mListVIew = view.findViewById(R.id.lv_menu);
        return view;
    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
        //MenuBasePager的封装
//        listmenus.add(new NewMenuPager(mActivity));
//        listmenus.add(new TopicMenuPager(mActivity));
//        listmenus.add(new PhoteMenuPager(mActivity));
//        listmenus.add(new InteractMenuPager(mActivity));
        mListVIew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentPosition = position;
                newPager=new NewPager(mActivity);

               EventBus.getDefault().post(new wuye(position));
                EventBus.getDefault().post(new CloseMenu());
                myAdapter.notifyDataSetInvalidated();
            }
        });

    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mNewsMenu.data.size();
        }

        @Override
        public NewsMenu.NewsMenuData getItem(int position) {
            return mNewsMenu.data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(mActivity, R.layout.menu_item_layout, null);

            TextView textView = view.findViewById(R.id.tv_menu);
            if (position == currentPosition) {
                textView.setEnabled(true);
            } else {
                textView.setEnabled(false);
            }
            textView.setText(getItem(position).title);
            return view;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void wuye(SelectNews selectNews) {
        if (selectNews.newsMenu != null) {
            Log.d("newspager", "123");
            mNewsMenu = selectNews.newsMenu;
            myAdapter = new MyAdapter();

            mListVIew.setAdapter(myAdapter);
            // myAdapter.notifyDataSetInvalidated();


        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
