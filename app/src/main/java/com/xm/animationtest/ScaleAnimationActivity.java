package com.xm.animationtest;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ScaleAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn2Times;
    private Button btn2Half;
    private Button btnOrigin;

    private ImageView ivChange;
    private float size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_animation);

        btn2Times = findViewById(R.id.btn_2_times);
        btn2Half = findViewById(R.id.btn_2_half);
        btnOrigin = findViewById(R.id.btn_origin);
        ivChange = findViewById(R.id.iv_change);

        btn2Times.setOnClickListener(this);
        btn2Half.setOnClickListener(this);
        btnOrigin.setOnClickListener(this);

        ivChange.setBackgroundResource(R.drawable.dot);
        size = 1;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_2_times:
                setSize(BIGGER_SIZE);
                break;
            case R.id.btn_2_half:
                setSize(SMALL_SIZE);
                break;
            case R.id.btn_origin:
                setSize(ORIGIN_SIZE);
                break;
            default:
                break;
        }
    }

    private static final int ORIGIN_SIZE = 0;
    private static final int BIGGER_SIZE = 1;
    private static final int SMALL_SIZE = 2;

    private void setSize(int type) {
        switch (type) {
            case ORIGIN_SIZE:
                scaleAnimate(1 / size);
                break;
            case BIGGER_SIZE:
                scaleAnimate(2);
                break;
            case SMALL_SIZE:
                scaleAnimate(1f / 2f);
                break;
            default:
                break;
        }
    }

    private void scaleAnimate(float times) {
        AnimatorSet animatorSet = new AnimatorSet();

        float from = size;
        float to = from * times;

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(ivChange, "scaleX", from, to);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(ivChange, "scaleY", from, to);

        animatorSet.setDuration(1000);
        animatorSet.play(scaleX).with(scaleY);//两个动画同时开始

        animatorSet.start();

        size = to;
    }
}