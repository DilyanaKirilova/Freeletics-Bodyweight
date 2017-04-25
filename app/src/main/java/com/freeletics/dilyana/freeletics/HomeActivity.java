package com.freeletics.dilyana.freeletics;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.Profile;
import com.freeletics.dilyana.freeletics.fragments.CategoryFragment;
import com.freeletics.dilyana.freeletics.fragments.FacebookFragment;
import com.freeletics.dilyana.freeletics.fragments.SettingsFragment;
import com.freeletics.dilyana.freeletics.fragments.WeekScheduleFragment;
import com.freeletics.dilyana.freeletics.fragments.MyProfileFragment;
import com.freeletics.dilyana.freeletics.fragments.MyProgramFragment;
import com.freeletics.dilyana.freeletics.model.DownloadImage;
import com.freeletics.dilyana.freeletics.model.ImageHelper;
import com.freeletics.dilyana.freeletics.model.actions.Workout;
import com.freeletics.dilyana.freeletics.model.users.User;
import com.freeletics.dilyana.freeletics.model.users.UsersManager;

import java.io.InputStream;

import static java.security.AccessController.getContext;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, TimePickerDialog.OnTimeSetListener {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private ImageView profileImage;
    private TextView name;
    private com.facebook.login.widget.LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        profileImage = (ImageView) findViewById(R.id.imageView);

        name = (TextView) findViewById(R.id.first_last_name);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        LinearLayout head = (LinearLayout) header.findViewById(R.id.nav_header);

        name = (TextView) header.findViewById(R.id.first_last_name);
        UsersManager usersManager = UsersManager.getInstance();
        User u = usersManager.getLoggedUser();

        name.setText(u.getFirstName() + " " + u.getLastName());

        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, new MyProfileFragment()).commit();
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, new CategoryFragment(), "Category Fragment").commit();
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_training) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new CategoryFragment()).commit();

        } else if (id == R.id.nav_feed) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.fragment_container, new MyProgramFragment()).commit();


        } else if (id == R.id.nav_leaderboards) {


        } else if (id == R.id.nav_settings) {

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("request_code", "settings");
            startActivity(intent);

        } else if(id == R.id.nav_my_schedule){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new WeekScheduleFragment()).commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setFacebookInfo(){
        Bundle inBundle = getIntent().getExtras();
        String name = inBundle.get("name").toString();
        String surname = inBundle.get("surname").toString();
        String imageUrl = inBundle.get("imageUrl").toString();
        int profilePic = Integer.parseInt(imageUrl);
       // UsersManager usersManager = UsersManager.getInstance();
        //usersManager.setLoggedUser(new User(name, surname, profilePic));
        new DownloadImage((ImageView)findViewById(R.id.imageView)).execute(imageUrl);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(profilePic);
        TextView names = (TextView) findViewById(R.id.first_last_name);
        names.setText(name+" "+surname);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        ((TextView) findViewById(R.id.tv_fae_hour)).setText( hourOfDay + "");
        ((TextView) findViewById(R.id.tv_fae_minute)).setText( minute + "");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
           fragment.onActivityResult(requestCode, resultCode, data); 
        }
    }
}
