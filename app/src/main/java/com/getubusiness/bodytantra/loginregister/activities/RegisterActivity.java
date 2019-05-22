package com.getubusiness.bodytantra.loginregister.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;


import com.getubusiness.R;
import com.getubusiness.bodytantra.loginregister.OTPActivity;
import com.getubusiness.bodytantra.loginregister.helpers.InputValidation;
import com.getubusiness.bodytantra.loginregister.model.User;
import com.getubusiness.bodytantra.loginregister.sql.DatabaseHelper;
import com.msg91.sendotp.library.Verification;
import com.msg91.sendotp.library.VerificationListener;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, VerificationListener {

    private final AppCompatActivity activity = RegisterActivity.this;

    private NestedScrollView nestedScrollView;
    private Verification mVerification;
    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutPhoneNumber;
    private TextInputLayout textInputLayoutConfirmPassword;
    private TextInputLayout textInputLayoutotp;
    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextPhoneNumber;
    private TextInputEditText textInputEditTextConfirmPassword;
    private TextInputEditText textInputEditTextotp;
    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;
    private AppCompatButton appCompatButtonSubmit;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
textInputLayoutPhoneNumber=(TextInputLayout) findViewById(R.id.textInputLayoutPhoneNumber);
        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);
//textInputLayoutotp=findViewById(R.id.textInputLayoutotp);
//textInputEditTextotp=findViewById(R.id.textInputEditTextotp);
        textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);
textInputEditTextPhoneNumber=(TextInputEditText) findViewById(R.id.textInputEditTextPhoneNumber);
        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);


        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);
      //  appCompatButtonSubmit.setOnClickListener(this);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();

    }


    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonRegister:
                postDataToSQLite();
                break;

            case R.id.appCompatTextViewLoginLink:
                finish();
                break;

        }
    }

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }

        if (!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) {
if(!textInputEditTextPhoneNumber.getText().toString().isEmpty()) {

       user.setName(textInputEditTextName.getText().toString().trim());
        user.setEmail(textInputEditTextEmail.getText().toString().trim());
        user.setPassword(textInputEditTextPassword.getText().toString().trim());
       user.setPhoneNumber(textInputEditTextPhoneNumber.getText().toString().trim());
        databaseHelper.addUser(user);
 //   mVerification.verify(textInputEditTextotp.getText().toString());
    Intent intent = new Intent(RegisterActivity.this, OTPActivity.class);
    intent.putExtra("classnum",2);
    intent.putExtra("email",textInputEditTextEmail.getText().toString());
    startActivity(intent);

    emptyInputEditText();
        Log.d("control", "control here");

        // Snack Bar to show success message that record saved successfully


}
else{
    textInputEditTextPhoneNumber.setError("Please enter phone number");
}

        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }


    }
    public void otpsubmit(){

    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
    }

    @Override
    public void onInitiated(String response) {

    }

    @Override
    public void onInitiationFailed(Exception paramException) {

    }

    @Override
    public void onVerified(String response) {

    }

    @Override
    public void onVerificationFailed(Exception paramException) {

    }
}
