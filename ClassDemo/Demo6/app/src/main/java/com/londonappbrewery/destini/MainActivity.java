package com.londonappbrewery.destini;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // TODO: Steps 4 & 8 - Declare member variables here:

    Button mTop;// = findViewById(R.id.buttonTop);
    Button mBottom;// = findViewById(R.id.buttonBottom);
    TextView mTextView;//= findViewById(R.id.storyTextView);

    int mCurrentStoryId;
    Story[] mStorieSet =
            {
                    new Story(R.string.T1_Story),
                    new Story(R.string.T2_Story),//getString(R.string.T2_Story)),
                    new Story(R.string.T3_Story)// getString(R.string.T3_Story))
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //find by view

        mTop = findViewById(R.id.buttonTop);
        mBottom = findViewById(R.id.buttonBottom);
        mTextView = findViewById(R.id.storyTextView);


        this.mTextView.setText(this.mStorieSet[0].getStoryID());
        this.mCurrentStoryId = this.mStorieSet[0].getStoryID();


        this.mTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStory(true);
            }
        });
        this.mBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStory(false);
            }
        });

    }

    private void updateStory(Boolean isTopButton) {
        //T1 proecess
        if (this.mCurrentStoryId == this.mStorieSet[0].getStoryID()) { //lay

            if (isTopButton) {
                //at layer 1, go layer 3
                this.mTextView.setText(this.mStorieSet[2].getStoryID());
                this.mCurrentStoryId = this.mStorieSet[2].getStoryID();
            } else {
                this.mTextView.setText(this.mStorieSet[1].getStoryID());
                this.mCurrentStoryId = this.mStorieSet[1].getStoryID();
            }
        } else if (this.mCurrentStoryId == this.mStorieSet[1].getStoryID()) {
            if (isTopButton) {
                this.mTextView.setText(this.mStorieSet[2].getStoryID());
                this.mCurrentStoryId = this.mStorieSet[2].getStoryID();
            } else {
                this.mTextView.setText(R.string.T4_End);
                this.mCurrentStoryId = 0;
            }
        }
        //T3 process
        else if (this.mCurrentStoryId == this.mStorieSet[2].getStoryID()) {

            if (isTopButton) {
                this.mTextView.setText(R.string.T6_End);
            } else {
                this.mTextView.setText(R.string.T5_End);
            }
            this.mCurrentStoryId = 0;//index 0 story ends
        }
        updateButtonText();
    }

    private void updateButtonText()
    {
        if(this.mCurrentStoryId==this.mStorieSet[2].getStoryID())
        {
            this.mTop.setText(R.string.T3_Ans1);
            this.mBottom.setText(R.string.T3_Ans2);
        }
        else if(this.mCurrentStoryId == this.mStorieSet[1].getStoryID())
        {
            this.mTop.setText(R.string.T2_Ans1);
            this.mBottom.setText(R.string.T2_Ans2);
        }

        if(this.mCurrentStoryId == 0)
        {
            disableButton();
        }
    }
    private void disableButton()
    {
        this.mTop.setText("Ended, Exit");
        this.mTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        this.mBottom.setEnabled(false);
        this.mBottom.setVisibility(View.GONE);
    }
}