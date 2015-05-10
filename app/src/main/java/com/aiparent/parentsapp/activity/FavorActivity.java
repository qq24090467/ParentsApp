package com.aiparent.parentsapp.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.aiparent.parentsapp.R;
import com.aiparent.parentsapp.adapter.MyFragmentAdapter;
import com.aiparent.parentsapp.fragment.Fragment2;
import com.aiparent.parentsapp.fragment.Fragment3;
import com.aiparent.parentsapp.fragment.Fragment5;
import com.aiparent.parentsapp.fragment.FragmentFav1;
import com.aiparent.parentsapp.fragment.FragmentFav2;
import com.aiparent.parentsapp.fragment.FragmentFav3;
import com.aiparent.parentsapp.fragment.FragmentIndex;
import com.aiparent.parentsapp.fragment.FragmentProfile;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by weilanzhuan on 2015/5/5.
 */
public class FavorActivity extends FragmentActivity implements View.OnClickListener{
    private ViewPager vp;
    private int[] tabIDs=new int[]{R.id.fav_person_text,
            R.id.fav_activity_text,R.id.fav_article_text};
    private TextView[] TabTitles=new TextView[tabIDs.length];

    private LinearLayout back_btn=null;
    private TextView common_title=null;

    private List<Fragment> list = new ArrayList<Fragment>();
    private int old_clicked_flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_fav);

        findView();
        initPage();
        listener();
    }

    public void findView(){
        back_btn=(LinearLayout)findViewById(R.id.back_btn);
        common_title=(TextView)findViewById(R.id.common_title);

        common_title.setText(R.string.my_favtor);
        back_btn.setOnClickListener(this);

        vp = (ViewPager) findViewById(R.id.fav_pager);

        for (int i=0;i<tabIDs.length;i++){
            TabTitles[i]=(TextView)findViewById(tabIDs[i]);
            TabTitles[i].setOnClickListener(this);
        }

    }


    /**
     * 初始化Fragment
     */
    private void initPage() {
        FragmentFav1 fragment1 = new FragmentFav1();
        FragmentFav2 fragment2 = new FragmentFav2();
        FragmentFav3 fragment3 = new FragmentFav3();

        list.add(fragment1);
        list.add(fragment2);
        list.add(fragment3);

        vp.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(), list));
        vp.setCurrentItem(0);
    }

    public void listener(){
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fav_person_text:
                vp.setCurrentItem(0);
                break;
            case R.id.fav_activity_text:
                vp.setCurrentItem(1);
                break;
            case R.id.fav_article_text:
                vp.setCurrentItem(2);
                break;
            case R.id.back_btn:
                finish();
                break;
        }
    }

    private void tabSelected(int position){
        TabTitles[position].setTextColor(getResources().getColor(R.color.text_click_color));
        TabTitles[position].setBackgroundResource(R.drawable.actionbar_tab_selected);
        TabTitles[old_clicked_flag].setTextColor(getResources().getColor(R.color.textcolor_80));
        TabTitles[old_clicked_flag].setBackgroundColor(getResources().getColor(R.color.white));
        old_clicked_flag=position;
    }
}
