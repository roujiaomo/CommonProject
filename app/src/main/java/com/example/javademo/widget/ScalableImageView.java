package com.example.javademo.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.example.javademo.R;

public class ScalableImageView extends AppCompatImageView {

    private static final float IMAGE_WIDTH = AdaptScreenUtils.pt2Px(300);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;

    public ScalableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        bitmap = getAvatar(getResources(), (int) IMAGE_WIDTH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, paint);
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
