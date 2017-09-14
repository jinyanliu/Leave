package se.sugarest.jane.leave.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import se.sugarest.jane.leave.data.PlaceBookContract.DiaryEntry;
import se.sugarest.jane.leave.data.PlaceBookContract.PlaceEntry;


/**
 * Created by jane on 17-9-14.
 */

public class PlaceBookDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "placebook.db";

    private static final int DATABASE_VERSION = 1;

    public PlaceBookDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_PLACE_TABLE = "CREATE TABLE " + PlaceEntry.TABLE_NAME + " (" +
                PlaceEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PlaceEntry.COLUMN_PLACE_NAME + " TEXT NOT NULL, " +
                PlaceEntry.COLUMN_CATEGORY + " INTEGER NOT NULL DEFAULT 0, " +
                PlaceEntry.COLUMN_SPECIALTIES + " TEXT, " +
                PlaceEntry.COLUMN_RESERVATION + " INTEGER, " +
                PlaceEntry.COLUMN_COMMMENTS + " TEXT, " +
                PlaceEntry.COLUMN_FACEBOOK + " TEXT, " +
                PlaceEntry.COLUMN_WEBSITE + " TEXT, " +
                PlaceEntry.COLUMN_LOCATION + " TEXT);";

        db.execSQL(SQL_CREATE_PLACE_TABLE);

        final String SQL_CREATE_DIARY_TABLE = "CREATE TABLE " + DiaryEntry.TABLE_NAME + " (" +
                DiaryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DiaryEntry.COLUMN_CONTENT + " TEXT NOT NULL, " +
                DiaryEntry.COLUMN_CITY_NAME + " TEXT NOT NULL, " +
                DiaryEntry.COLUMN_CITY_IMAGE + " TEXT NOT NULL, " +
                DiaryEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";

        db.execSQL(SQL_CREATE_DIARY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
