package com.gmail.khanhit100896.foody.restaurant;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
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
import java.util.List;
import java.util.Objects;

public class RestaurantBranchActivity extends AppCompatActivity {

    protected ImageView imgRes;
    protected TextView txtResName;

    private Intent intent;
    private RecyclerView recyclerBranch;
    private List<Branch> branchList;
    private BranchRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_branch);

        init();

        String resCode = Objects.requireNonNull(intent.getExtras()).getString("ResCode");
        getAllKind(Config.getConfig().getPathGetAllBranch(),resCode);

        this.adapter = new BranchRecyclerViewAdapter(getApplicationContext(),this.branchList);
        this.recyclerBranch.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        this.recyclerBranch.setAdapter(this.adapter);
    }

    @SuppressLint("SetTextI18n")
    private void init() {
        this.imgRes = findViewById(R.id.img_res_branch);
        this.txtResName = findViewById(R.id.txt_name_res_branch);
        this.recyclerBranch = findViewById(R.id.recycler_branch);
        this.branchList = new ArrayList<>();

        intent = getIntent();
        String image = Objects.requireNonNull(intent.getExtras()).getString("ResImage");
        String name = intent.getExtras().getString("ResName");
        String numOfBranch = intent.getExtras().getString("NumOfBranch");

        if (image != null) {
            Picasso.get().load(Config.getConfig().getPathLoadImgRes().concat(image))
                    .placeholder(R.drawable.search_mages_icon).into(this.imgRes);
        }
        this.txtResName.setText(name + " - " + numOfBranch + " chi nhánh.");
    }

    private void getAllKind(String getURL, final String resCode) {
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
}
