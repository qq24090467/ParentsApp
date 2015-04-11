package com.aiparent.parentsapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aiparent.parentsapp.R;

/**
 * Created by weilanzhuan on 2015/3/31.
 */
public class AboutActivity extends Activity {
    LinearLayout back_btn;
    TextView common_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_soft);

        findView();
        listener();
    }

    public void findView(){
        back_btn=(LinearLayout)findViewById(R.id.back_btn);

        common_title=(TextView)findViewById(R.id.common_title);
        common_title.setText(R.string.aboutapp);


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
