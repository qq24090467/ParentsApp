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
import android.widget.ScrollView;
import android.widget.TextView;


import com.aiparent.parentsapp.MyApplication;
import com.aiparent.parentsapp.R;
import com.aiparent.parentsapp.activity.AlbumActivity;
import com.aiparent.parentsapp.activity.DailyActivity;
import com.aiparent.parentsapp.activity.FavorActivity;
import com.aiparent.parentsapp.activity.FriendsActivity;
import com.aiparent.parentsapp.activity.LoginActivity;
import com.aiparent.parentsapp.activity.SelfInfoActivity;
import com.aiparent.parentsapp.activity.SettingActivity;
import com.aiparent.parentsapp.config.SystemConstant;
import com.aiparent.parentsapp.config.UserInfoConfig;
import com.aiparent.parentsapp.utill.DisplayOptionsUtils;
import com.aiparent.parentsapp.utill.JsonUtils;
import com.aiparent.parentsapp.view.LoginDialog;
import com.nostra13.universalimageloader.core.ImageLoader;

public class FragmentProfile extends Fragment implements View.OnClickListener {
    private ImageView profile_setting_img,info_icon;
    private LinearLayout self_info_center,my_favor,diary_center,my_ablums,my_friends;
    private SharedPreferences shared;
    private LoginDialog loginDialog;
    private ScrollView scrollView_islogin;
    private RelativeLayout un_login_rela;
    private TextView tv_to_login;

    private static final  int TO_LOGIN=1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.user_profile, container, false);
        findView(rootView);
        showLoginDialog();
		return rootView;
	}

    private void findView(View view){
        scrollView_islogin=(ScrollView)view.findViewById(R.id.scrollView_islogin);
        un_login_rela=(RelativeLayout)view.findViewById(R.id.un_login_rela);
        tv_to_login=(TextView)view.findViewById(R.id.tv_to_login);

        profile_setting_img=(ImageView)view.findViewById(R.id.profile_setting_img);

        self_info_center=(LinearLayout)view.findViewById(R.id.self_info_center);
        my_favor=(LinearLayout)view.findViewById(R.id.my_favor);
        diary_center=(LinearLayout)view.findViewById(R.id.diary_center);
        my_ablums=(LinearLayout)view.findViewById(R.id.my_ablums);
        my_friends=(LinearLayout)view.findViewById(R.id.my_friends);

        info_icon=(ImageView)view.findViewById(R.id.info_icon);

        self_info_center.setOnClickListener(this);
        my_favor.setOnClickListener(this);
        profile_setting_img.setOnClickListener(this);
        diary_center.setOnClickListener(this);
        my_ablums.setOnClickListener(this);
        my_friends.setOnClickListener(this);
        tv_to_login.setOnClickListener(this);

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
            case R.id.my_friends:
                startActivity(new Intent(getActivity().getApplicationContext(), FriendsActivity.class));
                break;
            case R.id.tv_to_login:
                startActivityForResult(new Intent(getActivity().getApplicationContext(),LoginActivity.class),TO_LOGIN);
                break;
        }
    }

    public void showLoginDialog(){
        if (!MyApplication.isIsLogin()){
            loginDialog=new LoginDialog(getActivity()).Builder();
            loginDialog.show();
            scrollView_islogin.setVisibility(View.GONE);
            un_login_rela.setVisibility(View.VISIBLE);

        }else {
            scrollView_islogin.setVisibility(View.VISIBLE);
            un_login_rela.setVisibility(View.GONE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==TO_LOGIN){
            showLoginDialog();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}