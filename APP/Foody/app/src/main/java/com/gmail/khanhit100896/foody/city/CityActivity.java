package com.gmail.khanhit100896.foody.city;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.gmail.khanhit100896.foody.R;

public class CityActivity extends AppCompatActivity {

    CityFragment cityFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        cityFragment = new CityFragment();
        setFragment(cityFragment);
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.city_frame,fragment);
        transaction.commit();
    }
}
