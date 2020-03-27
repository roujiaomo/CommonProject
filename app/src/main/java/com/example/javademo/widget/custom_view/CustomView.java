package com.example.javademo.widget.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.javademo.utils.DpAndPxUtil;

/**
 * 基本要素 : OnDraw()  Canvas Paint 坐标系 尺寸单位
 * <p>
 * View大致生命周期 :
 * <p>
 * 构造方法 ->  onFinishInflate() -> onMeasure() -> onSizeChanged() -> onDraw()
 */
public class CustomView extends View {
//    AdaptScreenUtils像素dp转换

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);  //Paint.ANTI_ALIAS_FLAG 参数不加一定有锯齿
    Path path = new Path();
    private static final float RADIUS = DpAndPxUtil.dp2px(100);
    PathMeasure pathMeasure;//测量我们已经画完的图形

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 当View中所有的子控件均被映射成xml后触发
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    /**
     * 在View放置到父容器时调用
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 在控件大小发生改变时调用。所以这里初始化会被调用一次
     * 一个View要经过多次绘制 ,当绘制的图形和上次绘制的不一样时, 该方法会被调用
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //做一个相交的图形 如果Direction选择同方向的话 相交部分为实心填充, 不同方向, 相交部分不填充
        path.addCircle(getWidth() / 2, getHeight() / 2, RADIUS, Path.Direction.CW); //最后一个参数是方向(顺逆时针)
//        path.addRect(getWidth() / 2 - RADIUS, getHeight() / 2,
//                getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS * 2, Path.Direction.CCW);
        path.addRect(getWidth() / 2 - RADIUS, getHeight() / 2,
                getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS * 2, Path.Direction.CW);
//        path.setFillType(Path.FillType.EVEN_ODD); 如果设置相交填充与否 使用这个属性里的
//        pathMeasure = new PathMeasure(path,false);// forceClose : 是否将我们画的图形封闭
//        pathMeasure.getLength();//我们画的所有图形的路径的长度, 比如周长
//        pathMeasure.getPosTan();//获取某个线的∠
    }

    /**
     * 绘制
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawLine(100, 100, 400, 400, paint);
//        canvas.drawCircle((float) getWidth() / 2, (float) getHeight() / 2, DpAndPxUtil.dp2px(150), paint);
        canvas.drawPath(path, paint);
//        path.setFillType(Path.FillType.EVEN_ODD); 如果设置相交填充与否 使用这个属性里的
//        pathMeasure = new PathMeasure(path,false);// forceClose : 是否将我们画的图形封闭
//        pathMeasure.getLength();//我们画的所有图形的路径的长度, 比如周长
//        pathMeasure.getPosTan();//获取某个线的∠
        //根据x,y轴 , 正角度是靠近Y轴的方向, 即顺时针为正角度

    }


}


