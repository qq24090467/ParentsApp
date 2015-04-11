package com.aiparent.parentsapp.impl;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.aiparent.parentsapp.Interface.UserInterface;
import com.aiparent.parentsapp.MyApplication;
import com.aiparent.parentsapp.R;
import com.aiparent.parentsapp.config.HttpsConstant;
import com.aiparent.parentsapp.utill.AsyncHttpCilentUtil;
import com.aiparent.parentsapp.utill.JsonUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;

/**
 * Created by weilanzhuan on 2015/4/10.
 */
public class UserImpl implements UserInterface {
    //退出登录
    @Override
    public void LoginOut(AsyncHttpResponseHandler AsyncHttpResponseHandler) {
        AsyncHttpCilentUtil.getInstance().get(HttpsConstant.LOGIN_OUT,null, AsyncHttpResponseHandler);
    }
    //登陆
    @Override
    public void Login(String username,String password,AsyncHttpResponseHandler AsyncHttpResponseHandler) {
        RequestParams params=new RequestParams();
        params.put("username",username);
        params.put("password",password);
        AsyncHttpCilentUtil.getInstance().get(HttpsConstant.LOGIN_URL, params, AsyncHttpResponseHandler);
    }

    /**
     * 更新信息
     * @param params 提交的信息
     * @param AsyncHttpResponseHandler
     */
    @Override
    public void UpdateInfo(RequestParams params, AsyncHttpResponseHandler AsyncHttpResponseHandler) {
        AsyncHttpCilentUtil.getInstance().post(HttpsConstant.UPDATE_INFO_URL, params, AsyncHttpResponseHandler);
    }

    /**
     * 修改密码
     * @param params
     * @param AsyncHttpResponseHandler
     */
    @Override
    public void Updatepassword(RequestParams params, AsyncHttpResponseHandler AsyncHttpResponseHandler) {
        AsyncHttpCilentUtil.getInstance().get(HttpsConstant.PASSWD_CHANGE_URL, params, AsyncHttpResponseHandler);
    }
}
