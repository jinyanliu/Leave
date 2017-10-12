package se.sugarest.jane.leave.layout;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import se.sugarest.jane.leave.R;
import se.sugarest.jane.leave.data.PlaceBookContract.DiaryEntry;

public class MainActivity extends AppCompatActivity {

    private EditText editTextDiaryContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup FAB to open AddAndEditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddAndEditorActivity.class);
                startActivity(intent);
            }
        });

        Button button = (Button) findViewById(R.id.button_find_your_city);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                changeTodayScreen();
            }
        });

        editTextDiaryContent = (EditText) findViewById(R.id.et_say_sth_today);

    }

    private void changeTodayScreen() {
        String diaryContent = editTextDiaryContent.getText().toString();
        String cityName = findYourCity();
        String cityImage = findYourCityImage();

        ContentValues values = new ContentValues();
        values.put(DiaryEntry.COLUMN_CONTENT, diaryContent);
        values.put(DiaryEntry.COLUMN_CITY_NAME, cityName);
        values.put(DiaryEntry.COLUMN_CITY_IMAGE, cityImage);
        Uri newUri = getContentResolver().insert(DiaryEntry.CONTENT_URI, values);

        Cursor cursor = getContentResolver().query(newUri, null, null, null, null);
    }

    // TODO FIND A URI AND USE PICASSO TO PARSE
    private String findYourCityImage() {
        Uri path = Uri.parse("android.resource://se.sugarest.jane.leave/" + R.drawable.stockholm_city_image);
        return path.toString();
    }

    // TODO FIND
    private String findYourCity() {
        return "Stockholm, Sweden";
    }
}
