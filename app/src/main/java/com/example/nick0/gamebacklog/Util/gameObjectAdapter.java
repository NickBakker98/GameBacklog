package com.example.nick0.gamebacklog.Util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nick0.gamebacklog.R;
import com.example.nick0.gamebacklog.Model.GameObject;
import com.example.nick0.gamebacklog.View.AddGameActivity;
import com.example.nick0.gamebacklog.View.EditGameActivity;
import com.example.nick0.gamebacklog.View.MainActivity;

import java.util.List;

import static com.example.nick0.gamebacklog.View.AddGameActivity.EXTRA_GAME;

public class gameObjectAdapter extends RecyclerView.Adapter<gameObjectAdapter.gameObjectViewHolder>{

    private Context context;
    public List<GameObject> listGameObject;
    public static final int EXTRA_POSITION = 0;
    final private GameClickListener mGameClickListener;

    //Now for the Cardview
    public class gameObjectViewHolder extends RecyclerView.ViewHolder {
        public TextView gameTitle, gamePlatform, gameDate, gameStatus;

         gameObjectViewHolder(View view) {
            super(view);
            gameTitle = (TextView) view.findViewById(R.id.Title_textview);
            gamePlatform = (TextView) view.findViewById(R.id.Platform_textview);
            gameDate = (TextView) view.findViewById(R.id.Date_view);
            gameStatus = (TextView) view.findViewById(R.id.Status_textview);
        }
    }

    public interface GameClickListener{
        void gameOnClick (int i);
    }

    public gameObjectAdapter(List<GameObject> listGameObject,GameClickListener mGameClickListener) {
        this.context = context;
        this.listGameObject = listGameObject;
        this.mGameClickListener = mGameClickListener;
    }

    @Override
    public gameObjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_cell, parent, false);
        return new gameObjectViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(final gameObjectViewHolder holder, final int position) {

        //final GameObject gameObject = listGameObject.get(position);
        final GameObject gameobject = listGameObject.get(position);
        holder.gameTitle.setText(gameobject.getmTitle());
        holder.gamePlatform.setText(gameobject.getmPlatform());
        holder.gameDate.setText(gameobject.getmDate());
        holder.gameStatus.setText(gameobject.getmStatus());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickedPosition = position;
                mGameClickListener.gameOnClick(clickedPosition);

//                GameObject addData = listGameObject.get(position);
//                Intent i = new Intent(context, EditGameActivity.class);
//                i.putExtra(EXTRA_GAME, addData);
//                i.putExtra("position",position);
            }
        });
    }

    @Override
    public int getItemCount(){
        return listGameObject.size();
    }


}
