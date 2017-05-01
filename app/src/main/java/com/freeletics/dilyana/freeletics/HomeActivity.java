package com.freeletics.dilyana.freeletics;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

import com.facebook.CallbackManager;
import com.freeletics.dilyana.freeletics.fragments.CategoryFragment;
import com.freeletics.dilyana.freeletics.fragments.SettingsFragment;
import com.freeletics.dilyana.freeletics.fragments.WeekScheduleFragment;
import com.freeletics.dilyana.freeletics.fragments.MyProfileFragment;
import com.freeletics.dilyana.freeletics.fragments.MyProgramFragment;
import com.freeletics.dilyana.freeletics.model.users.User;
import com.freeletics.dilyana.freeletics.model.users.UsersManager;

import java.io.Serializable;
import java.net.URL;

import static android.graphics.BitmapFactory.decodeFile;
import static com.freeletics.dilyana.freeletics.R.array.gender;
import static com.freeletics.dilyana.freeletics.R.array.weight;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, TimePickerDialog.OnTimeSetListener {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private ImageView profileImage;
    private TextView name;
    private com.facebook.login.widget.LoginButton loginButton;
    private CallbackManager callbackManager;

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
        callbackManager =  CallbackManager.Factory.create();




        //name = (TextView) findViewById(R.id.first_last_name);

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
                Bundle b = getIntent().getExtras();
                MyProfileFragment myProfileFragment = new MyProfileFragment();
                if (b != null) {
                    String photopPath = b.getString("photo");
                   // photoUri = Uri.parse(b.getString("photoUri"));
                    //Bundle bundle = new Bundle();
                    //bundle.putSerializable("uri", (Serializable) photoUri);
                    myProfileFragment.setArguments(b);

                }
                ft.replace(R.id.fragment_container, myProfileFragment).addToBackStack("my_profile_frag").commit();
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
            fragmentTransaction.replace(R.id.fragment_container, new CategoryFragment()).addToBackStack("category").commit();

        } else if (id == R.id.nav_my_bmi) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.fragment_container, new MyProgramFragment()).addToBackStack("my_program").commit();

        }  else if (id == R.id.nav_settings) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.fragment_container, new SettingsFragment()).addToBackStack("settings").commit();

        } else if(id == R.id.nav_my_schedule){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new WeekScheduleFragment()).addToBackStack("schedule").commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        ((TextView) findViewById(R.id.tv_fae_hour)).setText( hourOfDay + "");
        ((TextView) findViewById(R.id.tv_fae_minute)).setText( minute + "");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            // Intent publishIntent = new Intent(HomeActivity.this, HomeActivity.class);
            // publishIntent.putExtra("photoUri", filePath.toString());
            //startActivity(publishIntent);
            Cursor cursor = getContentResolver().query(filePath, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Bundle bundle = new Bundle();
            bundle.putString("picture", picturePath);
            MyProfileFragment myProfileFragment = new MyProfileFragment();
            myProfileFragment.setArguments(bundle);
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.fragment_container, myProfileFragment).addToBackStack("take_photo").commit();

        }
    }

}
