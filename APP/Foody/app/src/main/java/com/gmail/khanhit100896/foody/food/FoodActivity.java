package com.gmail.khanhit100896.foody.food;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.gmail.khanhit100896.foody.R;

public class FoodActivity extends AppCompatActivity {

    FoodFragment foodFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        foodFragment = new FoodFragment();
        setFragment(foodFragment);
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.food_frame,fragment);
        transaction.commit();
    }
}
