package com.example.javademo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * ViewGroup事件分发
 *  0-> ACTION_DOWN  1-> ACTION_UP  2-> ACTION_MOVE  3-> ACTION_CANCEL
 */
public class EventViewGroup extends ViewGroup {

    private static final String TAG = "EventViewGroup";
    // ViewGroup每次事件分发时，都需调用onInterceptTouchEvent()询问是否拦截事件


    /**
     * Activity将事件传递过来
     * 首先第一层调用 onInterceptTouchEvent 是否拦截事件(默认返回false/)
     * (一)
     * 如果onInterceptTouchEvent返回false , 即未拦截, 则遍历该ViewGroup下所有View , 逐个调用子View的View.dispatchTouchEvent
     * 如果有一个子View的View.dispatchTouchEvent返回了True , 则代表被事件完成传递 , 事件在子View开始处理
     * (二)
     * 如果onInterceptTouchEvent返回true  , 即自己拦截了 ,则调用ViewGroup父类的 dispatchTouchEvent方法
     *
     * 然后如果mOnTouchListener被设置 , 则调用自身的 ViewGroup.onTouch (setOnTouchListener里的回调方法)
     * 当 ViewGroup.onTouch返回true时,即事件已被消费,最后 dispatchTouchEvent 返回true 事件结束
     * 当 ViewGroup.onTouch返回false时 , 调用onTouchEvent (->  performClick() -> onClick() )
     *  如果未设置 mOnTouchListener时 , 直接调用 onTouchEvent
     *
     *  事件处理优先级 : onTouch() > onTouchEvent() > onClick()
     *  例如 : 如果 onTouchEvent()返回 false , 即使外部设置了mOnclickListener ,该点击事件(onClick()方法里)也不会响应
     *
     * 总述 ：该方法为分发 , 如果该方法最终返回true 则代表事件在该层级分发完毕
     * 如果返回false , 则代表未分发该事件 , 事件在该层处理 , 或返回上层
     *
     * @param event
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }


    /**
     * 如果ViewGroup设置了 click事件 setOnclickListener ,则返回true ,否则返回false
     * @return
     */
    @Override
    public boolean performClick() {
        return super.performClick();
    }

    /**
     * 仅ViewGroup中有该方法 判断ViewGroup是否拦截该事件
     * ViewGroup的 dispatchTouchEvent 默认调用 onInterceptTouchEvent
     * 是否拦截事件  默认返回false
     * 返回false 向下传递 返回true拦截事件
     * @param event
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return super.onInterceptTouchEvent(event);
    }

    /**
     * 返回false事件未被消费
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: ");
        return super.onTouchEvent(event);
    }

    public EventViewGroup(Context context) {
        super(context);
    }

    public EventViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EventViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
