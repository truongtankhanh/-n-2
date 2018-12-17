package com.gmail.khanhit100896.foody.food;

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
import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.gmail.khanhit100896.foody.R;
import com.gmail.khanhit100896.foody.config.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FoodActivity extends AppCompatActivity {

    /*
     * Khai báo biến cần thiết
     */
    protected String getURL = Config.getConfig().getPathGetAllFood();
    protected RecyclerView recyclerFood;
    protected List<Food> foodList;
    protected FoodRecyclerViewAdapter adapter;
    protected Toolbar app_bar_food;
    protected AppBarLayout appBarLayout;
    /*
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        /*
         * Ánh xạ và khởi tạo view, các biến đã khai báo
         */
        this.app_bar_food       = findViewById(R.id.app_bar_food);
        this.recyclerFood       = findViewById(R.id.recycler_food);
        this.foodList           = new ArrayList<>();
        this.app_bar_food.setTitle("Danh sách các món ăn");
        setSupportActionBar(this.app_bar_food);
        this.appBarLayout = findViewById(R.id.appBarLayout);
        /*
         */

        // Gọi hàm lấy tất cả các món ăn
        getAllFood(this.getURL);

        // Bắt sự kiện ẩn appBarLayout khi scroll up
//        this.recyclerFood.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                if (dy > 0 && appBarLayout.isShown()) {
//                    appBarLayout.setVisibility(View.GONE);
//                } else if (dy < 0 ) {
//                    appBarLayout.setVisibility(View.VISIBLE);
//                }
//            }
//        });
    }

    /*
     * Hàm lấy tất cả các món ăn từ CSDL và hiển thị theo dạng lưới
     */
    private void getAllFood(String getURL) {
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getApplicationContext()));
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getURL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        foodList.clear();
                        SkeletonScreen skeletonScreen;
                        skeletonScreen = Skeleton.bind(recyclerFood)
                                .adapter(adapter)
                                .load(R.layout.layout_default_item_skeleton)
                                .show();
                        for (int i=0;i<response.length();i++){
                            try {
                                JSONObject object = response.getJSONObject(i);

                                Food food = new Food(
                                        object.getInt("ID"),
                                        object.getString("FoodCode"),
                                        object.getString("FoodName"),
                                        object.getString("FoodAddress"),
                                        object.getString("FoodPrice"),
                                        object.getString("ImageAddress"),
                                        object.getString("ResCode"),
                                        object.getString("KindCode")
                                );

                                foodList.add(food);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        /*
                         * Đổ dữ liệu đồ ăn vặt lên RecyclerView
                         */
                        adapter = new FoodRecyclerViewAdapter(getApplicationContext(),foodList);
                        recyclerFood.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
                        recyclerFood.setAdapter(adapter);
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
     * Hàm khởi tạo menu
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
