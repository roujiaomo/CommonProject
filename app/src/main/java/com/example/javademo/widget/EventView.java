package com.example.javademo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * View事件分发(区分于ViewGroup)
 * 手触碰屏幕到离开的一整套动作是一套事件序列 包含 :
 *  *  0-> ACTION_DOWN  1-> ACTION_UP  2-> ACTION_MOVE 或
 *  *  0-> ACTION_DOWN  1-> ACTION_UP  3-> ACTION_CANCEL(非人为的结束)
 *
 * 每种动作都会触发一次 dispatchTouchEvent() 方法 即每个动作都是一个事件 都会进行分发
 *
 * View被点击时 , 首先判断View setOnTouchListener里onTouch的返回值
 * 返回false代表未被消费 , 事件向下传递
 * 调用该View的 dispatchTouchEvent() ->  performClick() -> onClick()
 */
public class EventView extends View {

    /**
     * 父布局(ViewGroup)将事件传递过来
     * 当给给该控件设置
     * View.setOnTouchListener
     * 并且 setOnTouchListener 返回true
     * 并且 View.setEnable(true) (默认是true)
     * 则子View的dispatchTouchEvent方法返回true
     * 即父ViewGroup的dispatchTouchEvent遍历子View跳出循环 ,父ViewGroup的dispatchTouchEvent也返回true
     * 如果以上三项条件有任意不符合 ,则调用View的onTouchEvent方法()
     *
     *  OnTouchListener的onTouch()方法优先级高于onTouchEvent() , 因为方便在界面(Activity)上控制
     *  事件处理优先级 : onTouch() > onTouchEvent() > onClick()
     *  例如 : 如果 onTouchEvent()返回 false , 即使外部设置了mOnclickListener ,该点击事件(onClick()方法里)也不会响应
     *
     * 总述 : 如果该View设置了触摸监听 , 并且触摸监听返回true ,且View enable 为true ,则拦截下事件处理
     *
     * @param event
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        //        if (mOnTouchListener != null && (mViewFlags & ENABLED_MASK) == ENABLED &&
//                mOnTouchListener.onTouch(this, event)) {
//            return true;
//        }
//        return onTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }


    /**
     * 处理触摸点击事件的方法
     *
     * 如果该View可点击(短按长按) , 则一定返回true ,否则返回false
     * 即如果 dispatchTouchEvent的三种条件不符合的情况下 , 走这里判断
     * 比如  该view 未设置 setOnTouchListener 但是设置了 setOnClickListener
     * 此时如果该View是enable的 即该子View成功接收事件 即 dispatchTouchEvent 返回true
     * 否则如果 该View的enable为false.则在dispatchTouchEvent返回false  ,将事件返回给上级
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }


    /**
     * 如果该View设置了 click事件 setOnclickListener ,则返回true ,否则返回false
     * @return
     */
    @Override
    public boolean performClick() {
        return super.performClick();
    }

    public EventView(Context context) {
        super(context);
    }

    public EventView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EventView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}