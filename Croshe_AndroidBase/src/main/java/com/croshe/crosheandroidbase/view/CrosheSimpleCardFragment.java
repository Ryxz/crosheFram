package com.croshe.crosheandroidbase.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.croshe.crosheandroidbase.R;


/**
 * Created by niuyongwei on 17/3/3.
 */

@SuppressLint("ValidFragment")
public class CrosheSimpleCardFragment extends Fragment {
    private String mTitle;

    public static CrosheSimpleCardFragment getInstance(String title) {
        CrosheSimpleCardFragment sf = new CrosheSimpleCardFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.croshe_fragment, null);

        return v;
    }
}