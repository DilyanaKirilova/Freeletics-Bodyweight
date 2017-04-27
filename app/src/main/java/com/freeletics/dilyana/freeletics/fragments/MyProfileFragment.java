package com.freeletics.dilyana.freeletics.fragments;


import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.freeletics.dilyana.freeletics.R;
import com.freeletics.dilyana.freeletics.adapters.MyProfileAdapter;
import com.freeletics.dilyana.freeletics.dialog_fragments.GalleryFragment;
import com.freeletics.dilyana.freeletics.model.actions.Action;
import com.freeletics.dilyana.freeletics.model.users.User;
import com.freeletics.dilyana.freeletics.model.users.UsersManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyProfileFragment extends Fragment {

    private ImageView profileImage;
    private TextView profileName;
    private TextView level;
    private TextView doneWorkouts;
    private RecyclerView recyclerView;
    private Context context;
    private static final int REQUEST_CODE = 1313;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_my_profile, container, false);
        if(getArguments()!=null && getArguments().getString("fragment")!=null){
            MediaPlayer mediaPlayer = MediaPlayer.create(getActivity(), R.raw.applause);
            mediaPlayer.start();
        }

        profileImage = (ImageView) root.findViewById(R.id.profile_pic_my_profile);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GalleryFragment galleryFragment = new GalleryFragment();
                galleryFragment.show(getActivity().getSupportFragmentManager(), "my_gallery");

            }
        });
        profileName = (TextView) root.findViewById(R.id.name_my_profile);
        level = (TextView) root.findViewById(R.id.level_my_profile);
        doneWorkouts = (TextView) root.findViewById(R.id.workouts_my_profile);
        context = root.getContext();
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view_my_profile);

        UsersManager usersManager = UsersManager.getInstance();
        User u = usersManager.getLoggedUser();
        if (u.getPicture() != 0) {
            profileImage.setImageResource(u.getPicture());
        }

        Uri profile;
        if(getArguments()!=null && getArguments().getSerializable("uri")!=null) {
            profile = (Uri) getArguments().getSerializable("uri");
            String prof = profile.toString();
            int pic = Integer.parseInt(prof);
            u.setPicture(pic);
            profileImage.setImageResource(pic);
        }
        profileName.setText(u.getFirstName() + " " + u.getLastName());
        level.setText(String.valueOf(u.getLevel()));
        if(getArguments() != null && getArguments().getSerializable("action") != null){

            Action a = (Action) getArguments().getSerializable("action");
            doneWorkouts.setText(String.valueOf(u.getWorkouts(a.getName()).size()));

            MyProfileAdapter myProfileAdapter = new MyProfileAdapter(u.getWorkouts(a.getName()), (AppCompatActivity) getActivity());
            recyclerView.setAdapter(myProfileAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        }

        return root;
    }
}
