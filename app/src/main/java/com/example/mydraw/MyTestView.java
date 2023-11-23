package com.example.mydraw;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
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
    private Paint shadowPaint = null;
    private Paint linearGradientPaint = null;
    private Paint radialGradientPaint = null;
    private RectF shadowBounds;
    private int heightPixels;
    private int widthPixels;
    private Path path;
    private Path polygonPath;
    private ArrayList<Point> points = null;
    private int width;
    private int height;

    public MyTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initShader();
        myDraw(canvas);
    }
    private void myDraw(Canvas canvas){
        for(Point p: points){
            canvas.drawPoint(p.x,p.y,bluePaint);
        }
        Log.v("1111",height + " "+ width);
        Log.v("1111",getHeight() + " "+ getWidth());
        // canvas.drawCircle(width/2, height/2,100,bluePaint);
        canvas.drawLine(20,30,width-20,30,greenPaint);
        canvas.drawText("Simple application 2023!!!",20,height-50,redPaint);
        //  canvas.drawTextOnPath("Mistrzostwa Świata w piłce nożnej 2022",
        //         path,0,0,redPaint);
        canvas.drawRect(45,45,width-195,135,redPaint);

        canvas.drawRect(50,50,width-200,130,yellowPaint);
        // linear gradient
        canvas.drawRect(50,250,1000,400,linearGradientPaint);

        canvas.drawCircle(getWidth()/2.0f,getHeight()/2.0f,getWidth()/3.0f,radialGradientPaint);

        shadowBounds = new RectF(50,0.75f*getHeight(),getWidth()-50,getHeight()-150);
        canvas.drawOval(shadowBounds,shadowPaint);
        //
        canvas.drawPath(polygonPath,bluePaint);
    }
    private void initShader() {
        linearGradientPaint.setShader(new LinearGradient(50,250,1000,400,
                Color.BLUE,
                Color.GREEN,
                Shader.TileMode.MIRROR
        ));
        radialGradientPaint.setShader( new RadialGradient(
                getWidth()/2.0f,
                getHeight()/2.0f,
                getWidth()/3.0f,
                Color.RED,
                Color.YELLOW,
                Shader.TileMode.MIRROR
        ));
    }


    private void init(){

        points = new ArrayList<>();
           heightPixels = getResources().getDisplayMetrics().heightPixels;
           widthPixels = getResources().getDisplayMetrics().widthPixels;
        path = new Path();
        path.addCircle(400,700,200, Path.Direction.CW);
        //
        polygonPath = new Path();
        polygonPath.moveTo(100, 0);
        polygonPath.lineTo(141, 71);
        polygonPath.lineTo(217, 78);
        polygonPath.lineTo(154, 125);
        polygonPath.lineTo(166, 201);
        polygonPath.lineTo(100, 170);
        polygonPath.lineTo(34, 201);
        polygonPath.lineTo(46, 125);
        polygonPath.lineTo(0, 78);
        polygonPath.lineTo(76, 71);
        polygonPath.close();
        Typeface tf =getResources().getFont(R.font.rage);
        // blue
        bluePaint = new Paint();
        //bluePaint.setColor(Color.BLUE);
        bluePaint.setColor(getResources().getColor(R.color.blue,null));
        bluePaint.setTextSize(125);
        bluePaint.setStrokeWidth(30);
        bluePaint.setTypeface(tf);
        bluePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        // red
        redPaint = new Paint();
        redPaint.setColor(   getResources().getColor( R.color.red, null) );
        redPaint.setTextSize(85);
        redPaint.setTypeface(tf);
        // green
        greenPaint = new Paint();
        greenPaint.setStrokeWidth(10);
        greenPaint.setColor(getResources().getColor(R.color.green,null));
        greenPaint.setTextSize(125);
        greenPaint.setTypeface(tf);
        // yellow
        yellowPaint = new Paint();
        yellowPaint.setStrokeWidth(10);
        yellowPaint.setColor(getResources().getColor(R.color.yellow,null));
        yellowPaint.setTextSize(125);
        yellowPaint.setTypeface(tf);
        // shadow
        shadowPaint = new Paint(0);
        shadowPaint.setColor(0xff101010);
        shadowPaint.setMaskFilter(new BlurMaskFilter(9, BlurMaskFilter.Blur.NORMAL));
        // linear
        linearGradientPaint = new Paint();
        //  linearGradientPaint.setColor(getResources().getColor(R.color.red,null));
        linearGradientPaint.setStrokeWidth(2);
        // radial
        radialGradientPaint = new Paint();
        radialGradientPaint.setColor(Color.BLACK);
        radialGradientPaint.setStrokeWidth(1);
        radialGradientPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        points.add(new Point((int)event.getX(),(int)event.getY()));
        invalidate();
        return true;
    }
}
