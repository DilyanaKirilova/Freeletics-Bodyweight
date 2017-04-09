package com.freeletics.dilyana.freeletics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */

    private static String tOne;
    private static String tTwo;
    private static TextView tvTitleOne;
    private static TextView tvTitleTwo;
    private static Button btnStart;
    private static Button btnLogin;

    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            tOne = sectionNumber +"1";
            tTwo = sectionNumber +"2";
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            if(sectionNumber == 2){
                args.putString(tOne, "BECOME A FREE ATHLETE");
                args.putString(tTwo, "With Freeletics you will become fitter, healthier and more motivated than ever before.");
            }
            else if(sectionNumber == 3){
                args.putString(tOne, "BE PART OF THE MOVEMENT");
                args.putString(tTwo, "Join 12 million athletes. Train together, compete, motivate each other. Be pushed to unleash your potential!");
            }
            else if(sectionNumber == 4){
                args.putString(tOne, "DEVELOP A WHOLE NEW LIFESTYLE");
                args.putString(tTwo, "The Freeletics Coach is your own personalized training plan that guides you into a new athletic lifestyle.");
            }
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_welcome, container, false);

            tvTitleOne = (TextView) rootView.findViewById(R.id.tv_fw_first);
            tvTitleTwo = (TextView) rootView.findViewById(R.id.tv_fw_sec);

            if(getArguments().getString(tOne) != null && getArguments().getString(tTwo) != null) {
                tvTitleOne.setText(getArguments().getString(tOne));
                tvTitleTwo.setText(getArguments().getString(tTwo));
            }

            btnStart = (Button) rootView.findViewById(R.id.btn_fw_start);
            btnLogin = (Button) rootView.findViewById(R.id.btn_fw_login);

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
                case 3:
                    return "SECTION 4";
            }
            return null;
        }
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == btnStart.getId()){
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            intent.putExtra("request_code", "start_now");
            startActivity(intent);
        }
        else if(v.getId() == btnLogin.getId()){
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            intent.putExtra("request_code", "login");
            startActivity(intent);
        }
    }
}
