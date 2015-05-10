package com.aiparent.parentsapp.Interface;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by weilanzhuan on 2015/5/9.
 */
public interface ArticleInerface {
    public void getArticleCategory(AsyncHttpResponseHandler asyncHttpResponseHandler);
    public  void addArticle(RequestParams params,AsyncHttpResponseHandler asyncHttpResponseHandler);
}
