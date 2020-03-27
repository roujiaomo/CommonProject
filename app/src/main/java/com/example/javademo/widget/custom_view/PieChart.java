package com.example.javademo.widget.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.javademo.utils.DpAndPxUtil;

/**
 * 饼状图(多个扇形公用一个外切矩形)
 */
public class PieChart extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    RectF bounds = new RectF();//扇形的公用矩形,作为画扇形的参数传入
    private static final float RADIUS = DpAndPxUtil.dp2px(100); //圆半径
    int[] colors = {Color.parseColor("#448AFF"), Color.parseColor("#D81B60"),
            Color.parseColor("#43A047"), Color.parseColor("#FDD835")};
    int[] angles = {60, 100, 120, 80};

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //设置矩形
        bounds.set(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int currentAngle = 0;
        for(int i=0; i<colors.length; i++){
            paint.setColor(colors[i]);
            canvas.drawArc(bounds, currentAngle, angles[i], true, paint);
            currentAngle += angles[i];
        }
    }

    public PieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
