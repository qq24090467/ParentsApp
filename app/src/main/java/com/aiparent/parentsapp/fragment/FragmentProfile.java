package com.aiparent.parentsapp.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.aiparent.parentsapp.R;
import com.aiparent.parentsapp.activity.AlbumActivity;
import com.aiparent.parentsapp.activity.DailyActivity;
import com.aiparent.parentsapp.activity.FavorActivity;
import com.aiparent.parentsapp.activity.SelfInfoActivity;
import com.aiparent.parentsapp.activity.SettingActivity;
import com.aiparent.parentsapp.config.SystemConstant;
import com.aiparent.parentsapp.config.UserInfoConfig;
import com.aiparent.parentsapp.utill.DisplayOptionsUtils;
import com.aiparent.parentsapp.utill.JsonUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

public class FragmentProfile extends Fragment implements View.OnClickListener {
    private ImageView profile_setting_img,info_icon;
    private LinearLayout self_info_center,my_favor,diary_center,my_ablums;
    private SharedPreferences shared;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.user_profile, container, false);
        findView(rootView);
		return rootView;
	}

    private void findView(View view){
        profile_setting_img=(ImageView)view.findViewById(R.id.profile_setting_img);

        self_info_center=(LinearLayout)view.findViewById(R.id.self_info_center);
        my_favor=(LinearLayout)view.findViewById(R.id.my_favor);
        diary_center=(LinearLayout)view.findViewById(R.id.diary_center);
        my_ablums=(LinearLayout)view.findViewById(R.id.my_ablums);

        info_icon=(ImageView)view.findViewById(R.id.info_icon);

        self_info_center.setOnClickListener(this);
        my_favor.setOnClickListener(this);
        profile_setting_img.setOnClickListener(this);
        diary_center.setOnClickListener(this);
        my_ablums.setOnClickListener(this);

        shared= new UserInfoConfig(getActivity().getApplicationContext()).GetConfig();
        String user_info_json=shared.getString(SystemConstant.DETAIL_INFO,"");
        ImageLoader.getInstance().displayImage(JsonUtils.getValue(user_info_json, "photos"),info_icon, DisplayOptionsUtils.getDisplayImageOptions());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_favor:
                startActivity(new Intent(getActivity().getApplicationContext(), FavorActivity.class));
                break;
            case R.id.self_info_center:
                startActivity(new Intent(getActivity().getApplicationContext(), SelfInfoActivity.class));
                break;
            case R.id.profile_setting_img:
                startActivity(new Intent(getActivity().getApplicationContext(), SettingActivity.class));
                break;
            case R.id.diary_center:
                startActivity(new Intent(getActivity().getApplicationContext(), DailyActivity.class));
                break;
            case R.id.my_ablums:
                startActivity(new Intent(getActivity().getApplicationContext(), AlbumActivity.class));
                break;
        }
    }
}