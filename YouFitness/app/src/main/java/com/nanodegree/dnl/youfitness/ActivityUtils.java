package com.nanodegree.dnl.youfitness;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

public class ActivityUtils {
    public static void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }
}
