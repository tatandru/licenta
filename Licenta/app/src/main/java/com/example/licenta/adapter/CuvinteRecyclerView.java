package com.example.licenta.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.licenta.R;

import java.util.ArrayList;

public class CuvinteRecyclerView extends RecyclerView.Adapter<CuvinteRecyclerView.ItemsViewHolder> {
    private final LayoutInflater inflater;
    private ArrayList<String> cuvinte;
    private ItemClickListener itemClickListener;


    public CuvinteRecyclerView(ArrayList<String> cuvant, Context context) {
        this.cuvinte = cuvant;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CuvinteRecyclerView.ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemsViewHolder(inflater.inflate(R.layout.optiune_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CuvinteRecyclerView.ItemsViewHolder holder, int position) {
        final int pos=holder.getAdapterPosition();
        final String cuvant = this.cuvinte.get(position);
        holder.textItem.setText(cuvant);
        holder.locationItemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null) {
                    itemClickListener.onClick(cuvant,pos);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cuvinte.size();
    }

    class ItemsViewHolder extends RecyclerView.ViewHolder {
        private final View locationItemRoot;
        private final TextView textItem;



        ItemsViewHolder(final View itemView) {
            super(itemView);
            this.locationItemRoot = itemView.findViewById(R.id.cl_optiune_root_item);
            this.textItem = itemView.findViewById(R.id.textOptiune);



        }
    }

    public interface ItemClickListener {
        void onClick(String cuvant,int position);
    }

    public void setCategoryClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

}
