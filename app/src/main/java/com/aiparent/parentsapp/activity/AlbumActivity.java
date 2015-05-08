package com.aiparent.parentsapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aiparent.parentsapp.R;
import com.aiparent.parentsapp.bean.AlbumBean;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by weilanzhuan on 2015/5/7.
 */
public class AlbumActivity extends Activity implements View.OnClickListener{
    private GridView gv;
    private AlbumAdapter adapter;

    private LinearLayout back_btn=null;
    private TextView common_title=null;

    private LayoutInflater inflater;
    private List<AlbumBean> AlbumList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        findView();
    }

    private void findView(){
        gv=(GridView)findViewById(R.id.grid_album);
        back_btn=(LinearLayout)findViewById(R.id.back_btn);
        common_title=(TextView)findViewById(R.id.common_title);

        common_title.setText(R.string.my_album);
        back_btn.setOnClickListener(this);

        AlbumList=new ArrayList<AlbumBean>();
        AlbumList.add(null);

        adapter=new AlbumAdapter();

        gv.setAdapter(adapter);
    }

    private class AlbumAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            if (AlbumList == null) {
                return 0;
            }
            return AlbumList.size();
        }

        @Override
        public Object getItem(int position) {
            return AlbumList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            inflater = LayoutInflater.from(AlbumActivity.this);
            if (position == AlbumList.size() - 1) {
                View addView = inflater.inflate(R.layout.layout_create_album, null);
                return addView;
            } else {
                View dairy_view = inflater.inflate(R.layout.layout_album_item, null);
                return dairy_view;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_btn:
                finish();
                break;
        }
    }
}
