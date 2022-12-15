package com.example.mydraw;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class MyTestView extends View {
    private Paint bluePaint = null;
    private Paint redPaint = null;
    private Paint greenPaint = null;
    private Paint yellowPaint = null;
    private Paint pointPaint = null;
    private int heightPixels;
    private int widthPixels;
    private Path path;
    private ArrayList<Point> points = null;

    public MyTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

       myDraw(canvas);
    }
    private void myDraw(Canvas canvas){
        // draw point
        for(Point p: points){
            canvas.drawPoint(p.x,p.y, pointPaint);
        }
        Log.v("1111",heightPixels + " "+ widthPixels);
        canvas.drawCircle(widthPixels/2, heightPixels/2,100,greenPaint);
        canvas.drawLine(20,30,widthPixels-20,30,greenPaint);
        canvas.drawText("Idą Święta i Nowy Rok 2023!!!",20,heightPixels-200,redPaint);
        canvas.drawTextOnPath("Mistrzostwa Świata w piłce nożnej wygra Argentyna", path,0,0,bluePaint);
        canvas.drawRect(45,45,widthPixels-195,135,redPaint);

        canvas.drawRect(50,50,widthPixels-200,130,yellowPaint);


    }
    private void init(){

        points = new ArrayList<Point>();

        heightPixels = getResources().getDisplayMetrics().heightPixels;
        widthPixels = getResources().getDisplayMetrics().widthPixels;

        path = new Path();
        path.addCircle(400,700,200, Path.Direction.CCW);

        Typeface redTf =getResources().getFont(R.font.rage);
        Typeface blueTf =getResources().getFont(R.font.chancery);
        Typeface greenTf =getResources().getFont(R.font.freesctp);
      // blue
        bluePaint = new Paint();
        bluePaint.setColor(   getResources().getColor( R.color.blue , null ));
        bluePaint.setTextSize(55);
        bluePaint.setStrokeWidth(3);
        bluePaint.setTypeface(blueTf);
        bluePaint.setStyle(Paint.Style.STROKE);
      // red
        redPaint = new Paint();
        redPaint.setColor(   getResources().getColor( R.color.red , null ));
        redPaint.setTextSize(85);
        redPaint.setTypeface(redTf);
        // green
        greenPaint = new Paint();
        greenPaint.setStrokeWidth(10);
        greenPaint.setColor(getResources().getColor(R.color.green,null));
        greenPaint.setTextSize(125);
        greenPaint.setTypeface(greenTf);

        // yellow
        yellowPaint = new Paint();
        yellowPaint.setStrokeWidth(10);
        yellowPaint.setColor(getResources().getColor(R.color.yellow,null));
        yellowPaint.setTextSize(125);
       // another
        pointPaint = new Paint();
        pointPaint.setStrokeWidth(50);
        pointPaint.setColor(getResources().getColor(R.color.orange,null));
        pointPaint.setTextSize(25);
    }

    private void setMyFont() {
        TextPaint textPaint = new TextPaint();
        Typeface tf =Typeface.createFromAsset(
                getContext().getAssets(),
                "fonts/10249.ttf"
        );
        textPaint.setTypeface(tf);
        textPaint.setStrokeWidth(7);
       // textPaint.setTextSize(Tool.coconvertSpToPx(getContext(), 30));
        textPaint.setAntiAlias(true);
        textPaint.setPathEffect(null);
        textPaint.setColor(getResources().getColor(R.color.green, null));
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        points.add(new Point((int)event.getX(),(int)event.getY()));
        invalidate();
        return true;
    }
}
