package com.example.nick0.gamebacklog.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.nick0.gamebacklog.Model.GameObject;
import com.example.nick0.gamebacklog.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.nick0.gamebacklog.Util.gameObjectAdapter.EXTRA_POSITION;
import static com.example.nick0.gamebacklog.View.AddGameActivity.EXTRA_GAME;

public class EditGameActivity extends AppCompatActivity {

    private FloatingActionButton edit_addButton;
    private EditText Title_input;
    private EditText Platform_input;
    private EditText Notes_input;
    private Spinner Status_input;

    public static final String NICK = "key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_);

        final GameObject editGames = getIntent().getParcelableExtra(MainActivity.EXTRA_GAME);
        final int position = getIntent().getIntExtra("position", -1);

        edit_addButton = findViewById(R.id.save_addButton);
        Title_input = findViewById(R.id.edit_title);
        Platform_input = findViewById(R.id.edit_platform);
        Notes_input = findViewById(R.id.edit_notes);
        Status_input = findViewById(R.id.edit_status);

        Title_input.setText(editGames.getmTitle());
        Platform_input.setText(editGames.getmPlatform());
        Notes_input.setText(editGames.getmNotes());

        edit_addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Date mDate = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String date = dateFormat.format(mDate);

                String Title = Title_input.getText().toString();
                String Platform = Platform_input.getText().toString();
                String Note = Notes_input.getText().toString();
                String Status = Status_input.getSelectedItem().toString();
                String datum = date.toString();


                //Check if all the fields have been filled (else Snackbar).
                if(TextUtils.isEmpty(Title) || TextUtils.isEmpty(Platform) || TextUtils.isEmpty(Note)){
                    Snackbar.make(getCurrentFocus(),"Fill in all fields.", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                editGames.setmTitle(Title);
                editGames.setmPlatform(Platform);
                editGames.setmNotes(Note);
                editGames.setmStatus(Status);
                editGames.setmDate(datum);

                Intent data = new Intent();
                data.putExtra(EXTRA_GAME,editGames);
                data.putExtra("position", position);
                setResult(Activity.RESULT_OK,data);
                finish();
            }
        });

    }
}
