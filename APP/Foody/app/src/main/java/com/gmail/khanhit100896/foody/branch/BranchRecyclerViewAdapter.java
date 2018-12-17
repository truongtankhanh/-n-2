package com.gmail.khanhit100896.foody.branch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gmail.khanhit100896.foody.R;
import com.gmail.khanhit100896.foody.config.Config;
import com.gmail.khanhit100896.foody.main.GeocodingLocation;
import com.gmail.khanhit100896.foody.main.MapActivity;
import com.gmail.khanhit100896.foody.main.Point;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BranchRecyclerViewAdapter extends RecyclerView.Adapter<BranchRecyclerViewAdapter.BranchViewHolder>
        implements Filterable {

    /*
     * Khai báo biến cần thiết
     */
    private String getURL = Config.getConfig().getPathUpdateBranch();
    private String getURLTouch = Config.getConfig().getPathUpdateTouchBranch();
    private Context context;
    private List<Branch> branchList;
    private List<Branch> branchListFull;
    /*
     */

    /*
     * Hàm Constructor
     */
    public BranchRecyclerViewAdapter(Context context, List<Branch> branchList) {
        this.context = context;
        this.branchList = branchList;
        branchListFull = new ArrayList<>();
        branchListFull.addAll(branchList);
    }

    /*
     * Ánh xạ và khởi tạo viewholder
     */
    @NonNull
    @Override
    public BranchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.cardview_item_branch,parent,false);
        view.startAnimation(AnimationUtils.loadAnimation(context,R.anim.anim_recycler_view));
        return new BranchViewHolder(view);
    }
    /*
     */

    @Override
    public void onBindViewHolder(@NonNull final BranchViewHolder holder,
                                 @SuppressLint("RecyclerView") final int position) {

        Picasso.get().load(Config.getConfig().getPathLoadImgBranch()
                .concat(this.branchList.get(position).getBranch_image()))
                .into(holder.img_branch);
        holder.txt_name_branch.setText(this.branchList.get(position).getBranch_name());
        if(this.branchList.get(position).getBranch_price().equals("")){
            holder.txt_price_branch.setText("Đang cập nhật");
        }else{
            holder.txt_price_branch.setText(this.branchList.get(position).getBranch_price());
        }

        /*
         * Gửi dữ liệu đến BranchDetailsActivity
         */
        holder.cardview_branch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                branchList.get(position).setAction_touch(1);
                saveHistory(getURLTouch,branchList.get(position).getId(),
                        branchList.get(position).getAction_touch());
                Intent intent = new Intent(context,BranchDetailsActivity.class);
                intent.putExtra("class",BranchDetailsActivity.class.getSimpleName());
                intent.putExtra("ID",branchList.get(position).getId());
                intent.putExtra("BranchCode",branchList.get(position).getBranch_code());
                intent.putExtra("BranchName",branchList.get(position).getBranch_name());
                intent.putExtra("BranchImage",branchList.get(position).getBranch_image());
                intent.putExtra("BranchAddress",branchList.get(position).getBranch_address());
                intent.putExtra("BranchPrice",branchList.get(position).getBranch_price());
                intent.putExtra("BranchOpenTime",branchList.get(position).getBranch_opentime());
                branchList.get(position).setAction_like(1);
                intent.putExtra("ActionLike",branchList.get(position).getAction_like());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        /*
         */

        /*
         * Tạo menu cho cardview
         */
        holder.iv_menu_branch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Context wrapper = new ContextThemeWrapper(context, R.style.MyPopupMenu);
                PopupMenu popupMenu = new PopupMenu(wrapper,holder.iv_menu_branch);
                popupMenu.inflate(R.menu.menu_food);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_details:
                                Intent intent0 = new Intent(context,BranchDetailsActivity.class);
                                intent0.putExtra("ID",branchList.get(position).getId());
                                intent0.putExtra("BranchName",branchList.get(position).getBranch_name());
                                intent0.putExtra("BranchImage",branchList.get(position).getBranch_image());
                                intent0.putExtra("BranchAddress",branchList.get(position).getBranch_address());
                                intent0.putExtra("BranchPrice",branchList.get(position).getBranch_price());
                                intent0.putExtra("BranchOpenTime",branchList.get(position).getBranch_opentime());
                                branchList.get(position).setAction_like(1);
                                intent0.putExtra("ActionLike",branchList.get(position).getAction_like());
                                intent0.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent0);
                                break;

                            case R.id.action_map:
                                Point point = new Point(
                                        GeocodingLocation.getLatitude(branchList.get(position).getBranch_address(),context)
                                        ,GeocodingLocation.getLongitude(branchList.get(position).getBranch_address(),context));

                                Intent intent2 = new Intent(context,MapActivity.class);
                                String className = BranchRecyclerViewAdapter.class.getSimpleName();
                                intent2.putExtra("context",className);
                                intent2.putExtra("name",branchList.get(position).getBranch_name());
                                intent2.putExtra("address",branchList.get(position).getBranch_address());
                                intent2.putExtra("latitude",String.valueOf(point.getLatitude()));
                                intent2.putExtra("longitude",String.valueOf(point.getLongitude()));
                                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent2);
                                break;

                            case R.id.action_like:
                                branchList.get(position).setAction_like(1);
                                addLike(getURL,branchList.get(position).getId(),
                                        branchList.get(position).getAction_like(),branchList.get(position).getBranch_name());
                                return true;
                            default: break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        /*
         */
    }

    @Override
    public int getItemCount() {
        return this.branchList.size();
    }

    /*
     * Hàm thêm vào danh sách yêu thích
     */
    private void addLike(String getURL, final int id, final int actionLike, final String name){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            Toast toast = Toast.makeText(context,"Đã thêm "+ name +" vào danh sách yêu thích.",Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER,0,0);
                            toast.show();
                        }else{
                            Toast toast = Toast.makeText(context,"Lỗi. Vui lòng kiểm tra lại.",Toast.LENGTH_SHORT);
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

    // Hàm lưu lịch sử
    private void saveHistory(String getURL, final int id, final int actionTouch){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        if(response.trim().equals("success")){
//                            Toast.makeText(context,"Đã xóa " + name + " khỏi danh sách lịch sử.",Toast.LENGTH_SHORT).show();
//                        }else{
//                            Toast.makeText(context,"Lỗi. Vui lòng kiểm tra lại.",Toast.LENGTH_SHORT).show();
//                        }
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
                params.put("actionTouch", String.valueOf(actionTouch));

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    /*
     * Hàm lọc dữ liệu trên RecyclerView
     */
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                List<Branch> list = new ArrayList<>();
                if(charSequence.toString().isEmpty()){
                    list.addAll(branchListFull);
                }
                else{
                    String filter = charSequence.toString().toLowerCase().trim();

                    for (Branch branch : branchListFull){
                        if(branch.getBranch_name().toLowerCase().contains(filter)){
                            list.add(branch);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = list;

                return results;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                branchList.clear();
                branchList.addAll((Collection<? extends Branch>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }

    /*
     * Class holder - ánh xạ các thuộc tính
     */
    static class BranchViewHolder extends RecyclerView.ViewHolder{
        ImageView img_branch, iv_menu_branch;
        TextView txt_name_branch;
        TextView txt_price_branch;

        CardView cardview_branch;

        BranchViewHolder(View itemView) {
            super(itemView);

            img_branch          = itemView.findViewById(R.id.img_branch);
            iv_menu_branch      = itemView.findViewById(R.id.iv_menu_branch);
            txt_name_branch     = itemView.findViewById(R.id.txt_name_branch);
            txt_price_branch    = itemView.findViewById(R.id.txt_price_branch);
            cardview_branch     = itemView.findViewById(R.id.cardview_branch);
        }
    }
}
