package com.aiparent.parentsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import org.apache.http.client.CookieStore;

import java.io.UnsupportedEncodingException;


public class LoginActivity extends Activity implements View.OnClickListener {

    private EditText username,password=null;
    private Button to_login_btn=null;
    private LinearLayout back_btn=null;
    private TextView title_msg,left_msg=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        InitView();
    }

    public void InitView(){

        back_btn=(LinearLayout)findViewById(R.id.back_btn);

        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);

        title_msg=(TextView)findViewById(R.id.title_msg);
        left_msg=(TextView)findViewById(R.id.left_msg);

        title_msg.setText(R.string.title_m_activity_login);
        left_msg.setText(R.string.title_activity_regsiter);

        to_login_btn=(Button)findViewById(R.id.to_login_btn);
        to_login_btn.setOnClickListener(this);
        left_msg.setOnClickListener(this);
        back_btn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case  R.id.to_login_btn:
              if (judgeInput())toLogin();
              break;
          case R.id.back_btn:
              finish();
              break;
          case R.id.left_msg:
              Intent intent=new Intent(getApplicationContext(),RegsiterActivity.class);
              startActivity(intent);
              finish();
              break;

      }
    }

    public  void toLogin(){

        AsyncHttpClient client = new AsyncHttpClient();
        //保存登陆信息到cookie
        MyApplication myApplication=(MyApplication)getApplication();
        PersistentCookieStore myCookieStore = new PersistentCookieStore(myApplication);
        client.setCookieStore(myCookieStore);


        RequestParams params=new RequestParams();
        params.put("username",username.getText().toString().trim());
        params.put("password",password.getText().toString().trim());
        client.get(HttpsConstant.LOGIN_URL,params,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String result="";
                try {
                     result=new String(bytes ,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                int status=Integer.parseInt(JsonUtils.getValue(result,"status"));
                if (status>0){//登陆成功
                    Toast.makeText(getApplicationContext(), JsonUtils.getValue(result,"content"),3000).show();
                }else {
                    Toast.makeText(getApplicationContext(), JsonUtils.getValue(result,"content"),3000).show();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
               Toast.makeText(getApplicationContext(),R.string.network_busy,3000);
            }
        }
        );
    }

    public boolean judgeInput(){
        String user_name=username.getText().toString().trim();
        String pass_word=password.getText().toString().trim();

        if (user_name.equals("")){
            Toast.makeText(getApplicationContext(),R.string.username_is_not_empty,3000).show();
            return  false;
        }
        if (pass_word.equals("")){
            Toast.makeText(getApplicationContext(),R.string.password_is_not_empty,3000).show();
            return false;
        }

        return true;
    }
}
