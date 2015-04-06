package com.aiparent.parentsapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.aiparent.parentsapp.R;

public class Fragment3 extends Fragment {
	TextView msg;
    private FragmentManager fManager;
    private FragmentTransaction fTransaction;
    RadioGroup TypeRadio;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment3, container, false);


        fManager = getChildFragmentManager();
        findView(rootView);
        changeFragment(0);
		return rootView;
	}

    public void findView(View view){
        TypeRadio=(RadioGroup)view.findViewById(R.id.TypeRadio);
        TypeRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                changeFragment(group.getCheckedRadioButtonId());
            }
        });
    }

    public  void changeFragment(int viewId){
        fTransaction = fManager.beginTransaction();
        switch (viewId){
            case R.id.radioFriend:
                fTransaction.replace(R.id.list_content,new FriendsFragment());
                break;

            case R.id.radioMsg:
                fTransaction.replace(R.id.list_content,new MsgFragment());
                break;
            case 0:
                fTransaction.replace(R.id.list_content,new FriendsFragment());
                break;
        }
        fTransaction.commit();
    }


}