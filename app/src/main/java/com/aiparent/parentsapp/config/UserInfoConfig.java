package com.aiparent.parentsapp.config;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by weilanzhuan on 2015/5/3.
 */
public class UserInfoConfig {
    Context context;
    SharedPreferences shared;
    public UserInfoConfig(Context context){
        this.context=context;
        shared=context.getSharedPreferences(SystemConstant.USER_INFO, Context.MODE_PRIVATE);
    }

    public SharedPreferences GetConfig(){
        return shared;
    }
    public void ClearConfig(){
        shared.edit().clear().commit();
    }
}
