package com.aiparent.parentsapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.aiparent.parentsapp.R;
import com.aiparent.parentsapp.adapter.ImagePagerAdapter;
import com.aiparent.parentsapp.view.AutoScrollView.AutoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

public class FragmentIndex extends Fragment {

    private AutoScrollViewPager viewPager;
    private List<Integer> imageIdList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_index, null);
        findView(rootView);
		return rootView;
	}

    private void findView(View view){
        viewPager = (AutoScrollViewPager)view.findViewById(R.id.auto_view_pager);
        imageIdList = new ArrayList<Integer>();
        imageIdList.add(R.drawable.test1);
        imageIdList.add(R.drawable.test2);
        imageIdList.add(R.drawable.test3);
        imageIdList.add(R.drawable.test4);
        viewPager.setAdapter(new ImagePagerAdapter(getActivity().getApplicationContext(), imageIdList).setInfiniteLoop(true));
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());

        viewPager.setInterval(2000);
        viewPager.startAutoScroll();
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % imageIdList.size());
    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        @Override
        public void onPageScrollStateChanged(int arg0) {}
    }

    @Override
    public void onPause() {
        super.onPause();
        viewPager.stopAutoScroll();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewPager.startAutoScroll();
    }

}