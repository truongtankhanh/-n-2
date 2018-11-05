package com.gmail.khanhit100896.foody.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.gmail.khanhit100896.foody.R;
import com.gmail.khanhit100896.foody.food.ChayFoodFragment;

public class HomeFoodChayActivity extends AppCompatActivity {

    ChayFoodFragment chayFoodFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_food_chay);

        chayFoodFragment = new ChayFoodFragment();
        setFragment(chayFoodFragment);
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_chay_frame,fragment);
        transaction.commit();
    }
}
