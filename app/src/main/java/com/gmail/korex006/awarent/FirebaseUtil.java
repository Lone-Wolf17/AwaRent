package com.gmail.korex006.awarent;

import android.app.ListActivity;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirebaseUtil {
    public static FirebaseDatabase mFirebaseDB;
    public static DatabaseReference mDatabaseRef;
    private static FirebaseUtil firebaseUtil;
    public static FirebaseAuth mFirebaseAuth;
    public static FirebaseStorage mStorage;
    public static StorageReference mStorageRef;
    public static FirebaseAuth.AuthStateListener mAuthListener;
    public static ArrayList<Vacancy> mVacancies;
    private static final int RC_SIGN_IN = 123;
    private static VacancyListActivity caller;
    public static String userId;

    private FirebaseUtil(){};
    public static boolean isAdmin;


    public static void openFbReference(String ref) {
        if (firebaseUtil == null) {
            firebaseUtil = new FirebaseUtil();
            mFirebaseDB = FirebaseDatabase.getInstance();
            //mFirebaseAuth = FirebaseAuth.getInstance();
            //caller = callerActivity;
            connectStorage();

        }
//    public static void openFbReference(String ref, final VacancyListActivity callerActivity) {
//        if (firebaseUtil == null) {
//            firebaseUtil = new FirebaseUtil();
//            mFirebaseDB = FirebaseDatabase.getInstance();
//            mFirebaseAuth = FirebaseAuth.getInstance();
//            caller = callerActivity;
//            //connectStorage();
//
//        }

        mVacancies = new ArrayList<Vacancy>();
        mDatabaseRef = mFirebaseDB.getReference().child(ref);
    }

    private static void signIn() {
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

// Create and launch sign-in intent
        caller.startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setIsSmartLockEnabled(false)
                        .build(),
                RC_SIGN_IN);
    }

//    private static void checkAdmin(String uid) {
//        DatabaseReference ref = mFirebaseDB.getReference().child("administrators");
//
//        userId = uid;
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                if (snapshot.hasChild(userId)) {
//                    System.out.println(userId + " is an admin ------------------------------------------------");
//                    FirebaseUtil.isAdmin = true;
//                    caller.showMenu();
//                } else {
//                    System.out.println(userId + " is not not not not an admin ------------------------------------------------");
//                    FirebaseUtil.isAdmin = false;
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }

    public static void attachListener() {
        mFirebaseAuth.addAuthStateListener(mAuthListener);
    }
    public static void detachListener() {
        mFirebaseAuth.removeAuthStateListener(mAuthListener);
    }
    public static void connectStorage() {
        mStorage = FirebaseStorage.getInstance();
        mStorageRef = mStorage.getReference().child("vacancies_images");
    }
}