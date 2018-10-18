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

import static com.example.nick0.gamebacklog.Util.gameObjectAdapter.EXTRA_POSITION;
import static com.example.nick0.gamebacklog.View.EditGameActivity.NICK;

public class MainActivity extends AppCompatActivity implements gameObjectAdapter.GameClickListener {

    //Declaring the variables.
    private RecyclerView recyclerview;
    private FloatingActionButton addButton;
    List<GameObject> gameObjects = new ArrayList<>();
    gameObjectAdapter mAdapter = new gameObjectAdapter(gameObjects, this);
    //Constants used when calling the update activity
    public static final String EXTRA_GAME = "Game";
    public static final int REQUESTCODE = 1234;
    private int mModifyPosition;

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
    public void gameOnClick(int i) {
        Intent intent2 = new Intent(MainActivity.this, EditGameActivity.class);
        mModifyPosition = i;
        GameObject nick = gameObjects.get(i);
        intent2.putExtra(EXTRA_GAME, nick);
        intent2.putExtra("position", mModifyPosition);
        startActivityForResult(intent2, 2);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        GameObject game = data.getParcelableExtra(AddGameActivity.EXTRA_GAME);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                gameObjects.add(game);
                mAdapter.notifyDataSetChanged();
            }
        }
        GameObject game2 = data.getParcelableExtra(EditGameActivity.NICK);
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                int position = data.getIntExtra("position", 0);
                gameObjects.set(position, game);
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}


