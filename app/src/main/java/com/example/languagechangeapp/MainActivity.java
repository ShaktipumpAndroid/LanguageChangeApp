package com.example.languagechangeapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    String[] language = {"Select Language", "Hindi", "English"};

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.changeLanguage);


        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, language);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(aa);
        spinner.setSelection(0, false);
        spinner.setOnItemSelectedListener(this);

        /* */
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getSelectedItem().toString().equals("Hindi")) {
            updateView("hi");
        } else {
            updateView("en");
        }
    }

    private void updateView(String languageCode) {
        Context context = LocaleHelper.setLocale(MainActivity.this, languageCode);
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration conf = resources.getConfiguration();
        conf.setLocale(new Locale(languageCode)); // API 17+ only.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            context.createConfigurationContext(conf);
        } else {
            context.getResources().updateConfiguration(conf, dm);
        }
        Intent refresh = new Intent(this, MainActivity.class);
        finish();
        startActivity(refresh);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}