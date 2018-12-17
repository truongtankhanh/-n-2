package com.gmail.khanhit100896.foody.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.Formatter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.gmail.khanhit100896.foody.R;
import com.gmail.khanhit100896.foody.account.AccountFragment;
import com.gmail.khanhit100896.foody.city.CityFragment;
import com.gmail.khanhit100896.foody.config.Config;
import com.gmail.khanhit100896.foody.config.RoundedTransformation;
import com.gmail.khanhit100896.foody.food.FoodActivity;
import com.gmail.khanhit100896.foody.food.FoodFragment;
import com.gmail.khanhit100896.foody.food.NearFragment;
import com.gmail.khanhit100896.foody.history.HistoryActivity;
import com.gmail.khanhit100896.foody.home.HomRestaurantActivity;
import com.gmail.khanhit100896.foody.home.HomeFoodAVFragment;
import com.gmail.khanhit100896.foody.home.HomeFoodBuffetFragment;
import com.gmail.khanhit100896.foody.home.HomeFoodChayFragment;
import com.gmail.khanhit100896.foody.home.HomeFragment;
import com.gmail.khanhit100896.foody.home.HomeNearFragment;
import com.gmail.khanhit100896.foody.home.HomeRestaurantFoodFragment;
import com.gmail.khanhit100896.foody.home.HomeRestaurantFragment;
import com.gmail.khanhit100896.foody.like.LikeActivity;
import com.gmail.khanhit100896.foody.notification.NotificationFragment;
import com.gmail.khanhit100896.foody.restaurant.RestaurantFragment;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /*
     * Khai báo các biến cần thiết
     */
    protected Toolbar toolbar;
    protected AppBarLayout appBarLayout;
    protected DrawerLayout drawer;
    protected NavigationView navigationView;
    protected View headerView;
    protected View appBarMain;
    protected ImageView img_search_main;

    // BottomNavigationView
    protected BottomNavigationView bottomNavigationView;
    protected FrameLayout frameLayout;

    protected HomeFragment homeFragment;
    protected CityFragment cityFragment;
    protected HomeNearFragment homeNearFragment;
    protected NearFragment nearFragment;
    protected RestaurantFragment restaurantFragment;
    protected FoodFragment foodFragment;
    protected HomeRestaurantFragment homeRestaurantFragment;
    protected HomeRestaurantFoodFragment homeRestaurantFoodFragment;
    protected HomeFoodBuffetFragment homeFood1Fragment;
    protected HomeFoodChayFragment homeFood2Fragment;
    protected HomeFoodAVFragment homeFood3Fragment;

    protected AccountFragment accountFragment;
    protected NotificationFragment notificationFragment;
    protected CheckInternetFragment checkInternetFragment;

    protected int REQUEST_CODE = 1;
    protected SharedPreferences preferences;

    // User info
    protected ImageView imgUser;
    protected TextView txtUserNameMain;
    protected TextView txtEmailMain;

    protected LocationManager locationManager;
    protected double latitudeCurrent;
    protected double longitudeCurrent;
    protected Point point;
    protected boolean GpsStatus ;
    /*
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_main);

//        // Add code to print out the key hash
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(
//                    "com.gmail.khanhit100896.foody",
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//
//        } catch (NoSuchAlgorithmException e) {
//
//        }

        // Gọi hàm ánh xạ
        init();

        if(isNetworkAvailable()){
            //recreate();
            setFragment(homeFragment,R.id.main_frame);
            setFragment(homeNearFragment,R.id.home_suggestions_frame);
            setFragment(homeRestaurantFragment,R.id.home_restaurant_frame);
            setFragment(homeRestaurantFoodFragment,R.id.home_restaurant_food);
            setFragment(homeFood1Fragment,R.id.home_buffet_food);
            setFragment(homeFood2Fragment,R.id.home_chay_food);
            setFragment(homeFood3Fragment,R.id.home_av_food);
        }
        else {
            setFragment(checkInternetFragment,R.id.main_frame);
        }

        // Hàm xét sự kiện click cho bottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        recreate();
                        return true;

//                    case R.id.nav_notification:
//                        toolbar.setTitle("Thông báo");
//                        if(isNetworkAvailable()){
//                            setFragment(notificationFragment,R.id.main_frame);
//                            appBarLayout.setBackgroundResource(R.color.green_primary);
//                            toolbar.setTitleTextColor(Color.BLACK);
//                            bottomNavigationView.setBackgroundResource(R.color.green_primary);
//                            bottomNavigationView.setItemTextColor(ColorStateList.valueOf(Color.BLACK));
//                            bottomNavigationView.setItemIconTintList(ColorStateList.valueOf(Color.BLACK));
//                            return true;
//                        }else{
//                            setFragment(checkInternetFragment,R.id.main_frame);
//                            return false;
//                        }

                    case R.id.nav_account:
                        toolbar.setTitle("Tài khoản");
                        if(isNetworkAvailable()){
                            setFragment(accountFragment,R.id.main_frame);
//                            appBarLayout.setBackgroundResource(R.color.app_blue);
//                            toolbar.setTitleTextColor(Color.WHITE);
//                            bottomNavigationView.setBackgroundResource(R.color.app_blue);
//                            bottomNavigationView.setItemTextColor(ColorStateList.valueOf(Color.WHITE));
//                            bottomNavigationView.setItemIconTintList(ColorStateList.valueOf(Color.WHITE));
                            return true;
                        }else{
                            setFragment(checkInternetFragment,R.id.main_frame);
                            return false;
                        }

                    default:
//                        appBarLayout.setBackgroundResource(R.color.red);
//                        toolbar.setTitleTextColor(Color.WHITE);
//                        bottomNavigationView.setBackgroundResource(R.color.red);
//                        bottomNavigationView.setItemTextColor(ColorStateList.valueOf(Color.WHITE));
//                        bottomNavigationView.setItemIconTintList(ColorStateList.valueOf(Color.WHITE));
                        return false;
                }
            }
        });

//        // Hàm tìm kiếm món ăn
//        this.img_search_main.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(),SearchActivity.class));
//                overridePendingTransition(R.anim.anim_out,R.anim.anim_in);
//            }
//        });

        // Hàm lấy thông tin user khi đã đăng nhập rồi
        FirebaseUser user = Config.getConfig().getmAuth().getInstance().getCurrentUser();
        if(isNetworkAvailable()){
            if (user != null) {
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
        }
    }

    // Hàm kiểm tra đã có internet hay chưa
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /*
     * Hàm ánh xạ
     */
    private void init() {
        this.toolbar                    = findViewById(R.id.toolbar);
        this.appBarLayout               = findViewById(R.id.appBarLayout);
        this.drawer                     = findViewById(R.id.drawer_layout);
        this.navigationView             = findViewById(R.id.nav_view);
        this.headerView                 = this.navigationView.getHeaderView(0);
        this.appBarMain                 = this.drawer.getChildAt(0);
        //this.img_search_main            = findViewById(R.id.img_search_main);
        this.bottomNavigationView       = findViewById(R.id.main_nav);
        this.frameLayout                = findViewById(R.id.main_frame);
        this.imgUser                    = headerView.findViewById(R.id.imgUser);
        this.txtUserNameMain            = headerView.findViewById(R.id.txtUserNameMain);
        this.txtEmailMain               = headerView.findViewById(R.id.txtEmailMain);
        this.preferences                = getSharedPreferences("userInfo",MODE_PRIVATE);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        this.homeFragment                    = new HomeFragment();
        this.cityFragment                    = new CityFragment();
        this.nearFragment                    = new NearFragment(this.point);
        this.homeNearFragment                = new HomeNearFragment();
        this.restaurantFragment              = new RestaurantFragment();
        this.foodFragment                    = new FoodFragment();
        this.homeRestaurantFragment          = new HomeRestaurantFragment();
        this.homeRestaurantFoodFragment      = new HomeRestaurantFoodFragment();
        this.homeFood1Fragment               = new HomeFoodBuffetFragment();
        this.homeFood2Fragment               = new HomeFoodChayFragment();
        this.homeFood3Fragment               = new HomeFoodAVFragment();
        this.accountFragment                 = new AccountFragment();
        this.notificationFragment            = new NotificationFragment();
        this.checkInternetFragment           = new CheckInternetFragment();
    }

    private void setFragment(Fragment fragment, int id) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(id == R.id.main_frame){
            transaction.replace(R.id.main_frame,fragment);
            transaction.commit();
        }
        if(id == R.id.home_suggestions_frame){
            transaction.replace(R.id.home_suggestions_frame,fragment);
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

    /*
     * Hàm nhận kết quả trả về từ LoginActivity
     */
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
//            if (data != null) {
//                String photoUrl = data.getStringExtra("photoUrl");
//                String username = data.getStringExtra("username");
//                String email = data.getStringExtra("email");
//
//                Picasso.get().load(photoUrl)
//                        .transform(new RoundedTransformation(60, 0))
//                        .into(this.imgUser);
//                this.txtUserNameMain.setText(username);
//                this.txtEmailMain.setText(email);
//            }
//        }
//        recreate();
//    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*
     * Hàm khởi tạo menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            startActivity(new Intent(Settings.ACTION_SETTINGS));
            return true;
        }
        else if(id == R.id.action_search){
            startActivity(new Intent(getApplicationContext(),SearchActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
     * Hàm bắt sự kiện cho NavigationMenu
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.nav_home) {
            recreate();
        }else if(id == R.id.nav_restaurant){
            startActivity(new Intent(getApplicationContext(),HomRestaurantActivity.class));
        }else if(id == R.id.nav_food){
            startActivity(new Intent(getApplicationContext(),FoodActivity.class));
        }else if(id == R.id.nav_like){
            startActivity(new Intent(getApplicationContext(),LikeActivity.class));
        }else if(id == R.id.nav_history){
            startActivity(new Intent(getApplicationContext(),HistoryActivity.class));
        }

        drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
