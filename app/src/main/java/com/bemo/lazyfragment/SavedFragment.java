package com.bemo.lazyfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


public class SavedFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_saved, container, false);
        TextView t =view.findViewById(R.id.txt);
        ProgressBar bar =view.findViewById(R.id.progressBar);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                bar.setVisibility(View.INVISIBLE);
                t.setVisibility(View.VISIBLE);
            }
        },2 *1000);
        return view;
    }
}