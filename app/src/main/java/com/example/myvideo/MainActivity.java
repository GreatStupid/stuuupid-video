package com.example.myvideo;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Scroller;
import android.widget.VideoView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {//用geeture识别手势

    private GestureDetector gestureDetector;//声明
    public VideoView videoView;//用mediaplayer实现比较麻烦，用vieoview也够了。

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar= getSupportActionBar();
        actionBar.hide();//隐藏标题栏
        gestureDetector = new GestureDetector(this,this);
        videoView = (VideoView) findViewById(R.id.videoView);
        MediaController mediaController = new MediaController(this);//mediaplayer自带进度条，也能实现全屏播放
        videoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoView);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        videoView.setVideoPath(path + "/jjj.mp4");//也可以用URL播放在线视频，这里便于测试
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {//必须有
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {//用滑动实现快进or倒退
        float xxx = e2.getX()-e1.getX();
        xxx=xxx*2;
        if (xxx>0){
            videoView.seekTo(videoView.getCurrentPosition()+(int)xxx);
        }
        else {
            videoView.seekTo(videoView.getCurrentPosition() + (int) xxx);
        }
        return false;
    }

}

