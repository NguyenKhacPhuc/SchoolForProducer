package com.example.schoolforproducer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ArticlesTitleAdapter extends RecyclerView.Adapter<ArticlesTitleAdapter.ArticleTitleViewHolder> {
    private ArrayList<String> titles;
    private Context context;
    public ArticlesTitleAdapter(Context context,ArrayList<String> titles){
        this.context = context;
        this.titles = titles;
    }
    @NonNull
    @Override
    public ArticleTitleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.articles_title_item,parent,false);
        return new ArticleTitleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleTitleViewHolder holder, int position) {
        holder.textView.setText(titles.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public static class ArticleTitleViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ArticleTitleViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.titles_brief);
        }
    }
}
