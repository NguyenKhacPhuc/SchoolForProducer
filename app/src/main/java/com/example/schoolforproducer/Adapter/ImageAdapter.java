package com.example.schoolforproducer.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.schoolforproducer.R;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    ArrayList<String> urls;
    Context context;

    public ImageAdapter(Context context, ArrayList<String> urls){
        this.context = context;
        this.urls = urls;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.image_item,parent,false);
        return new ImageViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
            String url = urls.get(position);
           Glide.with(context).load(url).into(holder.imageView);
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
