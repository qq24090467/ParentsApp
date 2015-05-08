package com.aiparent.parentsapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aiparent.parentsapp.R;
import com.aiparent.parentsapp.bean.DailyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weilanzhuan on 2015/5/7.
 */
public class DailyActivity extends Activity implements View.OnClickListener {
    private GridView gv;
    private DailyAdapter adapter;

    private LinearLayout back_btn=null;
    private TextView common_title=null;

    private LayoutInflater inflater;
    private List<DailyBean> DailyList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        findView();
    }

    private void findView(){
        gv=(GridView)findViewById(R.id.grid_diary);
        back_btn=(LinearLayout)findViewById(R.id.back_btn);
        common_title=(TextView)findViewById(R.id.common_title);

        common_title.setText(R.string.my_diary);
        back_btn.setOnClickListener(this);

        DailyList=new ArrayList<DailyBean>();
        DailyList.add(null);

        adapter=new DailyAdapter();

        gv.setAdapter(adapter);
    }

    private class DailyAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            if (DailyList == null) {
                return 0;
            }
            return DailyList.size();
        }

        @Override
        public Object getItem(int position) {
            return DailyList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            inflater = LayoutInflater.from(DailyActivity.this);
            if (position == DailyList.size() - 1) {
                View addView = inflater.inflate(R.layout.layout_add_diary, null);
                addView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(DailyActivity.this,DairyEditActivity.class));
                    }
                });
                return addView;
            } else {
                View dairy_view = inflater.inflate(R.layout.layout_diary_item, null);
                return dairy_view;
            }
        }
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.back_btn:
               finish();
               break;
       }
    }
}
