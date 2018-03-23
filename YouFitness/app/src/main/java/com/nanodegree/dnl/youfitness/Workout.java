package com.nanodegree.dnl.youfitness;


import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Workout {

    @PropertyName("name")
    public String name;

    @PropertyName("youtube_video_url")
    public String youTubeVideoUrl;

    @PropertyName("exercises")
    public Map<String, WorkoutExercise> exercises;

    @PropertyName("activities")
    public Map<String, WorkoutActivity> activities;

    public Workout() {

    }

    public Workout(String name, String youTubeVideoUrl) {
        this.name = name;
        this.youTubeVideoUrl = youTubeVideoUrl;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("youtube_video_url", youTubeVideoUrl);
        result.put("exercises", exercises);
        result.put("activities", activities);

        return result;
    }
}