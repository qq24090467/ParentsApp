package com.aiparent.parentsapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aiparent.parentsapp.MyApplication;
import com.aiparent.parentsapp.R;
import com.aiparent.parentsapp.config.HttpsConstant;
import com.aiparent.parentsapp.config.SystemConstant;
import com.aiparent.parentsapp.config.UserInfoConfig;
import com.aiparent.parentsapp.impl.UserImpl;
import com.aiparent.parentsapp.utill.JsonUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;

/**
 * Created by weilanzhuan on 2015/3/31.
 */
public class ChangeSexActivity extends Activity implements View.OnClickListener{
    private ImageView save_info=null;
    private LinearLayout back_btn=null;
    private TextView title_text=null;
    private ImageView male_img,female_img=null;
    public int sex_flag=0;
    private static final int SEX_REQUEST_CODE = 8;// 家庭住址
    private SharedPreferences shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sex_change);
        initView();
    }

    public void initView(){

        back_btn=(LinearLayout)findViewById(R.id.back_btn);
        save_info=(ImageView)findViewById(R.id.save_info);

        female_img=(ImageView)findViewById(R.id.female_img);
        male_img=(ImageView)findViewById(R.id.male_img);

        title_text=(TextView)findViewById(R.id.title_text);
        title_text.setText(R.string.sex_title);

        shared=new UserInfoConfig(getApplicationContext()).GetConfig();
        String user_info_json=shared.getString(SystemConstant.DETAIL_INFO,"");
        Log.v("userinfo",user_info_json);
        sex_flag=Integer.parseInt(JsonUtils.getValue(user_info_json,"sex"));

        back_btn.setOnClickListener(this);
        save_info.setOnClickListener(this);
        male_img.setOnClickListener(this);
        female_img.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save_info:
                Intent intent=new Intent();
                intent.putExtra("sex",sex_flag+"");
                setResult(SEX_REQUEST_CODE, intent);
                finish();
                break;
            case R.id.back_btn:
                finish();
                break;
            case R.id.male_img:
                sex_flag=1;
                male_img.setImageResource(R.drawable.personal_male_selected);
                female_img.setImageResource(R.drawable.personal_female_unselected);
                break;
            case R.id.female_img:
                sex_flag=0;
                female_img.setImageResource(R.drawable.personal_female_selected);
                male_img.setImageResource(R.drawable.personal_male_unselected);
                break;
        }
    }


}
