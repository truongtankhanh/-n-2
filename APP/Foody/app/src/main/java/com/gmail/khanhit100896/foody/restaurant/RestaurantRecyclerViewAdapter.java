package com.gmail.khanhit100896.foody.restaurant;

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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantRecyclerViewAdapter extends RecyclerView.Adapter<RestaurantRecyclerViewAdapter.RestaurantViewHolder>
                                            implements Filterable {

    /*
     * Khai báo biến cần thiết
     */
    private Context context;
    private List<Restaurant> restaurantList;
    private List<Restaurant> restaurantListFull;

    private String getURL = Config.getConfig().getPathUpdateRestaurant();
    private String getURLTouch = Config.getConfig().getPathUpdateTouchRestaurant();
    /*
     */

    /*
     * Hàm Constructor
     */
    public RestaurantRecyclerViewAdapter(Context context, List<Restaurant> restaurantList) {
        this.context = context;
        this.restaurantList = restaurantList;
        restaurantListFull = new ArrayList<>();
        restaurantListFull.addAll(restaurantList);
    }

    /*
     * Ánh xạ và khởi tạo viewholder
     */
    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(context).inflate(R.layout.cardview_item_restaurant,parent,false);
        view.startAnimation(AnimationUtils.loadAnimation(context,R.anim.anim_recycler_view));
        return new RestaurantViewHolder(view);
    }
    /*
     */

    @Override
    public void onBindViewHolder(@NonNull final RestaurantViewHolder holder,
                                 @SuppressLint("RecyclerView") final int position) {

        // Gán giá trị cho thuộc tính tên và hình nhà hàng
        holder.txt_name_res.setText(this.restaurantList.get(position).getRes_name());
        if(this.restaurantList.get(position).getNum_of_branch().equals("")){
            holder.txt_num_branch.setText("Đang cập nhật");
        }else{
            holder.txt_num_branch.setText(this.restaurantList.get(position).getNum_of_branch() + " chi nhánh");
        }
        Picasso.get().load(Config.getConfig().getPathLoadImgRes()
                .concat(this.restaurantList.get(position).getImg_address()))
                .into(holder.img_res);

        int lineCount = holder.txt_name_res.getLineCount();
        if(lineCount == 1){
            //holder.txt_name_res.setMaxLines(1);
            holder.txt_name_res.setPaddingRelative(0,10,0,0);
        }

        /*
         * Gửi dữ liệu đến RestaurantBranchActivity
         */
        holder.cardview_restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restaurantList.get(position).setAction_touch(1);
                saveHistory(getURLTouch,restaurantList.get(position).getId(),
                        restaurantList.get(position).getAction_touch(),restaurantList.get(position).getRes_name());
                Intent intent = new Intent(context,RestaurantBranchActivity.class);
                intent.putExtra("class",RestaurantRecyclerViewAdapter.class.getSimpleName());
                intent.putExtra("ID",restaurantList.get(position).getId());
                intent.putExtra("ResCode",restaurantList.get(position).getRes_code());
                intent.putExtra("ResName",restaurantList.get(position).getRes_name());
                intent.putExtra("ResImage",restaurantList.get(position).getImg_address());
                intent.putExtra("NumOfBranch",restaurantList.get(position).getNum_of_branch());
                restaurantList.get(position).setAction_like(1);
                intent.putExtra("ActionLike",restaurantList.get(position).getAction_like());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //Toast.makeText(context,holder.txt_name_res.getLineCount() + "",Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });
        /*
         */

        /*
         * Tạo menu cho cardview
         */
        holder.iv_menu_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context wrapper = new ContextThemeWrapper(context, R.style.MyPopupMenu);
                //PopupMenu popup = new PopupMenu(wrapper, view);
                PopupMenu popupMenu = new PopupMenu(wrapper,holder.iv_menu_res);
                popupMenu.inflate(R.menu.menu_restaurant);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_details:
                                Intent intent = new Intent(context,RestaurantBranchActivity.class);
                                intent.putExtra("ID",restaurantList.get(position).getId());
                                intent.putExtra("ResCode",restaurantList.get(position).getRes_code());
                                intent.putExtra("ResName",restaurantList.get(position).getRes_name());
                                intent.putExtra("ResImage",restaurantList.get(position).getImg_address());
                                intent.putExtra("NumOfBranch",restaurantList.get(position).getNum_of_branch());
                                restaurantList.get(position).setAction_like(1);
                                intent.putExtra("ActionLike",restaurantList.get(position).getAction_like());
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                                return true;

                            case R.id.action_love:
                                restaurantList.get(position).setAction_like(1);
                                addLike(getURL,restaurantList.get(position).getId(),
                                        restaurantList.get(position).getAction_like(),
                                        restaurantList.get(position).getRes_name());
                                return true;

                            default: return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });
        /*
         */
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

    @Override
    public int getItemCount() {
        return this.restaurantList.size();
    }

    // Hàm lưu lịch sử
    private void saveHistory(String getURL, final int id, final int actionTouch, final String name){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        if(response.trim().equals("success")){
//                            Toast.makeText(context,"Đã thêm " + name + " khỏi danh sách lịch sử.",Toast.LENGTH_SHORT).show();
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
                List<Restaurant> list = new ArrayList<>();
                if(charSequence.toString().isEmpty()){
                    list.addAll(restaurantListFull);
                }
                else{
                    String filter = charSequence.toString().toLowerCase().trim();

                    for (Restaurant restaurant : restaurantListFull){
                        if(restaurant.getRes_name().toLowerCase().contains(filter)){
                            list.add(restaurant);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = list;

                return results;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                restaurantList.clear();
                restaurantList.addAll((Collection<? extends Restaurant>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }

    /*
     * Class holder - ánh xạ các thuộc tính
     */
    static class RestaurantViewHolder extends RecyclerView.ViewHolder{
        ImageView img_res, iv_menu_res;
        TextView txt_name_res;
        TextView txt_num_branch;

        CardView cardview_restaurant;

        RestaurantViewHolder(View itemView) {
            super(itemView);

            img_res = itemView.findViewById(R.id.img_res);
            iv_menu_res = itemView.findViewById(R.id.iv_menu_res);
            txt_name_res = itemView.findViewById(R.id.txt_name_res);
            txt_num_branch = itemView.findViewById(R.id.txt_num_branch);
            cardview_restaurant = itemView.findViewById(R.id.cardview_restaurant);
        }
    }
}
