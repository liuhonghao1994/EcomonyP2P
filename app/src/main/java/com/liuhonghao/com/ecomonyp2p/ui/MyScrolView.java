package com.liuhonghao.com.ecomonyp2p.ui;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * Created by 刘红豪 on 2017/3/13.
 */

public class MyScrolView extends ScrollView {
    private int lastY;
    private View childView;

    public MyScrolView(Context context) {
        super(context);
    }

    public MyScrolView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    private Rect rect=new Rect();
    private boolean isAnimtionEnd = true;
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(getChildCount()==0 || !isAnimtionEnd){
            return super.onTouchEvent(ev);
        }
        int  eventy = (int) ev.getY();//相对于父布局y值
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //保存第一次触摸的点
                lastY = eventy;
                break;
            case MotionEvent.ACTION_MOVE:
                if (rect.isEmpty()) {
                    //记录最初的位置
                    rect.set(childView.getLeft(), childView.getTop(),
                            childView.getRight(), childView.getBottom());
                }
                if (isNeedMove()) {
                    //记录滑动后的距离
                    int dyy = eventy - lastY;
                    childView.layout(childView.getLeft(), childView.getTop() + dyy / 2, childView.getRight()
                            , childView.getBottom() + dyy / 2);
                }
                lastY = eventy;
                break;
            case MotionEvent.ACTION_UP:
                int translate = childView.getBottom() - rect.bottom;
                TranslateAnimation animation = new TranslateAnimation(0, 0, 0, -translate);
                animation.setDuration(200);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        isAnimtionEnd = false;
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        childView.clearAnimation();
                        childView.layout(rect.left,rect.top,rect.right,rect.bottom);
                        rect.setEmpty();
                        isAnimtionEnd = true;
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
               childView.startAnimation(animation);
               break;
       }
        return super.onTouchEvent(ev);

    }

    private boolean isNeedMove() {
        //滑动的高度
        int scrollheight = this.getMeasuredHeight();
        //孩子的高度
        int childheight = childView.getMeasuredHeight();
        int dy = childheight - scrollheight;
        //拿到滑动距离,向下滑变小，向上变大getScrollY是偏移量。
        int scrollY = getScrollY();
        if(scrollY<=0 || scrollY>=dy){
            return true;
        }
        return false;
    }

    //布局杂家完成调用
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //判断如果里面有包裹孩子，得到孩子对象
        if(getChildCount()>0) {
             childView = getChildAt(0);
        }
    }
      /*
  * 拦截事件
  * 返回true 拦截
   * 返回false 不拦截
   *
 * */
      private int downy,lastx,downx;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
       boolean isOnIntercept=false;
        int eventx = (int) ev.getX();
        int eventy = (int) ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastY=downy-eventy;
                lastx = downx = eventx;

                break;
            case MotionEvent.ACTION_MOVE:
                int absx = Math.abs(eventx - downx);
                int absy = Math.abs(eventy - downy);
                if (absy > absx && absy >= 20){
                    isOnIntercept = true;
                }
                lastY = eventy;
                lastx = eventx;
                break;
        }
        return isOnIntercept;
    }
}
