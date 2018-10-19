package com.example.nick0.gamebacklog.View;

import android.app.Activity;
import android.content.Intent;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddGameActivity extends AppCompatActivity {

    //Initialize the variables.
    private FloatingActionButton save_addButton;
    private EditText titleInput;
    private EditText platformInput;
    private EditText notesInput;
    private Spinner statusInput;
    private String mTitle;
    private String mPlatform;
    private String mNote;
    private String mStatus;

    //Create a key for sending data to other Activity.
    public static final String EXTRA_GAME = "game";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_);

        //Linking variables to xml.file.
        save_addButton = findViewById(R.id.save_addButton);
        titleInput = findViewById(R.id.input_title);
        platformInput = findViewById(R.id.input_platform);
        notesInput = findViewById(R.id.input_notes);
        statusInput = findViewById(R.id.game_spinner);

        //Create OnClickListener for the fab.
        save_addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Create new date.
                Date mDate = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String date = dateFormat.format(mDate);

                mTitle = titleInput.getText().toString();
                mPlatform = platformInput.getText().toString();
                mNote = notesInput.getText().toString();
                mStatus = statusInput.getSelectedItem().toString();
                String datum = date.toString();

                //Check if all the fields have been filled (else Snackbar).
                if(TextUtils.isEmpty(mTitle) || TextUtils.isEmpty(mPlatform) || TextUtils.isEmpty(mNote)){
                    Snackbar.make(getCurrentFocus(),"Fill in all fields.", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                //Adding the data and send it to the MainActivity.
                GameObject addData = new GameObject(mTitle, mPlatform, mNote, mStatus, datum);
                Intent data = new Intent();
                data.putExtra(EXTRA_GAME, addData);
                setResult(Activity.RESULT_OK,data);
                finish();
            }
        });
    }
}
