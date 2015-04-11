package com.aiparent.parentsapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.aiparent.parentsapp.R;
import com.aiparent.parentsapp.adapter.HelpAdapter;
import com.aiparent.parentsapp.bean.HelpBean;
import com.aiparent.parentsapp.impl.UserImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by weilanzhuan on 2015/3/31.
 */
public class HelpActivity extends Activity {
    LinearLayout back_btn;
    TextView common_title;
    private int page=1;
    private List<HelpBean> mListItems;
    private HelpAdapter mAdapter;
    ListView actualListView;



    private PullToRefreshListView mPullToRefreshListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        findView();
        listener();
    }

    public void findView(){
        back_btn=(LinearLayout)findViewById(R.id.back_btn);

        common_title=(TextView)findViewById(R.id.common_title);
        common_title.setText(R.string.helpcenter);

        mPullToRefreshListView=(PullToRefreshListView)findViewById(R.id.pull_to_refresh_listview);
//        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                page++;
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
                getListData();

            }

        });

        actualListView = mPullToRefreshListView.getRefreshableView();

        getListData();

    }



    public void listener(){

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        actualListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(HelpActivity.this,DetailHelpActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString("title",mListItems.get(position-1).getTitle());
                mBundle.putString("content",mListItems.get(position-1).getContent());
                intent.putExtras(mBundle);
                startActivity(intent);
            }
        });
    }

    public void getListData(){

        UserImpl user=new UserImpl();
        user.getHelpProblem(page,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String result="";
                try {
                    result=new String(bytes,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Gson gson=new Gson();
                List<HelpBean> mList= gson.fromJson(result,new TypeToken<LinkedList<HelpBean>>(){
                }.getType());
                if (mListItems==null){
                    mListItems=mList;
                }else {
                    for (int j=0;j<mList.size();j++){
                       mListItems.add(mList.get(j));
                    }
                }

                Log.v("size=",mListItems.size()+"");
                if (mAdapter==null){
                    mAdapter=new HelpAdapter(HelpActivity.this,mListItems);
                }

                actualListView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }

            @Override
            public void onFinish() {
                mPullToRefreshListView.onRefreshComplete();
                mAdapter.notifyDataSetChanged();
            }
        });
    }


}
