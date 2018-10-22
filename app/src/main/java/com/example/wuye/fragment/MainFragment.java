package com.example.wuye.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import com.example.wuye.page.BasePager;
import com.example.wuye.page.impl.GovPager;
import com.example.wuye.page.impl.NewPager;
import com.example.wuye.page.impl.ServicePager;
import com.example.wuye.page.impl.SettingPager;
import com.example.wuye.page.impl.TablePager;
import com.example.wuye.util.ConstantUtil;
import com.example.wuye.util.SpUtil;
import com.example.wuye.view.NullSlideViewPager;
import com.example.wuye.view.SlideMenu;
import com.example.wuye.zhbj.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WUYE on 2018/10/9.
 */

public class MainFragment extends BaseFragment {
    private RadioGroup rg;
    private List<BasePager> list = null;
    private NullSlideViewPager mViewPager;
    private SlideMenu mSlideMenu;
    public BasePager mNewsPager;



    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.content_fragment_layout, null);
        rg = (RadioGroup) view.findViewById(R.id.rg_table);
        mViewPager = view.findViewById(R.id.vp_viewpager);
        mSlideMenu = mActivity.findViewById(R.id.sm_slidemenu);
        return view;
    }


    @Override
    public void initData() {


        mNewsPager = new NewPager(mActivity);
//BasePager的封装
        list = new ArrayList<BasePager>();
        list.add(new TablePager(mActivity));
        list.add(mNewsPager);
        list.add(new ServicePager(mActivity));
        list.add(new GovPager(mActivity));
        list.add(new SettingPager(mActivity));


        mViewPager.setAdapter(new MyAdapter());
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0 || position == 4) {
                    mSlideMenu.setEnable(SlideMenu.TOUCHMODE_NONE);
                } else {
                    mSlideMenu.setEnable(SlideMenu.TOUCHMODE_FULLSCREEN);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                SpUtil.putBoolean(mActivity,ConstantUtil.NEWKEY,false);
                switch (checkedId) {
                    case R.id.rb_table:
                        mViewPager.setCurrentItem(0, false);
                        break;
                    case R.id.rb_new:
                        mNewsPager.initData();
                        mViewPager.setCurrentItem(1, false);
                        break;
                    case R.id.rb_service:

                        mViewPager.setCurrentItem(2, false);
                        break;
                    case R.id.rb_gov:

                        mViewPager.setCurrentItem(3, false);
                        break;
                    case R.id.rb_setting:
                        mViewPager.setCurrentItem(4, false);
                        break;
                    default:
                        break;
                }
                if(mViewPager.getCurrentItem()!=1){
                    mNewsPager.destory();
                }
            }
        });


    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {

            View view = list.get(position).RootView;
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }


}
