package com.gmail.khanhit100896.foody.food;

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

public class FoodRecyclerViewAdapter extends RecyclerView.Adapter<FoodRecyclerViewAdapter.FoodViewHolder> implements Filterable {

    /*
     * Khai báo biến cần thiết
     */
    private Context context;
    private List<Food> foodList;
    private List<Food> foodListFull;

    private String getURL = Config.getConfig().getPathUpdateFood();
    private String getURLTouch = Config.getConfig().getPathUpdateTouchFood();
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

    /*
     * Ánh xạ và khởi tạo view, các biến đã khai báo
     */
    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(context).inflate(R.layout.cardview_item_food,parent,false);
        view.startAnimation(AnimationUtils.loadAnimation(context,R.anim.anim_recycler_view));
        return new FoodViewHolder(view);
    }
    /*
     */

    @Override
    public void onBindViewHolder(@NonNull final FoodViewHolder holder,
                                 @SuppressLint("RecyclerView") final int position) {

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
                foodList.get(position).setAction_touch(1);
                saveHistory(getURLTouch,foodList.get(position).getId(),foodList.get(position).getAction_touch(),foodList.get(position).getFood_name());
                Intent intent = new Intent(context,FoodDetailsActivity.class);
                intent.putExtra("ID",foodList.get(position).getId());
                intent.putExtra("FoodCode",foodList.get(position).getFood_code());
                intent.putExtra("FoodName",foodList.get(position).getFood_name());
                intent.putExtra("FoodImage",foodList.get(position).getImg_address());
                intent.putExtra("FoodAddress",foodList.get(position).getFood_address());
                intent.putExtra("FoodPrice",foodList.get(position).getFood_price());
                intent.putExtra("FoodKind",foodList.get(position).getKind_code());
                foodList.get(position).setAction_like(1);
                intent.putExtra("ActionLike",foodList.get(position).getAction_like());
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
                popupMenu.inflate(R.menu.menu_food);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {

                            // Bắt sự kiện khi nhấp vào cardview để xem chi tiết món ăn
                            case R.id.action_details:
                                Intent intent0 = new Intent(context,FoodDetailsActivity.class);
                                intent0.putExtra("class",FoodDetailsActivity.class.getSimpleName());
                                intent0.putExtra("ID",foodList.get(position).getId());
                                intent0.putExtra("FoodName",foodList.get(position).getFood_name());
                                intent0.putExtra("FoodImage",foodList.get(position).getImg_address());
                                intent0.putExtra("FoodAddress",foodList.get(position).getFood_address());
                                intent0.putExtra("FoodPrice",foodList.get(position).getFood_price());
                                intent0.putExtra("FoodKind",foodList.get(position).getKind_code());
                                foodList.get(position).setAction_like(1);
                                intent0.putExtra("ActionLike",foodList.get(position).getAction_like());
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

                            case R.id.action_like:
                                foodList.get(position).setAction_like(1);
                                addLike(getURL, foodList.get(position).getId(),
                                        foodList.get(position).getAction_like(),foodList.get(position).getFood_name());
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
        return this.foodList.size();
    }

    // Hàm lưu lịch sử
    private void saveHistory(String getURL, final int id, final int actionTouch, final String name){
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
            cardview_food   = itemView.findViewById(R.id.cardview_food);
        }
    }
}
