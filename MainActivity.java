package com.example.vanil_singh.musicplayer2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Button;

import static android.view.View.*;

public class MainActivity extends AppCompatActivity{
    MediaPlayer player ;
    AudioManager audioControl ;
    Button play;
    Button stop;
    Button pause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player = MediaPlayer.create( this ,R.raw.righttime );
        audioControl = (AudioManager)
                getSystemService(Context. AUDIO_SERVICE );
        int max_vol =
                audioControl .getStreamMaxVolume(AudioManager. STREAM_MUSIC );
        int curr_vol =
                audioControl .getStreamVolume(AudioManager. STREAM_MUSIC );
        SeekBar volume = (SeekBar)findViewById(R.id. Volume );
        volume.setMax(max_vol);
        volume.setProgress(curr_vol);
        volume.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioControl .setStreamVolume(AudioManager. STREAM_MUSIC , progress, 0 );
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        final SeekBar track = (SeekBar)findViewById(R.id. Track );
        track.setMax( player .getDuration());

        track.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                player .seekTo(progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        play = (Button) findViewById(R.id.Play);
        stop = (Button) findViewById(R.id.Stop);
        pause = (Button) findViewById(R.id.pause);

        play.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                player.start();
            }
        });
        pause.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                player.pause();
            }
        });
        stop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                player.seekTo(0);
                player.pause();
            }
        });
    }
}
