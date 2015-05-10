package com.aiparent.parentsapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;

import com.aiparent.parentsapp.R;

import com.aiparent.parentsapp.adapter.CategoryAdapter;
import com.aiparent.parentsapp.adapter.HelpAdapter;
import com.aiparent.parentsapp.bean.Category;
import com.aiparent.parentsapp.impl.ArticleImpl;
import com.aiparent.parentsapp.utill.StringUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.util.LinkedList;
import java.util.List;
/**
 * Created by weilanzhuan on 2015/5/8.
 */
public class FragmentSquare extends Fragment{

    List<Category> categoryList;

    private PullToRefreshListView mPullToRefreshListView;
    ListView actualListView;
    CategoryAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_square, container, false);
        findView(rootView);
        return rootView;
    }

    public void findView(View view){

        mPullToRefreshListView=(PullToRefreshListView)view.findViewById(R.id.category_listview);
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                // Update the LastUpdatedLabel
//                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                refreshView.getLoadingLayoutProxy().setRefreshingLabel("正在刷新");
                //设置下拉标签
                refreshView.getLoadingLayoutProxy().setPullLabel("下拉刷新");
                //设置释放标签
                refreshView.getLoadingLayoutProxy().setReleaseLabel("释放开始刷新");
                //设置上一次刷新的提示标签
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel("最后更新时间:" + label);
                // Do work to refresh the list here.
                getCategory();
            }

        });

        actualListView = mPullToRefreshListView.getRefreshableView();
        mPullToRefreshListView.setRefreshing();

        getCategory();
    }

    public void InitData(){


    }

    @Override
    public void onResume() {
        super.onResume();
    }


    public void getCategory(){
        ArticleImpl article=new ArticleImpl();
        article.getArticleCategory(new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String result= StringUtils.BytesToString(bytes);
                Gson gson=new Gson();
                categoryList= gson.fromJson(result,new TypeToken<LinkedList<Category>>(){
                }.getType());
                Log.d("size","size="+categoryList.size());
                mAdapter=new CategoryAdapter(getActivity(),categoryList);
                actualListView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
    }
}
