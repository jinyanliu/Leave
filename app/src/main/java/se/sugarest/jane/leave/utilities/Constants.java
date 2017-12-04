package se.sugarest.jane.leave.utilities;

import se.sugarest.jane.leave.data.PlaceDatabase;
import se.sugarest.jane.leave.data.PlaceEntry;

/**
 * Created by jane on 17-12-4.
 */

public class Constants {

    /**
     * The table name used in {@link PlaceEntry}
     */
    public static final String TABLE_NAME = "place";

    /**
     * The database name used in {@link PlaceDatabase}
     */
    public static final String DATABASE_NAME = "place";

    /**
     * Possible values for the category of the place.
     * Use with spinner.
     */
    public static final int CATEGORY_OTHER = 0;
    public static final int CATEGORY_ATTRACTION = 1;
    public static final int CATEGORY_CAFE = 2;
    public static final int CATEGORY_RESTAURANT = 3;
    public static final int CATEGORY_HOTEL = 4;
    public static final int CATEGORY_SHOP = 5;
}
