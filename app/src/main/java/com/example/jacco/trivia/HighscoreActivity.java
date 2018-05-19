package com.example.jacco.trivia;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HighscoreActivity extends AppCompatActivity implements HighscoreHelper.CallBack {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        HighscoreHelper helper = new HighscoreHelper(this);
        helper.getHighscores(this);
    }

    @Override
    public void gotHighscores(ArrayList<Highscore> highscores) {
        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(new HighscoreAdapter(this, R.layout.highscore, highscores));
    }

    @Override
    public void gotError(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        String text = "Something went wrong...";
        Toast.makeText(context, text, duration).show();

        // log error
        Log.e("ERROR", message);
    }

    public void startoverClicked(View view) {
        Intent intent = new Intent(HighscoreActivity.this, GameActivity.class);
        startActivity(intent);
    }
}
