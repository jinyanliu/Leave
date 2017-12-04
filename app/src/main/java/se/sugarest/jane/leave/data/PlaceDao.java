package se.sugarest.jane.leave.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * This interface provides an api for all database operations
 * <p>
 * Created by jane on 17-12-4.
 */
@Dao // Required annotation for Dao to be recognized by Room
public interface PlaceDao {

    /**
     * Insert a {@link PlaceEntry} into the place table.
     *
     * @param placeEntry A PlaceEntry to insert.
     */
    @Insert
    void insertPlace(PlaceEntry placeEntry);

    /**
     * Gets all the places from the place table.
     *
     * @return a list of all the places.
     */
    @Query("SELECT * FROM place")
    List<PlaceEntry> getPlacesList();
}
