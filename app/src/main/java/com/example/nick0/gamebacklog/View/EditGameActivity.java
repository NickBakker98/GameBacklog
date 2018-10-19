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

    //Initialize the variables.
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

        //Get the data and the position of the games from the MainActivity.
        final GameObject editGames = getIntent().getParcelableExtra(MainActivity.EXTRA_GAME);
        final int position = getIntent().getIntExtra("position", -1);

        //Linking variables to the xml.file.
        edit_addButton = findViewById(R.id.save_addButton);
        Title_input = findViewById(R.id.edit_title);
        Platform_input = findViewById(R.id.edit_platform);
        Notes_input = findViewById(R.id.edit_notes);
        Status_input = findViewById(R.id.edit_status);

        //Put the text which was added in the AddGameActivity in the fields, so it can be edited.
        Title_input.setText(editGames.getTitle());
        Platform_input.setText(editGames.getPlatform());
        Notes_input.setText(editGames.getNotes());

        //Create OnClickListener for the fab, which navigates back to the MainActivity.
        edit_addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Creating a new date.
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

                //Putting data in the editGames.
                editGames.setTitle(Title);
                editGames.setPlatform(Platform);
                editGames.setNotes(Note);
                editGames.setStatus(Status);
                editGames.setDate(datum);

                //Put the data from editGames back in the EXTRA_GAME.
                Intent data = new Intent();
                data.putExtra(EXTRA_GAME,editGames);
                data.putExtra("position", position);
                setResult(Activity.RESULT_OK,data);
                finish();
            }
        });

    }
}
