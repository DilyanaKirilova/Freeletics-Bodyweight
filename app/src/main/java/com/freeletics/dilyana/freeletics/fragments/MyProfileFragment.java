package com.freeletics.dilyana.freeletics.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
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

import static android.app.Activity.RESULT_OK;

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

        profileImage = (ImageView) root.findViewById(R.id.profile_pic_my_profile);
       /* profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//"android.media.action.IMAGE_CAPTURE");
                getActivity().startActivityForResult(intent, REQUEST_CODE);
            }
        }); */
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

   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            profileImage.setImageBitmap(bitmap);
        }
    }*/

}
