package com.nanodegree.dnl.youfitness;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class WorkoutActivity {

    @PropertyName("youtube_video_url")
    public int youtubeVideoUrl;

    @PropertyName("start_date_time_millis")
    public long startDateTimeMillis;

    @PropertyName("end_date_time_millis")
    public long endDateTimeMillis;

    @PropertyName("exercises")
    public Map<String, WorkoutActivityExercise> excersises;

    public WorkoutActivity() {

    }

    public WorkoutActivity(int youtubeVideoUrl, long startDateTimeMillis, long endDateTimeMillis) {
        this.youtubeVideoUrl = youtubeVideoUrl;
        this.startDateTimeMillis = startDateTimeMillis;
        this.endDateTimeMillis = endDateTimeMillis;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("youtube_video_url", youtubeVideoUrl);
        result.put("start_date_time_millis", startDateTimeMillis);
        result.put("end_date_time_millis", endDateTimeMillis);
        result.put("exercises", excersises);

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
