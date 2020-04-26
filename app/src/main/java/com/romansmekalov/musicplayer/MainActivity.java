package com.romansmekalov.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    ImageView toogleIcon;
    SeekBar seekBar;
    Track[] trackList = new Track[3];
    int selectedTrack = 0;
    ConstraintLayout layout;
    TextView trackNameView;
    boolean isPlaying = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.layout);
        toogleIcon = findViewById(R.id.imageViewToogleIcon);
        seekBar = findViewById(R.id.seekBarVolume);
        trackNameView = findViewById(R.id.textViewTrackName);

        Track trackWind = new Track();
        trackWind.audioPath = R.raw.wind;
        trackWind.name = "Song of Wind";
        trackWind.coverImagePath = R.drawable.nature;

        trackList[0]=trackWind;

        Track trackFire = new Track();
        trackFire.audioPath = R.raw.fire;
        trackFire.name = "Song of Fire";
        trackFire.coverImagePath = R.drawable.fire;

        trackList[1]=trackFire;

        Track trackSport = new Track();
        trackSport.audioPath = R.raw.sport;
        trackSport.name = "Song of Sport";
        trackSport.coverImagePath = R.drawable.sport;

        trackList[2]=trackSport;


        setActiveTrack(selectedTrack);

    }

    public void setActiveTrack(int trackInd){
        Track track = trackList[trackInd];
        mediaPlayer = MediaPlayer.create(getApplicationContext(), track.audioPath);
        layout.setBackgroundResource(track.coverImagePath);
        trackNameView.setText(track.name);

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

        if(isPlaying)
            mediaPlayer.start();

    }

    public void nextTrack(View view) {
        mediaPlayer.stop();
        selectedTrack++;
        if(selectedTrack > trackList.length-1)
            selectedTrack=0;
        setActiveTrack(selectedTrack);
    }

    public void prevTrack(View view) {
        mediaPlayer.stop();
        selectedTrack--;
        if(selectedTrack < 0)
            selectedTrack=trackList.length-1;
        setActiveTrack(selectedTrack);
    }

    public void toogleTrack(View view) {
        if(isPlaying){
            mediaPlayer.pause();
            toogleIcon.setImageResource(R.drawable.ic_play_arrow_black_24dp);
            isPlaying = false;
        }
        else {
            mediaPlayer.start();
            toogleIcon.setImageResource(R.drawable.ic_pause_black_24dp);
            isPlaying = true;
        }
    }
}

class Track {
    int coverImagePath;
    String name;
    int audioPath;
}
