package com.freeletics.dilyana.freeletics.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.freeletics.dilyana.freeletics.R;
import com.freeletics.dilyana.freeletics.model.actions.Action;
import com.freeletics.dilyana.freeletics.model.users.User;
import com.freeletics.dilyana.freeletics.model.users.UsersManager;

import java.util.List;

/**
 * Created by Ioana on 21.04.2017 г..
 */

public class MyProfileAdapter extends RecyclerView.Adapter<MyProfileAdapter.MyProfileViewHolder> {

    private List<Action> exerciseList;
    private Context context;

    public MyProfileAdapter(List<Action> exerciseList, Context context) {
        this.exerciseList = exerciseList;
        this.context = context;
    }


    @Override
    public MyProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(context);
        View row = li.inflate(R.layout.my_profile_row, parent, false);
        MyProfileViewHolder vh = new MyProfileViewHolder(row);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyProfileViewHolder holder, int position) {
        UsersManager manager = UsersManager.getInstance();
        User user = manager.getLoggedUser();
        holder.image.setImageResource(user.getPicture());
        holder.name.setText(user.getFirstName()+" "+user.getLastName());
        holder.exerciseName.setText(exerciseList.get(position).getName().toString());
        holder.timeOfExercise.setText("");
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }


    class MyProfileViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout linearLayout;
        private ImageView image;
        private TextView name;
        private TextView exerciseName;
        private TextView timeOfExercise;

        public MyProfileViewHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.profile_recycler_layout);
            image = (ImageView) itemView.findViewById(R.id.profile_pic_wall);
            name = (TextView) itemView.findViewById(R.id.my_name_wall);
            exerciseName = (TextView) itemView.findViewById(R.id.exercise_name_wall);
            timeOfExercise = (TextView) itemView.findViewById(R.id.time_of_exercise_wall);
        }
    }
}
