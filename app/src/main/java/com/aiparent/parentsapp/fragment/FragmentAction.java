package com.aiparent.parentsapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aiparent.parentsapp.R;

/**
 * Created by weilanzhuan on 2015/5/8.
 */
public class FragmentAction extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_action, container, false);

        return rootView;
    }
}
