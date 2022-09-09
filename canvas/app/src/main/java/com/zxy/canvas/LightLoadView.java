package com.zxy.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class LightLoadView extends View {
    String TAG = "LightLoadView";

    public LightLoadView(Context context) {
        super(context);
        initView();
    }


    public LightLoadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public LightLoadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public LightLoadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    Paint mBcPaint = new Paint();
    Paint paint = new Paint();
    Paint msymbolPaint = new Paint();

    private void initView() {
        mBcPaint.setAntiAlias(true);
        mBcPaint.setColor(Color.GRAY);

        paint.setAntiAlias(true);

        msymbolPaint.setAntiAlias(true);
        msymbolPaint.setColor(Color.parseColor("#BCBCBC"));
        msymbolPaint.setStrokeWidth(5f);

    }

    int top = 100;
    float eventY = 0;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent: x:" + event.getX() + "   y:" + event.getY() + "  event.getAction():" + event.getAction());

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                eventY = event.getY();
                break;

            case MotionEvent.ACTION_UP:
                if (eventY < 100 && top > 0) {//add
                    top--;

                } else if (eventY > 300 && top < 100) {//sub
                    top++;
                }
                invalidate();
                break;

            default:
        }

        return super.onTouchEvent(event);
    }

    private int colorArray2[] = {Color.YELLOW, Color.YELLOW, Color.GRAY};

    @Override
    public void draw(Canvas canvas) {

        mBcPaint.setColor(Color.GRAY);
        RectF rectBaF = new RectF(0f, 0f, 102f, 400f);
        canvas.drawRoundRect(rectBaF, 10f, 10f, mBcPaint);

        LinearGradient linearGradient = new LinearGradient(0f, (top * 4), 102f, 400f, colorArray2, null, Shader.TileMode.MIRROR);
        paint.setStyle(Paint.Style.FILL);
        paint.setShader(linearGradient);
        RectF rectSel = new RectF(0f, (top * 4), 102f, 400f);
        canvas.drawRoundRect(rectSel, 10f, 10f, paint);


        canvas.drawLine(29f, 50f, 75f, 50f, msymbolPaint);

        canvas.drawLine(53f, 29f, 53f, 73f, msymbolPaint);

        canvas.drawLine(29f, 370f, 75f, 370f, msymbolPaint);

        super.draw(canvas);
    }
}
