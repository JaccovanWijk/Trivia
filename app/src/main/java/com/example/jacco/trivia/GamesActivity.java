package com.example.jacco.trivia;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;

import java.util.ArrayList;

public class GamesActivity extends AppCompatActivity implements TriviaHelper.CallBack{

    Question question = new Question();
    String correctAnswer = "";
    int[] buttons = {R.id.answer1, R.id.answer2, R.id.answer3, R.id.answer4};
    int score = 0;
    String difficulty;
    public String[] difficulties = {"Easy", "Medium", "Hard", "Random"};
    int amount = 0;
    int end = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        Intent intent = getIntent();
        difficulty = (String) intent.getSerializableExtra("difficulty");

        updateData();
    }

    @Override
    public void gotQuestion(Question question) {

        this.question = question;
        correctAnswer = question.getRightAnswer();

        TextView showQuestion = findViewById(R.id.question);
        showQuestion.setText(question.getQuestion());

        TextView showDifficulty = findViewById(R.id.difficulty);
        showDifficulty.setText(question.getDifficulty());

        ArrayList<String> answers = question.getAnswers();
        for (int i = 0, length = answers.size(); i < length; i++) {
            Button answerI = findViewById(buttons[i]);
            answerI.setText(answers.get(i));
        }

        // set visibility last two checkboxes
        Button answer3 = findViewById(buttons[2]);
        Button answer4 = findViewById(buttons[3]);
        if(question.getType().equals("boolean")) {
            answer3.setVisibility(View.INVISIBLE);
            answer4.setVisibility(View.INVISIBLE);
        } else {
            answer3.setVisibility(View.VISIBLE);
            answer4.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void gotError(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast.makeText(context, "Something went wrong", duration).show();
        Log.e("ERROR", message);
    }

    public void answerClicked(View view) {

        Button button = (Button) view;
        String answer = button.getText().toString();
        if (answer.equals(question.getRightAnswer())){
            int duration = Toast.LENGTH_SHORT;
            Context context = getApplicationContext();
            Toast.makeText(context, "Right answer!", duration).show();
            for (int i = 0, length = difficulties.length; i < length; i++) {
                if (question.getDifficulty().equals(difficulties[i].toLowerCase())) {
                    score += i + 1;
                }
            }
            System.out.println(score);
            updateData();
        } else {
            int duration = Toast.LENGTH_SHORT;
            Context context = getApplicationContext();
            String rightAnswer = question.getRightAnswer();
            Toast.makeText(context, "Wrong answer! The right answer was "+rightAnswer, duration).show();
            updateData();
        }
    }

    private void updateData() {

        if (amount == end) {
            Intent intent = new Intent(GamesActivity.this, EndingActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
        }
        amount++;
        TriviaHelper helper = new TriviaHelper(this);
        helper.getNextQuestion(this, difficulty);
    }
}
