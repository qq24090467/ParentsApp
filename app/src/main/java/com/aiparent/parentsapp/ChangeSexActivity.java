package com.aiparent.parentsapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aiparent.parentsapp.config.HttpsConstant;
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

        back_btn.setOnClickListener(this);
        save_info.setOnClickListener(this);
        male_img.setOnClickListener(this);
        female_img.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save_info:
                toSubmitFeedback();
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

    public void toSubmitFeedback(){
        AsyncHttpClient client =new AsyncHttpClient();

        MyApplication myApplication=(MyApplication)getApplicationContext();
        client.setCookieStore(new PersistentCookieStore(myApplication));

        RequestParams params=new RequestParams();
        params.put("sex",sex_flag+"");

        client.post(HttpsConstant.UPDATE_INFO_URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String result = "";
                try {
                    result = new String(bytes, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                int status = Integer.parseInt(JsonUtils.getValue(result, "status"));
                if (status > 0) {//提交成功
                    Toast.makeText(getApplicationContext(), JsonUtils.getValue(result, "content"), 3000).show();
                } else {
                    Toast.makeText(getApplicationContext(), JsonUtils.getValue(result, "content"), 3000).show();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(getApplicationContext(), "网络繁忙，请稍后重试！"+i, 3000).show();

            }
        });
    }
}
