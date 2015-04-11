/***************************************************************************
 * COPYRIGHT (C) 2015, Rapid7 LLC, Boston, MA, USA.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Rapid7.
 **************************************************************************/
package com.rpowell.mymidwife;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import java.io.IOException;

public class RegisterActivity extends Activity {

    private AutoCompleteTextView mEmailView;
    private EditText mFirstNameView;
    private EditText mLastNameView;
    private EditText mPasswordView;
    private EditText mConfirmView;
    private final Context mAppCtx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Prevent soft input from modifying screen
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

        //set content view AFTER ABOVE sequence (to avoid crash)
        this.setContentView(R.layout.activity_register);

        // The font to use for each field
        Typeface type = FontCache.getFont(this, "DINOT-Light.ttf");

        // The email field
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mEmailView.setTypeface(type);

        // First name field
        mFirstNameView = (EditText) findViewById(R.id.first);
        mFirstNameView.setTypeface(type);

        // Last name field
        mLastNameView = (EditText) findViewById(R.id.last);
        mLastNameView.setTypeface(type);

        // The password field
        mPasswordView = (EditText) findViewById(R.id.password_field);
        mPasswordView.setTypeface(type);

        // The confirm password field
        mConfirmView = (EditText) findViewById(R.id.confirm_password);
        mConfirmView.setTypeface(type);

        mPasswordView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Drawable myIcon = getResources().getDrawable(R.drawable.info);
                mPasswordView.setError(getString(R.string.error_invalid_password), myIcon);
            }
        });

        mConfirmView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.register || id == EditorInfo.IME_NULL) {
                    try {
                        attemptRegistration();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return true;
            }
        });

        Button mRegisterButton = (Button) findViewById(R.id.register_button);
        mRegisterButton.setTypeface(type);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    attemptRegistration();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        TextView mloginButton = (TextView) findViewById(R.id.signin);
        mloginButton.setTypeface(type);
        mloginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerActivityIntent = new Intent(mAppCtx, LoginActivity.class);
                startActivity(registerActivityIntent);
            }
        });
    }

    /**
     * Attempts to register the account specified by the registration form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual registration attempt is made.
     */
    public void attemptRegistration()
            throws IOException, JSONException {

        View focusView = null;
        boolean cancel = false;

        final String email = mEmailView.getText().toString();
        String firstName = mFirstNameView.getText().toString();
        String lastName = mLastNameView.getText().toString();
        final String password = mPasswordView.getText().toString();
        String confirmPassword = mConfirmView.getText().toString();

        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            mConfirmView.setError(getString(R.string.error_password_mismatch));
            focusView = mConfirmView;
            cancel = true;
        }

        // Set required on confirm password
        if (TextUtils.isEmpty(confirmPassword)) {
            mConfirmView.setError(getString(R.string.error_field_required));
            focusView = mConfirmView;
            cancel = true;
        }

        // Check for valid password
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }

        // Check for a first name.
        if (TextUtils.isEmpty(firstName)) {
            mFirstNameView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }

        // Check for a last name.
        if (TextUtils.isEmpty(lastName)) {
            mLastNameView.setError(getString(R.string.error_field_required));
            focusView = mLastNameView;
            cancel = true;
        }

        if (cancel)
            focusView.requestFocus();
        else {
            final ProgressDialog progressDialog = new ProgressDialog(mAppCtx);
            progressDialog.setIndeterminate(true);
            progressDialog.setTitle("Registering");
            progressDialog.show();

            Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
            mainActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(mainActivityIntent);
            progressDialog.dismiss();
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }
}
