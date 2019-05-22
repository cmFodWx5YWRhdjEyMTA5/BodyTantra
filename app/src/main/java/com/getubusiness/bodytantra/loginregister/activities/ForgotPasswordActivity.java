package com.getubusiness.bodytantra.loginregister.activities;

import android.content.Intent;
import android.os.Bundle;
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


public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener{
    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutPassword_forgot_conform;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextPassword_forgot_conform;
    private AppCompatButton appCompatButtonLogin;
    private AppCompatButton appCompatButtonLogin_forgot;

    private AppCompatTextView textViewLinkRegister;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);



        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView_forgot);

        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail_forgot);



        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail_forgot);


        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin_forgot1);

      //  textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);


    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonLogin.setOnClickListener(this);
       // appCompatButtonLogin_forgot.setOnClickListener(this);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        databaseHelper = new DatabaseHelper(this);
        inputValidation = new InputValidation(this);

    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonLogin_forgot1:
                verifyFromSQLite();
                break;

        }
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
       User user= databaseHelper.getUser( textInputEditTextEmail.getText().toString());
       Intent intent=new Intent(ForgotPasswordActivity.this, OTPActivity.class);
       intent.putExtra("classnum",0);
       intent.putExtra("phoneNumber",user.getPhoneNumber());
       intent.putExtra("userid",user.getId());
       Log.d("useridinforg1", String.valueOf(user.getId()));
       intent.putExtra("username",user.getName());
       intent.putExtra("email",user.getEmail());
       startActivity(intent);
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }
}
