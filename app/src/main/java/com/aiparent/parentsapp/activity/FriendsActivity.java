package com.aiparent.parentsapp.activity;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.aiparent.parentsapp.R;
import com.aiparent.parentsapp.fragment.FriendsFragment;
import com.aiparent.parentsapp.fragment.MsgFragment;

/**
 * Created by weilanzhuan on 2015/5/8.
 */
public class FriendsActivity extends FragmentActivity {
    TextView msg;
    ImageView add_frd_btn;
    private FragmentManager fManager;
    private FragmentTransaction fTransaction;
    RadioGroup TypeRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_friends);

        fManager = getSupportFragmentManager();
        findView();
        changeFragment(0);
    }


    public void findView(){
        add_frd_btn=(ImageView)findViewById(R.id.add_frd_btn);

        TypeRadio=(RadioGroup)findViewById(R.id.TypeRadio);
        TypeRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                changeFragment(group.getCheckedRadioButtonId());
            }
        });
    }

    private   void changeFragment(int viewId){
        fTransaction = fManager.beginTransaction();
        switch (viewId){
            case R.id.radioFriend:
                add_frd_btn.setVisibility(View.VISIBLE);
                fTransaction.replace(R.id.list_content,new FriendsFragment());
                break;

            case R.id.radioMsg:
                add_frd_btn.setVisibility(View.INVISIBLE);
                fTransaction.replace(R.id.list_content,new MsgFragment());
                break;
            case 0:
                add_frd_btn.setVisibility(View.VISIBLE);
                fTransaction.replace(R.id.list_content,new FriendsFragment());
                break;
        }
        fTransaction.commit();
    }

}
