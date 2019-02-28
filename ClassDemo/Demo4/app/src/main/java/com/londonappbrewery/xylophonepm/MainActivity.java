package com.londonappbrewery.xylophonepm;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // Helpful Constants
    private final int NR_OF_SIMULTANEOUS_SOUNDS = 7;
    private final float LEFT_VOLUME = 1.0f;
    private final float RIGHT_VOLUME = 1.0f;
    private final int NO_LOOP = 0;
    private final int PRIORITY = 0;
    private final float NORMAL_PLAY_RATE = 1.0f;

    // TODO: Add member variables here
    private int mCSoundId ;
    private int mDSoundId;
    private int mESoundId;
    private int mFSoundId;
    private int mGSoundId;
    private int mASoundId;
    private int mBSoundId;



    SoundPool sound ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sound =  new SoundPool(this.NR_OF_SIMULTANEOUS_SOUNDS,AudioManager.STREAM_MUSIC,0);

        mCSoundId=sound.load(getApplicationContext(),R.raw.note1_c,1);
        mASoundId=sound.load(getApplicationContext(),R.raw.note6_a,1);
        mBSoundId=sound.load(getApplicationContext(),R.raw.note7_b,1);
        mDSoundId=sound.load(getApplicationContext(),R.raw.note2_d,1);
        mESoundId=sound.load(getApplicationContext(),R.raw.note3_e,1);
        mFSoundId=sound.load(getApplicationContext(),R.raw.note4_f,1);
        mGSoundId=sound.load(getApplicationContext(),R.raw.note5_g,1);

        // TODO: Create a new SoundPool
    }

    // TODO: Add the play methods triggered by the buttons
    public void playA(View v)
    {
        sound.play(mASoundId,1,1,0,0,1);
    }
    public void playB(View v)
    {
        sound.play(mBSoundId,1,1,0,0,1);
    }
    public void playC(View v)
    {
        sound.play(mCSoundId,1,1,0,0,1);
    } public void playD(View v)
    {
        sound.play(mDSoundId,1,1,0,0,1);
    } public void playE(View v)
    {
        sound.play(mESoundId,1,1,0,0,1);
    } public void playF(View v)
    {
        sound.play(mFSoundId,1,1,0,0,1);
    }
    public void playG(View v)
    {
        sound.play(mGSoundId,1,1,0,0,1);
    }



}
