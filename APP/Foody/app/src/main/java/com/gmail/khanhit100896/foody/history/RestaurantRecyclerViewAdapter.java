package com.gmail.khanhit100896.foody.history;

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
import com.gmail.khanhit100896.foody.restaurant.Restaurant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantRecyclerViewAdapter extends RecyclerView.Adapter<RestaurantRecyclerViewAdapter.RestaurantViewHolder> {

    /*
     * Khai báo biến cần thiết
     */
    private Context context;
    private List<Restaurant> restaurantList;
    private List<Restaurant> restaurantListFull;

    private String getURL = Config.getConfig().getPathUpdateTouchRestaurant();
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

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.cardview_item_restaurant_touch,parent,false);
        view.startAnimation(AnimationUtils.loadAnimation(context,R.anim.anim_recycler_view));
        return new RestaurantViewHolder(view);
    }

    public void updateData(List<Restaurant> viewModels) {
        restaurantList.clear();
        restaurantList.addAll(viewModels);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull final RestaurantViewHolder holder, final int position) {
        // Gán giá trị cho thuộc tính tên và hình nhà hàng
        holder.txt_name_res.setText(this.restaurantList.get(position).getRes_name());
        holder.txt_num_branch.setText(this.restaurantList.get(position).getNum_of_branch() + " chi nhánh");
        Picasso.get().load(Config.getConfig().getPathLoadImgRes()
                .concat(this.restaurantList.get(position).getImg_address()))
                .into(holder.img_res);

        /*
         * Gửi dữ liệu đến RestaurantBranchTouchActivity
         */
        holder.cardview_restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,RestaurantBranchTouchActivity.class);
                intent.putExtra("class", RestaurantRecyclerViewAdapter.class.getSimpleName());
                intent.putExtra("ID",restaurantList.get(position).getId());
                intent.putExtra("ResCode",restaurantList.get(position).getRes_code());
                intent.putExtra("ResName",restaurantList.get(position).getRes_name());
                intent.putExtra("ResImage",restaurantList.get(position).getImg_address());
                intent.putExtra("NumOfBranch",restaurantList.get(position).getNum_of_branch());
                restaurantList.get(position).setAction_touch(0);
                intent.putExtra("ActionTouch",restaurantList.get(position).getAction_touch());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
                popupMenu.inflate(R.menu.menu_restaurant_touch);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_details:
                                Intent intent = new Intent(context,RestaurantBranchTouchActivity.class);
                                intent.putExtra("ID",restaurantList.get(position).getId());
                                intent.putExtra("ResCode",restaurantList.get(position).getRes_code());
                                intent.putExtra("ResName",restaurantList.get(position).getRes_name());
                                intent.putExtra("ResImage",restaurantList.get(position).getImg_address());
                                intent.putExtra("NumOfBranch",restaurantList.get(position).getNum_of_branch());
                                restaurantList.get(position).setAction_touch(0);
                                intent.putExtra("ActionTouch",restaurantList.get(position).getAction_touch());
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                                return true;

                            case R.id.action_distouch:
                                RequestQueue requestQueue = Volley.newRequestQueue(context);
                                StringRequest stringRequest = new StringRequest(Request.Method.POST, getURL,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                if(response.trim().equals("success")){
                                                    Toast toast = Toast.makeText(context,"Đã xóa "+ restaurantList.get(position).getRes_name()
                                                            + " khỏi danh sách lịch sử",Toast.LENGTH_SHORT);
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
                                        restaurantList.get(position).setAction_touch(0);
                                        Map<String, String> params = new HashMap<>();
                                        params.put("id", String.valueOf(restaurantList.get(position).getId()));
                                        params.put("actionTouch", String.valueOf(restaurantList.get(position).getAction_touch()));
                                        return params;
                                    }
                                };
                                requestQueue.add(stringRequest);
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

    @Override
    public int getItemCount() {
        return restaurantList.size();
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
            cardview_restaurant = itemView.findViewById(R.id.cardview_restaurant_touch);
        }
    }
}
