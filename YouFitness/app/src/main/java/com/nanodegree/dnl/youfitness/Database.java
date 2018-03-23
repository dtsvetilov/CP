package com.nanodegree.dnl.youfitness;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Database {
    private DatabaseReference mDatabaseReference;

    public Database() {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
    }

    private FirebaseUser getLoggedUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    private String getLoggedUserId() {
        FirebaseUser loggedUser = getLoggedUser();
        if (loggedUser == null)
            return null;

        return loggedUser.getUid();
    }


    public Query getAllWorkoutsQuery() {
        String loggedUserId = getLoggedUserId();
        if (loggedUserId == null)
            return null;

        Query query = mDatabaseReference.child("users").child(loggedUserId).child("workouts");
        return query;
    }
}
