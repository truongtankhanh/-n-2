package com.gmail.khanhit100896.foody.history;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.gmail.khanhit100896.foody.R;
import com.gmail.khanhit100896.foody.branch.Branch;
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
public class BranchHistoryFragment extends Fragment {

    /*
     * Khai báo biến cần thiết
     */
    protected String getURL = Config.getConfig().getPathGetAllBranch();
    protected RecyclerView recyclerBranch;
    protected List<Branch> branchList;
    protected BranchRecyclerViewAdapter adapter;

    protected TextView txt_thong_bao;
    /*
     */


    public BranchHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        /*
         * Ánh xạ và khởi tạo view, các biến đã khai báo
         */
        View view = inflater.inflate(R.layout.fragment_branch_history, container, false);
        this.recyclerBranch = view.findViewById(R.id.recycler_branch);
        this.branchList     = new ArrayList<>();
        this.txt_thong_bao  = view.findViewById(R.id.txt_thong_bao);
        txt_thong_bao.setVisibility(View.GONE);
        /*
         */

        // Gọi hàm lấy tất cả món ăn
        getAllFood(this.getURL);

        return view;
    }

    /*
     * Hàm lấy tất cả món ăn từ CSDL và hiển thị theo dạng lưới
     */
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
                                if(object.getInt("ActionTouch") == 1) {
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

                        /*
                         * Đổ dữ liệu đồ ăn vặt lên RecyclerView
                         */
                        if(branchList.size() == 0){
                            recyclerBranch.setVisibility(View.GONE);
                            txt_thong_bao.setVisibility(View.VISIBLE);
                        }else{
                            recyclerBranch.setVisibility(View.VISIBLE);
                            adapter = new BranchRecyclerViewAdapter(getActivity(),branchList);
                            recyclerBranch.setLayoutManager(new GridLayoutManager(getActivity(),2));
                            recyclerBranch.setAdapter(adapter);
                        }
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
