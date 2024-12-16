package com.example.mydraw;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Dimension;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyTestView extends View {
    private List<Item> data;  // A list of items that are displayed.
    private float pointerRadius=100;                      // Obtained from styled attributes.
    private float pointerX=500;                           // Calculated in onSizeChanged.
    private float pointerY=200;                           // Calculated in onSizeChanged.
    private float textX=40;                              // Calculated in onSizeChanged.
    private float textY;                              // Calculated in onSizeChanged.
    private RectF bounds;                             // Calculated in onSizeChanged.
    private RectF shadowBounds;                       // Calculated in onSizeChanged.
    private Boolean showText=true;    // Obtained from styled attributes.
    private int textWidth=148;       // Obtained from styled attributes.

    private Paint textPaint;
    private Paint piePaint;
    private Paint shadowPaint;
    @ColorInt
    private int textColor;       // Obtained from style attributes.

    @Dimension
    private float textHeight=33;    // Obtained from style attributes.
    private int currentItem = 0;
    private Paint bluePaint = null;
    private Paint redPaint = null;
    private Paint greenPaint = null;
    private Paint yellowPaint = null;
    private Paint linearGradientPaint = null;
    private Paint radialGradientPaint = null;
    private Path polygonPath;
    private List<Point> points = null;
    private int width;
    private int height;
    private final String TAG = "1111";
    private Path path;
    private Bitmap appleBitmap;

    public MyTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        points = new ArrayList<>();
        data = new ArrayList<Item>();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        init();
        initDoc();
        initShader();
        myDraw(canvas);
    }
    private void myDraw(Canvas canvas){
        for(Point p: points){
            canvas.drawPoint(p.x,p.y,bluePaint);
        }
        Log.v(TAG,height + " "+ width);
        Log.v(TAG,getHeight() + " "+ getWidth());
        // canvas.drawCircle(width/2, height/2,100,bluePaint);
        //canvas.drawLine(20,30,width-20,30,greenPaint);
       // canvas.drawText("Simple application 2023!!!",20,height-50,redPaint);
       // canvas.drawTextOnPath("Mistrzostwa Świata w piłce nożnej 2022", path,0,0,redPaint);
      //  canvas.drawRect(45,45,width-195,135,redPaint);

      //  canvas.drawRect(50,50,width-200,130,yellowPaint);
        // linear gradient
      //  canvas.drawRect(50,250,1000,400,linearGradientPaint);

      //  canvas.drawCircle(getWidth()/2.0f,getHeight()/2.0f,getWidth()/3.0f,radialGradientPaint);

      //  RectF shadowBounds = new RectF(50, 0.75f * getHeight(), getWidth() - 50, getHeight() - 150);
      //  canvas.drawOval(shadowBounds,shadowPaint);
        //
      //  canvas.drawPath(polygonPath,bluePaint);

       // drawHexagon(canvas);

      //  drawSinus(canvas);
        // Załaduj bitmapę z zasobów (załóżmy, że apple.png jest w folderze drawable)

       // myDrawBitmap(canvas);

        drawDoc(canvas);
        // basicMethods(canvas);

        //myDrawARGB(canvas);

        //myDrawRectForLoop(canvas);


        //  myClipOutRect(canvas);


        // float[] myPoints = {100, 120, 170, 130, 140, 100, 60, 70, 30, 80};

        //drawMyLines(canvas);


        // drawRoundRectMy(canvas);

        //  drawTriangle(canvas);


    }
    private void initDoc() {

        bounds = new RectF(30,50,300,300);

        shadowBounds = new RectF(30,600,500,400);

        Item item = new Item();
        item.label="opcja 1";
        item.startAngle=60;
        item.endAngle=270;
        item.color=1;
        item.value=33;
        // item.shader=shadowPaint.getShader();
        Item item2 = new Item();
        item2.label="opcja 2";
        item2.startAngle=0;
        item2.endAngle=45;
        item2.color=Color.GREEN;
        item2.value=33;
        // item2.shader=shadowPaint.getShader();

        data.add(item);
        data.add(item2);


        textWidth=148;
        textColor=Color.RED;
        showText=true;
        textX= 40.0F;
        textY=50.0f;
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(textColor);
        if (textHeight == 0) {
            textHeight = textPaint.getTextSize();
        } else {
            textPaint.setTextSize(textHeight);
        }

        piePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        piePaint.setStyle(Paint.Style.FILL);
        piePaint.setTextSize(textHeight);
        piePaint.setColor(Color.GREEN);

        shadowPaint = new Paint(0);
        shadowPaint.setColor(0xff101010);
        shadowPaint.setMaskFilter(new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL));
    }
    private void drawDoc(Canvas canvas) {
        // Draw the shadow.
        canvas.drawOval(
                shadowBounds,
                shadowPaint
        );

        // Draw the label text.
        canvas.drawText(data.get(currentItem).label, textX, textY, textPaint);

        // Draw the pie slices.
        for (int i = 0; i < data.size(); ++i) {
            Item it = data.get(i);
            piePaint.setShader(it.shader);
            canvas.drawArc(
                    bounds,
                    360 - it.endAngle,
                    it.endAngle - it.startAngle,
                    true,
                    piePaint
            );
        }

        // Draw the pointer.
        // canvas.drawLine(textX, pointerY, pointerX, pointerY, textPaint);

        //  canvas.drawCircle(pointerX, pointerY, pointerRadius, textPaint);

        canvas.drawCircle(500, 200, 100, redPaint);
    }
    private void myDrawBitmap(Canvas canvas) {
        appleBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.apple);

        // Ustaw gęstość na wartość odpowiadającą gęstości ekranu urządzenia
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        canvas.setDensity(metrics.densityDpi);

        canvas.drawBitmap(appleBitmap, 0, 0, null);
    }

    private void basicMethods(Canvas canvas) {
        bluePaint.setStrokeWidth(5);
        canvas.drawLine(10,400,getWidth()-10,200,bluePaint);

        canvas.drawRect(1000,300,200,600,redPaint);

        RectF rectF = new RectF(1000,300,200,600);
        canvas.drawRect(rectF,yellowPaint);


        canvas.drawARGB(128, 0, 255, 0);


        canvas.drawRect(getWidth() / 2, 0, getWidth(), getHeight() / 4, yellowPaint);
    }

    private void myDrawARGB(Canvas canvas) {
        Path path = new Path();
        path.addCircle(getWidth() / 2, getHeight() / 2, 200, Path.Direction.CW);
        canvas.drawARGB(255, 0, 0, 255);
        canvas.drawPath(path, greenPaint);
    }

    private void myDrawRectForLoop(Canvas canvas) {
        Paint paint = new Paint();
        for (int i = 0; i < 255; i++) {
            paint.setColor(Color.argb(255, 255-i, i, 255 - i));
            canvas.drawRect(0, i, getWidth(), i + 1, paint);
        }
    }

    private static void myClipOutRect(Canvas canvas) {
        Paint paint = new Paint();


        paint.setColor(Color.RED);
        canvas.drawRect(100, 100, 300, 200, paint);

        RectF clipRect = new RectF(150, 125, 250, 175);
        canvas.clipOutRect(clipRect);

        paint.setColor(Color.BLUE);
        canvas.drawRect(100, 100, 300, 200, paint);
    }

    private void drawMyLines(Canvas canvas) {
        float[] myPoints = {100, 200, 170, 130,170,130,500,700,500,700,700,200};
        bluePaint.setStrokeWidth(5);
        canvas.drawLines(myPoints,bluePaint);
    }

    private void drawRoundRectMy(Canvas canvas) {
        RectF outerRect = new RectF(100, 100, 300, 200);
        RectF innerRect = new RectF(120, 120, 280, 180);
        float outerRadius = 20;
        float innerRadius = 10;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            canvas.drawDoubleRoundRect(outerRect, outerRadius, outerRadius,
                                       innerRect, innerRadius, innerRadius, bluePaint);
        }
    }

    private void drawTriangle(Canvas canvas) {
        float[] points = { 50, 50,
                175,475 ,
                525,35
        };
        int[] colors = {Color.RED,Color.RED,Color.RED};
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        canvas.drawVertices(Canvas.VertexMode.TRIANGLES, points.length,
                points, 0, null, 0, colors,
                0, null, 0, 0, redPaint);
    }

    private void drawSinus(Canvas canvas) {
        // Grubszy pędzel
        Paint p = new Paint();
        p.setStrokeWidth(5f); // Dostosuj grubość linii
        // Współczynniki funkcji sinus
        int amplitude = 150;  // amplituda (wysokość fali)
        int frequency = 5;   // częstotliwość (ilość fal na jednostkę długości)
        int phaseShift = 0;  // przesunięcie fazowe, Przesuwa falę w lewo lub w prawo wzdłuż osi X.
        int verticalShift = getHeight()/2; // przesunięcie pionowe (położenie środka fali),
        // Ustala, na jakiej wysokości na osi Y będzie oscylować fala sinusoidalna.

        // Rysowanie sinusoidy
        float startX = 10;
        float endX = getWidth()-10;
        p.setColor(Color.RED);
        for (float x = startX; x <= endX; x += 0.1) {
            float y =(float) (amplitude * Math.sin(frequency * (x + phaseShift) * 2 * Math.PI / getWidth())
                    + verticalShift);
            canvas.drawPoint( x, y, p);
        }
        // Rysowanie osi
        p.setColor(Color.BLACK);
       // p.setStyle(Paint.Style.STROKE);
        float startY = getHeight() / 2;
        // Oś x
        canvas.drawLine(startX, getHeight() / 2, getWidth()-10, getHeight() / 2, p);
        // Oś y
        canvas.drawLine(getWidth()/2, 0, getWidth()/2, getHeight(), p);

        // Podziałka na osi x co pi/2
        float step = (float) (Math.PI / 2);
        for (float x = startX; x <= endX; x += step) {
            //canvas.drawLine(x, startY - 10, x, startY + 10, p);
           // canvas.drawText(String.valueOf((int)(x / Math.PI) + "π/2"), x, startY + 20, p);
        }
    }

    private void drawHexagon(Canvas canvas) {
        // Środek sześciokąta
        int x = getWidth()/2;
        int y = getHeight()/2;
        int radius = 200; // Promień okręgu opisującego sześciokąt
        int[] xPoints = new int[6];
        int[] yPoints = new int[6];
        // Obliczanie wierzchołków sześciokąta
        for (int i = 0; i < 6; i++) {
            double angle = Math.PI / 3 * i;
            xPoints[i] = (int) (x + radius * Math.cos(angle));
            yPoints[i] = (int) (y + radius * Math.sin(angle));
        }
        Path hexagonPath = new Path();

        hexagonPath.moveTo(xPoints[0], yPoints[0]);
        for (int i = 1; i < xPoints.length; i++) {
            hexagonPath.lineTo(xPoints[i], yPoints[i]);
        }
        hexagonPath.close();

        canvas.drawPath(hexagonPath,greenPaint);
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


        int heightPixels = getResources().getDisplayMetrics().heightPixels;
        int widthPixels = getResources().getDisplayMetrics().widthPixels;
           Log.v(TAG, "heightPixels="+ heightPixels +" widthPixels="+ widthPixels +" ");
        path = new Path();
        path.addCircle(400,700,200, Path.Direction.CW);

        // Współrzędne punktów gwiazdy
        int[] xPoints = {100, 120, 170, 130, 140, 100, 60, 70, 30, 80};
        int[] yPoints = {30, 80, 80, 110, 160, 130, 160, 110, 80, 80};
        //
        polygonPath = new Path();
        // Start at the first point
        polygonPath.moveTo(xPoints[0], yPoints[0]);

// Iterate over the remaining points, connecting them with lines
        for (int i = 1; i < xPoints.length; i++) {
            polygonPath.lineTo(xPoints[i], yPoints[i]);
        }

// Close the path to form a polygon
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

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // Account for padding.
        float xpad = (float)(getPaddingLeft() + getPaddingRight());
        float ypad = (float)(getPaddingTop() + getPaddingBottom());
        xpad=5;
        ypad=5;
        // Account for the label.
        if (showText) xpad += textWidth;

        float ww = (float)w - xpad;
        float hh = (float)h - ypad;

        // Figure out how big you can make the pie.
        float diameter = Math.min(ww, hh);
    }

    // Maintains the state for a data item.
    private class Item {
        public String label;
        public float value;
        @ColorInt
        public int color;

        // Computed values.
        public int startAngle;
        public int endAngle;
        public Shader shader;
    }

}
