package com.aiparent.parentsapp.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.SharedPreferences;
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

import com.aiparent.parentsapp.R;
import com.aiparent.parentsapp.adapter.MyFragmentAdapter;
import com.aiparent.parentsapp.config.LoginInfoConfig;
import com.aiparent.parentsapp.config.SystemConstant;
import com.aiparent.parentsapp.fragment.FragmentIndex;
import com.aiparent.parentsapp.fragment.Fragment2;
import com.aiparent.parentsapp.fragment.Fragment3;
import com.aiparent.parentsapp.fragment.FragmentProfile;
import com.aiparent.parentsapp.fragment.Fragment5;
import com.aiparent.parentsapp.impl.UserImpl;
import com.aiparent.parentsapp.utill.NetWorkUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

public class MainActivity extends FragmentActivity  {

    private FragmentTabHost mTabHost;
    private LayoutInflater layoutInflater;
    private Class fragmentArray[] = { FragmentIndex.class, Fragment3.class,
            Fragment2.class, Fragment5.class ,FragmentProfile.class};
    int ViewListIDs[]={R.layout.tab_host_index_item,
            R.layout.tab_host_friends_item,
            R.layout.tab_host_makert_item,
            R.layout.tab_host_nearby_item,R.layout.tab_host_profiles_item};

    private List<Fragment> list = new ArrayList<Fragment>();
    private ViewPager vp;

    private SharedPreferences loginInfoConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initPage();
        listener();
        if (NetWorkUtils.isNetConnection(getApplicationContext())){
            autoLogin();
        }
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
        FragmentIndex fragment1 = new FragmentIndex();
        Fragment2 fragment2 = new Fragment2();
        Fragment3 fragment3 = new Fragment3();
        FragmentProfile fragment4 = new FragmentProfile();
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
    //自动登陆
    private void autoLogin(){
        loginInfoConfig=new LoginInfoConfig(getApplicationContext()).GetConfig();
        String username=loginInfoConfig.getString(SystemConstant.LOGIN_USER,"");
        String password=loginInfoConfig.getString(SystemConstant.LOGIN_PASSWORD,"");
        if (!"".equals(username)&&!"".equals(password)){
            UserImpl user=new UserImpl();
            user.Login(username,password,new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, byte[] bytes) {

                }

                @Override
                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                }
            });
        }
    }


}
