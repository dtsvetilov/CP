package com.nanodegree.dnl.youfitness;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExerciseDialog extends DialogFragment {

    @BindView(R.id.name_et)
    EditText mNameEt;

    @BindView(R.id.repeats_et)
    EditText mRepeatsEt;

    @BindView(R.id.cancel_btn)
    View mCancelBtn;

    @BindView(R.id.ok_btn)
    View mOkBtn;

    private ExerciseDialogResultListener mResultListener;

    private WorkoutExercise mWorkoutExercise;

    public static ExerciseDialog newInstance(WorkoutExercise workoutExercise, ExerciseDialogResultListener resultListener) {
        ExerciseDialog fragment = new ExerciseDialog();
        fragment.mResultListener = resultListener;
        fragment.mWorkoutExercise = workoutExercise;

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_exercise, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (mWorkoutExercise != null) {
            mNameEt.setText(mWorkoutExercise.name);
            mRepeatsEt.setText(String.valueOf(mWorkoutExercise.repeats));
        }

        mCancelBtn.setOnClickListener(v -> {
            mResultListener.onCancel();

            dismiss();
        });
        mOkBtn.setOnClickListener(v -> {
            String name = mNameEt.getText().toString();
            String repeats = mRepeatsEt.getText().toString();
            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(repeats) && TextUtils.isDigitsOnly(repeats)) {
                mWorkoutExercise.name = name;
                mWorkoutExercise.repeats = Integer.parseInt(repeats);

                mResultListener.onOk(mWorkoutExercise);
            }

            dismiss();
        });
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        ActivityUtils.hideKeyboard(getActivity());
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);

        ActivityUtils.hideKeyboard(getActivity());
    }

    public static void showDialog(AppCompatActivity activity, ExerciseDialogResultListener resultListener) {
        showDialog(activity, null, resultListener);
    }

    public static void showDialog(AppCompatActivity activity, WorkoutExercise workoutExercise, ExerciseDialogResultListener resultListener) {
        android.support.v4.app.FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        android.support.v4.app.Fragment prev = activity.getSupportFragmentManager().findFragmentByTag("exercise_dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        final ExerciseDialog newFragment = ExerciseDialog.newInstance(workoutExercise, resultListener);
        newFragment.show(ft, "exercise_dialog");
    }

    public static class ExerciseDialogResultListener {
        public void onOk(WorkoutExercise exercise) {
        }

        public void onCancel() {
        }
    }

}
