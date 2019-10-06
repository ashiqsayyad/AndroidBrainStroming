package com.ashsample.androidconcepts.recycleviews.pojos;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ashsample.androidconcepts.R;

import java.util.ArrayList;

public class MainRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<MainRecycleViewItem> listitems;
    public MainRecycleViewAdapter(ArrayList<MainRecycleViewItem> listitems){
        this.listitems = listitems;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mainlistitem,viewGroup,false);

       return new MainRecycleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((MainRecycleViewHolder)viewHolder).title .setText(listitems.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public  class MainRecycleViewHolder extends RecyclerView.ViewHolder{
         TextView title;

        public MainRecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.mainitemtitle);
        }
    }
}
