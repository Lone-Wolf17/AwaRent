package com.gmail.korex006.awarent;

import android.content.Intent;
import android.os.Bundle;

import com.gmail.korex006.awarent.ui.VacancyAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;

public class VacancyListActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_addVacancy:
                    startActivity(new Intent(VacancyListActivity.this, AddVacancy.class));
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //FirebaseUtil.detachListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseUtil.openFbReference("vacancies");
        RecyclerView vacancyList = (RecyclerView) findViewById(R.id.vacancy_list);
        final VacancyAdapter adapter = new VacancyAdapter();
        vacancyList.setAdapter(adapter);
        LinearLayoutManager vacanciesLayoutManager =
                new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        vacancyList.setLayoutManager(vacanciesLayoutManager);
      //  FirebaseUtil.attachListener();
    }

}
