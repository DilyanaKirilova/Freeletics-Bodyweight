package com.freeletics.dilyana.freeletics.dialog_fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import static android.app.Activity.RESULT_OK;

import com.freeletics.dilyana.freeletics.HomeActivity;
import com.freeletics.dilyana.freeletics.R;
import com.freeletics.dilyana.freeletics.model.actions.Action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends DialogFragment {

        Button btnGallery=null;
        Button btnCamera=null;


        @Override
        public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
                View root = inflater.inflate(R.layout.fragment_gallery, container, false);


                btnGallery = (Button) root.findViewById(R.id.button_gallery);
                btnCamera = (Button) root.findViewById(R.id.button_camera);
                btnGallery.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                showFileChooser();
                        }
                });
                btnCamera.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(photoCaptureIntent, 2);
                        }
                });
                return root;
        }

        private void showFileChooser() {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
                        Uri filePath = data.getData();
                        Intent publishIntent = new Intent(getActivity(), HomeActivity.class);
                        publishIntent.putExtra("photoUri", filePath.toString());
                        startActivity(publishIntent);
                } else if (requestCode == 2 && resultCode == RESULT_OK && data != null && data.getData() != null) {
                        Uri filePath = data.getData();
                        Intent publishIntent = new Intent(getActivity(), HomeActivity.class);
                        publishIntent.putExtra("photoUri", filePath.toString());
                        startActivity(publishIntent);
                }
        }
}
