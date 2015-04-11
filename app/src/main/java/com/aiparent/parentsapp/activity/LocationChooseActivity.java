package com.aiparent.parentsapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aiparent.parentsapp.MyApplication;
import com.aiparent.parentsapp.R;
import com.aiparent.parentsapp.adapter.LocationAdapter;
import com.aiparent.parentsapp.bean.Location;
import com.aiparent.parentsapp.config.HttpsConstant;
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
    List<Location>list=null;
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


    }
    public void listener(){
       location_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Log.v("level=",list.get(position).getLevel()+"");
               getLocationData(list.get(position).getId(),list.get(position).getLevel()+1);
           }
       });
    }
    public void getLocationData(int upid,int level){


        AsyncHttpClient client =new AsyncHttpClient();
        MyApplication myApplication=(MyApplication)getApplicationContext();
        client.setCookieStore(new PersistentCookieStore(myApplication));
        RequestParams params=new RequestParams();
        params.put("upid",upid);
        params.put("level",level);
        client.get(HttpsConstant.GET_LOCATION_URL,params,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String s="";
                try {
                    s=new String(bytes,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Gson gson=new Gson();
                list=gson.fromJson(s, new TypeToken<List<Location>>() {
                }.getType());
                location_list.setAdapter(new LocationAdapter(LocationChooseActivity.this,list));
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });



    }

}
