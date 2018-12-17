package com.gmail.khanhit100896.foody.comment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.khanhit100896.foody.R;
import com.gmail.khanhit100896.foody.config.RoundedTransformation;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private Context context;
    private List<Comment> commentList;

    public CommentAdapter(Context context, List<Comment> commentList) {
        this.context = context;
        this.commentList = commentList;
    }


    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.cardview_comment,parent,false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Picasso.get().load(commentList.get(position).getUser().getPhotoUri())
                .transform(new RoundedTransformation(60, 0))
                .into(holder.img_user);
        holder.txt_user_name.setText(commentList.get(position).getUser().getUserName());
        holder.txt_create_time.setText(commentList.get(position).getCreateTime());
        holder.txt_comment.setText(commentList.get(position).getComment());
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    static class CommentViewHolder extends RecyclerView.ViewHolder{

        ImageView img_user;
        TextView txt_user_name;
        TextView txt_create_time;
        TextView txt_comment;
        CardView cardview_comment;

        CommentViewHolder(View itemView) {
            super(itemView);

            img_user = itemView.findViewById(R.id.img_user);
            txt_user_name = itemView.findViewById(R.id.txt_user_name);
            txt_create_time = itemView.findViewById(R.id.txt_create_time);
            txt_comment = itemView.findViewById(R.id.txt_comment);
            cardview_comment = itemView.findViewById(R.id.cardview_comment);
        }
    }
}
