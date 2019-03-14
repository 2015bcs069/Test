package com.example.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Graphs extends AppCompatActivity {
   LineGraphSeries<DataPoint> series;
   DatabaseHelper dbn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);

        GraphView graph =  findViewById(R.id.graph);
        dbn = new DatabaseHelper(this);
        series = new LineGraphSeries<DataPoint>(dbn.getData()){

        };
        graph.addSeries(series);

    }



}
