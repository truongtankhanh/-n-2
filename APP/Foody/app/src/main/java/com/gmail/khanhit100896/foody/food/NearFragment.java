package com.gmail.khanhit100896.foody.food;


import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.gmail.khanhit100896.foody.R;
import com.gmail.khanhit100896.foody.config.Config;
import com.gmail.khanhit100896.foody.main.GeocodingLocation;
import com.gmail.khanhit100896.foody.main.IComparator;
import com.gmail.khanhit100896.foody.main.Point;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class NearFragment extends Fragment {

    /*
     * Khai báo biến cần thiết
     */
    protected String getURL = Config.getConfig().getPathGetAllFood();
    protected RecyclerView recyclerFood;
    protected List<Food> foodList;
    protected FoodRecyclerViewAdapter adapter;
    protected Point point;
    /*
     */

    public NearFragment(Point point) {
        this.point = point;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        /*
         * Ánh xạ và khởi tạo view, các biến đã khai báo
         */
        View view = inflater.inflate(R.layout.fragment_near, container, false);
        this.recyclerFood = view.findViewById(R.id.recycler_food);
        this.foodList = new ArrayList<>();
        /*
         */

        // Gọi hàm tất cả các món ăn vặt
        getAllFood(this.getURL);

        return view;
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
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));
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
                                        GeocodingLocation.getLatitude(object.getString("FoodAddress"),getContext()),
                                        GeocodingLocation.getLongitude(object.getString("FoodAddress"),getContext()));
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
                                    return -1;
                                else if(a.getDistance() < b.getDistance())
                                    return 1;
                                return 0;
                            }
                        },foodList);

                        adapter = new FoodRecyclerViewAdapter(getActivity(),result);
                        recyclerFood.setLayoutManager(new LinearLayoutManager(getActivity(),
                                LinearLayoutManager.HORIZONTAL, false));
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
