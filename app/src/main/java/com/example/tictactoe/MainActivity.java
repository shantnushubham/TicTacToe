package com.example.tictactoe;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 0 = cross, 1 = circle
    int activePlayer = 0;
    boolean gameIsActive = true;
    // 2 means unplayed
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameIsActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.cross);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.circle);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {
                    // Someone has won!
                    gameIsActive = false;
                    String winner = "";
                    if (gameState[winningPosition[0]] == 0) {
                        winner = "Cross";
                    } else if (gameState[winningPosition[0]] == 1) {
                        winner = "Circle";
                    }
                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner + " has won!");
                    if(winner.equals("Cross")) {
                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setBackgroundColor(Color.parseColor("RED"));
                        layout.setVisibility(View.VISIBLE);
                        Toast.makeText(MainActivity.this, "Created by Shantanu Shubham", Toast.LENGTH_LONG).show();
                    }
                        else{
                            LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                            layout.setBackgroundColor(Color.parseColor("YELLOW"));
                            layout.setVisibility(View.VISIBLE);
                            Toast.makeText(MainActivity.this, "Created by Shantanu Shubham", Toast.LENGTH_LONG).show();
                        }
                    } else {
                    boolean gameIsOver = true;
                    for (int counterState : gameState) {
                        if (counterState == 2) gameIsOver = false;
                    }
                    if (gameIsOver) {
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText("It's a draw");
                        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                        layout.setBackgroundColor(Color.parseColor("GREEN"));

                    }
                }
            }
        }
    }
    public void playAgain(View view) {
        gameIsActive = true;
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        for (int i = 0; i< gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    //    int activePlayer = 0; //circle=1. cross =0, unPlayed=2;
//    int[] gameState = {2,2,2,2,2,2,2,2,2};
//    int[][]winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},
//            {1,4,7},{2,5,8},{0,4,8},{2,4,6}};
//    public void dropIn(View view) {
//        ImageView counter = (ImageView) view;
//        int tappedCounter = Integer.parseInt(counter.getTag().toString());
//        if (gameState[tappedCounter] == 2) {
//            gameState[tappedCounter] = activePlayer;
//            counter.setTranslationY(-1000f);
//            if (activePlayer == 0) {
//                counter.setImageResource(R.drawable.cross);
//                activePlayer = 1;
//            } else {
//                counter.setImageResource(R.drawable.circle);
//                activePlayer = 0;
//            }
//            counter.animate().translationYBy(1000f).setDuration(300);
//            for (int[] winningPosition : winningPositions) {
//                if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
//                        && gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
//                        gameState[winningPosition[0]] != 2) {
//                }
//            }
//        }
//    }
        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }
    }
