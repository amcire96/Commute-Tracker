package me.amcire.commutetracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class AddRouteActivity extends AppCompatActivity {

    TextView mSrcLocationTextEdit;
    TextView mDestLocationTextEdit;
    TextView mTitleTextEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_route);

        mSrcLocationTextEdit =
                (TextInputEditText) findViewById(R.id.source_location_text_input_edit_text);
        mDestLocationTextEdit =
                (TextInputEditText) findViewById(R.id.destination_location_text_input_edit_text);
        mTitleTextEdit =
                (TextInputEditText) findViewById(R.id.title_text_input_edit_text);

        Toolbar toolbar = (Toolbar) findViewById(R.id.add_route_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_route, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent returnIntent = new Intent();
        switch (item.getItemId()) {
            case android.R.id.home:
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
                return true;
            case R.id.done_add_route:
                returnIntent.putExtra("source_location",
                        mSrcLocationTextEdit.getText().toString());
                returnIntent.putExtra("destination_location",
                        mDestLocationTextEdit.getText().toString());
                returnIntent.putExtra("title", mTitleTextEdit.getText().toString());
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
                return true;
            default:
                return false;
        }
    }


}
