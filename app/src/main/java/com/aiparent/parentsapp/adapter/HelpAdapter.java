package com.aiparent.parentsapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aiparent.parentsapp.R;
import com.aiparent.parentsapp.bean.HelpBean;
import com.aiparent.parentsapp.bean.Location;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by weilanzhuan on 2015/4/11.
 */
public class HelpAdapter extends BaseAdapter {
    private  Context context=null;
    private List<HelpBean> list=null;
    public HelpAdapter(Context context, List<HelpBean> list){
         this.context=context;
         this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder ;

        if (convertView == null) {

            holder = new ViewHolder();

            LayoutInflater inflater=LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.layou_help_item, null);

            holder.help_title_text = (TextView)convertView.findViewById(R.id.help_title_text);


            convertView.setTag(holder);

        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.help_title_text.setText(list.get(position).getTitle().toString());

        return convertView;
    }

    static class ViewHolder
    {
        TextView help_title_text;
    }
}
