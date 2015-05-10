package com.aiparent.parentsapp.impl;

import com.aiparent.parentsapp.Interface.ArticleInerface;
import com.aiparent.parentsapp.config.HttpsConstant;
import com.aiparent.parentsapp.utill.AsyncHttpCilentUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by weilanzhuan on 2015/5/9.
 */
public class ArticleImpl implements ArticleInerface {
    @Override
    public void getArticleCategory(AsyncHttpResponseHandler asyncHttpResponseHandler) {
        AsyncHttpCilentUtil.getInstance().get(HttpsConstant.ARTICLE_CATEGORY,null,asyncHttpResponseHandler);
    }

    @Override
    public void addArticle(RequestParams params, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        AsyncHttpCilentUtil.getInstance().post(HttpsConstant.ARTICLE_ADD,params,asyncHttpResponseHandler);

    }
}
