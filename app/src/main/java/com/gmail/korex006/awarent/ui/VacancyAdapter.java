package com.gmail.korex006.awarent.ui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gmail.korex006.awarent.FirebaseUtil;
import com.gmail.korex006.awarent.R;
import com.gmail.korex006.awarent.Vacancy;
import com.gmail.korex006.awarent.VacancyActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class VacancyAdapter extends RecyclerView.Adapter<VacancyAdapter.VacancyViewHolder>{
    ArrayList<Vacancy> vacancies;
    private Context mContext;
    private  ImageView imageVacancy;

    public VacancyAdapter () {
        FirebaseDatabase mFirebaseDatabase = FirebaseUtil.mFirebaseDB;
        DatabaseReference mDatabaseReference = FirebaseUtil.mDatabaseRef;
        this.vacancies = FirebaseUtil.mVacancies;
        ChildEventListener mChildListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Vacancy vacan = dataSnapshot.getValue(Vacancy.class);
                Log.d("Vacancy", vacan.getApartmentType());
                vacancies.add(vacan);
                notifyItemChanged(vacancies.size()-1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mDatabaseReference.addChildEventListener(mChildListener);
    }

    @Override
    public VacancyViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.vacancy_row, parent, false);
        return new VacancyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VacancyAdapter.VacancyViewHolder holder, int position) {
        Vacancy vacancy = vacancies.get(position);
        holder.bind(vacancy);
    }

    @Override
    public int getItemCount() { return vacancies.size();
    }

    public class VacancyViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        TextView vnApartmentType;
        TextView vnLocation;
        TextView vnPrice;

        public VacancyViewHolder(@NonNull View itemView) {
            super(itemView);
            vnApartmentType = (TextView)itemView.findViewById(R.id.text_apartment_type);
            vnLocation = (TextView) itemView.findViewById(R.id.text_location);
            vnPrice = (TextView) itemView.findViewById(R.id.text_price);
            imageVacancy = itemView.findViewById(R.id.imageVacancy);
            itemView.setOnClickListener(this);
        }

        public void bind (Vacancy vacancy) {
            vnApartmentType.setText(vacancy.getApartmentType());
            vnLocation.setText(vacancy.getApartmentLocation());
            String price = "₦" + vacancy.getmPrice();
            vnPrice.setText(price);
            showImage(vacancy.getImageUrl());
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Log.d("Click", String.valueOf(position));
            Vacancy selectedVacancy = vacancies.get(position);
            Intent intent = new Intent(v.getContext(), VacancyActivity.class );
            intent.putExtra("Vacancy", selectedVacancy);
            v.getContext().startActivity(intent);
        }

        private void showImage(String url) {
            if (url != null && url.isEmpty()==false) {
                Glide.with(mContext)
                        .load(url)
                        .apply(new RequestOptions().override(200, 250)
                        .fitCenter())
                        .into(imageVacancy);
            }
        }
    }
}





