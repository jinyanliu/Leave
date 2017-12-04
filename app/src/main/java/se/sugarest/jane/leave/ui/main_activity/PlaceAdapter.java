package se.sugarest.jane.leave.ui.main_activity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import se.sugarest.jane.leave.data.PlaceEntry;
import se.sugarest.jane.leave.databinding.PlaceListItemBinding;

/**
 * This class gets a list of PlaceEntry from inputs, then populates to the recycler view.
 * <p>
 * Created by jane on 17-12-4.
 */
public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceAdapterViewHolder> {

    // An On-click handler to make it easy for ListFragment to interact with the RecyclerView
    private final PlaceAdapterOnClickHandler mClickHandler;

    private ArrayList<PlaceEntry> mPlaceEntryArrayList = new ArrayList<>();

    /**
     * Creates a PlaceAdapter.
     *
     * @param clickHandler The on-click handler for this adapter. This single handler is called
     *                     when an item is clicked.
     */
    public PlaceAdapter(PlaceAdapterOnClickHandler clickHandler) {
        this.mClickHandler = clickHandler;
    }

    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param parent   The ViewGroup that these ViewHolders are contained within.
     * @param viewType If RecyclerView has more than one type of item (which this one don't)
     *                 this viewType can be used to provide a different layout.
     * @return A new PlaceAdapterViewHolder that holds the View for each list item
     */
    @Override
    public PlaceAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        PlaceListItemBinding placeListItemBinding
                = PlaceListItemBinding.inflate(inflater, parent, false);

        return new PlaceAdapterViewHolder(placeListItemBinding);
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, update the contents of the ViewHolder to display the movie
     * posters for each particular position, using the "position" argument that is conveniently
     * passed in.
     *
     * @param holder   The ViewHolder which should be updated to represent the
     *                 contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(PlaceAdapterViewHolder holder, int position) {
        String placeName = mPlaceEntryArrayList.get(position).getPlaceName();
        String placeCity = mPlaceEntryArrayList.get(position).getPlaceCity();
        String placeCountry = mPlaceEntryArrayList.get(position).getPlaceCountry();
        holder.bind(placeName, placeCity, placeCountry);
    }

    /**
     * @return The number of items to display.
     */
    @Override
    public int getItemCount() {
        if (mPlaceEntryArrayList != null) {
            return mPlaceEntryArrayList.size();
        }
        return 0;
    }

    /**
     * This method is used to set the places' name, city and country data on a PlaceAdapter
     */
    public void setUpPlaceEntryList(ArrayList<PlaceEntry> newArray) {
        if (newArray != null) {
            mPlaceEntryArrayList.clear();
            mPlaceEntryArrayList.addAll(newArray);
            notifyDataSetChanged();
        }
    }

    /**
     * The interface that receives onClick messages.
     */
    public interface PlaceAdapterOnClickHandler {
        void onClick(PlaceEntry placeEntry);
    }

    /**
     * Cache of the children views for a place's name, city and country
     */
    public class PlaceAdapterViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener {

        private final PlaceListItemBinding mBinding;

        public PlaceAdapterViewHolder(PlaceListItemBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
            mBinding.placeListItem.setOnClickListener(this);
        }

        public void bind(String placeName, String placeCity, String placeCountry) {
            mBinding.tvPlaceName.setText(placeName);
            mBinding.tvPlaceCity.setText(placeCity);
            mBinding.tvPlaceCountry.setText(placeCountry);
            mBinding.executePendingBindings();
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            PlaceEntry clickedPlaceEntry = mPlaceEntryArrayList.get(adapterPosition);
            mClickHandler.onClick(clickedPlaceEntry);
        }
    }
}
