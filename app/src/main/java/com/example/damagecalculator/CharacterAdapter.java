package com.example.damagecalculator;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {

    private ArrayList<Character> characters;

    CharacterAdapter(ArrayList<Character> characters){ this.characters = characters; }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public ViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.character_text_name);
        }
    }

    @Override
    public CharacterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.character, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(characters.get(position).name);
        if (position % 2 == 1){
            holder.name.setBackgroundColor(Color.parseColor("#34515e"));
            holder.name.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            holder.name.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.name.setTextColor(Color.parseColor("#34515e"));
        }
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }
}