package com.mbytessolution.trialxcinema.view.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.mbytessolution.trialxcinema.R;

public class VideoPlayerActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private PlayerView videoPlayerView;
    private SimpleExoPlayer simpleExoPlayer;
    private ImageView lock;
    private LinearLayout lock_line;
    private float x1, x2, y1, y2;
    private GestureDetector gestureDetector;
    private static int MIN_DISTANCE = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_video_player);

        videoPlayerView = (PlayerView) findViewById(R.id.video_player_view);
        lock = (ImageView) findViewById(R.id.exo_hide_controls);
        lock_line = (LinearLayout) findViewById(R.id.lock_line);
        String videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";

        this.gestureDetector = new GestureDetector(VideoPlayerActivity.this, this);

        lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoPlayerView.setUseController(false);
                if (lock_line.getVisibility() == View.GONE) {
                    lock_line.setVisibility(View.VISIBLE);
                }

            }
        });

        initExoplayer(videoUrl);
    }

    private void initExoplayer(String videoUrl) {

        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this);
        videoPlayerView.setPlayer(simpleExoPlayer);
        DataSource.Factory dFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "Pahuch"));
        MediaSource mediaSource = new ExtractorMediaSource.Factory(dFactory)
                .createMediaSource(Uri.parse(videoUrl));

        simpleExoPlayer.prepare(mediaSource);
        simpleExoPlayer.setPlayWhenReady(true);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        simpleExoPlayer.release();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);

        if (lock_line.getVisibility() == View.VISIBLE) {

            int[] arr = new int[2];
            lock_line.getLocationOnScreen(arr);
            int x = arr[0];
            int y = arr[1] + 100;


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;

            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();

                float valueX = x2 - x1;
                float valueY = y2 - y1;

                if (Math.abs(valueX) > MIN_DISTANCE) {
                    if (x2 > x1 && y1 > y) {
                       // Right Swipe
                        if (lock_line.getVisibility() == View.VISIBLE) {
                            lock_line.setVisibility(View.GONE);
                        }
                        videoPlayerView.setUseController(true);
                        videoPlayerView.showController();
                    }
                    else if (x2 < x1 && y1 > y){
                        // Left Swipe
                        if (lock_line.getVisibility() == View.VISIBLE) {
                            lock_line.setVisibility(View.GONE);
                        }
                        videoPlayerView.setUseController(true);
                        videoPlayerView.showController();
                    }
                    else {
                        Toast.makeText(VideoPlayerActivity.this, "Please Slide below to the lock indicator", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (Math.abs(valueY) > MIN_DISTANCE) {
                    if (y2 > y1) {
                        Toast.makeText(VideoPlayerActivity.this, "Please Slide in right direction", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(VideoPlayerActivity.this, "Please Slide in right direction", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
        }


        }
        else {

        }

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }
}