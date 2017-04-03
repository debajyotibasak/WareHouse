package com.xtream.warehouse;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class EditorActivity extends AppCompatActivity {

    /** EditText field to enter the mobile's name */
    private EditText mMobileNameEditText;

    /** EditText field to enter the mobile's company name */
    private EditText mCompanyEditText;

    /** Spinner field to enter the mobile's type */
    private Spinner mTypeSpinner;

    /** EditText field to enter the mobile's software version */
    private EditText mSoftwareVersionText;

    /** Spinner field to enter the mobile's RAM */
    private Spinner mRamSpinner;

    /** Spinner field to enter the mobile's storage */
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
                if (!TextUtils.isEmpty(selection)){
                    if(selection.equals(getString(R.string.type_smartphone))){
                        mType = 1;
                    } else if (selection.equals(getString(R.string.type_featurephone))){
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
                if (!TextUtils.isEmpty(selection)){
                    if(selection.equals(getString(R.string.ram_1GBminus))){
                        mType = 1;
                    } else if (selection.equals(getString(R.string.ram_1GB))){
                        mType = 2;
                    } else if (selection.equals(getString(R.string.ram_2GB))){
                        mType = 3;
                    } else if (selection.equals(getString(R.string.ram_3GB))){
                        mType = 4;
                    } else if (selection.equals(getString(R.string.ram_4GB))){
                        mType = 5;
                    } else if (selection.equals(getString(R.string.ram_4GBplus))){
                        mType = 6;
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

        // Set the storageSpinner
        mStorageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)){
                    if(selection.equals(getString(R.string.storage_8GBminus))){
                        mType = 1;
                    } else if (selection.equals(getString(R.string.storage_8GB))){
                        mType = 2;
                    } else if (selection.equals(getString(R.string.storage_16GB))){
                        mType = 3;
                    } else if (selection.equals(getString(R.string.storage_32GB))){
                        mType = 4;
                    } else if (selection.equals(getString(R.string.storage_64GB))){
                        mType = 5;
                    } else if (selection.equals(getString(R.string.storage_128GB))){
                        mType = 6;
                    } else if (selection.equals(getString(R.string.storage_128GBplus))){
                        mType = 6;
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
                // Do nothing for now
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
