package com.nanodegree.dnl.youfitness;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link WorkoutDetailInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorkoutDetailInfoFragment extends Fragment {

    @BindView(R.id.name_tv)
    TextView mNameTv;

    @BindView(R.id.edit_name_btn)
    ImageButton mEditNameBtn;

    @BindView(R.id.video_placeholder)
    FrameLayout mVideoPlaceholder;

    @BindView(R.id.exercises_rv)
    RecyclerView mExercisesRv;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WorkoutDetailInfoFragment() {
    }

    public static WorkoutDetailInfoFragment newInstance(String param1, String param2) {
        WorkoutDetailInfoFragment fragment = new WorkoutDetailInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout_detail_info, container, false);
        ButterKnife.bind(view);

        return view;
    }
}
