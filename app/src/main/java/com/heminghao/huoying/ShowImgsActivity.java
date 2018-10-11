package com.heminghao.huoying;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.gitonway.lee.niftymodaldialogeffects.lib.effects.BaseEffects;
import com.gitonway.lee.niftymodaldialogeffects.lib.effects.FadeIn;
import com.heminghao.huoying.animator.AlphaAnimator;
import com.heminghao.huoying.animator.BaseAnimator;
import com.heminghao.huoying.animator.SlideLeftAnimator;
import com.heminghao.huoying.animator.SlideTopAnimator;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShowImgsActivity extends AppCompatActivity {
    private Random random = new Random();
    private ImageView showImgsImg;
    private int index = 0;
    private BaseAnimator[] animators = new BaseAnimator[]{new AlphaAnimator(), new SlideLeftAnimator(), new SlideTopAnimator()};
    private ArrayList<String> list;
    private BaseAnimator baseAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_imgs);
        initView();

        init();
        setAnimator();
//      .start(showImgsImg);
//        showImgsImg.
    }

    private void init() {
        list = getIntent().getStringArrayListExtra("list");

    }

    private void initView() {
        showImgsImg = (ImageView) findViewById(R.id.show_imgs_img);
    }

    private void setAnimator() {
        showImgsImg.setImageURI(Uri.fromFile(new File(list.get(index))));
        baseAnimator = animators[random.nextInt(animators.length)];
        baseAnimator.setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                showImgsImg.setImageURI(Uri.fromFile(new File(list.get(index))));
                Log.e("Tag", "index = " + (index + 1) + "");
                if ((index + 1) == list.size()) {
                    index = 0;
//                    setAnimator();
                    return;
                }
                setAnimator();
                index++;

            }
        });
        baseAnimator.start(showImgsImg);
    }
}
