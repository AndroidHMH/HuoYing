package com.heminghao.huoying;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.dk.view.patheffect.PathTextView;


public class WelcomeActivity extends AppCompatActivity {


    private PathTextView welcomeHuoTv;
    private PathTextView welcomeYingTv;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();

        welcomeHuoTv.init("huo");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                welcomeYingTv.init("ying");
            }
        }, 3000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        }, 6000);
    }

    private void initView() {
        welcomeHuoTv = (PathTextView) findViewById(R.id.welcome_huo_tv);
        welcomeYingTv = (PathTextView) findViewById(R.id.welcome_ying_tv);

        setPathTextView(welcomeHuoTv);
        setPathTextView(welcomeYingTv);
    }

    private void setPathTextView(PathTextView pathTextView) {
        pathTextView.setPaintType(PathTextView.Type.SINGLE);
        pathTextView.setTextColor(Color.parseColor("#57faff"));
        pathTextView.setTextSize(144);
        pathTextView.setTextWeight(8);
        pathTextView.setDuration(3000);
        pathTextView.setShadow(8, 8, 8, Color.parseColor("#66535353"));
    }
}
