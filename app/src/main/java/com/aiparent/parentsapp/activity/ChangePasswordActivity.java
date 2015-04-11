package com.aiparent.parentsapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aiparent.parentsapp.MyApplication;
import com.aiparent.parentsapp.R;
import com.aiparent.parentsapp.config.HttpsConstant;
import com.aiparent.parentsapp.impl.UserImpl;
import com.aiparent.parentsapp.utill.JsonUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.apache.http.cookie.Cookie;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by weilanzhuan on 2015/3/30.
 */
public class ChangePasswordActivity extends Activity implements View.OnClickListener {

    private EditText old_password,new_password,repeat_new_password=null;
    private ImageView save_info=null;
    private LinearLayout back_btn=null;
    private TextView title_text=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_passwd);

        initView();
    }

    public void initView(){

        back_btn=(LinearLayout)findViewById(R.id.back_btn);
        save_info=(ImageView)findViewById(R.id.save_info);

        old_password=(EditText)findViewById(R.id.old_password);
        new_password=(EditText)findViewById(R.id.new_password);
        repeat_new_password=(EditText)findViewById(R.id.repeat_new_password);

        title_text=(TextView)findViewById(R.id.title_text);
        title_text.setText(R.string.change_passwd);

        back_btn.setOnClickListener(this);
        save_info.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_btn:
                finish();
                break;
            case R.id.save_info:
                if (isInputRight())changePasswd();
                break;
        }
    }

    public  boolean isInputRight(){
        String old_password_text=old_password.getText().toString().trim();
        String new_password_text=new_password.getText().toString().trim();
        String repeat_new_password_text=repeat_new_password.getText().toString().trim();

        if (old_password_text.equals("")){
            Toast.makeText(getApplicationContext(),R.string.password_is_not_empty,3000).show();
            return false;
        }
        if (new_password_text.equals("")){
            Toast.makeText(getApplicationContext(),"密码不能为空",3000).show();
            return false;
        }
        if (new_password_text.length()<6&&new_password_text.length()>0){
            Toast.makeText(getApplicationContext(),"新密码长度必须大于6",3000).show();
            return false;
        }
        if (!repeat_new_password_text.equals(new_password_text)){
            Toast.makeText(getApplicationContext(),"两次输入的新密码不一致，请重新输入！",3000).show();
            return false;
        }

        return  true;
    }

    public void changePasswd(){

        RequestParams params=new RequestParams();
        params.put("password",old_password.getText().toString());
        params.put("new_password",new_password.getText().toString());

        UserImpl user=new UserImpl();
        user.Updatepassword(params,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String result="";
                try {
                    result=new String(bytes,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                int status=Integer.parseInt(JsonUtils.getValue(result,"status"));
                if (status>0){//修改成功
                    Toast.makeText(getApplicationContext(),JsonUtils.getValue(result,"content"),3000).show();
                }else {
                    Toast.makeText(getApplicationContext(),JsonUtils.getValue(result,"content"),3000).show();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(getApplicationContext(),R.string.network_busy,3000).show();
            }
        });

    }
}
