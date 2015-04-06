package com.aiparent.parentsapp;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost.TabSpec;
import android.widget.TabHost.OnTabChangeListener;

import com.aiparent.parentsapp.adapter.MyFragmentAdapter;
import com.aiparent.parentsapp.fragment.Fragment1;
import com.aiparent.parentsapp.fragment.Fragment2;
import com.aiparent.parentsapp.fragment.Fragment3;
import com.aiparent.parentsapp.fragment.Fragment4;
import com.aiparent.parentsapp.fragment.Fragment5;

public class MainActivity extends FragmentActivity  {

    private FragmentTabHost mTabHost;
    private LayoutInflater layoutInflater;
    private Class fragmentArray[] = { Fragment1.class, Fragment3.class,
            Fragment2.class, Fragment5.class ,Fragment4.class};
    int ViewListIDs[]={R.layout.tab_host_index_item,
            R.layout.tab_host_friends_item,
            R.layout.tab_host_makert_item,
            R.layout.tab_host_nearby_item,R.layout.tab_host_profiles_item};

    private List<Fragment> list = new ArrayList<Fragment>();
    private ViewPager vp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initPage();
        listener();
    }

    /**
     * 控件初始化
     */
    private void initView() {
        vp = (ViewPager) findViewById(R.id.pager);

        layoutInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.pager);




        for (int i = 0; i < ViewListIDs.length; i++) {
            TabSpec tabSpec = mTabHost.newTabSpec(i+"")
                    .setIndicator(getTabItemView(i));
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            mTabHost.setTag(i);
        }

    }

    /**
     * 初始化Fragment
     */
    private void initPage() {
        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
        Fragment3 fragment3 = new Fragment3();
        Fragment4 fragment4 = new Fragment4();
        Fragment5 fragment5 = new Fragment5();
        list.add(fragment1);
        list.add(fragment3);
        list.add(fragment2);
        list.add(fragment5);
        list.add(fragment4);
        vp.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(), list));
        vp.setCurrentItem(0);
    }

    private View getTabItemView(int i) {

        View view = layoutInflater.inflate(ViewListIDs[i], null);
        return view;
    }

    public void listener(){
        vp.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabHost.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mTabHost.setOnTabChangedListener(new OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                int position=mTabHost.getCurrentTab();
                vp.setCurrentItem(position);
            }
        });
    }



}
