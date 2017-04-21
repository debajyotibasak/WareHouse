package com.xtream.warehouse;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.xtream.warehouse.data.MobileContract.MobileEntry;
import com.xtream.warehouse.data.MobileDbHelper;

public class EditorActivity extends AppCompatActivity {

    /**
     * EditText field to enter the mobile's name
     */
    private EditText mMobileNameEditText;

    /**
     * EditText field to enter the mobile's company name
     */
    private EditText mCompanyEditText;

    /**
     * Spinner field to enter the mobile's type
     */
    private Spinner mTypeSpinner;

    /**
     * EditText field to enter the mobile's software version
     */
    private EditText mSoftwareVersionText;

    /**
     * Spinner field to enter the mobile's RAM
     */
    private Spinner mRamSpinner;

    /**
     * Spinner field to enter the mobile's storage
     */
    private Spinner mStorageSpinner;

    private int mType = 0;
    private int mRam = 0;
    private int mStorage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        //Find all the relevant views that we would read User Input from.
        mMobileNameEditText = (EditText) findViewById(R.id.edit_mobile_name);
        mCompanyEditText = (EditText) findViewById(R.id.edit_mobile_company_name);
        mTypeSpinner = (Spinner) findViewById(R.id.spinner_type);
        mSoftwareVersionText = (EditText) findViewById(R.id.edit_software_version_field);
        mRamSpinner = (Spinner) findViewById(R.id.spinner_ram);
        mStorageSpinner = (Spinner) findViewById(R.id.spinner_storage);

        setupSpinner();
    }

    /**
     * Setup the dropdown spinner that allows the user to select the specifications of mobile.
     */
    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter typeSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.array_type_options, android.R.layout.simple_spinner_item);
        ArrayAdapter ramSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.array_ram_options, android.R.layout.simple_spinner_item);
        ArrayAdapter storageSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.array_storage_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        typeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        ramSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        storageSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mTypeSpinner.setAdapter(typeSpinnerAdapter);
        mRamSpinner.setAdapter(ramSpinnerAdapter);
        mStorageSpinner.setAdapter(storageSpinnerAdapter);

        // Set the typeSpinner
        mTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.type_smartphone))) {
                        mType = 1;
                    } else if (selection.equals(getString(R.string.type_featurephone))) {
                        mType = 2;
                    } else {
                        mType = 0;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mType = 0;
            }
        });

        // Set the ramSpinner
        mRamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.ram_1GBminus))) {
                        mRam = 1;
                    } else if (selection.equals(getString(R.string.ram_1GB))) {
                        mRam = 2;
                    } else if (selection.equals(getString(R.string.ram_2GB))) {
                        mRam = 3;
                    } else if (selection.equals(getString(R.string.ram_3GB))) {
                        mRam = 4;
                    } else if (selection.equals(getString(R.string.ram_4GB))) {
                        mRam = 5;
                    } else if (selection.equals(getString(R.string.ram_4GBplus))) {
                        mRam = 6;
                    } else {
                        mRam = 0;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mRam = 0;
            }
        });

        // Set the storageSpinner
        mStorageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.storage_8GBminus))) {
                        mStorage = 1;
                    } else if (selection.equals(getString(R.string.storage_8GB))) {
                        mStorage = 2;
                    } else if (selection.equals(getString(R.string.storage_16GB))) {
                        mStorage = 3;
                    } else if (selection.equals(getString(R.string.storage_32GB))) {
                        mStorage = 4;
                    } else if (selection.equals(getString(R.string.storage_64GB))) {
                        mStorage = 5;
                    } else if (selection.equals(getString(R.string.storage_128GB))) {
                        mStorage = 6;
                    } else if (selection.equals(getString(R.string.storage_128GBplus))) {
                        mStorage = 6;
                    } else {
                        mStorage = 0;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mStorage = 0;
            }
        });
    }

    /**
     * Get user input from editor and save new pet into database.
     */
    private void insertMobile() {
        //Read from input fields
        //Using trim to eliminate spaces

        String mobileNameString = mMobileNameEditText.getText().toString().trim();
        String companyString = mCompanyEditText.getText().toString().trim();
        String softwareVersionString = mSoftwareVersionText.getText().toString().trim();

        //Create database helper
        MobileDbHelper mDbHelper = new MobileDbHelper(this);
        //Get database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        //Create content values
        ContentValues values = new ContentValues();
        values.put(MobileEntry.COLUMN_MOBILE_NAME, mobileNameString);
        values.put(MobileEntry.COLUMN_COMPANY, companyString);
        values.put(MobileEntry.COLUMN_TYPE, mType);
        values.put(MobileEntry.COLUMN_SOFTWARE_VERSION, softwareVersionString);
        values.put(MobileEntry.COLUMN_RAM, mRam);
        values.put(MobileEntry.COLUMN_STORAGE, mStorage);

        // Insert a new row for mobile in the database, returning the ID of that new row.
        long newRowId = db.insert(MobileEntry.TABLE_NAME, null, values);

        //show toast message whether the pet was inserted successfully
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving mobile", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Mobile saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save pet to database
                insertMobile();
                // Exit activity
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
