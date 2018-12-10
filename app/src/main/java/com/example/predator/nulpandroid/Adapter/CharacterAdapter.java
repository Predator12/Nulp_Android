package com.example.predator.nulpandroid.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.predator.nulpandroid.Models.Hero;
import com.example.predator.nulpandroid.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Predator on 08.11.2018.
 */

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {
    private List<Hero> mHeroes = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Hero hero = mHeroes.get(position);
        Picasso.get().load(hero.getImage()).into(holder.ivPicture);
        holder.tvName.setText(hero.getName());
        holder.tvDeck.setText(hero.getSpecies());
    }

    @Override
    public int getItemCount() {
        return mHeroes.size();
    }

    public void update(List<Hero> gamesToReplace) {
        mHeroes.clear();
        mHeroes.addAll(gamesToReplace);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView ivPicture;
        @BindView(R.id.name)
        TextView tvName;
        @BindView(R.id.species)
        TextView tvDeck;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}