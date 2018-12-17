package com.gmail.khanhit100896.foody.restaurant;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gmail.khanhit100896.foody.R;
import com.gmail.khanhit100896.foody.branch.Branch;
import com.gmail.khanhit100896.foody.branch.BranchRecyclerViewAdapter;
import com.gmail.khanhit100896.foody.config.Config;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RestaurantBranchActivity extends AppCompatActivity {

    /*
     * Khai báo các biến cần thiết
     */
    protected Toolbar app_bar_restaurant_details;
    protected ImageView img_back_restaurant_details;

    protected ImageView imgRes;
    protected TextView txtResName;

    private Intent intent;
    private RecyclerView recyclerBranch;
    private List<Branch> branchList;
    private BranchRecyclerViewAdapter adapter;
    protected AppBarLayout appBarLayout;

    protected String image;
    protected String name;
    protected String numOfBranch;
    protected int id, actionLike;
    private String getURL = Config.getConfig().getPathUpdateRestaurant();
    /*
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_branch);

        // Gọi hàm ánh xạ
        init();

        /*
         * Đổ dự liệu lên RecyclerView
         */
        String resCode = Objects.requireNonNull(intent.getExtras()).getString("ResCode");
        getAllBranch(Config.getConfig().getPathGetAllBranch(),resCode);

        this.adapter = new BranchRecyclerViewAdapter(getApplicationContext(),this.branchList);
        this.recyclerBranch.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        this.recyclerBranch.setAdapter(this.adapter);
        /*
         */

    }

    /*
     * Hàm ánh xạ
     */
    @SuppressLint("SetTextI18n")
    private void init() {
        /*
         * Ánh xạ các thuộc tính và khởi tạo 1 số biến
         */
        this.app_bar_restaurant_details     = findViewById(R.id.app_bar_restaurant_details);
        this.img_back_restaurant_details    = findViewById(R.id.img_back_restaurant_details);
        this.imgRes                         = findViewById(R.id.img_res_branch);
        this.txtResName                     = findViewById(R.id.txt_name_res_branch);
        this.recyclerBranch                 = findViewById(R.id.recycler_branch);
        this.branchList = new ArrayList<>();
        setSupportActionBar(this.app_bar_restaurant_details);
        this.appBarLayout = findViewById(R.id.appBarLayout);
        /*
         */

        // Bắt sự kiện ẩn appBarLayout khi scroll up
//        this.recyclerBranch.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
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
//        });

        /*
         * Lấy thông tin nhà hàng được gửi từ RestaurantRecyclerViewAdapter và gán giá trị cho các thuộc tính
         */
        intent = getIntent();
        final String className = intent.getExtras().getString("class");
        image = Objects.requireNonNull(intent.getExtras()).getString("ResImage");
        name = intent.getExtras().getString("ResName");
        numOfBranch = intent.getExtras().getString("NumOfBranch");
        this.id = intent.getExtras().getInt("ID");
        this.actionLike = intent.getExtras().getInt("ActionLike");

        if (image != null) {
            Picasso.get().load(Config.getConfig().getPathLoadImgRes().concat(image))
                    .into(this.imgRes);
        }
        this.txtResName.setText(name + " - " + numOfBranch + " chi nhánh.");
        this.app_bar_restaurant_details.setTitle(name);
        /*
         */

        /*
         * Bắt sự kiện click cho image chuyển từ màn hình hiện tại về màn hình HomRestaurantActivity
         */
        this.img_back_restaurant_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(getApplicationContext(), Class.forName(className).getClass()));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });

    }

    /*
     *  Lấy tất cả thông tin chi nhánh nhà hàng từ CSDL
     */
    private void getAllBranch(String getURL, final String resCode) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getURL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        branchList.clear();
                        for (int i=0;i<response.length();i++){
                            try {
                                JSONObject object = response.getJSONObject(i);

                                if(object.getString("ResCode").equals(resCode)) {
                                    branchList.add(new Branch(
                                            object.getInt("ID"),
                                            object.getString("ResBranchCode"),
                                            object.getString("ResCode"),
                                            object.getString("ResBranchName"),
                                            object.getString("ResBranchAddress"),
                                            object.getString("ResBranchOpenTime"),
                                            object.getString("ResBranchPrice"),
                                            object.getString("ResBranchImage")
                                    ));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        switch (id){
            case R.id.action_like:
                addLike(getURL,id,actionLike,name);
                return true;

                default: return false;
        }
    }

    /*
     * Hàm thêm vào danh sách yêu thích
     */
    private void addLike(String getURL, final int id, final int actionLike, final String name){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Đã thêm "+ name +" vào danh sách yêu thích.",Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER,0,0);
                            toast.show();
                        }else{
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Lỗi. Vui lòng kiểm tra lại.",Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER,0,0);
                            toast.show();
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
                params.put("id", String.valueOf(id));
                params.put("actionLike", String.valueOf(actionLike));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
