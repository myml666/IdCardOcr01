package com.itfitness.idcardocr01.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @ProjectName: IdCardOcr01
 * @Package: com.itfitness.idcardocr01.widget
 * @ClassName: MaskView
 * @Description: java类作用描述 ：身份证遮罩
 * @Author: 作者名：lml
 * @CreateDate: 2019/5/15 14:20
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/5/15 14:20
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */

public class MaskView extends View {
    private Paint mPaint;
    private Paint mPaint_Text;
    private RectF mCardRectF;
    private float mCardWidth;
    private float mCardHeight;

    public MaskView(Context context) {
        this(context,null);
    }

    public MaskView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MaskView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE,null);//关闭硬件加速
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);

        mPaint_Text = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint_Text.setTextSize(60);
        mPaint_Text.setColor(Color.WHITE);
        mPaint_Text.setStrokeWidth(3);
        mPaint_Text.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //由于身份证的长为85.6mm，身份证的宽为54mm因此我们可以得出身份证的长宽比
        float ratio = (float) (85.6 / 54);
        //这里我们设置显示身份证区域的宽为整个控件宽度的2/3
        mCardWidth = w / 3 * 2;
        //因此可以得出身份证区域的高度为
        mCardHeight = mCardWidth * ratio;
        //接下来可以算出身份证区域的矩形位置为
        mCardRectF = new RectF((w - mCardWidth) / 2, (h - mCardHeight) / 2, w - (w - mCardWidth) / 2, h - (h - mCardHeight) / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();//保存画布
        canvas.drawColor(Color.parseColor("#aa000000"));//填充黑色半透明
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));//设置混合模式为清除
        canvas.drawRect(mCardRectF,mPaint);
        mPaint.setXfermode(null);//清除混合模式
        canvas.restore();//恢复画布
        canvas.translate(getWidth()/2,getHeight()/2);
        canvas.rotate(90);
        canvas.drawText("请扫描本人身份证人像面",0,-mCardWidth/2-20,mPaint_Text);
    }
}
