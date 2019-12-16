package com.example.sheanwoey_yifan.Pokemon_Team;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sheanwoey_yifan.Model.PokeDetail;
import com.example.sheanwoey_yifan.Pokemon_Detail.PokeDetailActivity;
import com.example.sheanwoey_yifan.Pokemon_List.PokemonListActivity;
import com.example.sheanwoey_yifan.R;

import java.io.Serializable;
import java.util.ArrayList;


public class PokemonSelectorAdapter extends RecyclerView.Adapter<PokemonSelectorAdapter.ViewHolder> {

    private static String TAG = PokemonSelectorAdapter.class.getSimpleName();

    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private ArrayList<PokeDetail> pokeDetails;
    private Context context;

    // data is passed into the constructor
    PokemonSelectorAdapter(Context context, ArrayList<PokeDetail> pokeDetails) {
        this.mInflater = LayoutInflater.from(context);
        this.pokeDetails = pokeDetails;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.pokemon_list_adapter, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d(TAG, "Adapter Run: "+ TAG);
        Glide.with(context).load(pokeDetails.get(position).getSprite()).into(holder.pokeIcon);
        holder.pokeName.setText(pokeDetails.get(position).getName());
        holder.pokeType1.setText(pokeDetails.get(position).getTypes()[0]);
        if (pokeDetails.get(position).getTypes()[1]!="none") {
            holder.pokeType2.setText(pokeDetails.get(position).getTypes()[1]);
        }
        else {
            holder.pokeType2.setVisibility(View.INVISIBLE);
        }
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return pokeDetails.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView pokeIcon;
        TextView pokeName, pokeType1,pokeType2;
        ConstraintLayout pokeContainer;

        ViewHolder(View itemView) {
            super(itemView);
            pokeIcon = itemView.findViewById(R.id.pokeIcon);
            pokeName = itemView.findViewById(R.id.pokeName);
            pokeType1 = itemView.findViewById(R.id.pokeType1);
            pokeType2 = itemView.findViewById(R.id.pokeType2);
            pokeContainer= itemView.findViewById(R.id.pokeContainer);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    PokeDetail getItem(int id) {
        return pokeDetails.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public void update(ArrayList<PokeDetail> pokeDetails) {
        this.pokeDetails.clear();
        this.pokeDetails.addAll(pokeDetails);
        notifyDataSetChanged();
    }
}