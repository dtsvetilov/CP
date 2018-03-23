package com.nanodegree.dnl.youfitness;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class BaseActivity extends AppCompatActivity {
    protected FirebaseAuth mAuth;
    private Database mDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
    }

    protected FirebaseUser getLoggedUser() {
        return mAuth.getCurrentUser();
    }

    protected Database getDatabase() {
        if (mDatabase == null)
            mDatabase = new Database();

        return mDatabase;
    }
}
