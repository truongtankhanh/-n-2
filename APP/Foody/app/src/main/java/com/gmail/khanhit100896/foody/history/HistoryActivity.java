package com.gmail.khanhit100896.foody.history;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gmail.khanhit100896.foody.R;
import com.gmail.khanhit100896.foody.config.Config;
import com.gmail.khanhit100896.foody.main.MainActivity;

import java.util.HashMap;
import java.util.Map;

public class HistoryActivity extends AppCompatActivity {

    protected Toolbar app_bar;
    protected ImageView img_back;
    protected BottomNavigationView bottomNavigationView;

    protected RestaurantHistoryFragment restaurantHistoryFragment;
    protected BranchHistoryFragment branchHistoryFragment;
    protected FoodHistoryFragment foodHistoryFragment;

    protected String getURLDeleteHisRes = Config.getConfig().getPathDeleteHistoryRestaurant();
    protected String getURLDeleteHisBranch = Config.getConfig().getPathDeleteHistoryBranch();
    protected String getURLDeleteHisFood = Config.getConfig().getPathDeleteHistoryFood();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        init();
    }

    private void init() {
        this.app_bar                = findViewById(R.id.app_bar);
        this.img_back               = findViewById(R.id.img_back);
        setSupportActionBar(this.app_bar);
        app_bar.setTitle("Lịch sử nhà hàng");
        this.bottomNavigationView   = findViewById(R.id.main_nav);

        this.restaurantHistoryFragment  = new RestaurantHistoryFragment();
        this.branchHistoryFragment      = new BranchHistoryFragment();
        this.foodHistoryFragment        = new FoodHistoryFragment();

        /*
         * Bắt sự kiện click cho image chuyển từ màn hình hiện tại về màn hình trước đó
         */
        this.img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
        /*
         */

        setFragment(this.restaurantHistoryFragment);

        // Hàm xét sự kiện click cho bottomNavigationView
        this.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_restaurant:
                        recreate();
                        return true;

                    case R.id.nav_branch:
                        app_bar.setTitle("Lịch sử món ăn nhà hàng");
                        setFragment(branchHistoryFragment);
                        return true;

                    case R.id.nav_food:
                        app_bar.setTitle("Lịch sử món ăn bình dân");
                        setFragment(foodHistoryFragment);
                        return true;

                        default:
                            return false;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_delete_history,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete_history) {
            deleteHistory(getURLDeleteHisRes);
            deleteHistory(getURLDeleteHisBranch);
            deleteHistory(getURLDeleteHisFood);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            recreate();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void deleteHistory(String getURL){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            Toast.makeText(getApplicationContext(),"Đã xóa tất cả lịch sử.",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"Lỗi. Vui lòng kiểm tra lại.",Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("actionTouch", String.valueOf(0));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame,fragment);
        transaction.commit();
    }
}
