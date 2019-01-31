package com.example.morgansweatman.lab2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Weapon playerChoice;
    private Weapon computerChoice;
    private int playerScore;
    private int computerScore;
    private static final int SCORE_CAP = 3;

    public enum Weapon {

        ROCK("Rock"),
        PAPER("Paper"),
        SCISSORS("Scissors");

        private String message;

        private Weapon(String msg) { message = msg; }

        @Override
        public String toString() { return message; }

    }

    public void rockButtonClicked(View v) {
        playerChoice = Weapon.ROCK;
        determineWinner(playerChoice);
    }

    public void paperButtonClicked(View v) {
        playerChoice = Weapon.PAPER;
        determineWinner(playerChoice);
    }

    public void scissorsButtonClicked(View v) {
        playerChoice = Weapon.SCISSORS;
        determineWinner(playerChoice);
    }


    public void setComputerChoice() {
        Random rand = new Random();
        int randomChoice = rand.nextInt(3);
        switch (randomChoice) {
            case 0:
                computerChoice = Weapon.ROCK;
                break;
            case 1:
                computerChoice = Weapon.PAPER;
                break;
            case 2:
                computerChoice = Weapon.SCISSORS;
                break;
        }
    }

    public void determineWinner(Weapon playerChoice) {
        TextView s = (TextView) findViewById(R.id.scoreLabel);
        TextView w = (TextView) findViewById(R.id.weaponLabel);
        TextView t = (TextView) findViewById(R.id.resultsLabel);

        setComputerChoice();

        switch (playerChoice) {
            case ROCK:
                switch (computerChoice) {
                    case ROCK:
                        t.setText("It's a tie!");
                        break;
                    case PAPER:
                        computerScore++;
                        t.setText("Computer wins... paper covers rock!");
                        break;
                    case SCISSORS:
                        playerScore++;
                        t.setText("Player wins... rock blunts scissors!");
                        break;
                }
                break;
            case PAPER:
                switch (computerChoice) {
                    case ROCK:
                        playerScore++;
                        t.setText("Player wins... paper covers rock!");
                        break;
                    case PAPER:
                        t.setText("It's a tie!");
                        break;
                    case SCISSORS:
                        computerScore++;
                        t.setText("Computer wins... scissors cut paper!");
                        break;
                }
                break;
            case SCISSORS:
                switch (computerChoice) {
                    case ROCK:
                        computerScore++;
                        t.setText("Computer wins... rock blunts scissors!");
                        break;
                    case PAPER:
                        playerScore++;
                        t.setText("Player wins... scissors cut paper!");
                        break;
                    case SCISSORS:
                        t.setText("It's a tie!");
                        break;
                }
                break;
        }

        StringBuilder scoreBuilder = new StringBuilder();
        scoreBuilder.append("Player: ");
        scoreBuilder.append(playerScore);
        scoreBuilder.append(" ");
        scoreBuilder.append("Computer: ");
        scoreBuilder.append(computerScore);
        s.setText(scoreBuilder);

        StringBuilder weaponBuilder = new StringBuilder();
        weaponBuilder.append("Player's Weapon: ");
        weaponBuilder.append(playerChoice);
        weaponBuilder.append("\n");
        weaponBuilder.append("Computer's Weapon: ");
        weaponBuilder.append(computerChoice);
        w.setText(weaponBuilder);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        playerScore = 0;
        computerScore = 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
