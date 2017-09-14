package se.sugarest.jane.leave.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by jane on 17-9-13.
 */

public class PlaceBookContract {

    public static final String CONTENT_AUTHORITY = "se.sugarest.jane.leave";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_PLACES = "places";

    public static final String PATH_DIARY = "dairy";

    private PlaceBookContract() {
    }

    public static abstract class PlaceEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PLACES);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PLACES;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PLACES;

        public final static String TABLE_NAME = "places";

        // Data Type : INTEGER
        public final static String _ID = BaseColumns._ID;

        // Data Type : TEXT
        public final static String COLUMN_PLACE_NAME = "name";

        // Data Type : INTEGER
        public final static String COLUMN_CATEGORY = "category";

        // Data Type : TEXT
        public final static String COLUMN_SPECIALTIES = "specialties";

        // Data Type : INTEGER
        public final static String COLUMN_RESERVATION = "reservation";

        // Data Type : TEXT
        public final static String COLUMN_COMMMENTS = "comments";

        // Data Type : TEXT
        public final static String COLUMN_FACEBOOK = "facebook";

        // Data Type : TEXT
        public final static String COLUMN_WEBSITE = "website";

        // Data Type : TEXT
        public final static String COLUMN_LOCATION = "location";

        public final static int CATEGORY_OTHER = 0;
        public final static int CATEGORY_ATTRACTION = 1;
        public final static int CATEGORY_CAFE = 2;
        public final static int CATEGORY_RESTAURANT = 3;
        public final static int CATEGORY_HOTEL = 4;
        public final static int CATEGORY_SHOP = 5;


        public static boolean isValidCategory(int category) {
            if (category == CATEGORY_ATTRACTION ||
                    category == CATEGORY_CAFE ||
                    category == CATEGORY_RESTAURANT ||
                    category == CATEGORY_HOTEL ||
                    category == CATEGORY_SHOP ||
                    category == CATEGORY_OTHER) {
                return true;
            }
            return false;
        }

    }

    public static abstract class DiaryEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_DIARY);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DIARY;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DIARY;

        public final static String TABLE_NAME = "diary";

        // Data Type : INTEGER
        public final static String _ID = BaseColumns._ID;

        // Data Type : TEXT
        public final static String COLUMN_CONTENT = "content";

        // Data Type : TEXT (image uri)
        public final static String COLUMN_CITY_IMAGE = "city_image";

        // Date Type : TEXT
        public final static String COLUMN_CITY_NAME = "city_name";

        // Data Type : TIMESTAMP
        public final static String COLUMN_TIMESTAMP = "time_stamp";

    }

}
