package com.gmail.korex006.awarent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageView;
import android.widget.TextView;

public class VacancyActivity extends AppCompatActivity {

    TextView vPosterName;
    TextView vApartmentType;
    TextView vApartmentLocation;
    TextView vPrice;
    Vacancy vacancy;
    ImageView imageVacancy;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacancy);

        Intent intent = getIntent();
        vacancy = (Vacancy) intent.getSerializableExtra("Vacancy");

        vPosterName = (TextView) findViewById(R.id.text_name);
        vApartmentType = (TextView) findViewById(R.id.text_apartment_type);
        vApartmentLocation = (TextView) findViewById(R.id.text_apartment_address);
        vPrice = (TextView) findViewById(R.id.text_price);
        imageVacancy = (ImageView) findViewById(R.id.imageView2);

        vPosterName.setText(vacancy.getmPoster());
        vApartmentType.setText(vacancy.getApartmentType());
        vApartmentLocation.setText(vacancy.getApartmentLocation());
        String price = "₦" + vacancy.getmPrice();
        vPrice.setText(price);
        showImage(vacancy.getImageUrl());



    }

    private void showImage(String url) {
        if (url != null && url.isEmpty()==false) {
            Glide.with(VacancyActivity.this)
                    .load(url)
                    .apply(new RequestOptions().override(400, 400)
                            .fitCenter())
                    .into(imageVacancy);
        }
    }

}
