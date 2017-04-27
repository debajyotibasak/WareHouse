package com.xtream.warehouse.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.xtream.warehouse.data.MobileContract.MobileEntry;

// Manages database creation and version management
public class MobileDbHelper extends SQLiteOpenHelper {

    // name of Database
    private static final String DATABASE_NAME = "mobileWarehouse.db";

    // version of Database
    private static final int DATABASE_VERSION = 1;

    // constructor which constructs a new instance of MobileDbHelper
    public MobileDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // called when the database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the mobile table
        String SQL_CREATE_MOBILES_TABLE =  "CREATE TABLE " + MobileEntry.TABLE_NAME + "("
                + MobileEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MobileEntry.COLUMN_MOBILE_NAME + " TEXT NOT NULL, "
                + MobileEntry.COLUMN_COMPANY + " TEXT, "
                + MobileEntry.COLUMN_TYPE + " INTEGER NOT NULL, "
                + MobileEntry.COLUMN_SOFTWARE_VERSION + " TEXT, "
                + MobileEntry.COLUMN_RAM + " INTEGER NOT NULL, "
                + MobileEntry.COLUMN_STORAGE + " INTEGER NOT NULL);";

        // Execute the SQL Statement
        db.execSQL(SQL_CREATE_MOBILES_TABLE);
    }

    // called when the database is upgraded.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}
