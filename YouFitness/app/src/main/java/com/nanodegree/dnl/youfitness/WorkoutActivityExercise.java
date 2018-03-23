package com.nanodegree.dnl.youfitness;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class WorkoutActivityExercise {

    @PropertyName("name")
    public String name;

    @PropertyName("repeats")
    public int repeats;

    public WorkoutActivityExercise() {
    }

    public WorkoutActivityExercise(String name, int repeats) {
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
