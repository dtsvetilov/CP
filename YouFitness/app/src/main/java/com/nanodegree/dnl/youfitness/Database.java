package com.nanodegree.dnl.youfitness;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class Database {
    private static final String NODE_USERS = "users";
    private static final String NODE_WORKOUTS = "workouts";

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

        Query query = mDatabaseReference.child(NODE_USERS).child(loggedUserId).child(NODE_WORKOUTS).orderByKey();
        return query;
    }

    public DatabaseReference getAllWorkoutsInstance() {
        String loggedUserId = getLoggedUserId();
        if (loggedUserId == null)
            return null;

        DatabaseReference recordsReference = mDatabaseReference.child(NODE_USERS).child(loggedUserId).child(NODE_WORKOUTS);
        return recordsReference;
    }

    public String createNewWorkout() {
        String loggedUserId = getLoggedUserId();
        if (loggedUserId == null)
            return null;

        String newRecordId = mDatabaseReference.child(NODE_USERS).child(loggedUserId).child(NODE_WORKOUTS).push().getKey();

        Workout record = new Workout("", "", new HashMap<>(), new HashMap<>());
        mDatabaseReference.child(NODE_USERS).child(loggedUserId).child(NODE_WORKOUTS).child(newRecordId).setValue(record);

        return newRecordId;
    }

    public DatabaseReference getWorkoutInstance(String recordId) {
        String loggedUserId = getLoggedUserId();
        if (loggedUserId == null)
            return null;

        DatabaseReference recordReference = mDatabaseReference.child(NODE_USERS).child(loggedUserId).child(NODE_WORKOUTS).child(recordId);
        return recordReference;
    }
}
