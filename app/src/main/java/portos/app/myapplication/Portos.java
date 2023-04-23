package portos.app.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int player1Score, player2Score, currentPlayer;
    TextView player1ScoreText, player2ScoreText;
    Button rollButton;
    boolean gameOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize variables
        player1Score = 0;
        player2Score = 0;
        currentPlayer = 1;
        gameOver = false;

        // get UI elements
        player1ScoreText = findViewById(R.id.player1_score);
        player2ScoreText = findViewById(R.id.player2_score);
        rollButton = findViewById(R.id.roll_button);

        // set button listener
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!gameOver) {
                    // generate random number between 1 and 6
                    int roll = (int) (Math.random() * 6) + 1;

                    // update player score and UI
                    if (currentPlayer == 1) {
                        player1Score += roll;
                        player1ScoreText.setText("Player 1 Score: " + player1Score);
                    } else {
                        player2Score += roll;
                        player2ScoreText.setText("Player 2 Score: " + player2Score);
                    }

                    // switch to other player's turn
                    currentPlayer = (currentPlayer == 1) ? 2 : 1;

                    // check if game is over
                    if (player1Score >= 100 || player2Score >= 100) {
                        gameOver = true;
                        String winner = (player1Score >= 100) ? "Player 1" : "Player 2";
                        player1ScoreText.setText(winner + " wins!");
                        player2ScoreText.setText("");
                        rollButton.setText("Play Again");
                    }
                } else {
                    // reset game
                    player1Score = 0;
                    player2Score = 0;
                    currentPlayer = 1;
                    gameOver = false;
                    player1ScoreText.setText("Player 1 Score: 0");
                    player2ScoreText.setText("Player 2 Score: 0");
                    rollButton.setText("Roll Dice");
                }
            }
        });
    }
}