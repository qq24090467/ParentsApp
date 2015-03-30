package com.aiparent.parentsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;


public class RegsiterActivity extends Activity implements View.OnClickListener {
    private EditText username,password,repeat_password=null;
    private Button to_reg_btn=null;
    private LinearLayout back_btn=null;
    private TextView title_msg,left_msg=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regsiter);

        InitView();
    }

    public void InitView(){

        back_btn=(LinearLayout)findViewById(R.id.back_btn);

        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        repeat_password=(EditText)findViewById(R.id.repeat_password);

        title_msg=(TextView)findViewById(R.id.title_msg);
        left_msg=(TextView)findViewById(R.id.left_msg);

        title_msg.setText(R.string.title_m_activity_login);
        left_msg.setText(R.string.title_activity_login);

        to_reg_btn=(Button)findViewById(R.id.to_reg_btn);
        to_reg_btn.setOnClickListener(this);
        left_msg.setOnClickListener(this);
        back_btn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.to_reg_btn:
                if (judgeInput())toLogin();
                break;
            case R.id.back_btn:
                finish();
                break;
            case R.id.left_msg:
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
                break;

        }
    }

    public  void toLogin(){

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params=new RequestParams();
        params.put("username",username.getText().toString().trim());
        params.put("password",password.getText().toString().trim());
        client.get(HttpsConstant.SIGN_UP_URL,params,new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        String result="";
                        try {
                            result=new String(bytes ,"UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        int status=Integer.parseInt(JsonUtils.getValue(result, "status"));
                        if (status>0){//登陆成功
                            Toast.makeText(getApplicationContext(), JsonUtils.getValue(result, "content"), 3000).show();
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
        String confirm_password=repeat_password.getText().toString().trim();

        if (user_name.equals("")){
            Toast.makeText(getApplicationContext(),R.string.username_is_not_empty,3000).show();
            return  false;
        }
        if (pass_word.equals("")){
            Toast.makeText(getApplicationContext(),R.string.password_is_not_empty,3000).show();
            return false;
        }

        if (!confirm_password.equals(pass_word)){
            Toast.makeText(getApplicationContext(),R.string.password_is_not_one,3000).show();
            return false;
        }

        return true;
    }

}
