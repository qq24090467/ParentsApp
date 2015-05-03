package com.aiparent.parentsapp.Interface;

import android.app.Activity;
import android.content.Context;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by weilanzhuan on 2015/4/10.
 */
public interface UserInterface {
    public void LoginOut(AsyncHttpResponseHandler AsyncHttpResponseHandler);
    public void Login(String username,String password,AsyncHttpResponseHandler AsyncHttpResponseHandler);
    public void UpdateInfo(RequestParams params,AsyncHttpResponseHandler AsyncHttpResponseHandler);
    public void Updatepassword(RequestParams params,AsyncHttpResponseHandler AsyncHttpResponseHandler);
    public void getHelpProblem(int page,AsyncHttpResponseHandler asyncHttpResponseHandler);
    public void toRegsinter(RequestParams params,AsyncHttpResponseHandler AsyncHttpResponseHandler);
    public void getDetailUserInfo(Context context);//获取个人详细信息
}
