package com.example.wuye.view;

import android.animation.FloatEvaluator;
import android.app.usage.UsageEvents;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.wuye.util.ColorUtil;

/**
 * Created by WUYE on 2018/10/2.
 */

public class SlideMenu extends FrameLayout {
    private View childAt;
    private View childAt1;
    private ViewDragHelper viewDragHelper;
    private String tag = "MainActivity";
    private int wirth;
    private float wirth1;
    private float wirth2;
    private float percent;
    private FloatEvaluator floatEvaluator;
    public static final int TOUCHMODE_FULLSCREEN = 100;
    public static final int TOUCHMODE_NONE = 200;
    private boolean key=true;


    //private Effect effect;


    public SlideMenu(@NonNull Context context) {
        this(context, null);
    }

    public SlideMenu(@NonNull Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);
        init();

    }

    /**
     * 初始化参数
     */
    private void init() {

        viewDragHelper = ViewDragHelper.create(this, Callback);
        floatEvaluator = new FloatEvaluator();

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //menu
        childAt = getChildAt(0);

        //main
        childAt1 = getChildAt(1);


    }

    /**
     * 记录宽度
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        wirth = getMeasuredWidth();
        wirth1 = wirth * 0.6f;
        wirth2 = wirth * 0.25f;
    }

    /**
     * 处理父视图接收的触摸事件。此方法将调度回调事件。
     * 在返回之前根据需要。父视图的OnTouChEngEnter实现应调用此项。
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    private ViewDragHelper.Callback Callback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return (child == childAt) || (child == childAt1);
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return super.getViewHorizontalDragRange(child);
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if (child == childAt1) {
                if (left > wirth1) {
                    //   return (int) wirth1;
                    left = (int) wirth1;
                } else if (left < 0||key) {


                    //   return 0;
                    left = 0;
                }

            }

            return left;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            if (changedView == childAt) {

                childAt1.layout(left+wirth, 0, left+wirth+wirth, childAt.getBottom());
            }
            if(changedView==childAt1){
                childAt.layout(left-wirth, 0, left, childAt.getBottom());
            }
            if(key){
                childAt.layout(0, 0, childAt.getMeasuredWidth(), childAt.getBottom());
                childAt1.layout(0, 0, childAt1.getMeasuredWidth(), childAt1.getBottom());
            }
//            percent = childAt1.getLeft() * 1f / wirth1;
//            //   Log.d("tag", String.valueOf(percent));
//            if (percent > 0 && percent < 1 || percent == 0 || percent == 1) {
//                transformer(percent);
//            }
            //callback();
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            if (releasedChild == childAt) {
                if (xvel > 0 || xvel == 0) {
                    viewDragHelper.smoothSlideViewTo(childAt1, (int) wirth1, childAt1.getTop());
                    ViewCompat.postInvalidateOnAnimation(SlideMenu.this);
                } else if (xvel < 0) {
                    viewDragHelper.smoothSlideViewTo(childAt1, 0, childAt1.getTop());
                    ViewCompat.postInvalidateOnAnimation(SlideMenu.this);
                }
            }
            if (releasedChild == childAt1) {
                if (xvel > wirth2) {
                   // viewDragHelper.smoothSlideViewTo(childAt,(int) -(0.4f*wirth), childAt1.getTop());
                    viewDragHelper.smoothSlideViewTo(childAt1, (int) wirth1, childAt1.getTop());
                    ViewCompat.postInvalidateOnAnimation(SlideMenu.this);
                } else if (xvel < wirth2) {
                    viewDragHelper.smoothSlideViewTo(childAt1, 0, childAt1.getTop());
                  //  viewDragHelper.smoothSlideViewTo(childAt, 0, childAt1.getTop());
                    ViewCompat.postInvalidateOnAnimation(SlideMenu.this);
                }
            }
        }
    };



    /**
     * 设置main缩放，menu平移
     *
     * @param percent
     */
    private void transformer(float percent) {

       // childAt1.setScaleX((float) (0.8 + 0.2 * (1 - percent)));

      //  childAt.setTranslationX(floatEvaluator.evaluate(percent, -childAt.getMeasuredWidth() / 2, 0));

    }

    @Override
    public void computeScroll() {
        if (viewDragHelper != null) {
            if (viewDragHelper.continueSettling(true)) {
                ViewCompat.postInvalidateOnAnimation(SlideMenu.this);
            }
        }
    }

    public void setEnable(int asd) {
        if (asd == TOUCHMODE_NONE) {
 key=true;
        }
        if(asd==TOUCHMODE_FULLSCREEN){
            key=false;
        }
    }

    public void openMenu(){
        viewDragHelper.smoothSlideViewTo(childAt, (int) wirth1-wirth, childAt1.getTop());
        viewDragHelper.smoothSlideViewTo(childAt1, (int) wirth1, childAt1.getTop());
        ViewCompat.postInvalidateOnAnimation(SlideMenu.this);
    }

    public void closeMenu(){
        viewDragHelper.smoothSlideViewTo(childAt, -wirth, childAt1.getTop());
        viewDragHelper.smoothSlideViewTo(childAt1, 0, childAt1.getTop());
        ViewCompat.postInvalidateOnAnimation(SlideMenu.this);
    }


}
