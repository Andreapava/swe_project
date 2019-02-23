package com.swe.duckware.megalexa;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.swe.duckware.megalexa.netrequests.InputValidator;

public class RegisterActivity extends AppCompatActivity {

    private ProgressBar progress;

    private EditText password;

    private EditText email;

    private TextWatcher getTextWatcher(final ProgressBar progress) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() <= InputValidator.MIN_PASSWORD_COUNT)
                    progress.setProgress(charSequence.length());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.email = findViewById(R.id.emailRegister);

        //Update the progress of the progress bar according to the remaining length
        this.progress = findViewById(R.id.passwordLength);
        this.password = findViewById(R.id.passwordRegister);
        this.password.addTextChangedListener(getTextWatcher(progress));
    }

    public void doRegister(View view) {
        InputValidator validator = new InputValidator(email.getText().toString(), password.getText().toString());

        if (!validator.isEmailValid()) {
            Toast.makeText(getApplicationContext(), getString(R.string.invalid_email), Toast.LENGTH_LONG).show();
        } else {
            if (!validator.isPasswordValid()) {
                Toast.makeText(getApplicationContext(), getString(R.string.invalid_password), Toast.LENGTH_LONG).show();
            } else {
                //Show the progress bar
                findViewById(R.id.registerLoader).setVisibility(View.VISIBLE);

                /*
                *
                * DA FARE
                * Implementazione asynctask + httpRequest per GET da REST AP
                *
                * */
                (new Handler()).postDelayed(this::onFinishRegister, 2000);
            }
        }
    }

    public void onFinishRegister() {
        //Hide the progress bar
        findViewById(R.id.registerLoader).setVisibility(View.INVISIBLE);

        //Start a new intent
        Intent intent = new Intent(RegisterActivity.this, StatusActivity.class);

        /*
        *
        * DA FARE
        * In base al messaggio di risposta dal server si decide che parametro passare all'intent
        * per capire che messaggio mostrare nell'activity di stato (Successo o Errore)
        *
        * */
        intent.putExtra("REQUEST_STATUS", 200);

        startActivity(intent);
    }

}
