package com.example.jacco.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EndingActivity extends AppCompatActivity {

    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ending);

        Intent intent = getIntent();
        score = (int) intent.getSerializableExtra("score");

        TextView showScore = findViewById(R.id.score);
        showScore.setText("Your score is: " + score);
    }

    public void submitButtonClicked(View view) {
        EditText editText = findViewById(R.id.name);
        String name = editText.getText().toString();

        // create highscore
        Highscore highscore = new Highscore();
        highscore.setName(name);
        highscore.setScore(score);

        // submit highscore
        HighscoreHelper helper = new HighscoreHelper(this);
        helper.postNewHighscore(highscore);

        // launch highscore activity
        Intent intent = new Intent(EndingActivity.this, HighscoreActivity.class);
        startActivity(intent);
    }
}
