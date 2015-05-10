package com.aiparent.parentsapp.config;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by weilanzhuan on 2015/5/9.
 */
public class LoginInfoConfig {
    Context context;
    SharedPreferences shared;
    public LoginInfoConfig(Context context){
        this.context=context;
        shared=context.getSharedPreferences(SystemConstant.LOGIN_INFO, Context.MODE_PRIVATE);
    }

    public SharedPreferences GetConfig(){
        return shared;
    }
    public void ClearConfig(){
        shared.edit().clear().commit();
    }
}
