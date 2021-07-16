package com.example.braintraner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int locationOfAllSelection;
    int score = 0;
    int numberOfQuestions = 0;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    Button goButton;
    Button playAgainButton;
    TextView correctTextView;
    TextView sumTextView;
    TextView scoreTextView;
    TextView timerTextView;
    Button button_0;
    Button button_1;
    Button button_2;
    Button button_3;
    ConstraintLayout gameLayout;

    public void goButtonClicked(View view) {
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgainButton(playAgainButton);
    }

    public void newQuestion() {

        // Random some numbers for the mini test
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfAllSelection = random.nextInt(4);

        answers.clear();
        // Fill ArrayList answer some info
        for (int i = 0; i < 4; i++) {
            if (i == locationOfAllSelection) {
                answers.add(a + b);
            } else {

                int wrongAnswer = random.nextInt(41);

                while (wrongAnswer == a + b) {
                    wrongAnswer = random.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        } // End of For loop
        button_0.setText(Integer.toString(answers.get(0)));
        button_1.setText(Integer.toString(answers.get(1)));
        button_2.setText(Integer.toString(answers.get(2)));
        button_3.setText(Integer.toString(answers.get(3)));

        correctTextView = findViewById((R.id.correctTextView));
        sumTextView = findViewById(R.id.sumTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
    }

    public void chooseAnswer(View view) {

        if (Integer.toString(locationOfAllSelection).equals(view.getTag().toString())){
            correctTextView.setText("CORRECT!");
            score++;
        } else {
            correctTextView.setText(("WRONG!"));
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestion();
    }

    // Clicked to reset the game
    public void playAgainButton(View view) {

        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        playAgainButton.setVisibility(View.INVISIBLE);
        sumTextView.setText("");
        newQuestion();

        new CountDownTimer(30100, 1000 ) {

            @Override
            // To update the Timer text view to seconds, i.e 30s
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l / 1000 + "s"));
                button_0.setClickable(true);
                button_1.setClickable(true);
                button_2.setClickable(true);
                button_3.setClickable(true);
            }
            // Change the text view when done
            @Override
            public void onFinish() {
                correctTextView.setText("TIME OUT!!");
                playAgainButton.setVisibility(View.VISIBLE);

                button_0.setClickable(false);
                button_1.setClickable(false);
                button_2.setClickable(false);
                button_3.setClickable(false);
            }
        }.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Create Objects on the console
        gameLayout = findViewById(R.id.gameLayout);
        sumTextView = findViewById(R.id.sumTextView);
        button_0 = findViewById(R.id.button_0);
        button_1 = findViewById(R.id.button_1);
        button_2 = findViewById(R.id.button_2);
        button_3 = findViewById(R.id.button_3);
        timerTextView = findViewById((R.id.timerTextView));
        goButton = findViewById(R.id.goButton);
        playAgainButton = findViewById(R.id.playAgainButton);

        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

    }
}













