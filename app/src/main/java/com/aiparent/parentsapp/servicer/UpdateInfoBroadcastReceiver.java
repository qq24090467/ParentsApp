package com.aiparent.parentsapp.servicer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.aiparent.parentsapp.MyApplication;
import com.aiparent.parentsapp.impl.UserImpl;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;

/**
 * Created by weilanzhuan on 2015/5/3.
 */
public class UpdateInfoBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("接受","更新资料");
        UserImpl user_impl=new UserImpl();
        user_impl.getDetailUserInfo(context);
        UpdataLocation();

    }

    public void UpdataLocation(){
        UserImpl user_impl=new UserImpl();
        RequestParams params= new RequestParams();
        params.put("longitude", MyApplication.getLatitude()+"");
        params.put("latitude",MyApplication.getLongitude()+"");
        user_impl.UpdateInfo(params,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String result="";
                try {
                    result=new String(bytes,"UTF_8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Log.d("位置更详细",result+MyApplication.getLatitude());
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
    }
}
