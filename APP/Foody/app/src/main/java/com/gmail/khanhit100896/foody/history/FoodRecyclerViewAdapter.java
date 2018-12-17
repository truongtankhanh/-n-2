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
import com.gmail.khanhit100896.foody.food.Food;
import com.gmail.khanhit100896.foody.like.FoodLikeDetailsActivity;
import com.gmail.khanhit100896.foody.main.GeocodingLocation;
import com.gmail.khanhit100896.foody.main.MapActivity;
import com.gmail.khanhit100896.foody.main.Point;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodRecyclerViewAdapter extends RecyclerView.Adapter<FoodRecyclerViewAdapter.FoodViewHolder> implements Filterable {

    /*
     * Khai báo biến cần thiết
     */
    private Context context;
    private List<Food> foodList;
    private List<Food> foodListFull;

    private String getURL = Config.getConfig().getPathUpdateTouchFood();
    /*
     */

    /*
     * Hàm Constructor
     */
    public FoodRecyclerViewAdapter(Context context, List<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
        this.foodListFull = new ArrayList<>();
        this.foodListFull.addAll(this.foodList);
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.cardview_item_food_touch,parent,false);
        view.startAnimation(AnimationUtils.loadAnimation(context,R.anim.anim_recycler_view));
        return new FoodRecyclerViewAdapter.FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FoodViewHolder holder, final int position) {
        // Gán giá trị cho thuộc tính tên và hình món ăn
        holder.txt_name_food.setText(this.foodList.get(position).getFood_name());
        if(this.foodList.get(position).getFood_price().equals("")){
            holder.txt_price_food.setText("Đang cập nhật");
        }else{
            holder.txt_price_food.setText(this.foodList.get(position).getFood_price());
        }
        Picasso.get().load(Config.getConfig().getPathLoadImgFood()
                .concat(this.foodList.get(position).getImg_address()))
                .into(holder.img_food);

        /*
         * Bắt sự kiện khi nhấp vào cardview để xem chi tiết món ăn
         */
        holder.cardview_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,FoodTouchDetailsActivity.class);
                intent.putExtra("ID",foodList.get(position).getId());
                intent.putExtra("FoodCode",foodList.get(position).getFood_code());
                intent.putExtra("FoodName",foodList.get(position).getFood_name());
                intent.putExtra("FoodImage",foodList.get(position).getImg_address());
                intent.putExtra("FoodAddress",foodList.get(position).getFood_address());
                intent.putExtra("FoodPrice",foodList.get(position).getFood_price());
                intent.putExtra("FoodKind",foodList.get(position).getKind_code());
                foodList.get(position).setAction_touch(0);
                intent.putExtra("ActionTouch",foodList.get(position).getAction_touch());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        /*
         */

        /*
         * Bắt sự kiện cho Popup Menu cardview
         */
        holder.iv_menu_food.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Context wrapper = new ContextThemeWrapper(context, R.style.MyPopupMenu);
                PopupMenu popupMenu = new PopupMenu(wrapper,holder.iv_menu_food);
                popupMenu.inflate(R.menu.menu_food_touch);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {

                            // Bắt sự kiện khi nhấp vào cardview để xem chi tiết món ăn
                            case R.id.action_details:
                                Intent intent0 = new Intent(context,FoodTouchDetailsActivity.class);
                                intent0.putExtra("ID",foodList.get(position).getId());
                                intent0.putExtra("class",FoodLikeDetailsActivity.class.getSimpleName());
                                intent0.putExtra("FoodName",foodList.get(position).getFood_name());
                                intent0.putExtra("FoodImage",foodList.get(position).getImg_address());
                                intent0.putExtra("FoodAddress",foodList.get(position).getFood_address());
                                intent0.putExtra("FoodPrice",foodList.get(position).getFood_price());
                                intent0.putExtra("FoodKind",foodList.get(position).getKind_code());
                                foodList.get(position).setAction_touch(0);
                                intent0.putExtra("ActionTouch",foodList.get(position).getAction_touch());
                                intent0.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent0);
                                break;

                            // Bát sự kiện chuyển đến MapActivity
                            case R.id.action_map:
                                Point point = new Point(GeocodingLocation.getLatitude(foodList.get(position).getFood_address(),context)
                                        ,GeocodingLocation.getLongitude(foodList.get(position).getFood_address(),context));

                                Intent intent2 = new Intent(context,MapActivity.class);
                                String className = FoodRecyclerViewAdapter.class.getSimpleName();
                                intent2.putExtra("context",className);
                                intent2.putExtra("name",foodList.get(position).getFood_name());
                                intent2.putExtra("image",foodList.get(position).getImg_address());
                                intent2.putExtra("address",foodList.get(position).getFood_address());
                                intent2.putExtra("latitude",String.valueOf(point.getLatitude()));
                                intent2.putExtra("longitude",String.valueOf(point.getLongitude()));
                                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent2);
                                break;

                            case R.id.action_distouch:
                                RequestQueue requestQueue = Volley.newRequestQueue(context);
                                StringRequest stringRequest = new StringRequest(Request.Method.POST, getURL,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                if(response.trim().equals("success")){
                                                    Toast toast = Toast.makeText(context,"Đã xóa " + foodList.get(position).getFood_name()
                                                            + " khỏi danh sách lịch sử.",Toast.LENGTH_SHORT);
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
                                        foodList.get(position).setAction_touch(0);
                                        Map<String, String> params = new HashMap<>();
                                        params.put("id", String.valueOf(foodList.get(position).getId()));
                                        params.put("actionTouch", String.valueOf(foodList.get(position).getAction_touch()));
                                        return params;
                                    }
                                };
                                requestQueue.add(stringRequest);
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
        return foodList.size();
    }
    /*
     */

    /*
     * Hàm lọc dữ liệu trên RecyclerView
     */
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                List<Food> list = new ArrayList<>();
                if(charSequence.toString().isEmpty()){
                    list.addAll(foodListFull);
                }
                else{
                    String filter = charSequence.toString().toLowerCase().trim();

                    for (Food food : foodListFull){
                        if(food.getFood_name().toLowerCase().contains(filter)){
                            list.add(food);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = list;

                return results;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                foodList.clear();
                foodList.addAll((Collection<? extends Food>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }

    /*
     * Class holder - ánh xạ các thuộc tính
     */
    static class FoodViewHolder extends RecyclerView.ViewHolder{
        ImageView img_food, iv_menu_food;
        TextView txt_name_food;
        TextView txt_price_food;

        CardView cardview_food;

        FoodViewHolder(View itemView) {
            super(itemView);

            img_food        = itemView.findViewById(R.id.img_food);
            iv_menu_food    = itemView.findViewById(R.id.iv_menu_food);
            txt_name_food   = itemView.findViewById(R.id.txt_name_food);
            txt_price_food  = itemView.findViewById(R.id.txt_price_food);
            cardview_food   = itemView.findViewById(R.id.cardview_food_touch);
        }
    }
}
