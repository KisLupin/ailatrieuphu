package com.t3h.ailatrieuphu.graphic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import java.util.logging.LogRecord;

public class Chart extends View {

    public Chart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Chart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        Paint paint2 = new Paint();
        paint.setColor(Color.parseColor("#E91E63"));
        paint2.setColor(Color.parseColor("#00B0FF"));

        Rect rect1 = new Rect();
        Rect rect2 = new Rect();
        Rect rect3 = new Rect();
        Rect rect4 = new Rect();

//        int a = 0;
//        while (a == 100){
//            rect1.left = 45;
//            rect1.right = 175;
//            rect1.top = 50+a;
//            rect1.bottom = getBottom();
//            a++;
//        }

        rect1.left = 45;
        rect1.right = 175;
        rect1.top = 50;
        rect1.bottom = getBottom();

        rect2.left = 250;
        rect2.right = 380;
        rect2.top = 50;
        rect2.bottom = getBottom();

        rect3.left = 470;
        rect3.right = 600;
        rect3.top = 50;
        rect3.bottom = getBottom();

        rect4.left = 680;
        rect4.right = 810;
        rect4.top = 50;
        rect4.bottom = getBottom();

        canvas.drawRect(rect1,paint);
        canvas.drawRect(rect2,paint);
        canvas.drawRect(rect3,paint);
        canvas.drawRect(rect4,paint);
    }
}
