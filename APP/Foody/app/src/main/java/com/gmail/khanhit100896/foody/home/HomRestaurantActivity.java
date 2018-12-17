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
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.gmail.khanhit100896.foody.R;
import com.gmail.khanhit100896.foody.config.Config;
import com.gmail.khanhit100896.foody.restaurant.Restaurant;
import com.gmail.khanhit100896.foody.restaurant.RestaurantRecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomRestaurantActivity extends AppCompatActivity {

    /*
     * Khai báo biến cần thiết
     */
    protected String getURL = Config.getConfig().getPathGetAllRestaurant();
    protected Toolbar app_bar_home_restaurant;
    protected RecyclerView recyclerRestaurant;
    protected List<Restaurant> restaurantList;
    protected RestaurantRecyclerViewAdapter adapter;
    protected AppBarLayout appBarLayout;
    /*
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hom_restaurant);

        /*
         * Ánh xạ các biến đã khai báo
         */
        this.app_bar_home_restaurant    = findViewById(R.id.app_bar_home_restaurant);
        this.recyclerRestaurant         = findViewById(R.id.recycler_home_restaurant);
        this.restaurantList             = new ArrayList<>();
        this.app_bar_home_restaurant.setTitle("Danh sách nhà hàng");
        setSupportActionBar(this.app_bar_home_restaurant);
        this.appBarLayout = findViewById(R.id.appBarLayout);
        /*
         */

        // Gọi hàm lấy tất cả các nhà hàng.
        getAllRestaurant(this.getURL);

        // Bắt sự kiện ẩn appBarLayout khi scroll up
        this.recyclerRestaurant.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                if (newState == RecyclerView.SCROLL_AXIS_HORIZONTAL)
//                {
//                    appBarLayout.setVisibility(View.VISIBLE);
//                }
//
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                if (dy > 0 && appBarLayout.isShown()) {
//                    appBarLayout.setVisibility(View.GONE);
//                }
//                else if(dy == 0 && !appBarLayout.isShown()){
//                    appBarLayout.setVisibility(View.GONE);
//                }
//                else if (dy < 0 ) {
//                    appBarLayout.setVisibility(View.VISIBLE);
//                }
//            }

//            private static final int HIDE_THRESHOLD = 20;
//            private int mScrolledDistance = 0;
//            private boolean mVisible = true;
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                //super.onScrolled(recyclerView, dx, dy);
//                int firstVisibleItem =
//                        ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
//                //show views if first item is first visible position and views are hidden
//                if (firstVisibleItem == 0) {
//                    if (!mVisible) {
//                        onShow();
//                        mVisible = true;
//                        //app_bar_home_restaurant.setVisibility(View.VISIBLE);
//                        appBarLayout.setVisibility(View.VISIBLE);
//                    }
//                } else {
//                    if (mScrolledDistance > HIDE_THRESHOLD && mVisible) {
//                        onHide();
//                        mVisible = false;
//                        mScrolledDistance = 0;
//                        //app_bar_home_restaurant.setVisibility(View.GONE);
//                        appBarLayout.setVisibility(View.GONE);
//                    } else if (mScrolledDistance < -HIDE_THRESHOLD && !mVisible) {
//                        onShow();
//                        mVisible = true;
//                        mScrolledDistance = 0;
//                        //app_bar_home_restaurant.setVisibility(View.VISIBLE);
//                        appBarLayout.setVisibility(View.VISIBLE);
//                    }
//                }
//                if ((mVisible && dy > 0) || (!mVisible && dy < 0)) {
//                    mScrolledDistance += dy;
//                }
//            }
        });
    }

    public void onHide() {
        app_bar_home_restaurant.animate().translationY(-this.app_bar_home_restaurant.getHeight())
                .setInterpolator(new AccelerateInterpolator(2));
        appBarLayout.animate().translationY(-this.appBarLayout.getHeight())
                .setInterpolator(new AccelerateInterpolator(2));

    }

    public void onShow() {
        app_bar_home_restaurant.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
        appBarLayout.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
    }

    /*
     * Hàm lấy tất cả các nhà hàng từ CSDL và hiển thị theo dạng lưới
     */
    private void getAllRestaurant(String getUrl){
        int size = 0;
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(this));
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getUrl, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        restaurantList.clear();
                        for (int i=0;i<response.length();i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                restaurantList.add(new Restaurant(
                                        object.getInt("ID"),
                                        object.getString("ResCode"),
                                        object.getString("ResName"),
                                        object.getString("NumberOfBranches"),
                                        object.getString("ImageAddress"),
                                        object.getString("CityCode")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        /*
                         * Đổ dữ liệu đồ ăn vặt lên RecyclerView
                         */
                        adapter = new RestaurantRecyclerViewAdapter(getApplicationContext(),restaurantList);
                        recyclerRestaurant.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
                        recyclerRestaurant.setAdapter(adapter);
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
