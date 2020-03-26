package com.example.javademo.widget.eventdispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * 本质 : 事件过来逐层递归  dispatchTouchEvent() -> onInterceptTouchEvent() -> onTouchEvent()
 * <p>
 * 分发 : 事件到达最底层的ViewGroup , 默认调用dispatchTouchEvent 在 super.dispatchTouchEvent()里 调用自身onInterceptTouchEvent()
 * <p>
 * 拦截 : 如果拦截 , 调用自己的 onTouchEvent()(如果未设置onTouchListener) ,
 * 如果未拦截 ,
 * 有上层布局且事件发生在上层布局里, 则调用上层ViewGroup/View的 dispatchTouchEvent(),
 * 该层 dispatchTouchEvent()返回true, 事件在该层分发完毕.
 * 无子布局接收事件, 则super.dispatchTouchEvent(ev) ,在ViewGroup的dispatchTouchEvent(ev)里调用super.dispatchTouchEvent(ev),
 * 即把该层的ViewGroup当成最顶层View处理, 若无设置onClickListener/onTouchListener, 调用自身的OnTouchEvent()方法,完成事件流
 * <p>
 * 消费 : onTouchEvent()
 * 若外界设置其他消费事件 onTouchListener / onClickListener 优先消费顺序 onTouch > onTouchEvent > onClick
 * (OnTouchEvent() -> PerformClick() -> onClick())
 *
 * 消费事件返回: 在消费层的onTouchEvent()里返回了True, 逐层调用下层的dispatchTouchEvent(ev)方法,完成事件流
 *
 * <p>
 * 无消费事件返回 :
 * 若每层都没有拦截消费 , 到最上层ViewGroup/View的时候 , 会调用该层ViewGroup/View的onTouchEvent(),
 * 并逐层调用下层的onTouchEvent()完成事件流
 *
 * 完成事件流: 最后都是返回到Activity的 dispatchTouchEvent(ev) 或者 onTouchEvent()里
 */
public class OutViewGroup extends ViewGroup {
    private static final String TAG = "qqqqqqqqqqqqq";

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//. // 仅贴出关键代码
//
//        // 重点分析1：ViewGroup每次事件分发时，都需调用onInterceptTouchEvent()询问是否拦截事件
//        if (disallowIntercept || !onInterceptTouchEvent(ev)) {
//
//            // 判断值1：disallowIntercept = 是否禁用事件拦截的功能(默认是false)，可通过调用requestDisallowInterceptTouchEvent（）修改
//            // 判断值2： !onInterceptTouchEvent(ev) = 对onInterceptTouchEvent()返回值取反
//            // a. 若在onInterceptTouchEvent()中返回false（即不拦截事件），就会让第二个值为true，从而进入到条件判断的内部
//            // b. 若在onInterceptTouchEvent()中返回true（即拦截事件），就会让第二个值为false，从而跳出了这个条件判断
//            // c. 关于onInterceptTouchEvent() ->>分析1
//
//            ev.setAction(MotionEvent.ACTION_DOWN);
//            final int scrolledXInt = (int) scrolledXFloat;
//            final int scrolledYInt = (int) scrolledYFloat;
//            final View[] children = mChildren;
//            final int count = mChildrenCount;
//
//            // 重点分析2
//            // 通过for循环，遍历了当前ViewGroup下的所有子View
//            for (int i = count - 1; i >= 0; i--) {
//                final View child = children[i];
//                if ((child.mViewFlags & VISIBILITY_MASK) == VISIBLE
//                        || child.getAnimation() != null) {
//                    child.getHitRect(frame);
//
//                    // 判断当前遍历的View是不是正在点击的View，从而找到当前被点击的View
//                    // 若是，则进入条件判断内部
//                    if (frame.contains(scrolledXInt, scrolledYInt)) {
//                        final float xc = scrolledXFloat - child.mLeft;
//                        final float yc = scrolledYFloat - child.mTop;
//                        ev.setLocation(xc, yc);
//                        child.mPrivateFlags &= ~CANCEL_NEXT_UP_EVENT;
//
//                        // 条件判断的内部调用了该View的dispatchTouchEvent()
//                        // 即 实现了点击事件从ViewGroup到子View的传递（具体请看下面的View事件分发机制）
//                        if (child.dispatchTouchEvent(ev))  {
//
//                            mMotionTarget = child;
//                            return true;
//                            // 调用子View的dispatchTouchEvent后是有返回值的
//                            // 若该控件可点击，那么点击时，dispatchTouchEvent的返回值必定是true，因此会导致条件判断成立
//                            // 于是给ViewGroup的dispatchTouchEvent（）直接返回了true，即直接跳出
//                            // 即把ViewGroup的点击事件拦截掉
//
//                        }
//                    }
//                }
//            }
//        }
//    }
//    boolean isUpOrCancel = (action == MotionEvent.ACTION_UP) ||
//            (action == MotionEvent.ACTION_CANCEL);
//            if (isUpOrCancel) {
//        mGroupFlags &= ~FLAG_DISALLOW_INTERCEPT;
//    }
//    final View target = mMotionTarget;
//
//    // 重点分析3
//    // 若点击的是空白处（即无任何View接收事件） / 拦截事件（手动复写onInterceptTouchEvent（），从而让其返回true）
//        if (target == null) {
//        ev.setLocation(xf, yf);
//        if ((mPrivateFlags & CANCEL_NEXT_UP_EVENT) != 0) {
//            ev.setAction(MotionEvent.ACTION_CANCEL);
//            mPrivateFlags &= ~CANCEL_NEXT_UP_EVENT;
//        }
//
//        return super.dispatchTouchEvent(ev);
//        // 调用ViewGroup父类的dispatchTouchEvent()，即View.dispatchTouchEvent()
//        // 因此会执行ViewGroup的onTouch() ->> onTouchEvent() ->> performClick（） ->> onClick()，即自己处理该事件，事件不会往下传递（具体请参考View事件的分发机制中的View.dispatchTouchEvent（））
//        // 此处需与上面区别：子View的dispatchTouchEvent（）
//    }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: //rv默认不拦截 (return super.onInterceptTouchEvent(ev) 为 false)
                Log.d(TAG, "外层ViewGroup onTouchEvent: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE: //rv默认拦截 (return super.onInterceptTouchEvent(ev) 为 true)
                Log.d(TAG, "外层ViewGroup onTouchEvent: ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "外层ViewGroup onTouchEvent: ACTION_CANCEL");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "外层ViewGroup onTouchEvent: ACTION_UP");
                break;
        }
//        Log.d(TAG, "外层ViewGroup onTouchEvent返回值: "+  super.onTouchEvent(ev));
        return super.onTouchEvent(ev);
    }

    /**
     * 一旦拦截 将拦截整个事件序列
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: //rv默认不拦截 (return super.onInterceptTouchEvent(ev) 为 false)
                Log.d(TAG, "外层ViewGroup onInterceptTouchEvent: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE: //rv默认拦截 (return super.onInterceptTouchEvent(ev) 为 true)
                Log.d(TAG, "外层ViewGroup onInterceptTouchEvent: ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "外层ViewGroup onInterceptTouchEvent: ACTION_CANCEL");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "外层ViewGroup onInterceptTouchEvent: ACTION_UP");
                break;
        }
//        Log.d(TAG, "外层ViewGroup onInterceptTouchEvent返回值: "+  super.onInterceptTouchEvent(ev));
        return super.onInterceptTouchEvent(ev);
    }

    public OutViewGroup(Context context) {
        super(context);
    }

    public OutViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OutViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 要求所有的孩子测量自己的大小，然后根据这些孩子的大小完成自己的尺寸测量
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        //测量并保存layout的宽高(使用getDefaultSize时，wrap_content和match_perent都是填充屏幕)
        //稍后会重新写这个方法，能达到wrap_content的效果
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
                getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        final int count = getChildCount();
        int childMeasureWidth = 0;
        int childMeasureHeight = 0;
        int layoutWidth = 0;    // 容器已经占据的宽度
        int layoutHeight = 0;   // 容器已经占据的宽度
        int maxChildHeight = 0; //一行中子控件最高的高度，用于决定下一行高度应该在目前基础上累加多少
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            //注意此处不能使用getWidth和getHeight，这两个方法必须在onLayout执行完，才能正确获取宽高
            childMeasureWidth = child.getMeasuredWidth();
            childMeasureHeight = child.getMeasuredHeight();
            if (layoutWidth < getWidth()) {
                //如果一行没有排满，继续往右排列
                left = layoutWidth;
                right = left + childMeasureWidth;
                top = layoutHeight;
                bottom = top + childMeasureHeight;
            } else {
                //排满后换行
                layoutWidth = 0;
                layoutHeight += maxChildHeight;
                maxChildHeight = 0;

                left = layoutWidth;
                right = left + childMeasureWidth;
                top = layoutHeight;
                bottom = top + childMeasureHeight;
            }

            layoutWidth += childMeasureWidth;  //宽度累加
            if (childMeasureHeight > maxChildHeight) {
                maxChildHeight = childMeasureHeight;
            }

            //确定子控件的位置，四个参数分别代表（左上右下）点的坐标值
            child.layout(left, top, right, bottom);
        }

    }
}
