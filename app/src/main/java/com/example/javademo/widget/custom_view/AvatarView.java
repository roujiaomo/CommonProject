package com.example.javademo.widget.custom_view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.javademo.R;
import com.example.javademo.utils.DpAndPxUtil;

/**
 * 头像(圆形)
 * 画一个圆覆盖到矩形图片上 (XferMode使用)
 */
public class AvatarView extends View {

    Bitmap avatar; //头像bitmap
    private static final float IMAGE_WIDTH = DpAndPxUtil.dp2px(300);//图宽度
    private static final float BORDER_WIDTH = DpAndPxUtil.dp2px(10);//环的宽度(内外圆半径差)
    private static final float PADDING = DpAndPxUtil.dp2px(20);//间距


    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);//SRC_IN : 绘制的是相交的地方
    RectF cut = new RectF();//要单独切出来的矩形(内圆)
    RectF border = new RectF();//要单独切出来的矩形(外圆)

    {
        avatar = getAvatar(getResources(), (int) IMAGE_WIDTH);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        cut.set(PADDING, PADDING, PADDING + IMAGE_WIDTH, PADDING + IMAGE_WIDTH);
        border.set(PADDING - BORDER_WIDTH, PADDING - BORDER_WIDTH,
                PADDING + IMAGE_WIDTH + BORDER_WIDTH, PADDING + IMAGE_WIDTH + BORDER_WIDTH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawOval(border, paint);//画大圆
        int saved = canvas.saveLayer(cut, paint); //bounds: 要单独切出来的区域
        canvas.drawOval(cut, paint);//画小圆
        //这里需要离屏缓冲 离屏缓冲：单独的一个绘制View的区域
        paint.setXfermode(xfermode);
        canvas.drawBitmap(avatar, PADDING, PADDING, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(saved);
    }

    public AvatarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Bitmap getAvatar(Resources res, int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, R.drawable.ic_logo, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(res, R.drawable.ic_logo, options);
    }
}
