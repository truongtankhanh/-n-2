package com.gmail.khanhit100896.foody.account;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.khanhit100896.foody.R;
import com.gmail.khanhit100896.foody.config.Config;
import com.gmail.khanhit100896.foody.config.RoundedTransformation;
import com.gmail.khanhit100896.foody.food.FoodActivity;
import com.gmail.khanhit100896.foody.history.HistoryActivity;
import com.gmail.khanhit100896.foody.home.HomRestaurantActivity;
import com.gmail.khanhit100896.foody.like.LikeActivity;
import com.gmail.khanhit100896.foody.main.LoginActivity;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    protected View view;
    protected TextView txt_trang_chu, txt_nha_hang,txt_mon_an,txt_yeu_thich,txt_lich_su,txt_dang_nhap,txt_dang_xuat;
    protected int REQUEST_CODE = 1;

    // User info
    protected ImageView imgUser;
    protected TextView txtUserNameMain;
    protected TextView txtEmailMain;


    public AccountFragment() {
        // Required empty public constructor
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        this.view = inflater.inflate(R.layout.fragment_account, container, false);
        init();

        // Hàm lấy thông tin user khi đã đăng nhập rồi
        final FirebaseUser user = Config.getConfig().getmAuth().getInstance().getCurrentUser();
        if (user != null) {
            this.txt_dang_nhap.setEnabled(false);
            this.txt_dang_nhap.setTextColor(Color.GRAY);
            txt_dang_xuat.setEnabled(true);
            txt_dang_xuat.setTextColor(Color.BLACK);
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            String photoUrl = user.getPhotoUrl().toString();

            Picasso.get().load(photoUrl)
                    .transform(new RoundedTransformation(60, 0))
                    .into(this.imgUser);
            this.txtUserNameMain.setText(name);
            this.txtEmailMain.setText(email);
        }
        else{
            txt_dang_xuat.setEnabled(false);
            txt_dang_xuat.setTextColor(Color.GRAY);
        }

        this.txt_trang_chu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().recreate();
            }
        });

        this.txt_nha_hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),HomRestaurantActivity.class));
            }
        });

        this.txt_mon_an.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),FoodActivity.class));
            }
        });

        this.txt_yeu_thich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),LikeActivity.class));
            }
        });

        this.txt_lich_su.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),HistoryActivity.class));
            }
        });

        this.txt_dang_nhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                String context = AccountFragment.class.getSimpleName();
                intent.putExtra("context",context);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        this.txt_dang_xuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Config.getConfig().signOut();
                imgUser.setImageResource(0);
                txtUserNameMain.setText("");
                txtEmailMain.setText("");
                txt_dang_nhap.setEnabled(true);
                txt_dang_nhap.setTextColor(Color.BLACK);
                Toast toast = Toast.makeText(getContext(),"Đăng xuất thành công",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
                getActivity().recreate();
            }
        });

        return this.view;
    }

    private void init() {
        this.imgUser = this.view.findViewById(R.id.imgUser);
        this.txtUserNameMain = this.view.findViewById(R.id.txtUserNameMain);
        this.txtEmailMain = this.view.findViewById(R.id.txtEmailMain);
        this.txt_trang_chu = this.view.findViewById(R.id.txt_trang_chu);
        this.txt_nha_hang = this.view.findViewById(R.id.txt_nha_hang);
        this.txt_mon_an = this.view.findViewById(R.id.txt_mon_an);
        this.txt_yeu_thich = this.view.findViewById(R.id.txt_yeu_thich);
        this.txt_lich_su = this.view.findViewById(R.id.txt_lich_su);
        this.txt_dang_nhap = this.view.findViewById(R.id.txt_dang_nhap);
        this.txt_dang_xuat = this.view.findViewById(R.id.txt_dang_xuat);

    }

}
