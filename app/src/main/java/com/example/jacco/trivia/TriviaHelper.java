package com.example.jacco.trivia;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.net.URLDecoder;
import java.util.Collections;

/**
 * Created by Jacco on 16-5-2018.
 * Load in questions
 */

public class TriviaHelper implements Response.Listener<JSONObject>, Response.ErrorListener{

    public String Url = "https://opentdb.com/api.php?amount=1&encode=urlLegacy";

    public interface CallBack {
        void gotQuestion(Question question);
        void gotError(String message);
    }

    private Context context;
    private CallBack delegate;

    public TriviaHelper(Context context) {
        this.context = context;
    }

    public void getNextQuestion(CallBack activity, String difficulty) {
        //TODO Zorg dat hij difficulty meeneemt
        RequestQueue queue = Volley.newRequestQueue(context);
        delegate = activity;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Url + difficulty, null, this, this);
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        delegate.gotError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        Question question = new Question();

        try {
            JSONArray array = response.getJSONArray("result");

            // set info question
            JSONObject jsonObject = array.getJSONObject(0);
            question.setQuestion(convertString(jsonObject.getString("question")));
            question.setRightAnswer(convertString(jsonObject.getString("correct_answer")));
            question.setType(convertString(jsonObject.getString("type")));
            question.setDifficulty(convertString(jsonObject.getString("difficulty")));

            // find answers
            ArrayList<String> answers = new ArrayList<>();
            JSONArray items = jsonObject.getJSONArray("incorrect_answers");
            answers.add(convertString(jsonObject.getString("correct_answer")));
            for(int i = 0; i < items.length(); i++) {
                answers.add(items.getString(i));
            }

            // set shuffled answers
            Collections.shuffle(answers);
            question.setAnswers(answers);

        } catch(JSONException e) {
            delegate.gotError(e.getMessage());
        }
    }

    private String convertString(String text) {

        // decode string
        String decodedText = "";
        try {
            decodedText = URLDecoder.decode(text, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            delegate.gotError(e.getMessage());
        }
        return decodedText;
    }
}
