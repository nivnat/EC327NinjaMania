package com.example.berkgur.catchtheball;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ViewPropertyAnimatorCompatSet;
import android.view.View;
import android.widget.TextView;

public class results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        TextView scorelabel=(TextView) findViewById(R.id.scoreLabel);
        TextView highscorelabel=(TextView) findViewById(R.id.highscore);

        int score = getIntent().getIntExtra("SCORE", 0);
        scorelabel.setText(score + "");

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore = settings.getInt("HIGH_SCORE",0);

        if(score>highScore)
        {
            highscorelabel.setText("High Score: " +score);
            //Save

            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE", + score);
            editor.commit();

        }
        else
        {
            highscorelabel.setText("High Score " + highScore);
        }


    }

    public void tryAgain(View view)
    {
        startActivity(new Intent(getApplicationContext(), main.class));
    }
}
