package com.gochain.gochainandroid.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.gochain.gochainandroid.R;
import com.gochain.gochainandroid.SessionValueHelper;
import com.gochain.gochainandroid.rest.GoChainRestService;
import com.gochain.gochainandroid.vo.AuthenticatedUserVo;
import com.gochain.gochainandroid.vo.UserVo;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText inputUsername, inputPassword;
    private TextInputLayout inputLayoutUsername, inputLayoutPassword;
    private Button btnSignUp;
    private ProgressBar progressBar;
    private LinearLayout loginForm;
    private UserLoginTask mAuthTask = null;


    private GoChainRestService goChainRestService;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//        startActivity(intent);
        goChainRestService = new GoChainRestService();
        inputLayoutUsername = (TextInputLayout) findViewById(R.id.input_layout_username);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        inputUsername = (EditText) findViewById(R.id.input_username);
        inputPassword = (EditText) findViewById(R.id.input_password);
        btnSignUp = (Button) findViewById(R.id.btn_signup);

        inputUsername.addTextChangedListener(new MyTextWatcher(inputUsername));
        inputPassword.addTextChangedListener(new MyTextWatcher(inputPassword));

        progressBar = (ProgressBar) findViewById(R.id.login_progress);
        loginForm = (LinearLayout) findViewById(R.id.login_form);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * Validating form
     */
    private void submitForm() {

        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }

//        showProgress(true);
        mAuthTask = new UserLoginTask();
        mAuthTask.execute(new String[]{inputUsername.getText().toString().trim(), inputPassword.getText().toString().trim()});

    }

    private boolean validateEmail() {
        if (inputUsername.getText().toString().trim().isEmpty()) {
            inputLayoutUsername.setError(getString(R.string.error_invalid_username));
            requestFocus(inputUsername);
            return false;
        } else {
            inputLayoutUsername.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (inputPassword.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.error_invalid_password));
            requestFocus(inputPassword);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

//    private static boolean isValidEmail(String email) {
//        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
//    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Login Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_username:
                    validateEmail();
                    break;
                case R.id.input_password:
                    validatePassword();
                    break;
            }
        }
    }


    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<String, Void, Boolean> {

        UserLoginTask() {
        }

        @Override
        protected Boolean doInBackground(String... params) {
            // TODO: attempt authentication against a network service.
            AuthenticatedUserVo authenticatedUserVo = goChainRestService.authenticateUser(new UserVo(params[0], params[1]));
            if (authenticatedUserVo != null && authenticatedUserVo.getAuthenticated()) {
                Log.i("Login", authenticatedUserVo.toString());
                SessionValueHelper.setSessionUser(authenticatedUserVo);
                return true;
            }

            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
//            showProgress(false);

            if (success) {
                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            } else {
                inputLayoutPassword.setError(getString(R.string.error_invalid_password));
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
        }
    }


}

