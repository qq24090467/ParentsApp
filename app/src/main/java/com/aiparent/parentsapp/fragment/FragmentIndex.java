package com.aiparent.parentsapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.aiparent.parentsapp.R;

import java.util.ArrayList;
import java.util.List;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;


public class FragmentIndex extends Fragment {
    private AutoScrollViewPager viewPager; // android-support-v4中的滑动组件
    private List<ImageView> imageViews; // 滑动的图片集合

    private String[] titles; // 图片标题
    private int[] imageResId; // 图片ID
    private List<View> dots; // 图片标题正文的那些点

    private TextView tv_title;
    private int currentItem = 0; // 当前图片的索引号

    private LinearLayout dot_loaction;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_index, null);
        initData(rootView);
        findView(rootView);
		return rootView;
	}

    private void findView(View view){
        dot_loaction=(LinearLayout)view.findViewById(R.id.dot_loaction);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_title.setText(titles[0]);


        viewPager = (AutoScrollViewPager)view.findViewById(R.id.vp);
        viewPager.setAdapter(new MyAdapter());

        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());

        viewPager.setInterval(2000);
        viewPager.startAutoScroll();
        viewPager.setCurrentItem(0);
    }


    private  void initData(View view){
        imageResId = new int[] { R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e };
        titles = new String[imageResId.length];
        titles[0] = "巩俐不低俗，我就不能低俗";
        titles[1] = "扑树又回来啦！再唱经典老歌引万人大合唱";
        titles[2] = "揭秘北京电影如何升级";
        titles[3] = "乐视网TV版大派送";
        titles[4] = "热血屌丝的反杀";

        imageViews = new ArrayList<ImageView>();

        // 初始化图片资源
        for (int i = 0; i < imageResId.length; i++) {
            ImageView imageView = new ImageView(getActivity().getApplication());
            imageView.setImageResource(imageResId[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageViews.add(imageView);
        }
        dots = new ArrayList<View>();
        dots.add(view.findViewById(R.id.v_dot0));
        dots.add(view.findViewById(R.id.v_dot1));
        dots.add(view.findViewById(R.id.v_dot2));
        dots.add(view.findViewById(R.id.v_dot3));
        dots.add(view.findViewById(R.id.v_dot4));

    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        private int oldPosition = 0;
        @Override
        public void onPageSelected(int position) {
            currentItem = position;
            tv_title.setText(titles[position]);
            dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
            dots.get(position).setBackgroundResource(R.drawable.dot_focused);
            oldPosition = position;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    }

    private class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return imageViews.size();
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViews.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageViews.get(position));
            return imageViews.get(position);
        }
    }


}