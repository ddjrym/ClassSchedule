package com.rymarczykdawidgmail.classschedule;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;
import java.util.Vector;

public class monday extends AppCompatActivity {
    private static final String TAG = "Monday";
    List<String> classNames = new Vector<>();
    List<Integer> dayClasses = new Vector<>();
    List<Integer> startTimesH = new Vector<>();
    List<Integer> endTimesH = new Vector<>();
    List<Integer> startTimesM = new Vector<>();
    List<Integer> endTimesM = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        setContentView(R.layout.monday_act);
        ((LinearLayout) findViewById(R.id.rel)).addView(new DayView(this));

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addClass = new Intent(monday.this, AddClass.class);
                startActivityForResult(addClass, 1);
            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                classNames.add(data.getStringExtra("ClassName"));
                startTimesH.add(data.getIntExtra("StartTimeH", 0));
                endTimesH.add(data.getIntExtra("EndTimeH", 0));
                startTimesM.add(data.getIntExtra("StartTimeM", 0));
                endTimesM.add(data.getIntExtra("EndTimeM", 0));
                dayClasses.add(data.getIntExtra("Day", 0));
            }
        }
    }

//    @Override
//    protected void onDraw(Canvas canvas) {
//        for (int i=0; i<classNames.size(); i++){
//            String className = classNames.get(i);
//            Integer day = dayClasses.get(i);
//            Integer startTimeH = startTimesH.get(i);
//            Integer endTimeH = endTimesH.get(i);
//            Integer startTimeM = startTimesM.get(i);
//            Integer endTimeM = endTimesM.get(i);
//
//            Button ClassOnSchedule = new Button(this);
//            ClassOnSchedule.setText(className);
//            Paint newClass = new Paint();
//            newClass.setColor(getResources().getColor(R.color.malachit));
//            canvas.drawRect(100, 100, 200, 200, newClass);
//        }
//}



        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_monday, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

