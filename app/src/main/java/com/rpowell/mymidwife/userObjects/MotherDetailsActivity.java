/***************************************************************************
 * COPYRIGHT (C) 2015, Rapid7 LLC, Boston, MA, USA.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Rapid7.
 **************************************************************************/
package com.rpowell.mymidwife.userObjects;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.rpowell.mymidwife.ApplicationData;
import com.rpowell.mymidwife.FontCache;
import com.rpowell.mymidwife.MainActivity;
import com.rpowell.mymidwife.R;
import com.rpowell.mymidwife.RegisterActivity;
import com.rpowell.mymidwife.network.Network;
import com.rpowell.mymidwife.network.NetworkUtils;
import com.rpowell.mymidwife.responseMappers.MotherDetailsResponseMapper;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A login screen that offers login via email/password.
 */
public class MotherDetailsActivity extends Activity {

    // UI references.
    private View mLoginFormView;
    private AutoCompleteTextView height;
    private AutoCompleteTextView address;
    private AutoCompleteTextView postcode;
    private EditText number;
    private AutoCompleteTextView weight;
    private AutoCompleteTextView waist;
    private AutoCompleteTextView weeks;
    private Spinner ailment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Prevent soft input from modifying screen
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

        //set content view AFTER ABOVE sequence (to avoid crash)
        this.setContentView(R.layout.frament_mother_details);

        // The font to use for each field
        Typeface type = FontCache.getFont(this, "DINOT-Light.ttf");

        TextView name = (TextView) findViewById(R.id.ProfileCardMainProfileDetailsName);
        if (ApplicationData.getInstance().getCurrentUser() != null)
        {
            name.setText(ApplicationData.getInstance().getCurrentUser().getFirstname().toUpperCase());
        }

        address =
                (AutoCompleteTextView) findViewById(R.id.ProfileCardMainProfileContactAddress);

        postcode =
                (AutoCompleteTextView) findViewById(R.id.ProfileCardMainProfileContactPostcode);

        number =
                (EditText) findViewById(R.id.ProfileCardMainProfileDetailsNumber);

        height =
                (AutoCompleteTextView) findViewById(R.id.ProfileCardMainProfileContactHeight);

        weight =
                (AutoCompleteTextView) findViewById(R.id.ProfileCardMainProfileContactWeight);

        waist =
                (AutoCompleteTextView) findViewById(R.id.ProfileCardMainProfileContactWaist);

        weeks =
                (AutoCompleteTextView) findViewById(R.id.ProfileCardMainProfileContactTerm);

        ailment =
                (Spinner) findViewById(R.id.ailmentSpinner);

        String[] arraySpinner = new String[] {
                "None", "Epilepsy", "Diabetes", "Heart Conditions"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        ailment.setAdapter(adapter);

        Button save = (Button) findViewById(R.id.saveButton);
        save.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Network.addMotherDetails(
                        Integer.valueOf(height.getText().toString()),
                        waist.getText().toString(),
                        weight.getText().toString(),
                        Integer.valueOf(weeks.getText().toString()),
                        postcode.getText().toString(),
                        ailment.getSelectedItem().toString(),
                        address.getText().toString(),
                        new Callback<MotherDetailsResponseMapper>() {
                            @Override
                            public void success(MotherDetailsResponseMapper s, Response response) {
                                MotherDetails motherDetails =
                                        new MotherDetails(s.getHeight(), s.getWaist(), s.getWeight(), s.getWeeks(), s.getPostcode());

                                ApplicationData.getInstance()
                                        .getCurrentUser()
                                        .setMotherDetails(motherDetails);

                                Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
                                mainActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                getApplicationContext().startActivity(mainActivityIntent);
                                finish();
                            }

                            @Override
                            public void failure(RetrofitError error)
                            {

                            }
                        });



            }
        });

        final Context registerContext = this;
        mLoginFormView = findViewById(R.id.login_form);
    }



    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });
        }
    }
}



