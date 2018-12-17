package com.gmail.khanhit100896.foody.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.khanhit100896.foody.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoadFragment extends Fragment {


    public LoadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_load, container, false);
    }

}
