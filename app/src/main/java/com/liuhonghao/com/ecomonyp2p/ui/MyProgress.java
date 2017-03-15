package com.liuhonghao.com.ecomonyp2p.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.liuhonghao.com.ecomonyp2p.R;


/**
 * Created by 刘红豪 on 2017/3/13.
 */
/**
     * 1.什么是自定义属性？
     * 定义可以在布局文件的标签中使用的属性。
     * 2.为什么要自定义视图属性?
     * 这样就可以通过布局的方式给视图对象指定特定的属性值, 而不用动态的设置
     * 3.理解属性值的类型(format)
    * 1. reference 引用类型值 :@id/...
     * 2. color 颜色类型值  #ff00ff
    * 3. boolean 布尔类型值 true false
     * 4. dimension 尺寸类型值 dp / px /sp
    * 5. integer 整数类型值  weight  progress max
     * 6. float 小数类型值  0.5f
     * 7. string 字符串类型值  ""
     * 8. <enum> 枚举类型值 ：水平/垂直
    */
 /*
  * 进度条
   * 第一步 画圆
   * 第二步 画弧
   * 第三步 画文字
   *
   * */
public class MyProgress extends View {
    private  int sweepArc=60;
    private  int sweepColor=Color.RED;
    private int measuredHeight;
    private int measuredWidth;
    private int roundWidth = 10;//圆环的宽度
    private Paint paint;
    private int roundColor; //圆环的颜色
    public MyProgress(Context context) {
        super(context);//在我们new一个空间的时候就会调用该构造器
        init();
    }


    public MyProgress(Context context, AttributeSet attrs) {
        super(context, attrs);//在布局配置该控件的时候会调用该构造器
        init();
        //自定义属性
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.progress);
        roundColor = typedArray.getColor(R.styleable.progress_roundColor, Color.BLUE);
        sweepColor = typedArray.getColor(R.styleable.progress_sweepColor, Color.RED);
        sweepArc = typedArray.getInteger(R.styleable.progress_sweepArc, 0);
        typedArray.recycle();

    }
    private void init() {
         paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);//抗锯齿
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measuredHeight=getMeasuredHeight();//控件的高
        measuredWidth=getMeasuredWidth();//控件的宽
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画圆
        int cx=measuredWidth/2;
        int cy=measuredHeight/2;
        int  radious=cx-roundWidth/2;
        paint.setColor(roundColor);//设置圆环颜色
        paint.setStrokeWidth(roundWidth);//设置圆环的宽度
        canvas.drawCircle(cx,cy,radious,paint);
        //画狐
        /*
        * RectF里面存放的是float的类型
       * Rect里面存放的是int值
        *
      * */
        //存放了圆环中间矩形的左上顶点和右下顶点的坐标，能够根据坐标确定台面的狐
        RectF rectf=new RectF(roundWidth/2,roundWidth/2,measuredWidth-roundWidth/2,measuredHeight-roundWidth/2);
        paint.setColor(sweepColor);
        //第二个参数是起始角 第三个参数多少度，就是要转多少度,其中sweepArc为多少份
        canvas.drawArc(rectf,0,sweepArc * 360 / 100 ,false,paint);
        //画文字
        String text=sweepArc+"%";
        Rect rect = new Rect();
        paint.setStrokeWidth(0);
        paint.setColor(Color.YELLOW);
        paint.setTextSize(30);
        paint.getTextBounds(text, 0, text.length(), rect);
        float tx = measuredWidth / 2 - rect.width() / 2;
        float ty = measuredHeight / 2 + rect.height() / 2;
        canvas.drawText(text,tx,ty,paint);
    }
    public void setProgress(int progress) {
          sweepArc = progress;
        /*
     * invalidate 是在主线程强制刷新
     * postinvalidate 是在分线程强制刷新
     * */
        postInvalidate();
   }
}
