package com.getubusiness.bodytantra;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import com.getubusiness.R;
import com.getubusiness.bodytantra.loginregister.activities.LoginActivity;

public class Packages extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages);
        webView=findViewById(R.id.webview);
        webView.loadUrl("https://www.bodytantra.com/packages/");
        Toolbar toolbar = findViewById(R.id.toolbar_packages);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout_packages);
        NavigationView navigationView = findViewById(R.id.navigation_view_packages);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_packages);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            Intent intent=new Intent(Packages.this,MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.logout) {
            YourPreference.getInstance(Packages.this).saveData("log","No");
            Intent intent=new Intent(Packages.this, LoginActivity.class);
            startActivity(intent);

        } else if (id == R.id.services) {
            Intent intent=new Intent(Packages.this, Services.class);
            startActivity(intent);
        } else if (id == R.id.packages) {
            Intent intent=new Intent(Packages.this, Packages.class);
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

        DrawerLayout drawer = findViewById(R.id.drawer_layout_packages);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
