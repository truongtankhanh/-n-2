package com.gmail.khanhit100896.foody.home;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.gmail.khanhit100896.foody.R;
import com.gmail.khanhit100896.foody.config.Config;
import com.gmail.khanhit100896.foody.food.Food;
import com.gmail.khanhit100896.foody.food.FoodRecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFoodBuffetActivity extends AppCompatActivity {

    /*
     * Khai báo biến cần thiết
     */
    protected String getURL = Config.getConfig().getPathGetAllFoodBuffet();
    protected Toolbar app_bar_home_food_buffet;
    protected RecyclerView recyclerFoodBuffet;
    protected List<Food> foodBuffetList;
    protected FoodRecyclerViewAdapter adapter;
    protected AppBarLayout appBarLayout;
    /*
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_food_buffet);

        /*
         * Ánh xạ các biến đã khai báo
         */
        app_bar_home_food_buffet = findViewById(R.id.app_bar_home_food_buffet);
        setSupportActionBar(app_bar_home_food_buffet);
        this.app_bar_home_food_buffet.setTitle("Buffet - món Việt");

        this.recyclerFoodBuffet = findViewById(R.id.recycler_buffet_food);
        this.foodBuffetList = new ArrayList<>();
        this.appBarLayout = findViewById(R.id.appBarLayout);
        /*
         */

        // Gọi hàm lấy tất cả các món buffet
        getAllFoodBuffet(this.getURL);

        // Bắt sự kiện ẩn appBarLayout khi scroll up
//        this.recyclerFoodBuffet.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                if (newState == RecyclerView.SCROLL_STATE_IDLE)
//                {
//                    appBarLayout.setVisibility(View.VISIBLE);
//                }
//
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                if (dy > 0 || dy < 0 && appBarLayout.isShown())
//                {
//                    appBarLayout.setVisibility(View.GONE);
//                }
//            }
//        });
    }

    /*
     * Hàm lấy tất cả các món buffet từ CSDL và hiển thị theo dạng lưới
     */
    private void getAllFoodBuffet(String getURL) {
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getApplicationContext()));
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getURL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        foodBuffetList.clear();
                        for (int i=0;i < response.length();i++){

                            try {
                                JSONObject object = response.getJSONObject(i);

                                foodBuffetList.add(new Food(
                                        object.getInt("ID"),
                                        object.getString("FoodCode"),
                                        object.getString("FoodName"),
                                        object.getString("FoodAddress"),
                                        object.getString("FoodPrice"),
                                        object.getString("ImageAddress"),
                                        object.getString("ResCode"),
                                        object.getString("KindCode")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        /*
                         * Đổ dữ liệu đồ ăn vặt lên RecyclerView
                         */
                        adapter = new FoodRecyclerViewAdapter(getApplicationContext(),foodBuffetList);
                        recyclerFoodBuffet.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
                        recyclerFoodBuffet.setAdapter(adapter);
                        /*
                         */
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    /*
     * Hàm khởi tạo menu và bắt sự kiện tìm kiếm món ăn
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);

        MenuItem item = menu.findItem(R.id.action_search);
        SearchView search = (SearchView) item.getActionView();

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getApplicationContext(),query,Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }
}
