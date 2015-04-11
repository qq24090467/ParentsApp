package com.aiparent.parentsapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aiparent.parentsapp.R;

/**
 * Created by weilanzhuan on 2015/4/2.
 */
public class SettingActivity extends Activity implements View.OnClickListener{
    LinearLayout about_item,feedback_item,help_item,changepassword_item;
    LinearLayout back_btn;
    TextView common_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        findView();
        listener();
    }

    public  void findView(){

        back_btn=(LinearLayout)findViewById(R.id.back_btn);

        about_item=(LinearLayout)findViewById(R.id.about_item);
        feedback_item=(LinearLayout)findViewById(R.id.feedback_item);
        help_item=(LinearLayout)findViewById(R.id.help_item);
        changepassword_item=(LinearLayout)findViewById(R.id.changepassword_item);

        common_title=(TextView)findViewById(R.id.common_title);
        common_title.setText(R.string.setting_title);
    }

    public void listener(){
        back_btn.setOnClickListener(this);

        about_item.setOnClickListener(this);
        feedback_item.setOnClickListener(this);
        help_item.setOnClickListener(this);
        changepassword_item.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent =new Intent();
        switch (v.getId()){
            case R.id.about_item:
                intent.setClass(SettingActivity.this,AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.feedback_item:
                intent.setClass(SettingActivity.this,FeedbackActivity.class);
                startActivity(intent);
                break;
            case R.id.help_item:
                intent.setClass(SettingActivity.this,HelpActivity.class);
                startActivity(intent);
                break;
            case R.id.changepassword_item:
                intent.setClass(SettingActivity.this,ChangePasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.back_btn:
                finish();
                break;
        }

    }


}
