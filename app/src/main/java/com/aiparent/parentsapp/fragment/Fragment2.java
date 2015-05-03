package com.aiparent.parentsapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aiparent.parentsapp.R;

public class Fragment2 extends Fragment implements View.OnClickListener{
    TextView square_text,news_text;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment2, container,
				false);
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




    @Override
    public void onClick(View v) {

       switch (v.getId()){
           case R.id.square_text:
               square_text.setTextColor(getResources().getColor(R.color.per_orange));
               news_text.setTextColor(getResources().getColor(R.color.textcolor_80));
               break;
           case R.id.news_text:
               square_text.setTextColor(getResources().getColor(R.color.textcolor_80));
               news_text.setTextColor(getResources().getColor(R.color.per_orange));
               break;
       }
    }
}