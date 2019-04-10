package com.itfitness.idcardocr01;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.itfitness.idcardocr01.widget.TakePictureCameraView;


public class TakePictureCardOcrActivity extends AppCompatActivity implements TakePictureCameraView.TakePictureCallBack{
    private TakePictureCameraView camera;
    private View viewTakepicture;
    private ImageView img;
    private ImageView imgOk;
    private ImageView imgCancle;
    private Bitmap mBitmapResult;
    private RelativeLayout layoutCardresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takepicturecardocr);
        initView();
    }

    private void initView(){
        camera = (TakePictureCameraView) findViewById(R.id.camera);
        viewTakepicture = (View) findViewById(R.id.view_takepicture);
        img = (ImageView) findViewById(R.id.img);
        camera.setTakePictureCallBack(this);
        imgOk = (ImageView) findViewById(R.id.img_ok);
        imgCancle = (ImageView) findViewById(R.id.img_cancle);
        layoutCardresult = (RelativeLayout) findViewById(R.id.layout_cardresult);
        viewTakepicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera.takePicture();
            }
        });
        imgOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imgCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(!camera.isPreviewing()){
            layoutCardresult.setVisibility(View.GONE);
            camera.startPreview();
        }else {
            super.onBackPressed();
        }
    }
    @Override
    public void onPictureTaken(boolean isSuccess,Bitmap filepath2) {
        if(isSuccess){
            layoutCardresult.setVisibility(View.VISIBLE);
            img.setImageBitmap(filepath2);
            mBitmapResult = filepath2;
        }
    }
}
