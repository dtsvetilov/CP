package com.nanodegree.dnl.youfitness;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import org.parceler.Parcel;

import java.util.HashMap;
import java.util.Map;

@Parcel
@IgnoreExtraProperties
public class WorkoutExercise {

    public String name;

    public int repeats;

    public WorkoutExercise() {
    }

    public WorkoutExercise(String name, int repeats) {
        this.name = name;
        this.repeats = repeats;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("repeats", repeats);

        return result;
    }
}
