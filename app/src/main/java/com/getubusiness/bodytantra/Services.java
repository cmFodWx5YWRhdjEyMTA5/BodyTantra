package com.getubusiness.bodytantra;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.getubusiness.R;
import com.getubusiness.bodytantra.adapters.CustomServiceAdapter;
import com.getubusiness.bodytantra.loginregister.activities.LoginActivity;

public class Services extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        listview=findViewById(R.id.services_listview);
        String services[]={
                "STYLING","ENHANCE","MEN'S GROOMING","CLLEAN UP'S","FACIALS","LIGHTENING&BLEACH","UNDER EYE TREATMENTS","SKIN","LASER","HAIR","ANTI-AGENING TREATMENTS","BODY WRAPS/MOSQUES","BODY MASSAGE THERAPIES","BODY SCRUBS","BODY SPA PACKAGES","TINTING","NAIL BAR","EYELASH EXTENSION","THREADING","MANICURES&PREDICURES","WAXIN","BRIDAL PACKAGES","PRE BRIDAL TREATMENTS"
        };
        CustomServiceAdapter adapter=new CustomServiceAdapter(this,  R.layout.services_list,services);
        listview.setAdapter(adapter);
listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("ehfr","cfgghgh");
        Intent intent=new Intent(Services.this,Booking.class);
      //  intent.putExtra("services",parent.)
        startActivity(intent);
    }
});

        Toolbar toolbar = findViewById(R.id.toolbar_services);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        DrawerLayout drawer = findViewById(R.id.drawer_layout_services);
        NavigationView navigationView = findViewById(R.id.navigation_view_services);
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
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            Intent intent=new Intent(Services.this,MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.logout) {
            YourPreference.getInstance(Services.this).saveData("log","No");
            Intent intent=new Intent(Services.this, LoginActivity.class);
            startActivity(intent);

        } else if (id == R.id.services) {
            Intent intent=new Intent(Services.this, Services.class);
            startActivity(intent);
        } else if (id == R.id.packages) {
            Intent intent=new Intent(Services.this, Packages.class);
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

        DrawerLayout drawer = findViewById(R.id.drawer_layout_services);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}


