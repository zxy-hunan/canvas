package com.zxy.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class LightImgView extends View {
    Paint mPaint=new Paint();
    int defaultColor=Color.GRAY;
    String TAG="LightImgView";

    Paint grayPaint=new Paint();

    public LightImgView(Context context) {
        super(context);
    }

    public LightImgView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LightImgView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LightImgView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    int line1=120;
    int line2=30;
    int line3=10;

    @Override
    protected void onDraw(Canvas canvas) {

        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(3);
        mPaint.setColor(defaultColor);


        int imgWidth=355;
        int imgHeight=507;

        canvas.drawLine(imgWidth/2-5,0,imgWidth/2-5,line1,mPaint);
        canvas.drawLine(imgWidth/2+5,0,imgWidth/2+5,line1,mPaint);

        canvas.drawLine(imgWidth/2-10,line1,imgWidth/2-5,line1,mPaint);
        canvas.drawLine(imgWidth/2+10,line1,imgWidth/2+5,line1,mPaint);

        canvas.drawLine(imgWidth/2-10,line1,imgWidth/2-10,line1+line2,mPaint);
        canvas.drawLine(imgWidth/2+10,line1,imgWidth/2+10,line1+line2,mPaint);

        canvas.drawLine(imgWidth/2-30,line1+line2+line3,imgWidth/2-line3,line1+line2,mPaint);

        canvas.drawLine(imgWidth/2+30,line1+line2+line3,imgWidth/2+line3,line1+line2,mPaint);

        Path path3=new Path();
        Paint paint = new Paint();
        paint.setStrokeWidth(3);
        paint.setAntiAlias(true);
        paint.setColor(defaultColor);
        paint.setStyle(Paint.Style.STROKE);
        path3.moveTo(imgWidth/2-30,line1+line2+line3);
        path3.cubicTo(imgWidth/2-40,220,60,200,20,400);
        canvas.drawPath(path3,paint);

        Path path4=new Path();
        Paint paint4 = new Paint();
        paint4.setStrokeWidth(3);
        paint4.setAntiAlias(true);
        paint4.setColor(defaultColor);
        paint4.setStyle(Paint.Style.STROKE);
        path4.moveTo(imgWidth/2+30,line1+line2+line3);
        path4.cubicTo(imgWidth/2+40,220,295,200,335,400);
        canvas.drawPath(path4,paint4);


        Path path5=new Path();
        Paint paint5 = new Paint();
        paint5.setAntiAlias(true);
        paint5.setStrokeWidth(5);
        paint5.setColor(defaultColor);
        paint5.setStyle(Paint.Style.STROKE);
        path5.moveTo(imgWidth/2,0);
        path5.cubicTo(imgWidth/2-60,line1/2,imgWidth/2+60,line1/2,imgWidth/2,line1);
        canvas.drawPath(path5,paint5);



        RectF mRound = new RectF(20F,377F ,(float)(imgWidth-20) , 417f);

        canvas.drawOval(mRound,mPaint);

        canvas.drawCircle((float)(imgWidth/2),(float)(383+42),42f,mPaint);


        super.onDraw(canvas);
    }

    public void controlLight(boolean b){
        if (b){
            defaultColor=Color.parseColor("#DADE96");
        }else{
            defaultColor=Color.GRAY;
        }

        invalidate();
    }
}
