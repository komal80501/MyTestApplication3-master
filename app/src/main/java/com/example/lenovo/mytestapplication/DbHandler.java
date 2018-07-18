package com.example.lenovo.mytestapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHandler extends SQLiteOpenHelper {
    Context context;
    // Database Name
    private static final String DATABASENAME="UserDb";
    private  static final int DATABASEVERSION=1;

    public DbHandler(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
    }

    public static final String TABLE_USERS="users";
    public static final String KEY_ID="user_id";
    public static final String KEY_USER_NAME="user_name";
    public static final String KEY_EMAIL="user_email";
    public static final String KEY_MOBILENO="user_mobileno";
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USERS +"("
                + KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +KEY_USER_NAME+" TEXT,"
                +KEY_EMAIL+" TEXT,"
                +KEY_MOBILENO+" TEXT" +")";
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Boolean insert_USER(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USER_NAME, user.getName());
        contentValues.put(KEY_EMAIL,user.getEmail());
        contentValues.put(KEY_MOBILENO,user.getMobileno());
        long result=db.insert(TABLE_USERS,null,contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }

    public Cursor getAlldata(int id,DbHandler dbHandler){
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        return db.rawQuery("SELECT * FROM users WHERE user_id = "+ id, null);
    }
}

