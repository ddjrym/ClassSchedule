package com.rymarczykdawidgmail.classschedule;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

/**
 * Created by JAN on 2015-10-31.
 */
public class DayView extends View {
    private static final String TAG = "Monday";
    private final monday FirstDay;

    public DayView(Context context) {
        super(context);
        this.FirstDay = (monday) context;
    }

    private float width;
    private float height;
    private int X;
    private int Y;
    private final Rect rect = new Rect();

    @Override
    public void onSizeChanged(int w, int h, int oldw, int olds) {
        width = w / 16f;
        height = h / 16f;
        makeRect(X, Y, rect);
        Log.d(TAG, "OnSizeChanged: width " + width + " height " + height);
        super.onSizeChanged(w, h, oldw, olds);
    }
    private void makeRect(int x, int y, Rect rect) {
        rect.set((int) (x * width), (int) (y * height), (int) (x * width + width),
                (int) (y * height + height));
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Paint background = new Paint();
        background.setColor(getResources().getColor(R.color.creamy));
        canvas.drawRect(0, 0, getWidth(), getHeight(), background);

        Paint pasowy = new Paint();
        pasowy.setColor(getResources().getColor(R.color.pasowy));

        canvas.drawLine(getWidth() / 7, getHeight() / 8, getWidth() / 7,
                getHeight() / 16 * 15, pasowy);
        for (int i = 2; i < 15; i++) {
            canvas.drawLine(0, i * getHeight() / 16, getWidth(), i * getHeight() / 16, pasowy);
        }

        Paint hours = new Paint(Paint.ANTI_ALIAS_FLAG);
        hours.setColor(getResources().getColor(R.color.pasowy));
        hours.setStyle(Paint.Style.FILL);
        hours.setTextSize(height * 0.5f);
        hours.setTextScaleX(height / width / 2);
        hours.setTextAlign(Paint.Align.CENTER);

        String[] hoursTab = new String[13];
        hoursTab[0] = getResources().getString(R.string.Eight);
        hoursTab[1] = getResources().getString(R.string.Nine);
        hoursTab[2] = getResources().getString(R.string.Ten);
        hoursTab[3] = getResources().getString(R.string.Eleven);
        hoursTab[4] = getResources().getString(R.string.Twelve);
        hoursTab[5] = getResources().getString(R.string.One);
        hoursTab[6] = getResources().getString(R.string.Two);
        hoursTab[7] = getResources().getString(R.string.Three);
        hoursTab[8] = getResources().getString(R.string.Four);
        hoursTab[9] = getResources().getString(R.string.Five);
        hoursTab[10] = getResources().getString(R.string.Six);
        hoursTab[11] = getResources().getString(R.string.Seven);
        hoursTab[12] = getResources().getString(R.string.Eight);


        Paint.FontMetrics fm = hours.getFontMetrics();
        float x = getHeight() / 10 / 2;
        float y = height / 2 + 5;
        for (int i = 2; i < 15; i++) {
            canvas.drawText(hoursTab[i - 2], x, i * height + y, hours);
        }

        Paint DayName = new Paint(Paint.ANTI_ALIAS_FLAG);
        DayName.setColor(getResources().getColor(R.color.pasowy));
        DayName.setStyle(Paint.Style.FILL);
        DayName.setTextSize(height);
        DayName.setTextScaleX(height / width);
        DayName.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(getResources().getString(R.string.Monday), getWidth() / 2, getHeight() / 10,
                DayName);
    }
}
