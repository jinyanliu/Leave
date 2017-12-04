package se.sugarest.jane.leave.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import static se.sugarest.jane.leave.utilities.Constants.DATABASE_NAME;

/**
 * This abstract class is the database for the application including a table for {@link PlaceEntry}
 * with the DAO {@link PlaceDao}.
 * <p>
 * Created by jane on 17-12-4.
 */
@Database(entities = {PlaceEntry.class}, version = 1, exportSchema = false)
public abstract class PlaceDatabase extends RoomDatabase {

    private static final String LOG_TAG = PlaceDatabase.class.getSimpleName();

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static PlaceDatabase sInstance;

    public static PlaceDatabase getInstance(Context context) {
        Log.d(LOG_TAG, "Getting the database");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        PlaceDatabase.class, DATABASE_NAME).build();
                Log.d(LOG_TAG, "Made new database");
            }
        }
        return sInstance;
    }

    // The associated DAO for the database
    public abstract PlaceDao placeDao(); // Getters for Dao
}
