package com.example.sheanwoey_yifan.Pokemon_Detail.PokeSkills;

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
import com.example.sheanwoey_yifan.Model.PokeMove;
import com.example.sheanwoey_yifan.Pokemon_Detail.PokeDetailActivity;
import com.example.sheanwoey_yifan.Pokemon_List.PokemonListActivity;
import com.example.sheanwoey_yifan.R;

import java.util.ArrayList;


public class PokeSkillsAdapter extends RecyclerView.Adapter<PokeSkillsAdapter.ViewHolder> {

    private static String TAG = PokemonListActivity.class.getSimpleName();

    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private ArrayList<PokeMove> pokeMoves;
    private Context context;

    // data is passed into the constructor
    PokeSkillsAdapter(Context context, ArrayList<PokeMove> pokeMoves) {
        this.mInflater = LayoutInflater.from(context);
        this.pokeMoves = pokeMoves;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.poke_moves_item, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.skillName.setText(pokeMoves.get(position).getMoveName());
        holder.skillPP.setText(pokeMoves.get(position).getPp());
        holder.skillATK.setText(pokeMoves.get(position).getPower());
        holder.skillACC.setText(pokeMoves.get(position).getAccuracy());
        holder.skillTYPE.setText(pokeMoves.get(position).getElement());
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return pokeMoves.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView skillName, skillPP,skillATK,skillACC,skillTYPE,skillCLASS;

        ViewHolder(View itemView) {
            super(itemView);
            skillName = itemView.findViewById(R.id.skillName);
            skillPP = itemView.findViewById(R.id.skillPP);
            skillATK = itemView.findViewById(R.id.skillATK);
            skillACC = itemView.findViewById(R.id.skillACC);
            skillTYPE= itemView.findViewById(R.id.skillTYPE);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}