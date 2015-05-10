package com.aiparent.parentsapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.aiparent.parentsapp.R;
import com.aiparent.parentsapp.adapter.CategoryAdapter;
import com.aiparent.parentsapp.bean.Category;
import com.aiparent.parentsapp.impl.ArticleImpl;
import com.aiparent.parentsapp.utill.StringUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.lang.reflect.AccessibleObject;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by weilanzhuan on 2015/5/9.
 */
public class CategoryActivity extends Activity {
    LinearLayout back_btn;
    TextView common_title;
    List<Category> categoryList;
    private PullToRefreshListView mPullToRefreshListView;
    ListView actualListView;
    private MyCategoryAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        findView();
        listener();
    }

    public void findView(){
        back_btn=(LinearLayout)findViewById(R.id.back_btn);

        common_title=(TextView)findViewById(R.id.common_title);
        common_title.setText(R.string.category_selected);

        mPullToRefreshListView=(PullToRefreshListView)findViewById(R.id.category_pullrefresh);
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);


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

    public void getListData(){
        ArticleImpl article=new ArticleImpl();
        article.getArticleCategory(new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String result= StringUtils.BytesToString(bytes);
                Gson gson=new Gson();
                categoryList= gson.fromJson(result,new TypeToken<LinkedList<Category>>(){
                }.getType());
                Log.d("size", "size=" + categoryList.size());
                mAdapter=new MyCategoryAdapter(categoryList);
                actualListView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                mPullToRefreshListView.onRefreshComplete();

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                mPullToRefreshListView.onRefreshComplete();
                mAdapter.notifyDataSetChanged();
            }
        });
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
                Intent intent=new Intent(CategoryActivity.this,DairyEditActivity.class);
                intent.putExtra("cate_id",categoryList.get(position-1).getCate_id());
                startActivity(intent);
            }
        });
    }

    private class MyCategoryAdapter extends BaseAdapter{
        private List<Category> list;
        MyCategoryAdapter(List<Category> list){
            this.list=list;
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater=LayoutInflater.from(getApplicationContext());
            convertView=inflater.inflate(R.layout.layou_cate_item,null);
            TextView cate_title_text=(TextView)convertView.findViewById(R.id.cate_title_text);
            cate_title_text.setText(list.get(position).getCate_name().toString());
            return convertView;
        }
    }

}
