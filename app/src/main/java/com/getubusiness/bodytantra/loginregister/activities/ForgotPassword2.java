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
import android.widget.Toast;

import com.getubusiness.R;
import com.getubusiness.bodytantra.loginregister.model.User;
import com.getubusiness.bodytantra.loginregister.sql.DatabaseHelper;


public class ForgotPassword2 extends AppCompatActivity {
    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatButton appCompatButtonLogin;
    private DatabaseHelper databaseHelper;
private User user;
    private AppCompatTextView textViewLinkRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password2);
          user=new User();
        user.setId(getIntent().getIntExtra("userid",1));
        user.setPhoneNumber(getIntent().getStringExtra("phoneNumber"));
        user.setEmail(getIntent().getStringExtra("email"));
        user.setName(getIntent().getStringExtra("username"));
        Log.d("useridinforg1", String.valueOf(getIntent().getIntExtra("userid",6)));
        nestedScrollView = (NestedScrollView) findViewById(R.id.forgot2_ns);

        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.password_forgot2);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.password_forgot2_conform);

        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.password_forgot2_edit);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.password_forgot2_conform_edit);


        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.forgot2_button);
        appCompatButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper = new DatabaseHelper(ForgotPassword2.this);
                if ((textInputEditTextEmail.getText().toString()).equals((textInputEditTextPassword.getText().toString()))) {
                    user.setPassword(textInputEditTextEmail.getText().toString());
                    databaseHelper.updateUser(user);
                    Toast.makeText(ForgotPassword2.this, "Password succesfully changed", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ForgotPassword2.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(ForgotPassword2.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                }
            }
        });

}
}
