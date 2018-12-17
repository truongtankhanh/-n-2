package com.gmail.khanhit100896.foody.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.khanhit100896.foody.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckSearchFragment extends Fragment {


    public CheckSearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_check_search, container, false);
    }

}
