package com.gmail.khanhit100896.foody.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.gmail.khanhit100896.foody.R;
import com.gmail.khanhit100896.foody.food.AVFoodFragment;

public class HomeFoodAVActivity extends AppCompatActivity {

    AVFoodFragment avFoodFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_food_av);

        avFoodFragment = new AVFoodFragment();
        setFragment(avFoodFragment);
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_av_frame,fragment);
        transaction.commit();
    }
}
