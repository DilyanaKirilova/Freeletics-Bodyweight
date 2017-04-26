package com.freeletics.dilyana.freeletics.data_base;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.freeletics.dilyana.freeletics.model.users.User;
import com.freeletics.dilyana.freeletics.model.users.UsersManager;

/**
 * Created by Ioana on 26.04.2017 г..
 */

public class DBManager extends SQLiteOpenHelper {

    private static DBManager ourInstance;
    private static Context context;

    public static final String DATABASE_NAME = "User.db";
    public static final String TABLE_USERS = "Users.db";
    public static final String COL_USER_ID = "USER_ID";
    public static final String COL_USER_FIRST_NAME = "USER_FIRST_NAME";
    public static final String COL_USER_LAST_NAME = "USER_LAST_NAME";
    public static final String COL_USER_EMAIL = "USER_EMAIL";
    public static final String COL_USER_PASSWORD = "USER_PASSWORD";
    public static final String COL_USER_GENDER = "USER_GENDER";
    public static final String COL_USER_WEIGHT = "USER_WEIGHT";
    public static final String COL_USER_HEIGHT = "USER_HEIGHT";
    public static final String COL_USER_AGE = "USER_AGE";
    public static final String COL_USER_PICTURE = "USER_PICTURE";
    public static final String COL_USER_LEVEL = "USER_LEVEL";

    private static final String SQL_CREATE_USERS = "CREATE TABLE" + TABLE_USERS + "(\n" +
            "\n" +
            " USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            " USER_FIRST_NAME text NOT NULL,\n" +
            " USER_PASSWORD text ,\n" +
            " USER_EMAIL text NOT NULL,\n" +
            " USER_GENDER text NOT NULL,\n" +
            " USER_AGE INTEGER NOT NULL\n" +
            " USER_WEIGHT INTEGER NOT NULL\n" +
            " USER_HEIGHT INTEGER NOT NULL\n" +
            " USER_PICTURE INTEGER NOT NULL\n" +
            " USER_LEVEL INTEGER NOT NULL\n" +
            ");";

    //TODO FINISHED ACTIONS AND SCHEDULE


    public static final String TABLE_USERS_MANAGER = "UsersManager.db";

    private DBManager(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    public static DBManager getInstance(Context context) {
        if(ourInstance == null){
            ourInstance = new DBManager(context);
            DBManager.context = context;
        }
        return ourInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(User u){
        if(UsersManager.getInstance().existsUser(u.getEmail())){
            Toast.makeText(context, "User already exists", Toast.LENGTH_SHORT).show();
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USER_FIRST_NAME, u.getFirstName());
        contentValues.put(COL_USER_LAST_NAME, u.getLastName());
        contentValues.put(COL_USER_AGE, u.getAge());
        contentValues.put(COL_USER_EMAIL, u.getEmail());
        contentValues.put(COL_USER_PASSWORD, u.getPassword());
        contentValues.put(COL_USER_PICTURE, u.getPicture());
        contentValues.put(COL_USER_WEIGHT, u.getWeight());
        contentValues.put(COL_USER_HEIGHT, u.getHeight());
        contentValues.put(COL_USER_GENDER, u.getStringGender());
        contentValues.put(COL_USER_LEVEL, u.getLevel());

        long id = getWritableDatabase().insert(TABLE_USERS, null, contentValues );
        u.setId((int)id);

        UsersManager.getInstance().registerUser(u);
        Toast.makeText(context, "User added successfully", Toast.LENGTH_SHORT).show();
    }


}
