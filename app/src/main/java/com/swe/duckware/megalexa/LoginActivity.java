package com.swe.duckware.megalexa;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.*;

import com.swe.duckware.megalexa.alexa.Workflow;
import com.swe.duckware.megalexa.netrequests.InputValidator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.READ_CONTACTS;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loadLastAccess();
    }

    public void doLogin(View view) {
        String username = ((EditText) findViewById(R.id.emailLogin)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordLogin)).getText().toString();

        InputValidator validator = new InputValidator(username, password);

        if (!validator.isEmailValid()) {
            Toast.makeText(getApplicationContext(), getString(R.string.invalid_email), Toast.LENGTH_LONG).show();
        } else {
            if (!validator.isPasswordValid()) {
                Toast.makeText(getApplicationContext(), getString(R.string.invalid_password), Toast.LENGTH_LONG).show();
            } else {
                //Show the progress bar
                findViewById(R.id.progressLogin).setVisibility(View.VISIBLE);

                /*
                 *
                 * DA FARE
                 * Implementazione asynctask + httpRequest per GET da REST API
                 *
                 * */
                (new Handler()).postDelayed(this::onFinishLogin, 2000);
            }
        }
    }

    public void onFinishLogin() {
        //Hide the progress bar
        findViewById(R.id.progressLogin).setVisibility(View.INVISIBLE);

        //Save the last access
        storeLastAccess();

        //Start a new intent
        Intent intent = new Intent(LoginActivity.this, WorkflowActivity.class);

        /*
         *
         * DA FARE
         * In base al messaggio di risposta dal server si decide che parametro passare all'intent
         * per capire che messaggio mostrare nell'activity di stato (Successo o Errore)
         *
         * */
        intent.putExtra("REQUEST_STATUS", 500);

        startActivity(intent);
    }

    public void storeLastAccess() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("last_access_login", sdf.format(new Date()));

        editor.apply();
    }

    public void loadLastAccess() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String textLastAccess = getString(R.string.last_access);
        String textNoAccess = getString(R.string.no_access);

        TextView lastAccess = findViewById(R.id.lastAccessLogin);
        lastAccess.setText(textLastAccess + " " + prefs.getString("last_access_login", textNoAccess));

    }

}

