package com.example.wanglei.clockview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Calendar;

/**
 * Created by wanglei on 17-10-14.
 */

public class ClockView extends View {
    int mWidth,mHeight,mRadius;
    Paint mPaint1;
    int widthLine,shortLine;
    int hourWidth,minuteWidth,secondWidth;
    public ClockView(Context context) {
        super(context);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint1 = new Paint(Paint.DITHER_FLAG|Paint.ANTI_ALIAS_FLAG);

    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        hourWidth = 40;
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mRadius = mWidth/4;
        canvas.save();
        drawCircle(canvas);
        drawScale(canvas);
        drawPoints(canvas);
        canvas.restore();
        postInvalidateDelayed(1000);
    }

    private void drawPoints(Canvas canvas) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int hourAngle = (hour%12)*360/12;
        int minutesAngle = (minutes)*360/60;
        int secondAngle = second*360/60;
        //时针
        canvas.save();
        canvas.translate(mWidth/2,mHeight/2);
        canvas.rotate(hourAngle);
        mPaint1.setStrokeWidth(1);
        mPaint1.setColor(Color.BLACK);
        //canvas.drawLine(10,10,40,40,mPaint1);
        //canvas.drawLine(0,0,50,0,mPaint1);
        canvas.drawLine(0,0,0,50,mPaint1);
        RectF mRectF = new RectF(-hourWidth/4,-mRadius*3/5,
                hourWidth/4,mRadius/6);
        mPaint1.setStyle(Paint.Style.FILL);

        canvas.drawRoundRect(mRectF,10,10,mPaint1);
        Log.d("wanglei drawPoint","hour = "+hour+" hourAngle= "+hourAngle+"  "
        +mRectF.left+" "+mRectF.top+" "+mRectF.right+"  "+mRectF.bottom);

        canvas.restore();
        //分针
        canvas.save();
        canvas.translate(mWidth/2,mHeight/2);
        canvas.rotate(minutesAngle);
        mPaint1.setStrokeWidth(1);
        mPaint1.setColor(Color.BLACK);
        //canvas.drawLine(10,10,40,40,mPaint1);
        //canvas.drawLine(0,0,50,0,mPaint1);
        canvas.drawLine(0,0,0,50,mPaint1);
        RectF mRectF1 = new RectF(-hourWidth/6,-mRadius*4/5,
                hourWidth/6,mRadius/6);
        mPaint1.setStyle(Paint.Style.FILL);

        canvas.drawRoundRect(mRectF1,10,10,mPaint1);
        Log.d("wanglei drawPoint","hour = "+hour+" hourAngle= "+hourAngle+"  "
                +mRectF.left+" "+mRectF.top+" "+mRectF.right+"  "+mRectF.bottom);

        canvas.restore();

        //秒针
        canvas.save();
        canvas.translate(mWidth/2,mHeight/2);
        canvas.rotate(secondAngle);
        mPaint1.setStrokeWidth(1);
        mPaint1.setColor(Color.RED);
        //canvas.drawLine(10,10,40,40,mPaint1);
        //canvas.drawLine(0,0,50,0,mPaint1);
        canvas.drawLine(0,0,0,50,mPaint1);
        RectF mRectF2 = new RectF(-hourWidth/8,-mRadius*4/5,
                hourWidth/8,mRadius/6);
        mPaint1.setStyle(Paint.Style.FILL);

        canvas.drawRoundRect(mRectF2,10,10,mPaint1);
        Log.d("wanglei drawPoint","hour = "+hour+" hourAngle= "+hourAngle+"  "
                +mRectF.left+" "+mRectF.top+" "+mRectF.right+"  "+mRectF.bottom);

        canvas.restore();

    }

    private void drawScale(Canvas canvas) {
        widthLine = 30;
        shortLine = 20;

        canvas.save();
        canvas.translate(mWidth/2,mHeight/2);
        for (int i = 0; i <60 ; i++) {
            if (i%5==0){

            mPaint1.setColor(Color.BLACK);
            mPaint1.setStrokeWidth(5);
            canvas.drawLine(0,-mRadius,0,-mRadius+widthLine,mPaint1);
            String text = (((i/5))==0?12:(i/5))+"";
            drawText(canvas,-i*6,text);


            }else {
                mPaint1.setColor(Color.GRAY);
                mPaint1.setStrokeWidth(2);
                canvas.drawLine(0,-mRadius,0,-mRadius+shortLine,mPaint1);
            }
            canvas.rotate(6);
        }

        canvas.restore();


    }

    private void drawText(Canvas canvas,int degree,String text) {
    canvas.save();
    canvas.translate(0,-mRadius+widthLine+20);
    canvas.rotate(degree);
        Log.d("wanglei","degress= "+degree);
    Rect rect = new Rect();
    mPaint1.setTextSize(20);
    mPaint1.getTextBounds(text,0,text.length(),rect);
    int fontWidth = rect.width();
    int fontHeight = rect.height();
    canvas.drawText(text,-0,fontHeight/2,mPaint1);
    canvas.restore();

    }


    private void drawCircle(Canvas canvas) {
        canvas.save();
    mPaint1.setColor(Color.WHITE);
    canvas.drawCircle(mWidth/2,mHeight/2,mRadius,mPaint1);
        canvas.restore();
    }


}
