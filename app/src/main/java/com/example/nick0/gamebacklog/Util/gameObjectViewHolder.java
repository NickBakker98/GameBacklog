package com.example.nick0.gamebacklog.Util;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.nick0.gamebacklog.R;

public class gameObjectViewHolder extends RecyclerView.ViewHolder {

    public CardView gameCardView;
    public View view;

    public gameObjectViewHolder(View itemView) {
        super(itemView);
        gameCardView = itemView.findViewById(R.id.gridcell_cardview);
        view = itemView;
    }
}
