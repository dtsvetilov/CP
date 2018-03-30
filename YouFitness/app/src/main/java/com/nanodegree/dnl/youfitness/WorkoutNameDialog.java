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

public class WorkoutNameDialog extends DialogFragment {

    @BindView(R.id.name_et)
    EditText mNameEt;

    @BindView(R.id.cancel_btn)
    View mCancelBtn;

    @BindView(R.id.ok_btn)
    View mOkBtn;

    private YouTubeAttachmentDialogResultListener mResultListener;

    private Workout mWorkout;

    public static WorkoutNameDialog newInstance(Workout editWorkout, YouTubeAttachmentDialogResultListener resultListener) {
        WorkoutNameDialog fragment = new WorkoutNameDialog();
        fragment.mResultListener = resultListener;
        fragment.mWorkout = editWorkout;

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_workout_name, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (mWorkout != null && !TextUtils.isEmpty(mWorkout.youTubeVideoUrl)) {
            mNameEt.setText(mWorkout.name);
        }

        mCancelBtn.setOnClickListener(v -> {
            mResultListener.onCancel();

            dismiss();
        });
        mOkBtn.setOnClickListener(v -> {
            String name = mNameEt.getText().toString();
            if (!TextUtils.isEmpty(name)) {
                mWorkout.name = name;

                mResultListener.onOk(mWorkout);
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

    public static void showDialog(AppCompatActivity activity, YouTubeAttachmentDialogResultListener resultListener) {
        showDialog(activity, null, resultListener);
    }

    public static void showDialog(AppCompatActivity activity, Workout editWorkout, YouTubeAttachmentDialogResultListener resultListener) {
        android.support.v4.app.FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        android.support.v4.app.Fragment prev = activity.getSupportFragmentManager().findFragmentByTag("workout_name_dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        final WorkoutNameDialog newFragment = WorkoutNameDialog.newInstance(editWorkout, resultListener);
        newFragment.show(ft, "workout_name_dialog");
    }

    public static class YouTubeAttachmentDialogResultListener {
        public void onOk(Workout workout) {
        }

        public void onCancel() {
        }
    }

}
