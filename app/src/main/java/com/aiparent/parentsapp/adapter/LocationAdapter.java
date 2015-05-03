package com.aiparent.parentsapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.aiparent.parentsapp.R;

import com.aiparent.parentsapp.bean.Region;

import java.util.List;

/**
 * Created by weilanzhuan on 2015/4/3.
 */
public class LocationAdapter extends BaseAdapter {

    private  Context context=null;
    private  List<Region> list=null;

    public LocationAdapter(Context context, List<Region> list){
        this.context=context;
        this.list=list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder ;

        if (convertView == null) {

            holder = new ViewHolder();

            LayoutInflater inflater=LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_localtion, null);

            holder.region_id = (TextView)convertView.findViewById(R.id.region_id);
            holder.region_name = (TextView)convertView.findViewById(R.id.region_name);
            holder.region_upid = (TextView)convertView.findViewById(R.id.region_upid);
            holder.region_level = (TextView)convertView.findViewById(R.id.region_level);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.region_id.setText(list.get(position).getId()+"");
        holder.region_name.setText(list.get(position).getName());
        holder.region_level.setText(list.get(position).getLevel()+"");
        holder.region_upid.setText(list.get(position).getUpid()+"");
        Log.v("id=",list.get(position).getName()+"");

        return convertView;
    }
    static class ViewHolder
    {
        TextView region_id;
        TextView region_name;
        TextView region_upid;
        TextView region_level;
    }
}
