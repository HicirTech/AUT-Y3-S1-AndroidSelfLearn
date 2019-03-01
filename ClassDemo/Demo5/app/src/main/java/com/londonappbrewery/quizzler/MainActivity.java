package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // TODO: Declare constants here


    // TODO: Declare member variables here:
    Button trueButton;
    Button falseButton;
    TextView textView;
    TextView scoreView;
    ProgressBar mProgressBar;
    int mIndex;
    int mScore;


  //   TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };


    final int progressBarIncreace =9;// (int)Math.ceil(100/mQuestionBank.length);
    int currentQuestionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        textView = findViewById(R.id.question_text_view);
        scoreView = findViewById(R.id.score);
        mProgressBar = findViewById(R.id.progress_bar);

        TrueFalse firstQuestion = mQuestionBank[mIndex];

        currentQuestionId = firstQuestion.getQuestionID();


        textView.setText(currentQuestionId);
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateQuestion();
                checkInputAnswer(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateQuestion();
                checkInputAnswer(false);
            }
        });


        if(savedInstanceState!=null)
        {
            mScore=savedInstanceState.getInt("ScoreKey");
            mIndex=savedInstanceState.getInt("Index");
            updateScore();

        }
        else {
            mScore=0;
            mIndex=0;
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("ScoreKey",mScore);
        savedInstanceState.putInt("Index",mIndex);
    }
    private void updateQuestion() {
        this.mIndex++;
        if(mIndex<this.mQuestionBank.length-1) {
            this.currentQuestionId = this.mQuestionBank[mIndex].getQuestionID();
            this.textView.setText(currentQuestionId);
        }
        else
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("You get the end");
            alert.setCancelable(false);
            alert.setMessage("your score : "+this.mScore+"!");
            alert.setPositiveButton("close app", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            alert.show();
        }

    }
    private void updateScore()
    {
        this.scoreView.setText("Score "+mScore+" / " +this.mQuestionBank.length);
    }
    private void  checkInputAnswer(boolean userInput) {
        this.mProgressBar.incrementProgressBy(this.progressBarIncreace);
        if(mIndex<this.mQuestionBank.length-1) {
            boolean isUserCorrect = mQuestionBank[mIndex].isAnswer() == userInput;
            Toast.makeText(getApplicationContext(),
                    isUserCorrect ?
                            R.string.correct_toast : R.string.incorrect_toast,
                    Toast.LENGTH_SHORT).show();
            if (isUserCorrect) {
                this.mScore++;
                updateScore();
            }
        }
    }


}
