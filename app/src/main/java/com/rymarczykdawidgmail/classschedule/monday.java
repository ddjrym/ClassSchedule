package com.rymarczykdawidgmail.classschedule;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Display;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.util.List;
import java.util.Vector;

public class monday extends AppCompatActivity {
    private static final String TAG = "Monday";
    List<String> classNames = new Vector<>();
    List<String> dayClasses = new Vector<>();
    List<Integer> startTimesH = new Vector<>();
    List<Integer> endTimesH = new Vector<>();
    List<Integer> startTimesM = new Vector<>();
    List<Integer> endTimesM = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        setContentView(R.layout.monday_act);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ((RelativeLayout) findViewById(R.id.rel)).addView(new DayView(this));

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1)if (resultCode == RESULT_OK) {
            String className = data.getStringExtra("ClassName");
            Integer startTimeH = data.getIntExtra("StartTimeH", 0);
            Integer endTimeH = data.getIntExtra("EndTimeH", 0);
            Integer startTimeM = data.getIntExtra("StartTimeM", 0);
            Integer endTimeM = data.getIntExtra("EndTimeM", 0);
            String dayClass = data.getStringExtra("Day");

            final File destination = new File(Environment
                    .getExternalStorageDirectory(), className + ".jpg");
            classNames.add(className);
            startTimesH.add(startTimeH);
            endTimesH.add(endTimeH);
            startTimesM.add(startTimeM);
            endTimesM.add(endTimeM);
            dayClasses.add(dayClass);

            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);

            float time = size.y * ((startTimeH - 6f) + (startTimeM / 60f)) / 16f;
            Integer textSize = (int)(size.y * (((-startTimeH + endTimeH) +
                    (Math.abs(startTimeM - endTimeM) / 60f)) / 16f));
            String colon1, colon2;
            final Button lesson = new Button(this);
            if (startTimeM < 10){
                colon1 = ":0";
            }else{
                colon1 = ":";
            }
            if (endTimeM < 10){
                colon2 = ":0";
            }else{
                colon2 = ":";
            }

            Toast.makeText(getApplicationContext(), textSize.toString(),
                    Toast.LENGTH_LONG).show();

            lesson.setText(className + " ".toString() + startTimeH.toString() + colon1
                    + startTimeM.toString() + " - ".toString() + endTimeH.toString() + colon2
                    + endTimeM.toString());
            RelativeLayout ll = (RelativeLayout) findViewById(R.id.rel);
            lesson.setBackgroundColor(getResources().getColor(R.color.pasowy));
            lesson.setTextColor(getResources().getColor(R.color.creamy));
            lesson.setX(size.x / 7);
            lesson.setY(time);
            lesson.setWidth(size.x / 7 * 6);
            lesson.setHeight(textSize);
            lesson.setEnabled(true);
            lesson.setTextSize(10);
            lesson.setTag(className);
            lesson.setFocusable(true);

            lesson.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ViewGroup layout = (ViewGroup) findViewById(R.id.rel);
                    View lessonToRemove = layout.findViewWithTag(lesson.getTag());
                    layout.removeView(lessonToRemove);
                    return true;
                }
            });

            lesson.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent addNotes = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    addNotes.putExtra(MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(destination));
                    startActivityForResult(addNotes, 2);
                }
            });

            ll.addView(lesson);

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

