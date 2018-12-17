package com.gmail.khanhit100896.foody.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.gmail.khanhit100896.foody.R;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    /*
     * Khai báo biến cần thiết
     */
    protected ScrollView scrollview;
    protected TextView txtListNear;
    protected TextView txtListNhaHang;
    protected TextView txtListBranch;
    protected TextView txtListBuffet;
    protected TextView txtListMonChay;
    protected TextView txtListAV;
    /*
     */

    /*
     * Hàm Constructor
     */
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Gọi hàm ánh xạ
        init(view);

        // Gọi hàm bắt sự kiện cho các TextView
        action();

        return view;
    }

    /*
     * Hàm ánh xạ
     */
    private void init(View view) {
        scrollview = view.findViewById(R.id.scrollview);
        txtListNear = view.findViewById(R.id.txtDSGoiY);
        txtListNhaHang = view.findViewById(R.id.txtDSNhaHang);
        txtListBranch = view.findViewById(R.id.txtListBranch);
        txtListBuffet = view.findViewById(R.id.txtListBuffet);
        txtListMonChay = view.findViewById(R.id.txtListMonChay);
        txtListAV = view.findViewById(R.id.txtListAV);
    }

    /*
     * Hàm bắt sự kiện cho các TextView
     */
    private void action() {
        txtListNear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),HomeNearActivity.class));
                Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.anim_out,R.anim.anim_in);
            }
        });

        txtListNhaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),HomRestaurantActivity.class));
                Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.anim_out,R.anim.anim_in);
            }
        });

        txtListBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),HomeRestaurantFoodActivity.class));
                Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.anim_out,R.anim.anim_in);
            }
        });

        txtListBuffet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),HomeFoodBuffetActivity.class));
                Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.anim_out,R.anim.anim_in);
            }
        });

        txtListMonChay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),HomeFoodChayActivity.class));
                Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.anim_out,R.anim.anim_in);
            }
        });

        txtListAV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),HomeFoodAVActivity.class));
                Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.anim_out,R.anim.anim_in);
            }
        });
    }
}
