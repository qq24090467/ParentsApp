package com.aiparent.parentsapp.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.aiparent.parentsapp.Interface.UserInterface;
import com.aiparent.parentsapp.config.HttpsConstant;
import com.aiparent.parentsapp.config.SystemConstant;
import com.aiparent.parentsapp.config.UserInfoConfig;
import com.aiparent.parentsapp.utill.AsyncHttpCilentUtil;

import com.aiparent.parentsapp.utill.JsonUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;
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

    /**
     * 获取帮助
     * @param page
     * @param asyncHttpResponseHandler
     */
    @Override
    public void getHelpProblem(int page,AsyncHttpResponseHandler asyncHttpResponseHandler) {
        RequestParams params=new RequestParams();
        params.put("p",page);
        AsyncHttpCilentUtil.getInstance().post(HttpsConstant.PROBLEM_URL,params,asyncHttpResponseHandler);

    }
    /**
     * 注册
     */
    @Override
    public void toRegsinter(RequestParams params, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        AsyncHttpCilentUtil.getInstance().post(HttpsConstant.SIGN_UP_URL,params,asyncHttpResponseHandler);

    }

    /*
     *获取个人详细信息
     */

    @Override
    public void getDetailUserInfo(final Context context) {
        AsyncHttpCilentUtil.getInstance().get(HttpsConstant.DETAIL_USERINFO_URL,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String result="";
                try {
                    result=new String(bytes ,"UTF-8");

                    Log.v("result",JsonUtils.getValue(result,"content")+"==");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                SharedPreferences shared=new UserInfoConfig(context).GetConfig();
                shared.edit().putString(SystemConstant.DETAIL_INFO, JsonUtils.getValue(result,"content")).commit();
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
    }
}
