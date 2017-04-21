package com.freeletics.dilyana.freeletics.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.freeletics.dilyana.freeletics.R;
import com.freeletics.dilyana.freeletics.adapters.MyProfileAdapter;
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_my_profile, container, false);

        profileImage = (ImageView) root.findViewById(R.id.profile_pic_my_profile);
        profileName = (TextView) root.findViewById(R.id.name_my_profile);
        level = (TextView) root.findViewById(R.id.level_my_profile);
        doneWorkouts = (TextView) root.findViewById(R.id.workouts_my_profile);
        context = root.getContext();
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view_my_profile);

        UsersManager usersManager = UsersManager.getInstance();
        User u = usersManager.getLoggedUser();
        if(u.getPicture()!=0) {
            profileImage.setImageResource(u.getPicture());
        }
        profileName.setText(u.getFirstName()+" "+u.getLastName());
        level.setText(u.getLevel()+" ");
        doneWorkouts.setText(u.getWorkouts().size()+"");

        MyProfileAdapter myProfileAdapter = new MyProfileAdapter(u.getWorkouts(), context);
        recyclerView.setAdapter(myProfileAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        return root;
    }

}
