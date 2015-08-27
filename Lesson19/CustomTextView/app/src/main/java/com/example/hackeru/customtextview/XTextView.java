package com.example.hackeru.customtextview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by hackeru on 27/08/2015.
 */
public class XTextView extends TextView {

    public XTextView(Context context) {
        super(context);
    }

    public XTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(4);
        paint.setColor(Color.RED);
        canvas.drawLine(0,0,getWidth(),getHeight(),paint);
        canvas.drawLine(0,getHeight(),getWidth(),0,paint);

    }
}
