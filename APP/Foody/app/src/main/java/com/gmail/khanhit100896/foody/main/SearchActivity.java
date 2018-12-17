package com.gmail.khanhit100896.foody.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

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

public class SearchActivity extends AppCompatActivity {

    protected Toolbar app_bar;
    protected SearchView searchView;
    protected FrameLayout main_frame;

    protected String getURL = Config.getConfig().getPathGetAllFood();
    protected RecyclerView recyclerView;
    protected List<Food> foodList;
    protected FoodRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        init();

        getAllFood(getURL);

        this.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.equals("")){
                    main_frame.setVisibility(View.GONE);
                    return false;
                }else {
                    main_frame.setVisibility(View.VISIBLE);
                    adapter.getFilter().filter(newText);
                    return false;
                }
            }
        });
    }

    private void init() {
        this.app_bar = findViewById(R.id.app_bar);
        this.main_frame = findViewById(R.id.main_frame);
        this.recyclerView = findViewById(R.id.recyclerView);
        this.searchView = findViewById(R.id.searchView);
        setSupportActionBar(this.app_bar);
        this.foodList = new ArrayList<>();
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, fragment);
        transaction.commit();
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
                        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
                        recyclerView.setAdapter(adapter);
                        main_frame.setVisibility(View.GONE);
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
}
