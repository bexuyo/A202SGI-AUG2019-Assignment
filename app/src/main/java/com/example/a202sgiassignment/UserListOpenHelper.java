package com.example.a202sgiassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserListOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "UserList";
    private static final int DB_VER = 1;
    private static final String TABLE = "User";

    private static final String COLUMN_USER_ID = "id";
    private static final String COLUMN_USER_NAME = "name";
    private static final String COLUMN_USER_EMAIL = "email";
    private static final String COLUMN_USER_PASSWORD = "password";
    private static final String COLUMN_USER_GENDER = "gender";
    private static final String COLUMN_USER_CONTACT = "contact";
    private static final String COLUMN_USER_DOB = "DOB";

    public UserListOpenHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE + " (" + COLUMN_USER_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_NAME + " TEXT, "
                + COLUMN_USER_EMAIL + " TEXT, " + COLUMN_USER_PASSWORD + " TEXT, " + COLUMN_USER_GENDER
                + " TEXT, " + COLUMN_USER_CONTACT + " TEXT, " + COLUMN_USER_DOB + " TEXT)";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void addUser(User user)
    {
        SQLiteDatabase writeDB = null;

        ContentValues values = new ContentValues();

        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_GENDER, user.getGender());
        values.put(COLUMN_USER_CONTACT, user.getContact());
        values.put(COLUMN_USER_DOB, user.getDob());

        try
        {
            if(writeDB == null)
                writeDB = getWritableDatabase();

            writeDB.insert(TABLE, null, values);
        }
        catch (Exception e)
        {
            Log.e("AddUser", e.getMessage());
        }
        finally
        {
            if(writeDB != null && writeDB.isOpen())
                writeDB.close();
        }
    }

    public User findUser(String getEmail)
    {
        String find = "SELECT * FROM " + TABLE + " WHERE " + COLUMN_USER_EMAIL + " = '" + getEmail + "'";

        SQLiteDatabase readDB = null;
        Cursor cursor = null;
        User found = new User();

        try
        {
            if(readDB == null)
                readDB = getReadableDatabase();

            cursor = readDB.rawQuery(find, null);
            cursor.moveToFirst();

            int id = cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID));
            found.set_id(id);

            String  name = cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME));
            found.setName(name);

            String email = cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL));
            found.setEmail(email);

            String password = cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD));
            found.setPassword(password);

            String gender = cursor.getString(cursor.getColumnIndex(COLUMN_USER_GENDER));
            found.setGender(gender);

            String contact = cursor.getString(cursor.getColumnIndex(COLUMN_USER_CONTACT));
            found.setContact(contact);

            String dob = cursor.getString(cursor.getColumnIndex(COLUMN_USER_DOB));
            found.setDob(dob);
        }
        catch (Exception e)
        {
            Log.e("findUser", e.getMessage());
        }
        finally
        {
            if(cursor != null && !cursor.isClosed())
                cursor.close();

            if(readDB != null && readDB.isOpen())
                readDB.close();

            return found;
        }
    }

    public boolean checkUser(String email)
    {
        SQLiteDatabase readDB = null;

        String[] columns = {COLUMN_USER_ID};
        String selection = COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = {email};

        Cursor cursor = null;
        int cursorCount = 0;

        try
        {
            if(readDB == null)
                readDB = getReadableDatabase();

            cursor = readDB.query(TABLE, columns, selection, selectionArgs, null, null, null);
            cursorCount = cursor.getCount();
        }
        catch (Exception e)
        {
            Log.e("Register check: ", e.getMessage());
        }
        finally
        {
            if(cursor != null && !cursor.isClosed())
                cursor.close();

            if(readDB != null && readDB.isOpen())
                readDB.close();

            if(cursorCount > 0)
                return true;
            else
                return false;
        }
    }

    public boolean checkUser(String email, String password)
    {
        SQLiteDatabase readDB = null;
        String[] columns = {COLUMN_USER_ID};
        String selection = COLUMN_USER_EMAIL + " = ? " + "AND " + COLUMN_USER_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};

        Cursor cursor = null;
        int cursorCount = 0;

        try
        {
            if(readDB == null)
                readDB = getReadableDatabase();

            cursor = readDB.query(TABLE, columns, selection, selectionArgs,
                    null, null, null);
            cursorCount = cursor.getCount();
        }
        catch (Exception e)
        {
            Log.e("Login Check: ", e.getMessage());
        }
        finally
        {
            if(cursor != null && !cursor.isClosed())
                cursor.close();

            if(readDB != null && readDB.isOpen())
                readDB.close();

            if(cursorCount > 0)
                return true;
            else
                return false;
        }
    }
}
