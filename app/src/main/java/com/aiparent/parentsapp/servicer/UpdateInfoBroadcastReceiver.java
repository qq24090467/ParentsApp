package com.aiparent.parentsapp.servicer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.aiparent.parentsapp.impl.UserImpl;

/**
 * Created by weilanzhuan on 2015/5/3.
 */
public class UpdateInfoBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("接受","更新资料");
        UserImpl user_impl=new UserImpl();
        user_impl.getDetailUserInfo(context);
    }
}
