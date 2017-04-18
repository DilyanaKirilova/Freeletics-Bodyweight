package com.freeletics.dilyana.freeletics.model.actions;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Dilyana on 16.4.2017 г..
 */

public interface Action extends Serializable {

    ActionsManager.ActionName getName();

    int getDuration();

    int getDifficulty();

    double getPoints();

    String getEquipment();

    List<Exercise> getExercises();

    String getCategory();

    int getRepetitions();
}
