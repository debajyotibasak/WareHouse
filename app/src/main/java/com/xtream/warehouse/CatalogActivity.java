package com.xtream.warehouse;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.xtream.warehouse.data.MobileContract.MobileEntry;
import com.xtream.warehouse.data.MobileDbHelper;

public class CatalogActivity extends AppCompatActivity {

    private MobileDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new MobileDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pets database.
     */
    private void displayDatabaseInfo() {

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Perform this raw SQL query "SELECT * FROM pets"
        // to get a Cursor that contains all rows from the pets table.
        // Cursor cursor = db.rawQuery("SELECT * FROM " + MobileEntry.TABLE_NAME, null);

        // QUERY METHOD : Projection, Selection, SelectionARGS and cursor
        String[] projection = {
                MobileEntry._ID,
                MobileEntry.COLUMN_MOBILE_NAME,
                MobileEntry.COLUMN_COMPANY,
                MobileEntry.COLUMN_TYPE,
                MobileEntry.COLUMN_SOFTWARE_VERSION,
                MobileEntry.COLUMN_RAM,
                MobileEntry.COLUMN_STORAGE
        };

        Cursor cursor = db.query(
                MobileEntry.TABLE_NAME,     //Table to query
                projection,                 //The columns to return
                null,                       //The columns for where clause
                null,                       //The values for where clause
                null,                       //Don't group by rows
                null,                       //Don't filter by row groups
                null);                      //The sort order

        TextView displayView = (TextView) findViewById(R.id.text_view_item);

        try {
            // Create a header in the Text View that looks like this:
            //
            // The mobile table contains <number of rows in Cursor> mobile.
            // _id - mobile_name - company - type - software - RAM - Storage
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The Mobile table contains " + cursor.getCount() + " mobile.\n\n");
            displayView.append(MobileEntry._ID + " - " +
                    MobileEntry.COLUMN_MOBILE_NAME + " - " +
                    MobileEntry.COLUMN_COMPANY + " - " +
                    MobileEntry.COLUMN_TYPE + " - " +
                    MobileEntry.COLUMN_SOFTWARE_VERSION + " - " +
                    MobileEntry.COLUMN_RAM + " - " +
                    MobileEntry.COLUMN_STORAGE + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(MobileEntry._ID);
            int mobileNameColumnIndex = cursor.getColumnIndex(MobileEntry.COLUMN_MOBILE_NAME);
            int companyColumnIndex = cursor.getColumnIndex(MobileEntry.COLUMN_COMPANY);
            int typeColumnIndex = cursor.getColumnIndex(MobileEntry.COLUMN_TYPE);
            int softwareColumnIndex = cursor.getColumnIndex(MobileEntry.COLUMN_SOFTWARE_VERSION);
            int ramColumnIndex = cursor.getColumnIndex(MobileEntry.COLUMN_RAM);
            int storageColumnIndex = cursor.getColumnIndex(MobileEntry.COLUMN_STORAGE);

            // Iterate through all the return rows in cursor
            while(cursor.moveToNext()){
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentMobileName = cursor.getString(mobileNameColumnIndex);
                String currentCompany = cursor.getString(companyColumnIndex);
                int currentType = cursor.getInt(typeColumnIndex);
                String currentSoftware = cursor.getString(softwareColumnIndex);
                int currentRam = cursor.getInt(ramColumnIndex);
                int currentStorage = cursor.getInt(storageColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentMobileName + " - " +
                        currentCompany + " - " +
                        currentType + " - " +
                        currentSoftware + " - " +
                        currentRam + " - " +
                        currentStorage));
            }

        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    private void insertMobile(){
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and SAMSUNG GALAXY S8 are the values
        ContentValues values = new ContentValues();
        values.put(MobileEntry.COLUMN_MOBILE_NAME, "S8");
        values.put(MobileEntry.COLUMN_COMPANY, "Samsung");
        values.put(MobileEntry.COLUMN_TYPE, MobileEntry.TYPE_SMARTPHONE);
        values.put(MobileEntry.COLUMN_SOFTWARE_VERSION, "Android 7.0 Nougat");
        values.put(MobileEntry.COLUMN_RAM, MobileEntry.RAM_4GB);
        values.put(MobileEntry.COLUMN_STORAGE, MobileEntry.STORAGE_64GB);

        long newRowID = db.insert(MobileEntry.TABLE_NAME, null, values);

        Log.v("CatalogActivity ", "New RowID " + newRowID);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // User clicked on a menu option in the app bar overflow menu
        switch(item.getItemId()){
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertMobile();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
