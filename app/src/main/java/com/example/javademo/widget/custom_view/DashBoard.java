package com.example.javademo.widget.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.javademo.utils.DpAndPxUtil;

/**
 * 时钟
 */
public class DashBoard extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);  //Paint.ANTI_ALIAS_FLAG 参数不加一定有锯齿
    Path dash = new Path(); //刻度的path
    Path path; //弧线的path
    PathMeasure pathMeasure;//计算弧线周长
    private static final float RADIUS = DpAndPxUtil.dp2px(100); //圆半径
    private static final float ANGLE = 120;//初始角度(理论上扇形是由圆缺口后构成)
    private static final float LENGTH = DpAndPxUtil.dp2px(80); //指针线的长度
    PathDashPathEffect pathDashPathEffect; //虚线

    //初始化可使用静态代码块 防止构造方法多的时候 需要逐个init
    {
        paint.setStyle(Paint.Style.STROKE);//设置非填充
        paint.setStrokeWidth(DpAndPxUtil.dp2px(3));//设置线宽
        //在圆弧上运动, 往哪个方向运动, 则运动点的切线为X轴的正方向
        //画笔沿着圆弧路径每隔一段间距画虚线, 每隔虚线点是一个矩形
        dash.addRect(0, 0, DpAndPxUtil.dp2px(2), DpAndPxUtil.dp2px(8), Path.Direction.CCW);//顺时针
        path = new Path();
    }

    /**
     * 绘制
     *
     * @param canvas 使用canvas绘制
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画圆弧(原图形)
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS,
                90 + ANGLE / 2, 360 - ANGLE, false, paint); //开始角度与向上滑过的角度

        //设置绘画虚线特效
        paint.setPathEffect(pathDashPathEffect);

        //画刻度(画两遍)
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS,
                90 + ANGLE / 2, 360 - ANGLE, false, paint); //开始角度与向上滑过的角度

        paint.setPathEffect(null);//虚线画完了 要清空

        //画指针 线(起点为圆心, 终点使用三角函数计算)
        canvas.drawLine(getWidth() / 2, getHeight() / 2,
                getWidth() / 2 + (float)Math.cos(Math.toRadians(getAngelForMark(0))*LENGTH),
                getHeight() / 2 + (float)Math.sin(Math.toRadians(getAngelForMark(0))*LENGTH),
                paint);
    }

    /**
     * 一个View要经过多次绘制 ,当绘制的图形和上次绘制的不一样时, 该方法会被调用
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //第一次画完圆弧后
        //计算刻度
        path.addArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS,
                90 + ANGLE / 2, 360 - ANGLE);
        pathMeasure = new PathMeasure(path, false);
        pathMeasure.getLength();//圆弧周长
        //计算刻度间距, 这里设置间隔周长的1/20
        // 直接 pathMeasure.getLength()/20 会发现少画一个刻度 因为周长只够画20个刻度 而我们分成20份需要21个刻度
        //这里设置周长 - 一个矩形的宽度,即留给最后一个矩形一个宽度的距离画出来
        pathDashPathEffect = new PathDashPathEffect(dash, (pathMeasure.getLength() - DpAndPxUtil.dp2px(2)) / 20, 0,
                PathDashPathEffect.Style.ROTATE); //advance: 间距 phase: 起始偏移量
    }

    /**
     * 返回当前指针角度
     *
     * @param mark 当前第几个刻度
     * @return
     */
    private float getAngelForMark(int mark) {
        return 90 + ANGLE / 2  //初始角度
                + (360 - ANGLE) / 20 * mark ;//每个刻度长度*当前是第几个刻度
    }


    public DashBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
