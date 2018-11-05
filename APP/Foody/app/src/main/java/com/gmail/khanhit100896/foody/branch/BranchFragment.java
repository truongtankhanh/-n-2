package com.gmail.khanhit100896.foody.branch;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class BranchFragment extends Fragment {

    String getURL = Config.getConfig().getPathGetAllBranch();

    RecyclerView recyclerBranch;
    List<Branch> branchList;
    BranchRecyclerViewAdapter adapter;

    public BranchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_branch, container, false);

        View view = inflater.inflate(R.layout.fragment_branch, container, false);

        this.recyclerBranch = view.findViewById(R.id.recycler_branch);
        this.branchList = new ArrayList<>();

        getAllFood(this.getURL);

        this.adapter = new BranchRecyclerViewAdapter(getActivity(),this.branchList);
        this.recyclerBranch.setLayoutManager(new GridLayoutManager(getActivity(),2));
        this.recyclerBranch.setAdapter(this.adapter);

        return view;
    }

    private void getAllFood(String getURL) {
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getURL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        branchList.clear();
                        for (int i=0;i<response.length();i++){

                            try {
                                JSONObject object = response.getJSONObject(i);
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
