package com.gmail.khanhit100896.foody.home;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
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
import com.gmail.khanhit100896.foody.main.CheckGpsFragment;
import com.gmail.khanhit100896.foody.main.GeocodingLocation;
import com.gmail.khanhit100896.foody.main.IComparator;
import com.gmail.khanhit100896.foody.main.Point;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeNearActivity extends AppCompatActivity {

    /*
     * Khai báo biến cần thiết
     */
    protected String getURL = Config.getConfig().getPathGetAllFood();
    protected RecyclerView recyclerFood;
    protected List<Food> foodList;
    protected FoodRecyclerViewAdapter adapter;
    protected LocationManager locationManager;
    protected double latitudeCurrent;
    protected double longitudeCurrent;
    protected Point point;

    protected boolean GpsStatus ;
    private CheckGpsFragment checkGpsFragment;
    /*
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_near);

        CheckGpsStatus();

        if(GpsStatus){
            /*
             * Giải thuật lấy kinh độ vĩ độ hiện tại
             */
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);

            if(ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.ACCESS_COARSE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getApplicationContext(),"Vui lòng bật GPS.",Toast.LENGTH_SHORT).show();
            }

            final Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            // Gọi hàm lấy kinh độ vĩ độ tại nơi bạn đang đứng
            onLocationChanged(location);
            this.point = new Point(latitudeCurrent,longitudeCurrent);

            // Gọi hàm lấy thông tin nơi bạn đang đứng
            getLocationInfo();
            /*
             */
        }
        else{
            setFragment(checkGpsFragment,R.id.main_frame);
        }

        init();

        if(GpsStatus){
            // Gọi hàm tất cả các món ăn vặt
            getAllFood(this.getURL);
        }
        else{
            setFragment(checkGpsFragment,R.id.main_frame);
        }
    }

    private void init() {
        this.recyclerFood = findViewById(R.id.recycler_food);
        this.foodList = new ArrayList<>();
        this.checkGpsFragment = new CheckGpsFragment();
    }

    public void CheckGpsStatus(){
        locationManager = (LocationManager)getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private void setFragment(Fragment fragment, int id) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (id == R.id.main_frame) {
            transaction.replace(R.id.main_frame, fragment);
            transaction.commit();
        }
    }

    private void getLocationInfo() {
        Geocoder geocoder = new Geocoder(getApplicationContext());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitudeCurrent,longitudeCurrent,1);
            //country = addresses.get(0).getAddressLine(0);
            //Toast.makeText(getActivity(),country,Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onLocationChanged(Location location) {
        try {
            latitudeCurrent = location.getLatitude();
            longitudeCurrent = location.getLongitude();
        }catch (NullPointerException e){
            Toast toast = Toast.makeText(getApplicationContext(),"Vui lòng bật GPS",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }
    }

    public List<Food> sort(IComparator<Food> iComparator, List<Food> list){
        List<Food> result = list;

        for (int i = 0; i < result.size(); i++) {
            for (int j = i + 1; j < result.size(); j++) {
                Food food1 = result.get(i);
                Food food2 = result.get(j);

                if (iComparator.compare(food1, food2) > 0) {
                    Food tmp = food1;
                    result.set(i,food2);
                    result.set(j, tmp);
                }
            }
        }

        return result;
    }

    /*
     * Hàm lấy các món ăn gần người dùng nhất
     */
    private void getAllFood(String getURL) {
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getApplicationContext()));
        final Point pointCurrent = new Point(point.getLatitude(),point.getLongitude());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getURL, null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONArray response) {
                        foodList.clear();
                        for (int i=0;i < 10;i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                Point pointPersent = new Point(
                                        GeocodingLocation.getLatitude(object.getString("FoodAddress"),getApplicationContext()),
                                        GeocodingLocation.getLongitude(object.getString("FoodAddress"),getApplicationContext()));
                                Double distance = GeocodingLocation.distance(pointCurrent,pointPersent);

                                foodList.add(new Food(
                                        object.getInt("ID"),
                                        object.getString("FoodCode"),
                                        object.getString("FoodName"),
                                        object.getString("FoodAddress"),
                                        object.getString("FoodPrice"),
                                        object.getString("ImageAddress"),
                                        object.getString("ResCode"),
                                        object.getString("KindCode"),
                                        object.getInt("ActionLike"),
                                        distance
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        /*
                         * Đổ dữ liệu món ăn lên RecyclerView
                         */
                        List<Food> result = sort(new IComparator<Food>() {
                            @Override
                            public int compare(Food a, Food b) {
                                if(a.getDistance() > b.getDistance())
                                    return 1;
                                else if(a.getDistance() < b.getDistance())
                                    return -1;
                                return 0;
                            }
                        }, foodList);

                        adapter = new FoodRecyclerViewAdapter(getApplicationContext(),result);
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
}
