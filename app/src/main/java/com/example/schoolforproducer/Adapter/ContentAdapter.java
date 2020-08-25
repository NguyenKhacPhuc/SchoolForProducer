package com.example.schoolforproducer.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.schoolforproducer.Model.Articles;
import com.example.schoolforproducer.R;

import java.util.ArrayList;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentHolder> {
    Context context;
    ArrayList<Articles> articles;
    public ContentAdapter(Context context,ArrayList<Articles> articles){
        this.context = context;
        this.articles = articles;
    }
    @NonNull
    @Override
    public ContentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.content_holder,parent,false);

        return new ContentHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentHolder holder, int position) {
            Articles articleObj = articles.get(position);
            Glide.with(context).load(articleObj.getUrlToImage()).into(holder.contentImage);
            holder.content.setText(articleObj.getContent());
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ContentHolder extends RecyclerView.ViewHolder{
        ImageView contentImage;
        TextView content;
        public ContentHolder(@NonNull View itemView) {
            super(itemView);
            contentImage = itemView.findViewById(R.id.imageContent);
            content = itemView.findViewById(R.id.content);
        }
    }
}
