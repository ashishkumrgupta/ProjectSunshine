package com.example.android.sunshine.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static  final  String LOG_TAG=DetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String weatherString= intent.getStringExtra(MainActivity.INTENTEXTRAMSG);
        TextView textView =findViewById(R.id.selectedWeather);
        textView.setText(weatherString);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.settings,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectedItemId=item.getItemId();

        if(selectedItemId==R.id.action_setting){
            Log.d(LOG_TAG,"Settings is  selected from the details activity");
        }
        return super.onOptionsItemSelected(item);
    }
}
