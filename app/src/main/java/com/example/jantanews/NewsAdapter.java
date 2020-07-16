package com.example.jantanews;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private final List<DocumentChange> mUsers;
    private final Context mContext;

    public NewsAdapter(List<DocumentChange> mUsers, Context mContext) {
        this.mUsers = mUsers;
        this.mContext = mContext;

    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.news_item, parent, false);
        //mContext = parent.getContext();
        return new NewsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        try {
            holder.heading_textView.setText(mUsers.get(position).getDocument().get("heading_text").toString());
            holder.news_textView.setText(mUsers.get(position).getDocument().get("news_text").toString());
        } catch (Exception e) {
            Log.d("#fail",e.getMessage().toString());
        }

    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView heading_textView, news_textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            heading_textView = itemView.findViewById(R.id.heading_textView);
            news_textView = itemView.findViewById(R.id.news_textView);
        }
    }
}
