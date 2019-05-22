package com.getubusiness.bodytantra.loginregister;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.getubusiness.R;
import com.getubusiness.bodytantra.MainActivity;
import com.getubusiness.bodytantra.YourPreference;
import com.getubusiness.bodytantra.loginregister.activities.ForgotPassword2;
import com.getubusiness.bodytantra.loginregister.activities.LoginActivity;
import com.getubusiness.bodytantra.loginregister.model.User;
import com.getubusiness.bodytantra.loginregister.sql.DatabaseHelper;
import com.msg91.sendotp.library.SendOtpVerification;
import com.msg91.sendotp.library.Verification;
import com.msg91.sendotp.library.VerificationListener;

import org.json.JSONObject;

public class OTPActivity extends AppCompatActivity implements VerificationListener {
private EditText ed1,ed2,ed3,ed4;
private Button verfiyotp;
private String phoneNumber,pswd,name,email;
private int userid;
private Verification mVerification;
private int classnum;
private User user;
private DatabaseHelper databaseHelper;
private JSONObject json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ed1=findViewById(R.id.ed1);
        ed2=findViewById(R.id.ed2);
        ed3=findViewById(R.id.ed3);
        ed4=findViewById(R.id.ed4);
        verfiyotp=findViewById(R.id.verifyotp);
        databaseHelper=new DatabaseHelper(this);
        user=new User();


   classnum=getIntent().getIntExtra("classnum",0);

        email=getIntent().getStringExtra("email");
        user= databaseHelper.getUser(email);
userid=user.getId();
phoneNumber=user.getPhoneNumber();
Log.d("phoneNumber",phoneNumber);
name=user.getName();
        ed1.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (ed1.getText().toString().length() == 1)     //size as per your requirement
                {
                    ed2.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

        });

        ed2.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (ed2.getText().toString().length() == 1)     //size as per your requirement
                {
                    ed3.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

        });
        ed3.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (ed3.getText().toString().length() == 1)     //size as per your requirement
                {
                    ed4.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

        });

        ed4.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (ed4.getText().toString().length() == 1)     //size as per your requirement
                {
                  //  editText3.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

        });

        mVerification = SendOtpVerification.createSmsVerification
                (SendOtpVerification
                        .config("+91" +  phoneNumber)
                        .context(this)
                       //  .senderId("srii")

                        .autoVerification(true)
                        .build(), this);

        mVerification.initiate();

        verfiyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ed11=ed1.getText().toString();
                String ed22=ed2.getText().toString();
                String ed33=ed3.getText().toString();
                String ed44=ed4.getText().toString();
                String otp_code=ed11+ed22+ed33+ed44;
                mVerification.verify(otp_code);


            }
        });


    }
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase("otp")) {
                final String message = intent.getStringExtra("message");
                Log.d("msd",message);
                ed1.setText(message.toString().charAt(0));
                ed2.setText(message.toString().charAt(1));
                ed3.setText(message.toString().charAt(2));
                ed4.setText(message.toString().charAt(3));
            }
        }
    };

    @Override
    public void onInitiated(String response) {

    }

    @Override
    public void onInitiationFailed(Exception paramException) {
        Toast.makeText(OTPActivity.this,"Failed",Toast.LENGTH_LONG).show();
        if(classnum==2){
            databaseHelper.deleteUser(user);
        }
    }

    @Override
    public void onVerified(String response) {
        ProgressDialog progressDialog=new ProgressDialog(OTPActivity.this);
        progressDialog.setMessage("please wait.....");
        progressDialog.setCancelable(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        Toast.makeText(OTPActivity.this, "Succesfully generated", Toast.LENGTH_LONG).show();
        if (classnum == 1) {
            //YourPreference.getInstance(this).saveData("log","yes");
           //// Intent intent=new Intent(OTPActivity.this, MainActivity.class);
           // progressDialog.dismiss();
           // startActivity(intent);

        } else if (classnum == 2) {
          //  User user=new User();
          //  user.setId(userid);
           // user.setName(name);
           // user.setEmail(email);
           // user.setPhoneNumber(phoneNumber);
           // user.setPassword(pswd);
           // databaseHelper.addUser(user);
           // YourPreference.getInstance(this).saveData("log","yes");
            Intent intent=new Intent(OTPActivity.this, LoginActivity.class);
          progressDialog.dismiss();
            Toast.makeText(OTPActivity.this, "success_message", Toast.LENGTH_LONG).show();
            startActivity(intent);
        } else {
            Intent intent = new Intent(OTPActivity.this, ForgotPassword2.class);

            intent.putExtra("phoneNumber", phoneNumber);
            intent.putExtra("userid", userid);
            intent.putExtra("username", name);
            intent.putExtra("email", email);
            Log.d("useridinotp", String.valueOf(userid));
            progressDialog.dismiss();
            startActivity(intent);
        }
    }

    @Override
    public void onVerificationFailed(Exception paramException) {

    }
    @Override
    public void onResume() {
        LocalBroadcastManager.getInstance(this).
                registerReceiver(receiver, new IntentFilter("otp"));
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

}
