package com.getubusiness.bodytantra;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;



import com.getubusiness.R;
import com.getubusiness.bodytantra.CustomRecyclerAdapter;
import com.getubusiness.bodytantra.adapters.CustomAdapter;
import com.getubusiness.bodytantra.loginregister.activities.LoginActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
private RecyclerView recyclerView;
private ArrayList al;
private AdapterViewFlipper adapterViewFlipper;
private ArrayList IMAGES;
private BottomNavigationView bottomNavigationView;
    private boolean shouldLoadHomeFragOnBackPress = true;

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.bottom_nav_bar);
       toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



//        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
  //      navigationView = (NavigationView) findViewById(R.id.nav_view);


      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    //     actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer);



        //Setting the actionbarToggle to drawer layout
      // drawer.setDrawerListener(actionBarDrawerToggle);
       //actionBarDrawerToggle.syncState();

        //calling sync state is necessary or else your hamburger icon wont show up
       // actionBarDrawerToggle.syncState();

      //  drawer.closeDrawer(GravityCompat.START);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.home_bottom:
                        return true;
                    case R.id.offers_bottom:
                        Intent intent=new Intent(MainActivity.this,Offers.class);
                        startActivity(intent);
                    case R.id.services_bottom:
                        Intent intent1=new Intent(MainActivity.this,Services.class);
                        startActivity(intent1);
                }
                return true;
            }
        });
   //     navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
     //       @Override
       //     public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
         //       return false;
           // }
       // });
        IMAGES=new ArrayList();
        IMAGES.add(R.drawable.essential_oil_3073901_1920);
        IMAGES.add(R.drawable.back1);
        IMAGES.add(R.drawable.girl_2193272_1920);
        IMAGES.add(R.drawable.pool_690034);
        IMAGES.add(R.drawable.spa_1);
        IMAGES.add(R.drawable.spa_08);
        IMAGES.add(R.drawable.spa_07);
        IMAGES.add(R.drawable.spa_40);
        IMAGES.add(R.drawable.spa_beauty_21);
        IMAGES.add(R.drawable.woman_3288365_1920);
        IMAGES.add(R.drawable.wedding_2367561_1920);
        IMAGES.add(R.drawable.wedding_725432);
        IMAGES.add(R.drawable.spa_header_bg_1);
        IMAGES.add(R.drawable.spa_beauty_95);
        IMAGES.add(R.drawable.spa_beauty_85);

adapterViewFlipper=(AdapterViewFlipper) findViewById(R.id.adapter_view_flipper);
// FlipperAdapter flipperAdapter=new FlipperAdapter(this,R.layout.layout_flipper,IMAGES);
        CustomAdapter customAdapter=new CustomAdapter(MainActivity.this,IMAGES);
 adapterViewFlipper.setAdapter(customAdapter);
 adapterViewFlipper.startFlipping();
 adapterViewFlipper.setAutoStart(true);
        recyclerView=findViewById(R.id.recyclerview);
        al=new ArrayList();
      //  al.add(R.drawable.bath_balls_1617472_1);
    //   al.add(R.drawable.essential_oil_3073901_1920);
     //  al.add(R.drawable.pool_690034);
     //  al.add(R.drawable.spa_beauty_95);
     //  al.add(R.drawable.spa_beauty_85);
      // al.add(R.drawable.spa_beauty_21);
       al.add(R.mipmap.bath_balls_1617472_1_launch_round);
       al.add(R.mipmap.essential_oil_3073901_1920_launch_round);
       al.add(R.mipmap.girl_2193272_1920_launch_round);
       al.add(R.mipmap.spa_1_launch_round);
       al.add(R.mipmap.spa_07_launch_round);
       al.add(R.mipmap.spa_08_launch_round);
       al.add(R.mipmap.spa_40_launch_round);
       al.add(R.mipmap.spa_beauty_21_launch_round);
       al.add(R.mipmap.woman_3288365_1920_launch_round);
       al.add(R.mipmap.wedding_725432_launch_round);
       al.add(R.mipmap.wedding_2367561_1920_launch_round);
       al.add(R.mipmap.spa_header_bg_1_launch_round);
       al.add(R.mipmap.spa_beauty_95_launch_round);
       al.add(R.mipmap.spa_beauty_85_launch_round);
     //  al.add(R.mipmap.)
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter=new CustomRecyclerAdapter(this,al);
        recyclerView.setAdapter(adapter);
   /*    // ///*recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(MainActivity.this, Booking.class);
                startActivity(intent);

            }

        });
*/

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout_main);
        NavigationView navigationView = findViewById(R.id.navigation_view_main);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_main);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    public class FlipperAdapter extends ArrayAdapter {
        Context context;
        ArrayList IMAGES;
        LayoutInflater layoutInflater;
        public FlipperAdapter(Context context,int resource,ArrayList IMAGES){

           super(context,resource,IMAGES);
            Log.d("len", String.valueOf(IMAGES.size()));
            this.context=context;
            this.IMAGES=IMAGES;
            layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        }


         @Override
         public int getCount() {
             return 0;
         }

         @Override
         public Object getItem(int position) {
             return null;
         }

         @Override
         public long getItemId(int position) {
             return 0;
         }

         @Override
         public View getView(int position, View convertView, ViewGroup parent) {
             Log.d("here","here:");
             Log.d("length", String.valueOf(IMAGES.size()));
             convertView= layoutInflater.inflate(R.layout.layout_flipper,parent,false);

             ImageView imageView=(ImageView) convertView.findViewById(R.id.imageview_flipper);
             imageView.setImageResource((int)IMAGES.get(position));
             return convertView;
         }
     }
   /* @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    */
   @Override
   public boolean onNavigationItemSelected(MenuItem item) {
       // Handle navigation view item clicks here.
       int id = item.getItemId();

       if (id == R.id.nav_home) {
           // Handle the camera action
           Intent intent=new Intent(MainActivity.this,MainActivity.class);
           startActivity(intent);
       } else if (id == R.id.nav_gallery) {

       } else if (id == R.id.logout) {
           YourPreference.getInstance(MainActivity.this).saveData("log","No");
           Intent intent=new Intent(MainActivity.this, LoginActivity.class);
           startActivity(intent);

       } else if (id == R.id.services) {
           Intent intent=new Intent(MainActivity.this, Services.class);
           startActivity(intent);
       } else if (id == R.id.packages) {
           Intent intent=new Intent(MainActivity.this, Packages.class);
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

       DrawerLayout drawer = findViewById(R.id.drawer_layout_main);
       drawer.closeDrawer(GravityCompat.START);
       return true;
   }

}
