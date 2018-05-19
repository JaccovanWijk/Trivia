package com.example.jacco.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;

/**
 * Created by Jacco on 16-5-2018.
 * Start screen, choose difficulty
 */

public class GameActivity extends AppCompatActivity {

    public String difficulty;
    public String[] difficulties = {"Easy", "Medium", "Hard", "Random"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

//        DatabaseReference myRef = database.getReference("message");
//        myRef.setValue("Hello, World!");
    }

    public void difficultyClicked(View view) {
        Button button = (Button) view;
        for (int i = 0, length = difficulties.length; i < length; i++) {
            if (difficulties[i].equals(button.getText().toString())) {
                difficulty = difficulties[i];
            }
        }

        Intent intent = new Intent(GameActivity.this, GamesActivity.class);
        intent.putExtra("difficulty", difficulty);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        difficulty = "";
    }
}
