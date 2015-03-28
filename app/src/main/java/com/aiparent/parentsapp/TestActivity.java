package com.aiparent.parentsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;


public class TestActivity extends Activity implements View.OnClickListener{
    Button reg_btn,login_btn,zone_btn,profession_btn,guide_btn,main_btn=null;
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

        guide_btn.setOnClickListener(this);
        reg_btn.setOnClickListener(this);
        login_btn.setOnClickListener(this);
        zone_btn.setOnClickListener(this);
        profession_btn.setOnClickListener(this);
        main_btn.setOnClickListener(this);
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
        }
    }
}
