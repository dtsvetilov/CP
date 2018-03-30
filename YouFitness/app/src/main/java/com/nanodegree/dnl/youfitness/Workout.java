package com.nanodegree.dnl.youfitness;


import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import org.parceler.Parcel;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
@Parcel
public class Workout {

    public String name;

    public String youTubeVideoUrl;

    public Map<String, WorkoutExercise> exercises;

    public Map<String, WorkoutActivity> activities;

    public Workout() {

    }

    public Workout(String name, String youTubeVideoUrl, Map<String, WorkoutExercise> exercises, Map<String, WorkoutActivity> activities) {
        this.name = name;
        this.youTubeVideoUrl = youTubeVideoUrl;
        this.exercises = exercises;
        this.activities = activities;
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