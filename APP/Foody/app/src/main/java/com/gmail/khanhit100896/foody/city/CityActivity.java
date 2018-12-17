package com.gmail.khanhit100896.foody.city;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.gmail.khanhit100896.foody.R;
import com.gmail.khanhit100896.foody.config.Config;
import com.gmail.khanhit100896.foody.main.MapActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jp.wasabeef.recyclerview.animators.SlideInRightAnimator;

public class CityActivity extends AppCompatActivity {

    /*
     * Khái báo biến cần thiết
     */
    protected String getURL = Config.getConfig().getPathGetAllCity();
    protected RecyclerView recyclerCity;
    protected List<City> cityList;
    protected CityRecyclerViewAdapter adapter;
    protected Toolbar app_bar_city;
    protected AppBarLayout appBarLayout;
    /*
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        // Gọi hàm ánh xạ
        init();
    }

    /*
     * Hàm ánh xạ
     */
    private void init() {
        /*
         * Ánh xạ các id và khởi tạo 1 số biến
         */
        app_bar_city        = findViewById(R.id.app_bar_city);
        this.recyclerCity   = findViewById(R.id.recycler_city);
        this.cityList       = new ArrayList<>();
        this.setTitle("Danh sách các thành phố");
        setSupportActionBar(app_bar_city);
        this.appBarLayout = findViewById(R.id.appBarLayout);
        /*
         */

        // Gọi hàm lấy tất cả thành phố từ CSDL
        getAllCity(getURL);

        // Bắt sự kiện ẩn appBarLayout khi scroll up
//        this.recyclerCity.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
     * Hàm lấy tất cả thành phố từ CSDL
     */
    private void getAllCity(String getURL) {
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getApplicationContext()));
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getURL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        cityList.clear();
                        for (int i=0;i<response.length();i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                cityList.add(new City(
                                        object.getInt("ID"),
                                        object.getString("CityCode"),
                                        object.getString("CityName"),
                                        object.getString("CityImage")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        /*
                         * Đổ dữ liệu lên RecyclerView
                         */
                        adapter = new CityRecyclerViewAdapter(getApplicationContext(),cityList);
                        recyclerCity.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
                        recyclerCity.setAdapter(adapter);
                        /*
                         */
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
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
