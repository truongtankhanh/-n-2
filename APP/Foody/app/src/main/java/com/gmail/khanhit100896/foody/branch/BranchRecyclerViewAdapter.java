package com.gmail.khanhit100896.foody.branch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.gmail.khanhit100896.foody.R;
import com.gmail.khanhit100896.foody.config.Config;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BranchRecyclerViewAdapter extends RecyclerView.Adapter<BranchRecyclerViewAdapter.BranchViewHolder> {

    private Context context;
    private List<Branch> branchList;

    public BranchRecyclerViewAdapter(Context context, List<Branch> branchList) {
        this.context = context;
        this.branchList = branchList;
    }

    @NonNull
    @Override
    public BranchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.cardview_item_branch,parent,false);

        return new BranchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BranchViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Picasso.get().load(Config.getConfig().getPathLoadImgBranch().concat(this.branchList.get(position).getBranch_image()))
                .into(holder.img_branch);
        holder.txt_name_branch.setText(this.branchList.get(position).getBranch_name());

        holder.cardview_branch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,BranchDetailsActivity.class);
                intent.putExtra("BranchName",branchList.get(position).getBranch_name());
                intent.putExtra("BranchImage",branchList.get(position).getBranch_image());
                intent.putExtra("BranchAddress",branchList.get(position).getBranch_address());
                intent.putExtra("BranchPrice",branchList.get(position).getBranch_price());
                intent.putExtra("BranchOpenTime",branchList.get(position).getBranch_opentime());
                context.startActivity(intent);
            }
        });

        holder.iv_menu_branch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context,holder.iv_menu_branch);
                popupMenu.inflate(R.menu.main);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_settings:
                                context.startActivity(new Intent(Settings.ACTION_SETTINGS));
                                break;

                            default: break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.branchList.size();
    }

    static class BranchViewHolder extends RecyclerView.ViewHolder{
        ImageView img_branch, iv_menu_branch;
        TextView txt_name_branch;

        CardView cardview_branch;

        BranchViewHolder(View itemView) {
            super(itemView);

            img_branch = itemView.findViewById(R.id.img_branch);
            iv_menu_branch = itemView.findViewById(R.id.iv_menu_branch);
            txt_name_branch = itemView.findViewById(R.id.txt_name_branch);
            cardview_branch = itemView.findViewById(R.id.cardview_branch);
        }
    }
}
