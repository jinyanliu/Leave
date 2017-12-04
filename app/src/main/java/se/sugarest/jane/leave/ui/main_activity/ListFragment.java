package se.sugarest.jane.leave.ui.main_activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import se.sugarest.jane.leave.R;
import se.sugarest.jane.leave.data.PlaceEntry;
import se.sugarest.jane.leave.databinding.FragmentPlacesListBinding;

/**
 * This fragment class displays all of the places saved by user.
 * <p>
 * Created by jane on 17-12-4.
 */
public class ListFragment extends Fragment implements PlaceAdapter.PlaceAdapterOnClickHandler {

    private FragmentPlacesListBinding mBinding;
    private PlaceAdapter mPlaceAdapter;

    // Mandatory empty constructor
    public ListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_places_list, container, false);

        View rootView = mBinding.getRoot();

        setUpRecyclerViewWithAdapter();

        return rootView;
    }

    private void setUpRecyclerViewWithAdapter() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mBinding.recyclerViewPlacesList.setLayoutManager(layoutManager);
        mBinding.recyclerViewPlacesList.setHasFixedSize(true);
        if (mPlaceAdapter == null) {
            mPlaceAdapter = new PlaceAdapter(this);
        }
        mBinding.recyclerViewPlacesList.setAdapter(mPlaceAdapter);
    }

    @Override
    public void onClick(PlaceEntry placeEntry) {

    }
}
