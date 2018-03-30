package com.nanodegree.dnl.youfitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.Query;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WorkoutsListActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.workouts_rv)
    RecyclerView mWorkoutsRv;

    @BindInt(R.integer.workouts_grid_column_count)
    int mWorkoutGridColumnsCount;

    private FirebaseRecyclerAdapter<Workout, WorkoutViewHolder> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts_list);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_camera) {

            } else if (id == R.id.nav_gallery) {

            } else if (id == R.id.nav_slideshow) {

            } else if (id == R.id.nav_manage) {

            } else if (id == R.id.nav_share) {

            } else if (id == R.id.nav_send) {

            }

            mDrawer.closeDrawer(GravityCompat.START);
            return true;
        });

        initRecyclerView();
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.workouts_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initRecyclerView() {
        RecyclerView.LayoutManager mManager = new GridLayoutManager(this, mWorkoutGridColumnsCount);
        mWorkoutsRv.setLayoutManager(mManager);

        Query allEntriesQuery = getDatabase().getAllWorkoutsQuery();

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Workout>()
                .setQuery(allEntriesQuery, Workout.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<Workout, WorkoutViewHolder>(options) {

            @Override
            public WorkoutViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                LayoutInflater inflater = getLayoutInflater();
                return new WorkoutViewHolder(inflater.inflate(R.layout.grid_item_workout, viewGroup, false));
            }

            @Override
            protected void onBindViewHolder(WorkoutViewHolder viewHolder, int position, final Workout model) {
                //viewHolder.bindToData(model);
            }

        };

        mWorkoutsRv.setAdapter(mAdapter);
    }

    @OnClick(R.id.new_workout_fab)
    void onNewWorkoutClick(View view) {
        String newWorkoutId = getDatabase().createNewWorkout();
        if (newWorkoutId == null) {
            //TODO: message if error
            return;
        }

        Intent intent = new Intent(this, WorkoutDetailActivity.class);
        intent.putExtra(WorkoutDetailActivity.ARG_WORKOUT_ID, newWorkoutId);
        startActivity(intent);
    }

    class WorkoutViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name_tv)
        TextView mNameTv;

        @BindView(R.id.exercises_count_tv)
        TextView mExercisesCountTv;

        @BindView(R.id.activities_count_tv)
        TextView mActivitiesCountTv;

        @BindView(R.id.video_indicator_iv)
        ImageView mVideoIndicatorIv;

        public WorkoutViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }

        public void bindToData(Workout workout) {
            mNameTv.setText(workout.name);
            mExercisesCountTv.setText(String.valueOf(workout.exercises.size()));
            mActivitiesCountTv.setText(String.valueOf(workout.activities.size()));

            if (TextUtils.isEmpty(workout.youTubeVideoUrl))
                mVideoIndicatorIv.setVisibility(View.GONE);
            else
                mVideoIndicatorIv.setVisibility(View.VISIBLE);
        }
    }
}
