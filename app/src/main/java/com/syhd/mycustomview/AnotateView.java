package com.syhd.mycustomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Timer;

/**
 * @author 韩宝坤
 * @date 2016/8/31 15:53
 * @email kunyu1342@163.com
 * @description
 */
public class AnotateView extends View {
    private static final String TAG = AnotateView.class.getName();
    private Paint linePaint;
    private int centerX;
    private int centerY;
    private float pointY;
    private float downY;
    private float changeY;
    private Timer mTimer;

    private boolean isStart = false;
    private int startx = 20;
    private int ends = 0;
    private int circleIn = 108;

    public AnotateView(Context context) {
        super(context);
        init();
    }

    public AnotateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AnotateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        linePaint = new Paint();
        linePaint.setStyle(Paint.Style.FILL);
        linePaint.setAntiAlias(true);
        linePaint.setStrokeWidth(4);
        linePaint.setColor(Color.BLUE);
        mTimer = new Timer();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        centerX = getMeasuredWidth() / 2;
        centerY = getMeasuredHeight() / 2;

        if (!isStart) {
            if (changeY <= 0) {

                //画外圆
                linePaint.setStrokeWidth(2);
                linePaint.setStyle(Paint.Style.STROKE);
//            canvas.drawCircle(centerX, centerY - 50, 100, linePaint);
                Path path = new Path();
                path.addCircle(centerX, centerY, 100, Path.Direction.CCW);
                canvas.drawPath(path, linePaint);

                linePaint.setStyle(Paint.Style.FILL);
                linePaint.setStrokeWidth(14);
                linePaint.setStrokeCap(Paint.Cap.ROUND);

                float line1SX = centerX - 20;
                float line1SY = centerY - 30;
                float line1EX = centerX;
                float line1EY = centerY - 50;

                canvas.drawLine(line1SX, line1SY, line1EX, line1EY, linePaint);


                float line2SX = centerX;
                float line2SY = centerY - 50;
                float line2EX = centerX;
                float line2EY = centerY + 50;

                canvas.drawLine(line2SX, line2SY, line2EX, line2EY, linePaint);

                linePaint.setStyle(Paint.Style.FILL);
                linePaint.setStrokeWidth(4);
                canvas.drawCircle(centerX + 20, centerY + 50, 10, linePaint);
            } else {

                //画外圆
                linePaint.setStrokeWidth(2);
                linePaint.setStyle(Paint.Style.STROKE);

                Path path = new Path();
                path.moveTo(centerX, centerY + 100);
                float x1 = centerX + 100 * 0.551915024494f + changeY * 2;
                float y2 = centerY + 100 * 0.551915024494f + changeY * 2;
                if (x1 > centerX + circleIn) {
                    x1 = centerX + circleIn;
                }
                if (y2 > centerY + circleIn) {
                    y2 = centerY + circleIn;
                }
                path.cubicTo(x1, centerY + 100, centerX + 100, y2, centerX + 100, centerY);

                path.moveTo(centerX + 100, centerY);
//            path.cubicTo(centerX+100,centerY-100 * 0.551915024494f,centerX+100 * 0.551915024494f,centerY-100,centerX,centerY-100);
                float y1 = centerY - 100 * 0.551915024494f - changeY * 2;
                if (y1 < centerY - circleIn) {
                    y1 = centerY - circleIn;
                }
                float x2 = centerX + 100 * 0.551915024494f + changeY * 2;
                if (x2 > centerX + circleIn) {
                    x2 = centerX + circleIn;
                }
                path.cubicTo(centerX + 100, y1, x2, centerY - 100, centerX, centerY - 100);

                path.moveTo(centerX, centerY - 100);
//            path.cubicTo(centerX - 100 * 0.551915024494f, centerY - 100, centerX - 100, centerY - 100 * 0.551915024494f, centerX - 100, centerY);
                float x11 = centerX - 100 * 0.551915024494f - changeY * 2;
                float y21 = centerY - 100 * 0.551915024494f - changeY * 2;
                if (x11 < centerX - circleIn) {
                    x11 = centerX - circleIn;
                }
                if (y21 < centerY - circleIn) {
                    y21 = centerY - circleIn;
                }
                path.cubicTo(x11, centerY - 100, centerX - 100, y21, centerX - 100, centerY);

                path.moveTo(centerX - 100, centerY);
//            path.cubicTo(centerX - 100, centerY + 100 * 0.551915024494f, centerX - 100 * 0.551915024494f, centerY + 100, centerX, centerY + 100);
                float y11 = centerY + 100 * 0.551915024494f + changeY * 2;
                float x21 = centerX - 100 * 0.551915024494f - changeY * 2;
                if (y11 > centerY + circleIn) {
                    y11 = centerY + circleIn;
                }
                if (x21 < centerX - circleIn) {
                    x21 = centerX - circleIn;
                }
                path.cubicTo(centerX - 100, y11, x21, centerY + 100, centerX, centerY + 100);
                canvas.drawPath(path, linePaint);

                linePaint.setStyle(Paint.Style.FILL);
                linePaint.setStrokeWidth(14);
                linePaint.setStrokeCap(Paint.Cap.ROUND);

                float line1SX = centerX - 20 - changeY;
                float line1SY = centerY - 30 - changeY;
                float line1EX = centerX + changeY * 2;
                float line1EY = centerY - 50;
                if (line1SX < centerX - 40) {
                    line1SX = centerX - 40;
                }
                if (line1SY < centerY - 50) {
                    line1SY = centerY - 50;
                }
                if (line1EX > centerX + 40) {
                    line1EX = centerX + 40;
                }

                canvas.drawLine(line1SX, line1SY, line1EX, line1EY, linePaint);


                float line2SX = centerX - changeY * 2;
                float line2SY = centerY - 50 + changeY * 2;
                float line2EX = centerX + changeY * 2;
                float line2EY = centerY + 50 - changeY * 2;
                if (line2SX < centerX - 40) {
                    line2SX = centerX - 40;
                }
                if (line2SY > centerY) {
                    line2SY = centerY;
                }
                if (line2EX > centerX + 40) {
                    line2EX = centerX + 40;
                }
                if (line2EY < centerY) {
                    line2EY = centerY;
                }

                canvas.drawLine(line2SX, line2SY, line2EX, line2EY, linePaint);
                linePaint.setStyle(Paint.Style.FILL);
                linePaint.setStrokeWidth(14);
                linePaint.setStrokeCap(Paint.Cap.ROUND);
                float line3SX = centerX + changeY * 2;
                float line3SY = centerY + 50;
                float line3EX = centerX + 20 - changeY * 2;
                float line3EY = centerY + 50;
                if (line3SX > centerX + 40) {
                    line3SX = centerX + 40;
                }
                if (line3EX < centerX - 40) {
                    line3EX = centerX - 40;
                }
                canvas.drawLine(line3SX, line3SY, line3EX, line3EY, linePaint);
            }
        } else {
            float line2SX = centerX;
            float line2SY = centerY - 50;
            float line2EX = centerX;
            float line2EY = centerY + 50;

            canvas.drawLine(line2SX, line2SY, line2EX, line2EY, linePaint);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                pointY = event.getY();
                changeY = pointY - downY;
                changeY = changeY / 4;
                Log.i(TAG, "onTouchEvent: " + changeY);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mTimer.schedule(new TimerTask() {
//                            @Override
//                            public void run() {
//                                isStart = true;
//                                invalidate();
//
//                            }
//                        },100,100);
//                    }
//                }).start();
                break;
        }
        return true;
    }
}
