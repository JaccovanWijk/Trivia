package com.example.jacco.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GamesActivity extends AppCompatActivity implements TriviaHelper.CallBack{

    public String difficulty = "&difficulty=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        Intent intent = getIntent();
        String difficultyExtra = (String) intent.getSerializableExtra("difficulty");
        if (difficultyExtra.equals("Random")) {
            difficulty = "";
        } else {
            difficulty += difficultyExtra;
        }
        if (savedInstanceState == null) {


            TriviaHelper helper = new TriviaHelper(this);
            helper.getNextQuestion(this, difficulty);
            //score = 0;
        } else {

        }
    }

    @Override
    public void gotQuestion(Question question) {

    }

    @Override
    public void gotError(String message) {

    }

    public boolean answerClicked(View view) {
        //TODO als goede antwoord return true;
        return false;
    }
}
