package com.getubusiness.bodytantra;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.getubusiness.R;
import com.getubusiness.bodytantra.loginregister.activities.LoginActivity;
import com.getubusiness.bodytantra.loginregister.helpers.InputValidation;
import com.getubusiness.bodytantra.loginregister.model.User;
import com.getubusiness.bodytantra.loginregister.sql.DatabaseHelper;
import com.msg91.sendotp.library.Verification;


import java.util.Calendar;

public class Booking extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private NestedScrollView nestedScrollView_book;
    private Verification mVerification;
    private TextInputLayout textInputLayoutName_book;
    private TextInputLayout textInputLayoutEmail_book;
    private TextInputLayout textInputLayoutDate;
    private TextInputLayout textInputLayoutPhoneNumber_book;
    private TextInputLayout textInputLayoutTime;
    private TextInputLayout textInputLayoutotp;
    private TextInputEditText textInputEditTextName_book;
    private TextInputEditText textInputEditTextEmail_book;
    private TextInputEditText textInputEditTextDate;
    private TextInputEditText textInputEditTextPhoneNumber_book;
    private TextInputEditText textInputEditTextTime;
    private TextInputEditText textInputEditTextotp;
    private AppCompatButton appCompatButtonBook;
    private AppCompatTextView appCompatTextViewLoginLink;
    private AppCompatButton appCompatButtonSubmit;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

 initViews();
 appCompatButtonBook.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         Intent emailIntent =new Intent(Intent.ACTION_SENDTO,Uri.parse("naiduakhil64@gmail.com"));
       //  emailIntent.setData(Uri.parse("naiduakhil64@gmail.com"));
         emailIntent.setType("text/plain");
       //  emailIntent.putExtra(Intent.EXTRA_EMAIL,"naiduakhil64@gmail.com");
         emailIntent.putExtra(Intent.EXTRA_TEXT,textInputEditTextName_book.getText()+"   Booked an appionment\n  "+textInputEditTextDate.getText()+" "+textInputEditTextTime.getText()+"\n"+textInputEditTextPhoneNumber_book.getText());
    emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Appionment booked");
    //emailIntent.setType("message/rfc822");
    startActivity(Intent.createChooser(emailIntent,"chhose an app"));
     }
 });
 textInputEditTextDate.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {

         // Get Current Date
         final Calendar c = Calendar.getInstance();
         mYear = c.get(Calendar.YEAR);
         mMonth = c.get(Calendar.MONTH);
         mDay = c.get(Calendar.DAY_OF_MONTH);


         DatePickerDialog datePickerDialog = new DatePickerDialog(Booking.this,
                 new DatePickerDialog.OnDateSetListener() {

                     @Override
                     public void onDateSet(DatePicker view, int year,
                                           int monthOfYear, int dayOfMonth) {

                      textInputEditTextDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                     }
                 }, mYear, mMonth, mDay);
         datePickerDialog.show();


     }
 });
 textInputEditTextTime.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         final Calendar c = Calendar.getInstance();
         mHour = c.get(Calendar.HOUR_OF_DAY);
         mMinute = c.get(Calendar.MINUTE);

         // Launch Time Picker Dialog
         TimePickerDialog timePickerDialog = new TimePickerDialog(Booking.this,
                 new TimePickerDialog.OnTimeSetListener() {

                     @Override
                     public void onTimeSet(TimePicker view, int hourOfDay,
                                           int minute) {

                         textInputEditTextTime.setText(hourOfDay + ":" + minute);
                     }
                 }, mHour, mMinute, false);
         timePickerDialog.show();
     }

 });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.booking, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            Intent intent=new Intent(Booking.this,MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.logout) {
            YourPreference.getInstance(Booking.this).saveData("log","No");
            Intent intent=new Intent(Booking.this, LoginActivity.class);
            startActivity(intent);

        } else if (id == R.id.services) {
            Intent intent=new Intent(Booking.this, Services.class);
            startActivity(intent);
        } else if (id == R.id.packages) {
            Intent intent=new Intent(Booking.this, Packages.class);
            startActivity(intent);

        } else if (id == R.id.contact) {
Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bodytantra.com/contact/"));
startActivity(intent);
    } else if (id == R.id.about) {
            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bodytantra.com/about/"));
            startActivity(intent);

    }
        else if(id==R.id.blog){
            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bodytantra.com/blog/"));
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void initViews() {
        nestedScrollView_book = (NestedScrollView) findViewById(R.id.nestedScrollView_book);
        textInputLayoutPhoneNumber_book=(TextInputLayout) findViewById(R.id.textInputLayoutPhoneNumber_book);
        textInputLayoutName_book = (TextInputLayout) findViewById(R.id.textInputLayoutName_book);
        textInputLayoutEmail_book = (TextInputLayout) findViewById(R.id.textInputLayoutEmail_book);
        textInputLayoutDate= (TextInputLayout) findViewById(R.id.textInputLayoutDate);
        textInputLayoutTime = (TextInputLayout) findViewById(R.id.textInputLayoutTime);
//textInputLayoutotp=findViewById(R.id.textInputLayoutotp);
//textInputEditTextotp=findViewById(R.id.textInputEditTextotp);
        textInputEditTextName_book = (TextInputEditText) findViewById(R.id.textInputEditTextName_book);
        textInputEditTextEmail_book = (TextInputEditText) findViewById(R.id.textInputEditTextEmail_book);
        textInputEditTextDate = (TextInputEditText) findViewById(R.id.textInputEditTextDate);
        textInputEditTextTime = (TextInputEditText) findViewById(R.id.textInputEditTextTime);
        textInputEditTextPhoneNumber_book=(TextInputEditText) findViewById(R.id.textInputEditTextPhoneNumber_book);
        appCompatButtonBook = (AppCompatButton) findViewById(R.id.appCompatButtonBook);


        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);

    }

}
