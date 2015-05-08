package com.aiparent.parentsapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aiparent.parentsapp.R;

public class FragmentFav2 extends Fragment {
	TextView msg;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment5, container, false);
		msg = (TextView) rootView.findViewById(R.id.msg);

		return rootView;
	}

	public void setMsg(String text) {
		msg.setText(text);
	}
}