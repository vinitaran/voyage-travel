 package com.example.voyage_travel;

 import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.voyage_travel.Domains.PopularDomain;

public class DescriptionPage extends AppCompatActivity {
    private TextView titleTxt,locationTxt,bedTxt,guideTxt,wifiTxt,descriptionTxt,scoreTxt;
    private PopularDomain item;
    ImageView picImg,backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_page);

        initView();
        setVariable();
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