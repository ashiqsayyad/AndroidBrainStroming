package com.ashsample.androidconcepts.databinding;



import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ashsample.androidconcepts.R;

import java.util.List;


public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> implements CustomClickListener {

    private List<DataModel> dataModelList;
    private Context context;

    public MyRecyclerViewAdapter(List<DataModel> dataModelList, Context ctx) {
        this.dataModelList = dataModelList;
        context = ctx;
    }

    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        ItemRowBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_row, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DataModel dataModel = dataModelList.get(position);
        holder.bind(dataModel);
        holder.itemRowBinding.setItemClickListener(this);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ItemRowBinding itemRowBinding;

        public ViewHolder(ItemRowBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }

        public void bind(Object obj) {
            itemRowBinding.setVariable(BR.model, obj);
           // itemRowBinding.setModel((DataModel)obj);
            itemRowBinding.executePendingBindings();
        }
    }

    public void cardClicked(DataModel f) {
        Toast.makeText(context, "You clicked " + f.androidName,
                Toast.LENGTH_LONG).show();
        Intent temp = new Intent(context, ProductDetailsUsingDataBindingActivity.class);
        temp.putExtra("data",f);
        context.startActivity(temp);
    }
}


