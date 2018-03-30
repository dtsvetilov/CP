package com.nanodegree.dnl.youfitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkoutDetailActivity extends BaseActivity {

    public static final String ARG_WORKOUT_ID = "workout_id";

    private SectionsPagerAdapter mSectionsPagerAdapter;

    @BindView(R.id.container_vp)
    ViewPager mViewPager;

    @BindView(R.id.action_fab)
    View mActionFab;

    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_detail);

        Intent intent = getIntent();
        if (intent == null || !intent.hasExtra(ARG_WORKOUT_ID)) {
            //TODO: error message
            finish();
            return;
        }

        String recordId = intent.getStringExtra(ARG_WORKOUT_ID);
        DatabaseReference recordReference = getDatabase().getWorkoutInstance(recordId);
        if (recordReference == null) {
            //TODO: error message
            finish();
            return;
        }

        recordReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Workout record = dataSnapshot.getValue(Workout.class);
                populateUI(record);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        mActionFab.setOnClickListener(view -> ExerciseDialog.showDialog(this, new ExerciseDialog.ExerciseDialogResultListener() {
            @Override
            public void onOk(WorkoutExercise exercise) {
                super.onOk(exercise);
            }
        }));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_workout_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_delete) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void populateUI(Workout workout) {
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0)
                return WorkoutDetailActivityLogFragment.newInstance(null, null);

            return WorkoutDetailInfoFragment.newInstance(null, null);
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
