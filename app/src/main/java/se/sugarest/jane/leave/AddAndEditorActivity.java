package se.sugarest.jane.leave;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by jane on 17-9-12.
 */

public class AddAndEditorActivity extends AppCompatActivity {

    /**
     * EditText field to enter the place's category
     */
    private Spinner mCategorySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_editor);

        mCategorySpinner = (Spinner) findViewById(R.id.spinner_category);
        setUpSpinner();
    }

    /**
     * Setup the dropdown spinner that allows the user to select the category of a place.
     */
    private void setUpSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter categorySpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_category_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        categorySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mCategorySpinner.setAdapter(categorySpinnerAdapter);

        // Set the integer mSelected to the constant values
        mCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.category_spinner_attraction))) {
                        // TODO
                    } else if (selection.equals(getString(R.string.category_spinner_cafe))) {
                        // TODO
                    } else if (selection.equals(getString(R.string.category_spinner_restaurant))) {
                        // TODO
                    } else if (selection.equals(getString(R.string.category_spinner_hotel))) {
                        // TODO
                    } else if (selection.equals(getString(R.string.category_spinner_shop))) {
                        // TODO
                    } else {
                        // TODO
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // TODO
            }
        });
    }
}
