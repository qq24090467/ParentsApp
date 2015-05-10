package com.aiparent.parentsapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.aiparent.parentsapp.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by weilanzhuan on 2015/3/27.
 */
public class ProfessionActivity extends Activity{
    GridView job_gridview;
    JobAdapter jobAdapter;
    List<String> jobList;
    LayoutInflater layoutInflater;
    private LinearLayout back_btn;
    private TextView title_msg,left_msg;
    private int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profession);

        initData();
        initView();
        listener();
    }

    private void initData()
    {
        jobList=new ArrayList<String>();
        jobList.add("程序员");
        jobList.add("技工");
        jobList.add("值");
    }
    private void initView()
    {
        job_gridview=(GridView)findViewById(R.id.job_gridview);
        back_btn=(LinearLayout)findViewById(R.id.back_btn);
        left_msg=(TextView)findViewById(R.id.left_msg);
        title_msg=(TextView)findViewById(R.id.title_msg);

        left_msg.setText(R.string.confirm);
        title_msg.setText(R.string.edit_professor);

        job_gridview.setSelector(R.drawable.subscribe_item_bg);
        jobAdapter=new JobAdapter();
        job_gridview.setAdapter(jobAdapter);
    }

    private void listener(){
        job_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                flag=position;
            }

        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        left_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("job",jobList.get(flag));
                setResult(9,intent);
                finish();
            }
        });
    }

    private class JobAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return jobList.size();
        }

        @Override
        public Object getItem(int position) {
            return jobList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            layoutInflater=LayoutInflater.from(ProfessionActivity.this);
            convertView=layoutInflater.inflate(R.layout.subscribe_category_item,null);
            TextView text_item=(TextView)convertView.findViewById(R.id.text_item);
            text_item.setText(jobList.get(position));
            return convertView;
        }
    }


}
