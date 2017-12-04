package se.sugarest.jane.leave.ui.detail_activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import se.sugarest.jane.leave.R;
import se.sugarest.jane.leave.data.PlaceDatabase;
import se.sugarest.jane.leave.data.PlaceEntry;
import se.sugarest.jane.leave.databinding.FragmentPlaceDetailBinding;
import se.sugarest.jane.leave.utilities.AppExecutors;

import static se.sugarest.jane.leave.utilities.Constants.CATEGORY_ATTRACTION;
import static se.sugarest.jane.leave.utilities.Constants.CATEGORY_CAFE;
import static se.sugarest.jane.leave.utilities.Constants.CATEGORY_HOTEL;
import static se.sugarest.jane.leave.utilities.Constants.CATEGORY_OTHER;
import static se.sugarest.jane.leave.utilities.Constants.CATEGORY_RESTAURANT;
import static se.sugarest.jane.leave.utilities.Constants.CATEGORY_SHOP;

/**
 * Created by jane on 17-12-4.
 */

public class DetailFragment extends Fragment {

    private static final String LOG_TAG = DetailFragment.class.getSimpleName();

    private FragmentPlaceDetailBinding mBinding;

    /**
     * Category of the place. Default: other.
     */
    private int mCategory = CATEGORY_OTHER;

    // Mandatory empty constructor
    public DetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_place_detail, container, false);

        View rootView = mBinding.getRoot();

        setUpSpinner();

        setHasOptionsMenu(true);

        return rootView;
    }

    /**
     * Setup the dropdown spinner that allows the user to select the category of a place.
     */
    private void setUpSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter categorySpinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.array_category_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        categorySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mBinding.spinnerCategory.setAdapter(categorySpinnerAdapter);

        // Set the integer mSelected to the constant values
        mBinding.spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.category_spinner_attraction))) {
                        mCategory = CATEGORY_ATTRACTION;
                    } else if (selection.equals(getString(R.string.category_spinner_cafe))) {
                        mCategory = CATEGORY_CAFE;
                    } else if (selection.equals(getString(R.string.category_spinner_restaurant))) {
                        mCategory = CATEGORY_RESTAURANT;
                    } else if (selection.equals(getString(R.string.category_spinner_hotel))) {
                        mCategory = CATEGORY_HOTEL;
                    } else if (selection.equals(getString(R.string.category_spinner_shop))) {
                        mCategory = CATEGORY_SHOP;
                    } else {
                        mCategory = CATEGORY_OTHER;
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mCategory = CATEGORY_OTHER;
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_editor, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                savePlaceEntryToDatabase();
                return true;
            case R.id.action_delete:
                // TODO: delete the place from database
                return true;
            case android.R.id.home:
                // TODO: check if it is changed
                getActivity().finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void savePlaceEntryToDatabase() {

        String placeName = mBinding.etPlaceName.getText().toString();
        String placeCity = mBinding.etPlaceCity.getText().toString();
        String placeCountry = mBinding.etPlaceCountry.getText().toString();
        int category = mCategory;
        String specialties = mBinding.etSpecialties.getText().toString();
        String reservation = "";
        String comments = mBinding.etComments.getText().toString();
        String facebook = "";
        String website = "";
        String location = "";

        if (!placeName.isEmpty() && !placeCity.isEmpty() && !placeCountry.isEmpty()) {
            PlaceEntry newPlaceEntry = new PlaceEntry(placeName, placeCity, placeCountry, category, specialties, reservation, comments, facebook, website, location);

            AppExecutors executors = AppExecutors.getInstance();

            executors.diskIO().execute(() -> {
                PlaceDatabase database = PlaceDatabase.getInstance(getActivity().getApplicationContext());
                database.placeDao().insertPlace(newPlaceEntry);
                Log.i(LOG_TAG, "New PlaceEntry inserted with: " + newPlaceEntry);
            });
        } else {
            Toast.makeText(getActivity(), getString(R.string.toast_message_place_name_city_country_cannot_be_empty), Toast.LENGTH_SHORT).show();
        }
    }
}
