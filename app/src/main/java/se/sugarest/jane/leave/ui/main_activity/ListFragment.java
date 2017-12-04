package se.sugarest.jane.leave.ui.main_activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import se.sugarest.jane.leave.R;
import se.sugarest.jane.leave.databinding.FragmentPlacesListBinding;

/**
 * This fragment class displays all of the places saved by user.
 * <p>
 * Created by jane on 17-12-4.
 */
public class ListFragment extends Fragment {

    private FragmentPlacesListBinding mBinding;

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
    }
}
