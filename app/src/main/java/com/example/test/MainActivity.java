package com.example.test;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.UncheckedIOException;

// created by Pascal

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText edit_Humm,edit_Temp,edit_C02 ,editText_Moist,status_on;

    Button btnAddData;
    Button btnviewAll;


    Button viewGraph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        edit_Humm = (EditText)findViewById(R.id.editText_humm);
        edit_Temp = (EditText)findViewById(R.id.editText_temp);
        edit_C02 = (EditText)findViewById(R.id.editText_C02);
        editText_Moist = (EditText)findViewById(R.id.editText_Moist);
        status_on = (EditText)findViewById(R.id.editText_id_light);

        btnAddData=(Button)findViewById(R.id.button_add);
        btnviewAll=(Button)findViewById(R.id.button_viewAll);
        viewGraph=(Button)findViewById(R.id.button_viewGraph);

        // calling methods used
        AddData();
        viewAll ();
    }
   /* public void RadioButtonClicked(View view) {

        //This variable will store whether the user was male or female
        String lightStatus="";
        //  Check that the button is  now checked?
        boolean checked = ((RadioButton) view).isChecked();
        //Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_on:
                if (checked){
                    lightStatus = "ON";
                }
                else
                    lightStatus = "OFF";

                break;
            case R.id.radio_off:
                if (checked)
                    lightStatus = "OFF";
                break;
        }
        myDb.execSQL("INSER INTO TAB VALUES('"+lightStatus+"');");}*/
    public void AddData (){
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean inInserted= myDb.insertData(edit_Humm.getText().toString(),
                        edit_Temp.getText().toString(),
                        edit_C02.getText().toString(),
                        editText_Moist.getText().toString(),
                        status_on.getText().toString());
                if (inInserted=true){

                    Toast.makeText(MainActivity.this,"Data Uploaded", Toast.LENGTH_LONG).show();
                    ((EditText)findViewById(R.id.editText_humm)).setText("");
                    ((EditText)findViewById(R.id.editText_temp)).setText("");
                    ((EditText)findViewById(R.id.editText_C02)).setText("");
                    ((EditText)findViewById(R.id.editText_Moist)).setText("");
                    //((RadioButton)findViewById(R.id.radio_on)).setText("");
                    // ((RadioButton)findViewById(R.id.radi)).setText("");
                }
                else
                    Toast.makeText(MainActivity.this,"Data not Uploaded", Toast.LENGTH_LONG).show();

            }
        });
    }
    // Reatrieving data from SQLite
    public void viewAll (){
        btnviewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if (res.getCount()==0){
                    //  Toast.makeText(MainActivity.this,"No Data to be Loaded", Toast.LENGTH_LONG).show();
                    showMessage("Error", "No Data Found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Id: "+ res.getString(0)+"\n");
                    buffer.append("Hummidity: "+ res.getString(1)+"\n");
                    buffer.append("Temperature: "+ res.getString(2)+"\n");
                    buffer.append("C02: "+ res.getString(3)+"\n");
                    buffer.append("Moisture: "+ res.getString(4)+"\n");
                   // buffer.append("LightStatus: "+ res.getString(5)+"\n\n");
                }
                // show all the data
                showMessage("Data", buffer.toString());
            }
        });
    }
    public void showMessage (String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void LoadGraph(View view) {
        viewGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Graphs.class);
                startActivity(intent);
            }
        });

    }

    public void GoSettings(View view) {
        Intent intent = new Intent(MainActivity.this,ArduinoConect.class);
        startActivity(intent);
    }
}
