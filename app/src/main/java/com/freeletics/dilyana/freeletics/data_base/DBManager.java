package com.freeletics.dilyana.freeletics.data_base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
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

    public static final String DATABASE_NAME = "Freeleticsbg.db";

    public static final String TABLE_USERS = "Users";
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
    public static final String COL_USER_IS_LOGGED = "USER_IS_LOGGED";

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
            " USER_IS_LOGGED INTEGER NOT NULL,\n" +
            " USER_NOTIFICATION_ID INTEGER,\n" +
            " USER_ACTION_ID INTEGER,\n" +
            " FOREIGN KEY(USER_NOTIFICATION_ID) REFERENCES NotificationActions(NOTIFICATION_ACTION_ID),\n" +
            " FOREIGN KEY(USER_ACTION_ID) REFERENCES Actions(ACTION_ID)\n" +
            ");";

    private DBManager(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    public static DBManager getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new DBManager(context);
            DBManager.context = context;
            loadUsers();
        }
        return ourInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE Users;");
        onCreate(db);
    }

    public void addUser(User u) {

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
        contentValues.put(COL_USER_IS_LOGGED, 1);


        long id = getWritableDatabase().insert(TABLE_USERS, null, contentValues);
        u.setId((int) id);

        UsersManager.getInstance().registerUser(u);
        UsersManager.getInstance().setLoggedUser(u);

        Toast.makeText(context, "User added successfully in DataBase", Toast.LENGTH_SHORT).show();
    }

    public void deleteUser(String email) {

        new AsyncTask<String, Void, Void>(){

            @Override
            protected Void doInBackground(String... params) {

                String email = params[0];
                if (UsersManager.getInstance().existsUser(email)) {

                    getWritableDatabase().delete("Users", "USER_EMAIL = ?", new String[]{email});
                    UsersManager.getInstance().deleteUserRegistration();
                }

                return null;
            }
        }.execute(email);
    }

    private static void loadUsers() {
        //select from users and fill in collection

        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {

                Cursor cursor = ourInstance.getWritableDatabase().rawQuery("SELECT USER_ID,USER_FIRST_NAME, " +
                        "USER_LAST_NAME, USER_WEIGHT, USER_HEIGHT, USER_AGE, USER_GENDER, USER_EMAIL, USER_PASSWORD, USER_IS_LOGGED FROM Users;", null);
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(cursor.getColumnIndex(COL_USER_ID));
                    String firstName = cursor.getString(cursor.getColumnIndex(COL_USER_FIRST_NAME));
                    String lastName = cursor.getString(cursor.getColumnIndex(COL_USER_LAST_NAME));
                    String email = cursor.getString(cursor.getColumnIndex(COL_USER_EMAIL));
                    String password = cursor.getString(cursor.getColumnIndex(COL_USER_PASSWORD));
                    String gender = cursor.getString(cursor.getColumnIndex(COL_USER_GENDER));
                    int age = cursor.getInt(cursor.getColumnIndex(COL_USER_AGE));
                    int weight = cursor.getInt(cursor.getColumnIndex(COL_USER_WEIGHT));
                    int height = cursor.getInt(cursor.getColumnIndex(COL_USER_HEIGHT));
                    User.Gender gender1 = null;
                    if (gender.equals("MALE")) {
                        gender1 = User.Gender.MALE;
                    }
                    if (gender.equals("FEMALE")) {
                        gender1 = User.Gender.FEMALE;
                    }

                    User u = new User(firstName, lastName, weight, height, age, gender1, email, password);
                    u.userLogged(false);
                    u.setId(id);

                    if ((cursor.getInt(cursor.getColumnIndex(COL_USER_IS_LOGGED)) == 1)) {
                        u.userLogged(true);
                        UsersManager.getInstance().setLoggedUser(u);
                    }

                    UsersManager.getInstance().registerUser(u);
                }
                return null;
            }
        }.execute();
    }

    public void userLogged(Boolean flag) {

        new AsyncTask<Boolean, Void, Void>(){

            @Override
            protected Void doInBackground(Boolean... params) {
                Boolean flag = params[0];

                String email = UsersManager.getInstance().getLoggedUser().getEmail();
                ContentValues values = new ContentValues();
                values.put("USER_IS_LOGGED", flag);

                getWritableDatabase().update("Users", values ,"USER_EMAIL = ?", new String[]{email});
                UsersManager.getInstance().userLogged(flag);
                return null;
            }
        }.execute(flag);
    }
}

