package com.example.voyage_travel;

import android.Manifest;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.voyage_travel.Domains.PopularDomain;

public class DescriptionPage extends AppCompatActivity {
    private TextView titleTxt,locationTxt,bedTxt,guideTxt,wifiTxt,descriptionTxt,scoreTxt;
    private PopularDomain item;
    ImageView picImg,backBtn, imageView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_page);

        initView();
        setVariable();

        imageView = findViewById(R.id.capturedImage1);
        button = findViewById(R.id.CameraButton);
        ConstraintLayout constraintLayout = findViewById(R.id.guide_call);

        constraintLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Define the phone number you want to call
                String phoneNumber = "+49 1525 9933486"; // Replace with the actual phone number

                // Create an Intent to open the phone call app
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phoneNumber));
                startActivity(intent);

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                askPermissions();
            }
        });

    }
          private void askPermissions(){
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CAMERA}, 100);
        } else {
            openCamera();
        }
          }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(this, "Camera Permisssion is required to use camera.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openCamera(){
        Intent open_camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(open_camera, 100);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 100 && resultCode == RESULT_OK)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
        else {
            Toast.makeText(this,"Cancelled", Toast.LENGTH_SHORT).show();
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    private void setVariable() {
         item= (PopularDomain) getIntent().getSerializableExtra("object");

         titleTxt.setText(item.getTitle());
         locationTxt.setText(item.getLocation());
         descriptionTxt.setText(item.getDesc());
         wifiTxt.setText(item.getWifi());

         int drawableResID=getResources().getIdentifier(item.getSrc(),"drawable",getPackageName());

         Glide.with(this)
                 .load(drawableResID)
                 .into(picImg);

         backBtn.setOnClickListener(v -> finish());

     }

    private void initView() {
         titleTxt = findViewById(R.id.titleTxt);
         locationTxt = findViewById(R.id.locationTxt);
         backBtn = findViewById(R.id.backBtn);
         guideTxt = findViewById(R.id.guideTxt);
         wifiTxt = findViewById(R.id.wifiTxt);
         descriptionTxt = findViewById(R.id.descriptionTxt);
         scoreTxt = findViewById(R.id.scoreTxt);
         picImg = findViewById(R.id.picImg);
         backBtn = findViewById(R.id.backBtn);
         bedTxt = findViewById(R.id.bedTxt);
     }

}