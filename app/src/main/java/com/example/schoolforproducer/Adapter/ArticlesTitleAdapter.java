package com.example.schoolforproducer.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolforproducer.Interfaces.IItemClick;
import com.example.schoolforproducer.Interfaces.TittleItemClick;
import com.example.schoolforproducer.R;

import java.util.ArrayList;

public class ArticlesTitleAdapter extends RecyclerView.Adapter<ArticlesTitleAdapter.ArticleTitleViewHolder> {
    private ArrayList<String> titles;
    private Context context;
    private TittleItemClick tittleItemClick;
    public ArticlesTitleAdapter(Context context,ArrayList<String> titles,TittleItemClick tittleItemClick){
        this.context = context;
        this.titles = titles;
        this.tittleItemClick = tittleItemClick;
    }
    @NonNull
    @Override
    public ArticleTitleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.articles_title_item,parent,false);
        return new ArticleTitleViewHolder(v,tittleItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleTitleViewHolder holder, int position) {
        holder.textView.setText(titles.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public static class ArticleTitleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        TittleItemClick tittleItemClick;
        public ArticleTitleViewHolder(@NonNull View itemView, TittleItemClick iItemClick) {
            super(itemView);
            textView = itemView.findViewById(R.id.titles_brief);
            this.tittleItemClick = iItemClick;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            tittleItemClick.getTitlePosition(getAdapterPosition());
        }
    }
}
