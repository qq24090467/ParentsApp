package com.aiparent.parentsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aiparent.parentsapp.R;
import com.aiparent.parentsapp.bean.Category;
import com.aiparent.parentsapp.utill.DisplayOptionsUtils;
import com.aiparent.parentsapp.view.CircularImage;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by weilanzhuan on 2015/5/9.
 */
public class CategoryAdapter extends BaseAdapter {
    private Context context;
    private List<Category> categoryList;

    public CategoryAdapter(Context context,List<Category> categoryList){
        this.context=context;
        this.categoryList=categoryList;
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryList.get(position);
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
            LayoutInflater layoutInflater=LayoutInflater.from(context);

            LayoutInflater inflater=LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.layout_category_item, null);

            holder.category_title = (TextView)convertView.findViewById(R.id.category_title);
            holder.category_small_title = (TextView)convertView.findViewById(R.id.category_small_title);
            holder.category_icon=(CircularImage)convertView.findViewById(R.id.category_icon);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.category_title.setText(categoryList.get(position).getCate_name().toString());
        holder.category_small_title.setText(categoryList.get(position).getSmall_title().toString());

        ImageLoader.getInstance().displayImage(categoryList.get(position).getIcon().toString(),holder.category_icon, DisplayOptionsUtils.getDisplayImageOptions());
        return convertView;
    }

    static class ViewHolder{
       TextView category_title,category_small_title;
       CircularImage category_icon;
    }
}
