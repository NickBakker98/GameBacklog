package com.example.nick0.gamebacklog.View;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.example.nick0.gamebacklog.Model.GameObject;
import com.example.nick0.gamebacklog.R;
import com.example.nick0.gamebacklog.Util.gameObjectAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Declaring the variables.
    private RecyclerView recyclerview;
    private FloatingActionButton addButton;
    List<GameObject> gameObjects = new ArrayList<>();
    gameObjectAdapter mAdapter = new gameObjectAdapter(this, gameObjects);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Linking the variables to the xml file.
        addButton = findViewById(R.id.addButton);
        recyclerview = findViewById(R.id.recycler_view);

        //Create an OnClickListener to the add button.
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Starting the new activity.
                Intent intent = new Intent(MainActivity.this, AddGameActivity.class);
                startActivityForResult(intent,1);
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(mLayoutManager);
        recyclerview.setAdapter(mAdapter);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int position = (viewHolder.getAdapterPosition());
                final GameObject gameObject = gameObjects.get(position);
                gameObjects.remove(position);
                mAdapter.notifyItemRemoved(position);
                Toast.makeText(MainActivity.this, "Deleted: " + gameObject.getmTitle(), Toast.LENGTH_LONG).show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerview);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            GameObject game = data.getParcelableExtra(AddGameActivity.EXTRA_GAME);
            gameObjects.add(game);
            mAdapter.notifyDataSetChanged();
        }
    }
}


