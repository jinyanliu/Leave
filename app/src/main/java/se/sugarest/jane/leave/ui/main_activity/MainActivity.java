package se.sugarest.jane.leave.ui.main_activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import se.sugarest.jane.leave.R;
import se.sugarest.jane.leave.databinding.ActivityMainBinding;
import se.sugarest.jane.leave.ui.AddAndEditorActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Setup FAB to open AddAndEditorActivity
        mBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddAndEditorActivity.class);
                startActivity(intent);
            }
        });
    }
}
