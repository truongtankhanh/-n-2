package com.gmail.khanhit100896.foody.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.gmail.khanhit100896.foody.R;
import com.gmail.khanhit100896.foody.branch.BranchFragment;

public class HomeRestaurantFoodActivity extends AppCompatActivity {

    BranchFragment branchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_restaurant_food);

        branchFragment = new BranchFragment();

        setFragment(branchFragment);
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_restaurant_food_frame,fragment);
        transaction.commit();

    }
}
