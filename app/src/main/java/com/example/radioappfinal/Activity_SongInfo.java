package com.example.radioappfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

public class Activity_SongInfo extends AppCompatActivity {

    TextView bestFrequency = (TextView) findViewById(R.id.bestFrequency);
    TextView currentTrack = (TextView) findViewById(R.id.currentTrackTitle);
    TextView currentArtist = (TextView) findViewById(R.id.currentTrackArtist);
    TextView currentAlbum = (TextView) findViewById(R.id.currentTrackAlbum);
    TextView currentYear = (TextView) findViewById(R.id.currentTrackYear);
    TextView currentRating = (TextView) findViewById(R.id.currentTrackRanking);
    TextView currentAssessment = (TextView) findViewById(R.id.currentTrackAssessment);
    TextView currentTrackCurrentTime = (TextView) findViewById(R.id.currentTrackCurrentTime);
    TextView currentTrackTotalTime = (TextView) findViewById(R.id.currentTrackTotalTime);
    TextView nextTrack = (TextView) findViewById(R.id.nextTrackString);
    private Button goToSongrating = (Button) findViewById(R.id.b_goToSongRating);
    private Button goToModeratorRating = (Button) findViewById(R.id.b_goToModeratorRating);
    private Button goToSonRequest = (Button) findViewById(R.id.b_goToSongRequest);

    ProgressBar progressBar;

    Handler trackHandler = new Handler();
    Handler progressHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_info);

        Integer totalTrackTime;
        Integer actualTrackTime;
        Integer progress;
        Integer counter = 0;

        Runnable periodicTitleRequestor = new Runnable(){

            @Override
            public void run() {
                if(counter<10){
                    currentTrack.setText("Sweet child of mine");
                    currentArtist.setText("Guns n Roses");
                    currentAlbum.setText("Apetite for distruction");
                    currentYear.setText("1987");
                    currentRating.setText("2347");
                    currentAssessment.setText("4,95");
                    nextTrack.setText("A-Ha / Take on me");
                    Integer totalTrackTime = 245;
                    Integer actualTrackTime = 138+counter*5;
                    counter++;
                }
                if(counter<19){
                    currentTrack.setText("Take on me");
                    currentArtist.setText("A-Ha");
                    currentAlbum.setText("Hunting High and Low");
                    currentYear.setText("1985");
                    currentRating.setText("1834");
                    currentAssessment.setText("5,19");
                    nextTrack.setText("Guns n Roses / Sweet Child of mine");
                    Integer totalTrackTime = 243;
                    Integer actualTrackTime = 42+counter*5;
                    counter++;
                }
                else{
                    currentTrack.setText("Take on me");
                    currentArtist.setText("A-Ha");
                    currentAlbum.setText("Hunting High and Low");
                    currentYear.setText("1985");
                    currentRating.setText("1834");
                    currentAssessment.setText("5,19");
                    nextTrack.setText("Guns n Roses / Sweet Child of mine");
                    Integer totalTrackTime = 243;
                    Integer actualTrackTime = 42+counter*5;
                    counter = 0;
                }
            }
        };

        Runnable periodicProgressUpdator = new Runnable() {
            @Override
            public void run() {
                Integer lastTotalTrackTime = 5000;
                if(totalTrackTime=lastTotalTrackTime){
                    Integer progressCounter = 0;
                    if(totalTrackTime == 0){
                        progress = 0;
                    }
                    else {
                        progress = actualTrackTime + progressCounter;
                        progressBar.setMax(totalTrackTime);
                    }
                    lastTotalTrackTime=totalTrackTime;
                    progressCounter++;
                }
                else{
                    if(totalTrackTime == 0){
                        progress = 0;
                    }
                    else {
                        progress = actualTrackTime;
                        progressBar.setMax(totalTrackTime);
                    }
                    lastTotalTrackTime=totalTrackTime;
                }

                ProgressBar progressBar = (ProgressBar) findViewById(R.id.currentTrackProgress);
                progressBar.setProgress(progress);
                currentTrackTotalTime.setText(totalTrackTime/(totalTrackTime-(totalTrackTime % 60))":"totalTrackTime % 60);
                currentTrackCurrentTime.setText(actualTrackTime/(actualTrackTime-(actualTrackTime % 60))":"actualTrackTime % 60);
            }
        }

        trackHandler.postDelayed(periodicTitleRequestor, 5000);
        progressHandler.postDelayed(periodicProgressUpdator, 1000);

        goToSongrating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(view.getContext(), Activity_SongRating.class);
                startActivity(intent1);
            }
        });
        goToModeratorRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(view.getContext(), Activity_ModeratorRating.class);
                startActivity(intent2);
            }
        });
        goToSonRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(view.getContext(), Activity_SongRequest.class);
                startActivity(intent3);
            }
        });
    }
}