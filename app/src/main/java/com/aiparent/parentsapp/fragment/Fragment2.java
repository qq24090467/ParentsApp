package com.aiparent.parentsapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aiparent.parentsapp.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment2 extends Fragment implements View.OnClickListener{
    TextView square_text,news_text;
    private FragmentTransaction fTransaction;
    private FragmentManager fManager;
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment2, container,
				false);
        InitData();
        findView(rootView);

		return rootView;
	}
    public  void findView(View view){
        news_text=(TextView)view.findViewById(R.id.news_text);
        square_text=(TextView)view.findViewById(R.id.square_text);

        square_text.setTextColor(getResources().getColor(R.color.per_orange));
        news_text.setTextColor(getResources().getColor(R.color.textcolor_80));

        square_text.setOnClickListener(this);
        news_text.setOnClickListener(this);

    }

    private void InitData(){
        fManager=getChildFragmentManager();
        fTransaction = fManager.beginTransaction();
        fTransaction.replace(R.id.frame_content,new FragmentSquare()).commit();
    }




    @Override
    public void onClick(View v) {
       fTransaction = fManager.beginTransaction();
       switch (v.getId()){
           case R.id.square_text:
               square_text.setTextColor(getResources().getColor(R.color.per_orange));
               square_text.setBackgroundResource(R.drawable.actionbar_tab_selected);
               news_text.setTextColor(getResources().getColor(R.color.textcolor_80));
               news_text.setBackgroundColor(getResources().getColor(R.color.white_text));
               fTransaction.replace(R.id.frame_content,new FragmentSquare()).commit();
               break;
           case R.id.news_text:
               square_text.setTextColor(getResources().getColor(R.color.textcolor_80));
               square_text.setBackgroundColor(getResources().getColor(R.color.white_text));
               news_text.setTextColor(getResources().getColor(R.color.per_orange));
               news_text.setBackgroundResource(R.drawable.actionbar_tab_selected);
               fTransaction.replace(R.id.frame_content,new FragmentAction()).commit();
               break;
       }
    }
}