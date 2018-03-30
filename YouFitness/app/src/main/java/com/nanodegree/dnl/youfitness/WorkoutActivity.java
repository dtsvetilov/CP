package com.nanodegree.dnl.youfitness;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import org.parceler.Parcel;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Parcel
@IgnoreExtraProperties
public class WorkoutActivity {

    public int youtubeVideoUrl;

    public long startDateTimeMillis;

    public long endDateTimeMillis;

    public Map<String, WorkoutExercise> exercises;

    public WorkoutActivity() {

    }

    public WorkoutActivity(int youtubeVideoUrl, long startDateTimeMillis, long endDateTimeMillis, Map<String, WorkoutExercise> exercises) {
        this.youtubeVideoUrl = youtubeVideoUrl;
        this.startDateTimeMillis = startDateTimeMillis;
        this.endDateTimeMillis = endDateTimeMillis;
        this.exercises = exercises;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("youtube_video_url", youtubeVideoUrl);
        result.put("start_date_time_millis", startDateTimeMillis);
        result.put("end_date_time_millis", endDateTimeMillis);
        result.put("exercises", exercises);

        return result;
    }

    @Exclude
    public Date getStartDate() {
        return new Date(startDateTimeMillis);
    }

    @Exclude
    public Date getEndDate() {
        return new Date(endDateTimeMillis);
    }
}
