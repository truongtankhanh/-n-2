package com.gmail.khanhit100896.foody.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.khanhit100896.foody.R;
import com.gmail.khanhit100896.foody.city.CityActivity;
import com.gmail.khanhit100896.foody.city.CityFragment;
import com.gmail.khanhit100896.foody.config.Config;
import com.gmail.khanhit100896.foody.config.RoundedTransformation;
import com.gmail.khanhit100896.foody.food.FoodActivity;
import com.gmail.khanhit100896.foody.home.HomRestaurantActivity;
import com.gmail.khanhit100896.foody.home.HomeFood2Fragment;
import com.gmail.khanhit100896.foody.home.HomeFood3Fragment;
import com.gmail.khanhit100896.foody.home.HomeFragment;
import com.gmail.khanhit100896.foody.food.FoodFragment;
import com.gmail.khanhit100896.foody.home.HomeRestaurantFoodFragment;
import com.gmail.khanhit100896.foody.home.HomeRestaurantFragment;
import com.gmail.khanhit100896.foody.home.HomeFood1Fragment;
import com.gmail.khanhit100896.foody.restaurant.RestaurantFragment;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SearchView searchView;
    DrawerLayout drawer;
    NavigationView navigationView;

    // BottomNavigationView
    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;
    FrameLayout frameHomeRes;

    HomeFragment homeFragment;
    CityFragment cityFragment;
    RestaurantFragment restaurantFragment;
    FoodFragment foodFragment;
    HomeRestaurantFragment homeRestaurantFragment;
    HomeRestaurantFoodFragment homeRestaurantFoodFragment;
    HomeFood1Fragment homeFood1Fragment;
    HomeFood2Fragment homeFood2Fragment;
    HomeFood3Fragment homeFood3Fragment;

    int REQUEST_CODE = 123;
    SharedPreferences preferences;

    // User info
    ImageView imgUser;
    TextView txtUserNameMain;
    TextView txtEmailMain;

    // Frame nha hang


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        preferences = getSharedPreferences("userInfo",MODE_PRIVATE);

        // ???
        if(preferences != null){
            //String photoUrl = data.getStringExtra("photoUrl").trim();
            //String username = preferences.getString("username","");
            //String email = data.getStringExtra("email").trim();
//            Picasso.get().load(Config.getConfig().sharedPreferences.getString("photoUrl",""))
//                    .transform(new RoundedTransformation(60,0))
//                    .into(this.imgUser);
            //this.txtUserNameMain.setText(String.valueOf(username));
//            this.txtEmailMain.setText(Config.getConfig().sharedPreferences.getString("email",""));
            Toast.makeText(getApplicationContext(),preferences.getString("username",""),Toast.LENGTH_SHORT).show();
        }
        // ???
    }

    private void init() {
        this.searchView                 = findViewById(R.id.searchView);
        this.drawer                     = findViewById(R.id.drawer_layout);
        this.navigationView             = findViewById(R.id.nav_view);
        this.bottomNavigationView       = findViewById(R.id.main_nav);
        this.frameLayout                = findViewById(R.id.main_frame);
        this.frameHomeRes               = findViewById(R.id.home_restaurant_frame);
        this.imgUser                    = findViewById(R.id.imgUser);
        this.txtUserNameMain            = findViewById(R.id.txtUserNameMain);
        this.txtEmailMain               = findViewById(R.id.txtEmailMain);


//        setSupportActionBar(searchView);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, searchView, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        homeFragment = new HomeFragment();
        cityFragment = new CityFragment();
        restaurantFragment = new RestaurantFragment();
        foodFragment = new FoodFragment();
        homeRestaurantFragment = new HomeRestaurantFragment();
        homeRestaurantFoodFragment = new HomeRestaurantFoodFragment();
        homeFood1Fragment = new HomeFood1Fragment();
        homeFood2Fragment = new HomeFood2Fragment();
        homeFood3Fragment = new HomeFood3Fragment();

        setFragment(homeFragment,R.id.main_frame);
        setFragment(homeRestaurantFragment,R.id.home_restaurant_frame);
        setFragment(homeRestaurantFoodFragment,R.id.home_restaurant_food);
        setFragment(homeFood1Fragment,R.id.home_buffet_food);
        setFragment(homeFood2Fragment,R.id.home_chay_food);
        setFragment(homeFood3Fragment,R.id.home_av_food);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        recreate();
                        return true;

                    case R.id.nav_restaurant:
                        setFragment(restaurantFragment,R.id.main_frame);
                        return true;

                    case R.id.nav_food:
                        setFragment(foodFragment,R.id.main_frame);
                        return true;

                        default:
                            return false;
                }
            }
        });


    }

    private void setFragment(Fragment fragment, int id) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(id == R.id.main_frame){
            transaction.replace(R.id.main_frame,fragment);
            transaction.commit();
        }
        if(id == R.id.home_restaurant_frame){
            transaction.replace(R.id.home_restaurant_frame,fragment);
            transaction.commit();
        }
        if(id == R.id.home_restaurant_food){
            transaction.replace(R.id.home_restaurant_food,fragment);
            transaction.commit();
        }
        if(id == R.id.home_buffet_food){
            transaction.replace(R.id.home_buffet_food,fragment);
            transaction.commit();
        }
        if(id == R.id.home_chay_food){
            transaction.replace(R.id.home_chay_food,fragment);
            transaction.commit();
        }
        if(id == R.id.home_av_food){
            transaction.replace(R.id.home_av_food,fragment);
            transaction.commit();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        SharedPreferences.Editor editor = preferences.edit();

        this.imgUser = findViewById(R.id.imgUser);
        this.txtUserNameMain = findViewById(R.id.txtUserNameMain);
        this.txtEmailMain = findViewById(R.id.txtEmailMain);

        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null){
            String photoUrl = data.getStringExtra("photoUrl").trim();
            String username = data.getStringExtra("username").trim();
            String email = data.getStringExtra("email").trim();

            Picasso.get().load(photoUrl)
                    .transform(new RoundedTransformation(60,0))
                    .into(this.imgUser);
            this.txtUserNameMain.setText(username);
            this.txtEmailMain.setText(email);

            editor.putString("photoUrl",photoUrl);
            editor.putString("username",username);
            editor.putString("email",email);
            editor.apply();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_city){
            startActivity(new Intent(getApplicationContext(),CityActivity.class));
        }else if(id == R.id.nav_restaurant){
            startActivity(new Intent(getApplicationContext(),HomRestaurantActivity.class));
        }else if(id == R.id.nav_food){
            startActivity(new Intent(getApplicationContext(),FoodActivity.class));
        }
        else if (id == R.id.nav_signin) {
            startActivityForResult(new Intent(MainActivity.this,LoginActivity.class),this.REQUEST_CODE);
        }else if (id == R.id.nav_signout) {
            Config.getConfig().signOut();
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove("photoUrl");
            editor.remove("username");
            editor.remove("email");
            editor.apply();
            Toast.makeText(MainActivity.this,"Successful logout.",Toast.LENGTH_SHORT).show();
        }

        drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
