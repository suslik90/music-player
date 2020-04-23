package com.romansmekalov.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    ImageView toogleIcon;
    SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.wind);
        toogleIcon = findViewById(R.id.imageViewToogleIcon);

        seekBar = findViewById(R.id.seekBarVolume);
        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        }, 0, 1000);

    }

    public void nextTrack(View view) {
    }

    public void prevTrack(View view) {
    }

    public void toogleTrack(View view) {
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            toogleIcon.setImageResource(R.drawable.ic_play_arrow_black_24dp);
        }
        else {
            mediaPlayer.start();
            toogleIcon.setImageResource(R.drawable.ic_pause_black_24dp);
        }
    }
}
