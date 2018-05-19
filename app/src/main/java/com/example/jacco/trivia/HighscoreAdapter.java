package com.example.jacco.trivia;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacco on 19-5-2018.
 */

public class HighscoreAdapter extends ArrayAdapter<Highscore> {

    private ArrayList<Highscore> highscore;

    public HighscoreAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Highscore> highscore) {
        super(context, resource, highscore);
        this.highscore = highscore;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.highscore, parent, false);
        }
        Log.d("error?", getContext().getResources().getString(R.string.name, highscore.get(position).getName()));
        // fill in fields
        TextView textView = convertView.findViewById(R.id.name);
        textView.setText(getContext().getResources().getString(R.string.name, highscore.get(position).getName()));

        textView = convertView.findViewById(R.id.score);
        textView.setText(getContext().getResources().getString(R.string.score, highscore.get(position).getScore()));

        return convertView;
    }
}
