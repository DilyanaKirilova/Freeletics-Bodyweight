package com.freeletics.dilyana.freeletics.data_base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.freeletics.dilyana.freeletics.model.actions.Action;
import com.freeletics.dilyana.freeletics.model.users.User;
import com.freeletics.dilyana.freeletics.model.users.UsersManager;

import static com.freeletics.dilyana.freeletics.R.string.email;
import static com.freeletics.dilyana.freeletics.fragments.FragmentLogin.u;

/**
 * Created by Ioana on 26.04.2017 г..
 */

public class DBManager extends SQLiteOpenHelper {

    private static DBManager ourInstance;
    private static Context context;

    public static final String DATABASE_NAME = "Freeletics.db";

    public static final String TABLE_USERS = "Users";
    public static final String COL_USER_ID = "USER_ID";
    public static final String COL_USER_NOTIFICATION_ID = "USER_NOTIFICATION_ID";
    public static final String COL_USER_ACTION_ID = "USER_ACTION_ID";
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
    public static final String COL_USER_IS_LOGGED_USER = "USER_IS_LOGGED_USER";


    public static final String TABLE_NOTIFICATION_ACTION = "NotificationActions";
    public static final String COL_NOTIFICATION_ACTION_ID = "NOTIFICATION_ACTION_ID";
    public static final String COL_NOTIFICATION_ACTION_NAME = "NOTIFICATION_ACTION_NAME";
    public static final String COL_NOTIFICATION_ACTION_HOUR = "NOTIFICATION_ACTION_HOUR";
    public static final String COL_NOTIFICATION_ACTION_MINUTE = "NOTIFICATION_ACTION_MINUTE";
    public static final String COL_NOTIFICATION_ACTION_DAY = "NOTIFICATION_ACTION_DAY";
    public static final String COL_NOTIFICATION_ACTION_REPETITIONS = "NOTIFICATION_ACTION_REPETITIONS";
    public static final String COL_NOTIFICATION_ACTION_HAS_NOTIFICATION = "NOTIFICATION_ACTION_HAS_NOTIFICATION";

    public static final String TABLE_ACTION = "Actions";
    public static final String COL_ACTION_ID = "ACTION_ID";
    public static final String COL_ACTION_NAME = "ACTION_NAME";
    public static final String COL_ACTION_BEST_TIME = "ACTION_HOUR_BEST_TIME";
    public static final String COL_ACTION_REPETITIONS = "ACTION_REPETITIONS";

    private static final String SQL_CREATE_USERS = "CREATE TABLE Users (\n" +
            "\n" +
            " USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            " USER_FIRST_NAME text NOT NULL,\n" +
            " USER_LAST_NAME text NOT NULL, \n" +
            " USER_PASSWORD text ,\n" +
            " USER_EMAIL text NOT NULL,\n" +
            " USER_GENDER text NOT NULL,\n" +
            " USER_AGE INTEGER NOT NULL,\n" +
            " USER_WEIGHT INTEGER NOT NULL,\n" +
            " USER_HEIGHT INTEGER NOT NULL,\n" +
            " USER_PICTURE INTEGER NOT NULL,\n" +
            " USER_LEVEL INTEGER NOT NULL,\n" +
            " USER_IS_LOGGED_USER BOOLEAN NOT NULL,\n"+
            " USER_NOTIFICATION_ID INTEGER,\n"+
            " USER_ACTION_ID INTEGER,\n"+
            " FOREIGN KEY(USER_NOTIFICATION_ID) REFERENCES NotificationActions(NOTIFICATION_ACTION_ID),\n"+
            " FOREIGN KEY(USER_ACTION_ID) REFERENCES Actions(ACTION_ID),\n"+
            ");";

    private static final String SQL_CREATE_NOTIFICATION_ACTIONS = "CREATE TABLE NotificationActions (\n" +
            "\n" +
            " NOTIFICATION_ACTION_ID PRIMARY KEY AUTOINCREMENT,\n" +
            " NOTIFICATION_ACTION_NAME text NOT NULL,\n" +
            " NOTIFICATION_ACTION_HOUR INTEGER NOT NULL,\n" +
            " NOTIFICATION_ACTION_MINUTE INTEGER NOT NULL,\n" +
            " NOTIFICATION_ACTION_DAY INTEGER NOT NULL,\n" +
            " NOTIFICATION_ACTION_REPETITIONS INTEGER NOT NULL,\n" +
            " NOTIFICATION_ACTION_HAS_NOTIFICATIONS BOOLEAN NOT NULL,\n" +
            ");";

    private static final String SQL_CREATE_ACTIONS = "CREATE TABLE Actions (\n" +
            "\n" +
            " ACTION_ID PRIMARY KEY AUTOINCREMENT,\n" +
            " ACTION_NAME text NOT NULL,\n" +
            " ACTION_TIME  NOT NULL,\n" +
            " ACTION_REPETITIONS INTEGER NOT NULL,\n" +
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
        db.execSQL(TABLE_NOTIFICATION_ACTION);
        db.execSQL(TABLE_ACTION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE Users;");
        db.execSQL("DROP TABLE NotificationActions;");
        db.execSQL("DROP TABLE Actions;");
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
        contentValues.put(COL_USER_IS_LOGGED_USER, u.isLogged());

        long id = getWritableDatabase().insert(TABLE_USERS, null, contentValues );
        u.setId((int)id);

        Toast.makeText(context, "User added successfully", Toast.LENGTH_SHORT).show();
    }

    public void addNotification(Action a){

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NOTIFICATION_ACTION_NAME, String.valueOf(a.getName()));
        contentValues.put(COL_NOTIFICATION_ACTION_HOUR, a.getHour());
        contentValues.put(COL_NOTIFICATION_ACTION_MINUTE, a.getMinute());
        contentValues.put(COL_NOTIFICATION_ACTION_DAY, a.getDay());
        contentValues.put(COL_NOTIFICATION_ACTION_REPETITIONS, a.getRepetitions());
        contentValues.put(COL_NOTIFICATION_ACTION_HAS_NOTIFICATION, a.hasNotification());

        long id = getWritableDatabase().insert(TABLE_NOTIFICATION_ACTION, null, contentValues );
        a.setId((int)id);

        Toast.makeText(context, "Notification added successfully", Toast.LENGTH_SHORT).show();
    }

    public void addAction(Action a){

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ACTION_NAME, String.valueOf(a.getName()));
        contentValues.put(COL_ACTION_BEST_TIME, a.getBestTime());
        contentValues.put(COL_ACTION_REPETITIONS, a.getRepetitions());

        long id = getWritableDatabase().insert(TABLE_ACTION, null, contentValues );
        a.setId((int)id);

        Toast.makeText(context, "Action added successfully", Toast.LENGTH_SHORT).show();
    }


    public void deleteUser(String email){
            if(!UsersManager.getInstance().existsUser(email)){
               Toast.makeText(context, "User does not exist!", Toast.LENGTH_SHORT).show();
              return;
            }

            getWritableDatabase().delete("User", "username = ?", new String[]{email});
            Toast.makeText(context, email + " deleted successfully", Toast.LENGTH_SHORT).show();
            UsersManager.getInstance().deleteUserRegistration();
    }

    public void deleteAction(Action action){
        if(!UsersManager.getInstance().getLoggedUser().hasNotificationAction(action)){
            Toast.makeText(context, "User does not exist!", Toast.LENGTH_SHORT).show();
            return;
        }
        getWritableDatabase().delete("Actions", "ACTION_NAME = ?", new String[]{String.valueOf(action.getName())});
        UsersManager.getInstance().getLoggedUser().deleteExcercise(action);
        //todo
    }

    public void deleteNotification(){

    }

    private static void loadUsers(){
        //select from users and fill in collection
        Cursor cursor = ourInstance.getWritableDatabase().rawQuery("SELECT id, username, password, age FROM kozi;", null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(COL_USER_ID));
            String firstName = cursor.getString(cursor.getColumnIndex(COL_USER_FIRST_NAME));
            String lastName = cursor.getString(cursor.getColumnIndex(COL_USER_LAST_NAME));
            String email = cursor.getString(cursor.getColumnIndex(COL_USER_EMAIL));
            String password = cursor.getString(cursor.getColumnIndex(COL_USER_PASSWORD));
            String gender = cursor.getString(cursor.getColumnIndex(COL_USER_GENDER));
            int age = cursor.getInt(cursor.getColumnIndex(COL_USER_AGE));
            int weight = cursor.getInt(cursor.getColumnIndex(COL_USER_WEIGHT));
            int height = cursor.getInt(cursor.getColumnIndex(COL_USER_HEIGHT));
            int picture = cursor.getInt(cursor.getColumnIndex(COL_USER_PICTURE));
            int level = cursor.getInt(cursor.getColumnIndex(COL_USER_LEVEL));
            boolean isLoggedUser = cursor.getInt(cursor.getColumnIndex(COL_USER_IS_LOGGED_USER))>0;
            User.Gender gender1 = null;
            if(gender.equals("MALE")){
                gender1 = User.Gender.MALE;
            }
            if(gender.equals("FEMALE")){
                gender1 = User.Gender.FEMALE;
            }
            User u = new User(firstName, lastName, weight, height, age, gender1, email, password);
            u.setId(id);
            //registeredUsers.put(username, u);
        }

    }
}


