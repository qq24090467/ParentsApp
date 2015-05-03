package com.aiparent.parentsapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aiparent.parentsapp.R;
import com.aiparent.parentsapp.utill.ToastUtil;

/**
 * Created by weilanzhuan on 2015/5/3.
 */
public class EditSelfInfoActivity extends Activity implements View.OnClickListener {
    private TextView info_text;
    private ImageView save_info=null;
    private LinearLayout back_btn=null;
    private TextView title_text=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);
        findView();
    }

    private void findView(){
        info_text=(TextView)findViewById(R.id.info_text);
        back_btn=(LinearLayout)findViewById(R.id.back_btn);
        save_info=(ImageView)findViewById(R.id.save_info);

        title_text=(TextView)findViewById(R.id.title_text);
        title_text.setText(getIntent().getExtras().getString("title"));

        back_btn.setOnClickListener(this);
        save_info.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_btn:
                startActivity(new Intent(EditSelfInfoActivity.this,SelfInfoActivity.class));
                finish();
                break;
            case R.id.save_info:
                String info=info_text.getText().toString();
                if (info.equals("")){
                    ToastUtil.Show(getApplicationContext(),"输入不能为空");
                }else{
                    Intent intent=new Intent();
                    intent.putExtra("info",info_text.getText().toString());
                    setResult(getIntent().getExtras().getInt("request_code"),intent);
                    finish();
                }
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            startActivity(new Intent(EditSelfInfoActivity.this,SelfInfoActivity.class));
            finish();
            return  true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
