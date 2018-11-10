package com.example.android.sunshine.app;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG= MainActivity.class.getSimpleName();
    public static final String INTENTEXTRAMSG="FORECASTDATA";

    private ArrayList<String> weekForecast= new ArrayList<>();
    ArrayAdapter<String> mForecastAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mForecastAdapter = new ArrayAdapter<String>(this,R.layout.list_item_forecast,R.id.list_item_forecast_textView, weekForecast);

        Log.d("my Tag","String Adapter is working fine.");
        ListView listView=(ListView)this.findViewById(R.id.listView_forecast);
        listView.setAdapter(mForecastAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String forecast=mForecastAdapter.getItem(position);
                Toast toast=Toast.makeText(MainActivity.this ,forecast,Toast.LENGTH_SHORT);
                toast.show();
               startDetailActivity(forecast);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.forecastfragment,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        if(id==R.id.action_refresh) {
            AsyncTask task= new fetchWeatherTask().execute("1103,NL");
            try {
                String [] output=(String [])task.get();
                mForecastAdapter.clear();
                mForecastAdapter.addAll(output);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }
        if(id==R.id.action_setting) {
            Log.d(LOG_TAG,"Clicked on settings Tag.");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startDetailActivity(String msg){
        Intent intent = new Intent(this,DetailActivity.class);
        intent.putExtra("FORECASTDATA",msg);
        startActivity(intent);
    }
}
