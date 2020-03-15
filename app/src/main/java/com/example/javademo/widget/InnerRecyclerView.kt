package com.example.javademo.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs


/**
 * @descripe 解决recyclerview与其他滑动控件的冲突，同向，不同向都可以
 *  在{ACTION_MOVE}判断手势方向，如果是上下滑动，则判断recyclerView能不能上下滑动，能的话拦截事件，不能的话不做拦截；左右滑动也一样。
 *  1. requestDisallowInterceptTouchEvent(true)  //通知父控件不要拦截
 *  2. canScrollHorizontally() 判断view是否可以横向滑动
 *  3. canScrollVertically()   判断view是否可以上下滑动
 */


class InnerRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    var startX: Int = 0
    var startY: Int = 0

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = ev.x.toInt()
                startY = ev.y.toInt()
                //通知父控件不要拦截
                parent.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_MOVE -> {
                val endX = ev.x.toInt()
                val endY = ev.y.toInt()
                val distanceX = abs(startX - endX)
                val distanceY = abs(startY - endY)
                if (distanceX > distanceY) {
                    //手势为横向滑动
                    parent.requestDisallowInterceptTouchEvent(canScrollHorizontally(startX - endX))
                } else {
                    parent.requestDisallowInterceptTouchEvent(canScrollVertically(startY - endY))
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                parent.requestDisallowInterceptTouchEvent(false)
            }
        }

        return super.dispatchTouchEvent(ev)
    }
}