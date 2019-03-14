package com.example.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsGreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_green);
    }

    public void onSwitchClicked(View view) {



        //Is the switch on?
        boolean on = ((Switch) view).isChecked();

        if(on)
        {
            //Do something when switch is on
            Toast.makeText(SettingsGreen.this,"Switch is on....", Toast.LENGTH_LONG).show();

        }
        else
        {
            //Do something when switch is off
            Toast.makeText(SettingsGreen.this,"Switch is off....", Toast.LENGTH_LONG).show();

    }
}}
