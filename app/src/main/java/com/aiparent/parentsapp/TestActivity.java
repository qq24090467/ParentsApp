package com.aiparent.parentsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;


public class TestActivity extends Activity implements View.OnClickListener{
    Button reg_btn,login_btn,zone_btn,profession_btn,
            change_pwd_btn,guide_btn,main_btn,help_btn,about_btn,feedback_btn,
            sex_btn,self_info_btn=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        findView();

    }

    public  void  findView(){
        reg_btn=(Button)findViewById(R.id.reg_btn);
        login_btn=(Button)findViewById(R.id.login_btn);
        zone_btn=(Button)findViewById(R.id.zone_btn);
        profession_btn=(Button)findViewById(R.id.profession_btn);
        guide_btn=(Button)findViewById(R.id.guide_btn);
        main_btn=(Button)findViewById(R.id.main_btn);
        change_pwd_btn=(Button)findViewById(R.id.change_pwd_btn);
        feedback_btn=(Button)findViewById(R.id.feedback_btn);
        sex_btn=(Button)findViewById(R.id.sex_btn);
        self_info_btn=(Button)findViewById(R.id.self_info_btn);

        about_btn=(Button)findViewById(R.id.about_btn);
        help_btn=(Button)findViewById(R.id.help_btn);

        guide_btn.setOnClickListener(this);
        reg_btn.setOnClickListener(this);
        login_btn.setOnClickListener(this);
        zone_btn.setOnClickListener(this);
        profession_btn.setOnClickListener(this);
        main_btn.setOnClickListener(this);
        change_pwd_btn.setOnClickListener(this);
        about_btn.setOnClickListener(this);
        help_btn.setOnClickListener(this);
        feedback_btn.setOnClickListener(this);
        sex_btn.setOnClickListener(this);
        self_info_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reg_btn:
                Intent intent=new Intent(getApplicationContext(),RegsiterActivity.class);
                startActivity(intent);
                break;
            case R.id.login_btn:
                Intent intent2=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent2);
                break;
            case R.id.zone_btn:
                Intent intent3=new Intent(getApplicationContext(),UserProfileActivity.class);
                startActivity(intent3);
                break;
            case R.id.profession_btn:
                Intent intent4=new Intent(getApplicationContext(),ProfessionActivity.class);
                startActivity(intent4);
                break;
            case R.id.guide_btn:
                Intent intent5=new Intent(getApplicationContext(),FirstActivity.class);
                startActivity(intent5);
                break;
            case R.id.main_btn:
                Intent intent6=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent6);
                break;
            case R.id.change_pwd_btn:
                Intent intent7=new Intent(getApplicationContext(),ChangePasswordActivity.class);
                startActivity(intent7);
                break;
            case R.id.about_btn:
                Intent intent8=new Intent(getApplicationContext(),AboutActivity.class);
                startActivity(intent8);
                break;
            case R.id.help_btn:
                Intent intent9=new Intent(getApplicationContext(),HelpActivity.class);
                startActivity(intent9);
                break;
            case R.id.feedback_btn:
                Intent intent10=new Intent(getApplicationContext(),FeedbackActivity.class);
                startActivity(intent10);
                break;
            case R.id.sex_btn:
                Intent intent11=new Intent(getApplicationContext(),ChangeSexActivity.class);
                startActivity(intent11);
                break;
            case R.id.self_info_btn:
                Intent intent12=new Intent(getApplicationContext(),SelfInfoActivity.class);
                startActivity(intent12);
                break;
        }
    }
}
