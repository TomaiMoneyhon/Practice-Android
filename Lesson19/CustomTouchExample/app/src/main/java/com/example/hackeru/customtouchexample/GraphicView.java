package com.example.hackeru.customtouchexample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by hackeru on 27/08/2015.
 */
public class GraphicView extends View {

    Paint paint = new Paint();
    ArrayList<Line> lines = new ArrayList<>();

    public GraphicView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint.setAntiAlias(true);
        paint.setStrokeWidth(6f);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(Line l:lines) {
            canvas.drawLine(l.startX,l.startY,l.endX,l.endY,paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN) {
            lines.add(new Line(event.getX(),event.getY()));
            return true;
        }
        else if((event.getAction()==MotionEvent.ACTION_UP || event.getAction()==MotionEvent.ACTION_MOVE) && lines.size()>0) {
            Line current = lines.get(lines.size()-1);
            current.endX = event.getX();
            current.endY = event.getY();
            invalidate(); //tells the system that the view is not up to date and redraw it(calls onDraw())
            return true;
        }
        else return false;
    }

    class Line  {
        float startX,startY,endX,endY;

        public Line(float startX, float startY, float endX, float endY) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }
        public Line(float startX,float startY) {
           this(startX,startY,startX,startY);
        }
    }
}

