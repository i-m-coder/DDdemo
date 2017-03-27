package com.dddemo.sumanthmadala.androidapp.dddemo.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dddemo.sumanthmadala.androidapp.dddemo.R;
import com.dddemo.sumanthmadala.androidapp.dddemo.ui.activities.DDmainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {

   protected DDmainActivity mainActivity;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof DDmainActivity) {
            mainActivity = (DDmainActivity) context;
        }
    }

    public boolean onBackPressed(){
       return false;
    }

}
