package com.aiparent.parentsapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.aiparent.parentsapp.MyApplication;
import com.aiparent.parentsapp.R;
import com.aiparent.parentsapp.adapter.LocationAdapter;
import com.aiparent.parentsapp.bean.Region;
import com.aiparent.parentsapp.config.HttpsConstant;
import com.aiparent.parentsapp.utill.AsyncHttpCilentUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by weilanzhuan on 2015/4/3.
 */
public class LocationChooseActivity extends Activity {
    private ListView location_list=null;
    String result;
    List<Region>list=null;
    String location="";
    private LinearLayout back_btn;
    private TextView common_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_choose_localtion);
        findView();
        listener();
        getLocationData(0,1);
    }

    public  void findView(){
        location_list=(ListView)findViewById(R.id.location_list);
        back_btn=(LinearLayout)findViewById(R.id.back_btn);
        common_title=(TextView)findViewById(R.id.common_title);

        common_title.setText(getIntent().getExtras().getString("title"));
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LocationChooseActivity.this,SelfInfoActivity.class));
                finish();
            }
        });
    }
    public void listener(){
       location_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               location+=list.get(position).getId()+",";
               getLocationData(list.get(position).getId(),list.get(position).getLevel()+1);
           }
       });
    }
    public void getLocationData(int upid,int level){

        RequestParams params=new RequestParams();
        params.put("upid",upid);
        params.put("level",level);
        AsyncHttpCilentUtil.getInstance().get(HttpsConstant.GET_LOCATION_URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String s = "";
                try {
                    s = new String(bytes, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Gson gson = new Gson();
                list = gson.fromJson(s, new TypeToken<List<Region>>() {
                }.getType());
                if (list.size() > 0) {
                    location_list.setAdapter(new LocationAdapter(LocationChooseActivity.this, list));
                } else {
//                    startActivity(new Intent(LocationChooseActivity.this,TestActivity.class));
                    Intent intent = new Intent();
                    intent.putExtra("location", location);
                    setResult(getIntent().getExtras().getInt("request_code"), intent);
                    finish();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });



    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            startActivity(new Intent(LocationChooseActivity.this,SelfInfoActivity.class));
            finish();
            return  true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
