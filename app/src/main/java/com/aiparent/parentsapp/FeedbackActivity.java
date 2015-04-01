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
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;

/**
 * Created by weilanzhuan on 2015/3/31.
 */
public class FeedbackActivity extends Activity  implements View.OnClickListener{
    private ImageView save_info=null;
    private LinearLayout back_btn=null;
    private TextView title_text=null;
    private EditText feedback_edit,contact=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        initView();
    }

    public void initView(){

        back_btn=(LinearLayout)findViewById(R.id.back_btn);
        save_info=(ImageView)findViewById(R.id.save_info);

        feedback_edit=(EditText)findViewById(R.id.feedback_edit);
        contact=(EditText)findViewById(R.id.contact);

        title_text=(TextView)findViewById(R.id.title_text);
        title_text.setText(R.string.feedback_title);

        back_btn.setOnClickListener(this);
        save_info.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save_info:
                if (feedback_edit.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"反馈内容能为空！",3000).show();
                }else {
                    toSubmitFeedback();
                }
                break;
            case R.id.back_btn:
                finish();
                break;
        }
    }

    public void toSubmitFeedback(){
        AsyncHttpClient client =new AsyncHttpClient();
        RequestParams params=new RequestParams();
        params.put("content",feedback_edit.getText().toString().trim());
        params.put("contact",contact.getText().toString().trim());

        client.post(HttpsConstant.FEEDBACK_URL,params,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String result="";
                try {
                    result=new String(bytes,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                int status= Integer.parseInt(JsonUtils.getValue(result,"status"));
                if (status>0){//提交成功
                    Toast.makeText(getApplicationContext(),JsonUtils.getValue(result,"content"),3000).show();
                }else {
                    Toast.makeText(getApplicationContext(),JsonUtils.getValue(result,"content"),3000).show();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(getApplicationContext(),"网络繁忙，请稍后重试！",3000).show();

            }
        });
    }
}
