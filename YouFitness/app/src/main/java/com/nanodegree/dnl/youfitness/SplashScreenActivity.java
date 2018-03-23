package com.nanodegree.dnl.youfitness;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class SplashScreenActivity extends BaseActivity {

    private static final int RC_AUTH = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser loggedUser = getLoggedUser();
        if (loggedUser == null)
            proceedToAuth();
        else
            proceedToMainApp();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_AUTH && resultCode == RESULT_OK) {
            proceedToMainApp();
        }
    }


    private void proceedToMainApp() {
        startActivity(new Intent(this, WorkoutsListActivity.class));
        finish();
    }

    private void proceedToAuth() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setLogo(R.drawable.ic_launcher_foreground)
                        .setAvailableProviders(providers)
                        .build(),
                RC_AUTH);
    }
}
