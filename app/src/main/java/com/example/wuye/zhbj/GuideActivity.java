package com.example.wuye.zhbj;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.example.wuye.util.ConstantUtil;
import com.example.wuye.util.SpUtil;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {
    private android.support.v4.view.ViewPager viewPager;
    private MyPagerAdapter pagerAdapter;
    private List<ImageView> list;
    private int[] resource;
    private Button button_guide;
    private MyOnPageChangeListener myOnPageChangeListener;
    private LinearLayout ll_container;
    private ImageView iv_redpoint;
    private int mPointDis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guide);
        viewPager = findViewById(R.id.vp_guide);
        button_guide = findViewById(R.id.button_guide);
        button_guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                SpUtil.putBoolean(getApplicationContext(), ConstantUtil.ENTERMAIN, false);
            }
        });
        ll_container = findViewById(R.id.ll_container);
        iv_redpoint = findViewById(R.id.iv_redpoint);

        distance();

        buttonVisual();


        configureViewPager();
    }

    /**
     * 只有在最后一页，开启体验的按钮才能可见
     */
    private void buttonVisual() {
        button_guide.setVisibility(View.INVISIBLE);
        myOnPageChangeListener = new MyOnPageChangeListener();
        //设置viewPager监听器
        viewPager.setOnPageChangeListener(myOnPageChangeListener);
    }

    /**
     * 给viewpager设置资源，添加适配器
     */
    private void configureViewPager() {
        resource = new int[]{R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};
        list = new ArrayList<ImageView>();
        for (int i = 0; i < resource.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(resource[i]);
            list.add(imageView);

            //添加小灰点
            ImageView point = new ImageView(this);
            point.setImageResource(R.drawable.gray_point);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            if (i > 0) {
                params.leftMargin = 20;
            }
            point.setLayoutParams(params);
            ll_container.addView(point);

        }
        pagerAdapter = new MyPagerAdapter();

        viewPager.setAdapter(pagerAdapter);
    }

    /**
     * 计算两个小灰点的距离
     */
    private void distance() {
        iv_redpoint.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        // 移除监听,避免重复回调
                        iv_redpoint.getViewTreeObserver()
                                .removeGlobalOnLayoutListener(this);
                        // ivRedPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        // layout方法执行结束的回调
                        mPointDis = ll_container.getChildAt(1).getLeft()
                                - ll_container.getChildAt(0).getLeft();
                        System.out.println("圆点距离:" + mPointDis);
                    }
                });
    }

    class MyPagerAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = list.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv_redpoint.getLayoutParams();
            int leftmargin = (int) (mPointDis * positionOffset) + position * mPointDis;
            params.leftMargin = leftmargin;
            Log.d("tag", String.valueOf(leftmargin));
            iv_redpoint.setLayoutParams(params);
        }

        @Override
        public void onPageSelected(int position) {
            if (position == resource.length - 1) {
                button_guide.setVisibility(View.VISIBLE);
            } else {
                button_guide.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
