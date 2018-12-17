package com.gmail.khanhit100896.foody.like;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class LikeActivity extends AppCompatActivity {

    protected Toolbar app_bar;
    protected ImageView img_back;
    protected BottomNavigationView bottomNavigationView;

    protected RestaurantLikeFragment restaurantLikeFragment;
    protected BranchLikeFragment branchLikeFragment;
    protected FoodLikeFragment foodLikeFragment;

    protected String getURLDeleteLikeRes = Config.getConfig().getPathDeleteLikeRestaurant();
    protected String getURLDeleteLikeBranch = Config.getConfig().getPathDeleteLikeBranch();
    protected String getURLDeleteLikeFood = Config.getConfig().getPathDeleteLikeFood();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like);

        init();

        setFragment(restaurantLikeFragment);
    }

    private void init() {
        this.app_bar                = findViewById(R.id.app_bar);
        this.img_back               = findViewById(R.id.img_back);
        setSupportActionBar(this.app_bar);
        this.app_bar.setTitle("Danh sách yêu thích");
        this.bottomNavigationView   = findViewById(R.id.main_nav);

        this.restaurantLikeFragment = new RestaurantLikeFragment();
        this.branchLikeFragment     = new BranchLikeFragment();
        this.foodLikeFragment       = new FoodLikeFragment();

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

        // Hàm xét sự kiện click cho bottomNavigationView
        this.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_restaurant:
                        recreate();
                        return true;

                    case R.id.nav_branch:
                        setFragment(branchLikeFragment);
                        return true;

                    case R.id.nav_food:
                        setFragment(foodLikeFragment);
                        return true;

                    default: return false;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_delete_like,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete_like) {
            deleteLike(getURLDeleteLikeRes);
            deleteLike(getURLDeleteLikeBranch);
            deleteLike(getURLDeleteLikeFood);
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

    public void deleteLike(String getURL){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            Toast.makeText(getApplicationContext(),"Đã xóa tất cả ds yêu thích.",Toast.LENGTH_SHORT).show();
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
                params.put("actionLike", String.valueOf(0));
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
