package com.aiparent.parentsapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aiparent.parentsapp.R;

/**
 * Created by weilanzhuan on 2015/4/6.
 */
public class FriendsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.friend_new_header, container, false);

        return rootView;
    }
}
