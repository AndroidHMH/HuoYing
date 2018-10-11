package com.heminghao.huoying;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.effects.BaseEffects;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mainSelectPhoto;

    private ImageView mainImg;
    private EditText mainSelectCountEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mainSelectPhoto = (Button) findViewById(R.id.main_select_photo);
        mainImg = (ImageView) findViewById(R.id.main_img);
        mainSelectCountEt = (EditText) findViewById(R.id.main_select_count_et);

        mainSelectPhoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_select_photo:
                PictureSelector.create(MainActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .theme(R.style.picture_default_style)//默认样式
                        .maxSelectNum(Integer.parseInt(mainSelectCountEt.getText().toString().trim()))//最大图片数
                        .selectionMode(PictureConfig.MULTIPLE)//单选或多选
                        .previewImage(true)// 是否可预览图片 true or false
                        .isCamera(true)// 是否显示拍照按钮 true or false
                        .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        .enableCrop(false)// 是否裁剪 true or false
                        .compress(true)// 是否压缩 true or false
                        .isGif(false)// 是否显示gif图片 true or false
                        .forResult(PictureConfig.CHOOSE_REQUEST);
//

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
//                    mainImg.setImageURI(Uri.fromFile(new File(selectList.get(0).getPath())));
                    ArrayList<String> urls = new ArrayList<>();
                    for (LocalMedia localMedia : selectList) {
                        urls.add(localMedia.getPath());
                        Log.e("TAG", localMedia.getPath());
                    }
                    Intent intent = new Intent(this, ShowImgsActivity.class);
                    intent.putStringArrayListExtra("list", urls);
                    startActivity(intent);
                    break;
            }
        }
    }
}
