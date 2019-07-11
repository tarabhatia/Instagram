package com.example.instagram.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.instagram.R;
import com.parse.ParseFile;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private List<Post> mPosts;
    Context context;

    public HomeAdapter(List<Post> posts) {
        mPosts = posts;
    }


    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.item_home, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(postView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder viewHolder, int i) {
        final Post post = mPosts.get(i);

        viewHolder.tvUser.setText(post.getUser().getUsername());
        viewHolder.tvCaption.setText(post.getDescription());
        ParseFile image = post.getImage();
        if (image != null) {
            Glide.with(context).load(image.getUrl()).into(viewHolder.ivPost);
        }

    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView ivPost;
        public ImageView ivUser;
        public TextView tvUser;
        public TextView tvCaption;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivPost = itemView.findViewById(R.id.ivPost);
            ivUser = itemView.findViewById(R.id.ivUser);
            tvCaption = itemView.findViewById(R.id.tvCaption);
            tvUser = itemView.findViewById(R.id.tvUser);

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {

        }

        //@Override
//        public void onClick(View v) {
//            int position = getAdapterPosition();
//            if (position != RecyclerView.NO_POSITION) {
//                Post post = mPosts.get(position);
//                Intent intent = new Intent(context, )
//            }
//
//        }
    }
}
