package com.gmail.khanhit100896.foody.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.khanhit100896.foody.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    TextView txtListNhaHang;
    TextView txtListBranch;
    TextView txtListBuffet;
    TextView txtListMonChay;
    TextView txtListAV;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        txtListNhaHang = view.findViewById(R.id.txtDSNhaHang);
        txtListBranch = view.findViewById(R.id.txtListBranch);
        txtListBuffet = view.findViewById(R.id.txtListBuffet);
        txtListMonChay = view.findViewById(R.id.txtListMonChay);
        txtListAV = view.findViewById(R.id.txtListAV);

        txtListNhaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),HomRestaurantActivity.class));
            }
        });

        txtListBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),HomeRestaurantFoodActivity.class));
            }
        });

        txtListBuffet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),HomeFoodBuffetActivity.class));
            }
        });

        txtListMonChay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),HomeFoodChayActivity.class));
            }
        });

        txtListAV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),HomeFoodAVActivity.class));
            }
        });

        return view;
    }
}
