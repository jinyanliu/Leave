package se.sugarest.jane.leave.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import se.sugarest.jane.leave.data.PlaceBookContract.DiaryEntry;

/**
 * Created by jane on 17-9-14.
 */

public class PlaceBookProvider extends ContentProvider {

    public static final String TAG = PlaceBookProvider.class.getSimpleName();

    private static final int PLACES = 100;
    private static final int PLACES_ID = 101;
    private static final int DIARYS = 200;
    private static final int DIARYS_ID = 201;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(PlaceBookContract.CONTENT_AUTHORITY, PlaceBookContract.PATH_PLACES, PLACES);
        sUriMatcher.addURI(PlaceBookContract.CONTENT_AUTHORITY, PlaceBookContract.PATH_PLACES + "/#", PLACES_ID);
        sUriMatcher.addURI(PlaceBookContract.CONTENT_AUTHORITY, PlaceBookContract.PATH_DIARY, DIARYS);
        sUriMatcher.addURI(PlaceBookContract.CONTENT_AUTHORITY, PlaceBookContract.PATH_DIARY + "/#", DIARYS_ID);
    }

    private PlaceBookDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new PlaceBookDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        Cursor cursor;
        int match = sUriMatcher.match(uri);
        switch (match) {
            case DIARYS:
                cursor = database.query(
                        DiaryEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case DIARYS_ID:
                selection = DiaryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(
                        DiaryEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case DIARYS:
                return insertDiary(uri, values);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    private Uri insertDiary(Uri uri, ContentValues values) {

        String content = values.getAsString(DiaryEntry.COLUMN_CONTENT);
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Diary needs content text.");
        }

        String cityName = values.getAsString(DiaryEntry.COLUMN_CITY_NAME);
        if (cityName == null || cityName.isEmpty()) {
            throw new IllegalArgumentException("Diary needs a city text to show on the screen's bottom left corner.");
        }

        String cityImage = values.getAsString(DiaryEntry.COLUMN_CITY_IMAGE);
        if (cityImage == null || cityImage.isEmpty()) {
            throw new IllegalArgumentException("Diary needs a city image as a background.");
        }

        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        long id = database.insert(DiaryEntry.TABLE_NAME, null, values);

        if (id == -1) {
            Log.e(TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
