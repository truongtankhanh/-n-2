package com.gmail.khanhit100896.foody.main;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gmail.khanhit100896.foody.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckGpsFragment extends Fragment {

    protected Button btn_thu_lai;

    public CheckGpsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_check_gps, container, false);

        this.btn_thu_lai = view.findViewById(R.id.btn_thu_lai);

        this.btn_thu_lai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetActivity(getActivity());
            }
        });

        return view;
    }

    private void resetActivity(Activity activity){
        activity.recreate();
    }
}
