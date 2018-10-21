package com.example.wuye.zhbj;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.wuye.fragment.MainFragment;
import com.example.wuye.fragment.SlideFragment;
import com.example.wuye.view.SlideMenu;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_LEFT_MENU = "TAG_LEFT_MENU";
    private static final String TAG_CONTENT = "TAG_CONTENT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();


    }

    private void initFragment() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.slide_menu, new SlideFragment(), TAG_LEFT_MENU);
        transaction.replace(R.id.main_menu, new MainFragment(), TAG_CONTENT);
        transaction.commit();


    }

    public Fragment getMainFragment(){
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        Fragment contentFragment = fm.findFragmentByTag(TAG_CONTENT);
        return contentFragment;
    }


}
