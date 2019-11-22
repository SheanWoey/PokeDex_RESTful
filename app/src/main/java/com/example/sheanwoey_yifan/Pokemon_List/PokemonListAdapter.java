package com.example.sheanwoey_yifan.Pokemon_List;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sheanwoey_yifan.Model.PokeList;
import com.example.sheanwoey_yifan.R;

import java.util.ArrayList;


public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.ViewHolder> {

    private static String TAG = PokemonListActivity.class.getSimpleName();

    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private ArrayList<PokeList> pokeList;
    private Context context;

    // data is passed into the constructor
    PokemonListAdapter(Context context, ArrayList<PokeList> pokeList) {
        this.mInflater = LayoutInflater.from(context);
        this.pokeList = pokeList;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "Adapter Run: "+ TAG);
        Glide.with(context).load(pokeList.get(position).getIcon()).into(holder.pokeIcon);
        holder.pokeName.setText(pokeList.get(position).getName());
        holder.pokeType1.setText(pokeList.get(position).getType()[0]);
        if (pokeList.get(position).getType()[1]!="none") {
            holder.pokeType2.setText(pokeList.get(position).getType()[1]);
        }
        else {
            holder.pokeType2.setVisibility(View.INVISIBLE);
        }
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return pokeList.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView pokeIcon;
        TextView pokeName, pokeType1,pokeType2;

        ViewHolder(View itemView) {
            super(itemView);
            pokeIcon = itemView.findViewById(R.id.pokeIcon);
            pokeName = itemView.findViewById(R.id.pokeName);
            pokeType1 = itemView.findViewById(R.id.pokeType1);
            pokeType2 = itemView.findViewById(R.id.pokeType2);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    PokeList getItem(int id) {
        return pokeList.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}