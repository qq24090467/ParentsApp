package com.aiparent.parentsapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aiparent.parentsapp.R;

/**
 * Created by weilanzhuan on 2015/4/11.
 */
public class DetailHelpActivity extends Activity {
    LinearLayout back_btn;
    TextView common_title;
    TextView detai_help_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detail_help);

        findView();
        listener();
    }

    public void findView(){

        Bundle bundle=getIntent().getExtras();
        back_btn=(LinearLayout)findViewById(R.id.back_btn);

        common_title=(TextView)findViewById(R.id.common_title);
        detai_help_text=(TextView)findViewById(R.id.detai_help_text);
        common_title.setText(bundle.getString("title"));
        detai_help_text.setText(bundle.getString("content"));


    }

    public void listener(){
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
