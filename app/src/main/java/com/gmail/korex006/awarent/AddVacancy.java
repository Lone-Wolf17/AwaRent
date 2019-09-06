package com.gmail.korex006.awarent;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class AddVacancy extends AppCompatActivity {
    public static final int PICTURE_RESULT = 42;
    private FirebaseDatabase mFirebaseDB;
    private DatabaseReference mDatabaseRef;
    EditText vPosterName;
    EditText vApartmentType;
    EditText vApartmentLocation;
    EditText vPrice;
    Vacancy vacancy;
    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vacancy);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        FirebaseUtil.openFbReference("vacancies" );
        mFirebaseDB = FirebaseUtil.mFirebaseDB;
        mDatabaseRef = FirebaseUtil.mDatabaseRef;

        vPosterName = (EditText) findViewById(R.id.text_name);
        vApartmentType = (EditText) findViewById(R.id.text_apartment_type);
        vApartmentLocation = (EditText) findViewById(R.id.text_apartment_address);
        vPrice = (EditText) findViewById(R.id.text_price);
        imageView = (ImageView) findViewById(R.id.imageView2);

        if (vacancy == null) {
            vacancy = new Vacancy();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICTURE_RESULT && resultCode == RESULT_OK){
            final Uri imageUri = data.getData();
            final StorageReference ref = FirebaseUtil.mStorageRef.child(imageUri.getLastPathSegment());
            final UploadTask uploadTask = ref.putFile(imageUri);
            ref.putFile(imageUri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!urlTask.isSuccessful());
                    Uri downloadUrl = urlTask.getResult();
                    String imageName = taskSnapshot.getStorage().getPath();

                    final String strDownloadUrl = String.valueOf(downloadUrl);
                    vacancy.setImageUrl(strDownloadUrl);
                    vacancy.setImageName(imageName);
                    Log.d("Url", strDownloadUrl);
                    Log.d("Name", imageName);
                    showImage(strDownloadUrl);
                }
            });

        }
    }

    public void addVacancyButtonClicked(View view) {
        String name = vPosterName.getText().toString();
        String apartmentType = vApartmentType.getText().toString();
        String apartmentLocation = vApartmentLocation.getText().toString();
        String price = vPrice.getText().toString();
        vacancy.setmPoster(name);
        vacancy.setApartmentType(apartmentType);
        vacancy.setApartmentLocation(apartmentLocation);
        vacancy.setmPrice(price);
        //vacancy = new Vacancy(name, apartmentType, apartmentLocation, price);
        mDatabaseRef.push().setValue(vacancy);
        startActivity(new Intent(this, VacancyListActivity.class));
    }

    public void addImageButtonClicked(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        startActivityForResult(intent.createChooser(intent, "Insert Picture"),
                PICTURE_RESULT);
    }

    private void showImage (String url) {
        if (url != null && url.isEmpty() == false) {
            int width = Resources.getSystem().getDisplayMetrics().widthPixels;
            //int width = imageVacancy.getWidth();
            Glide.with(AddVacancy.this)
                    .load(url)
                    .apply(new RequestOptions().override(width*4, width*2/3))
                    .into(imageView);
        }
    }
}
