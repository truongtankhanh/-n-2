package com.gmail.khanhit100896.foody.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.gmail.khanhit100896.foody.R;
import com.gmail.khanhit100896.foody.restaurant.RestaurantFragment;

public class HomRestaurantActivity extends AppCompatActivity {

    RestaurantFragment restaurantFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hom_restaurant);

        restaurantFragment = new RestaurantFragment();

        setFragment(restaurantFragment);
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_restaurant_frame,fragment);
        transaction.commit();

    }
}
