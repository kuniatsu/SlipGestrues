package com.example.ux21a.slipgestrues;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.example.ux21a.slipgestrues.manualLine.SampleLine;

import java.util.ArrayList;

/**
 メニューないの書き方マニュアルのCanvasView
 */


class ManualCanvasView extends View {

    private Paint paint;
    private Paint paint2;
    private Path path;

    //    //内側の四角
    private Paint inBoxPaint;
    private Rect inBoxRext;
    //    //外側の四角
    private Paint outBoxPaint;
    private Rect outBoxRext;


    //線を描く
    private Paint sampleLinePaint;
    private Rect sampleLineRext;


    //サンプル線描画用のArrayList
    private ArrayList<Float> lineList;





    public ManualCanvasView(Context context,ArrayList<Float> lineList) {
        super(context); path = new Path();
        this.lineList = lineList;

        paint = new Paint();
        paint.setColor(0xFF008800);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(10);

        paint2 = new Paint();
        paint2.setColor(Color.WHITE);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeJoin(Paint.Join.ROUND);
        paint2.setStrokeCap(Paint.Cap.ROUND);
        paint2.setStrokeWidth(10);



        //基準線
        //in
        inBoxRext = new Rect();
        inBoxPaint = new Paint();
        inBoxPaint.setColor(0x11000000);
        inBoxPaint.setStyle(Paint.Style.STROKE);
        inBoxPaint.setStrokeJoin(Paint.Join.ROUND);
        inBoxPaint.setStrokeCap(Paint.Cap.ROUND);
        inBoxPaint.setStrokeWidth(10);

        //out
        outBoxRext = new Rect();
        outBoxPaint = new Paint();
        outBoxPaint.setColor(0x11000000);
        outBoxPaint.setStyle(Paint.Style.STROKE);
        outBoxPaint.setStrokeJoin(Paint.Join.ROUND);
        outBoxPaint.setStrokeCap(Paint.Cap.ROUND);
        outBoxPaint.setStrokeWidth(10);

        //線
        sampleLineRext = new Rect();
        sampleLinePaint = new Paint();

        sampleLinePaint.setStrokeWidth(10);//太さ
        sampleLinePaint.setColor(Color.argb(255, 0, 0, 0));//色



    }

    @Override protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, paint);
        int wid = getWidth();
        int hei = getHeight();


        outBoxPaint.setStyle(Paint.Style.STROKE);
        outBoxPaint.setAntiAlias(true);

        //円の書き込み
        canvas.drawCircle((wid/2), (hei/2), Parameter.inLine, outBoxPaint);//引数:中心点xのいち：中心点ｙの位置
        canvas.drawCircle((wid / 2), (hei / 2), Parameter.outLine, outBoxPaint);//引数:中心点xのいち：中心点ｙの位置



        //直線か曲線か
        if((lineList.size())==4){
            //直線
            Log.d("ManualCanvasView", "直線情報");


            float kaishiX = lineList.get(0);
            float kaishiY = lineList.get(1);
            float owariX = lineList.get(2);
            float owariY = lineList.get(3);
            float habaX = owariX - kaishiX;
            float habaY = owariY - kaishiY;
            int centerX=wid/2;
            int centerY=hei/2;


            canvas.drawLine((centerX - habaX), (centerY - habaY), (centerX + habaX), (centerY + habaY), sampleLinePaint);//線の書込、数字は描画位置
        }else if((lineList.size())==8){
            //をわ
            Log.d("ManualCanvasView", "２せん情報");
            float kaishiX = lineList.get(0);
            float kaishiY = lineList.get(1);
            float owariX = lineList.get(2);
            float owariY = lineList.get(3);
            float habaX = owariX - kaishiX;
            float habaY = owariY - kaishiY;
            int centerX=wid/2;
            int centerY=hei/2;


            canvas.drawLine((centerX - habaX), (centerY), (centerX), (centerY + habaY), sampleLinePaint);//線の書込、数字は描画位置
            canvas.drawLine((centerX + habaX), (centerY), (centerX), (centerY + habaY), sampleLinePaint);//線の書込、数字は描画位置
        }else if((lineList.size())==9){
            //句読点

            String len = "middle";
            if(lineList.get(4)==3)len = "long";

            final float eoLine = Parameter.getEoLine(len);
            SampleLine s4 = new SampleLine(new ArrayList<Float>(){{add((float)0);add((float)0);add(eoLine);add((float)0);}});

            Log.d("ManualCanvasView", "句読点");
            float kaishiX = s4.slist.get(0);
            float kaishiY = s4.slist.get(1);
            float owariX = s4.slist.get(2);
            float owariY = s4.slist.get(3);
            float habaX = owariX - kaishiX;
            float habaY = owariY - kaishiY;

            int centerX=wid/2;
            int centerY=hei/2;


            float maruBool1 = lineList.get(0);
            float maruBool2 = lineList.get(1);
            float enhaba = Parameter.outLine/2;


            //○の描画
            RectF oval8 = new RectF((centerX - 5), (centerY - 5), (centerX + 5), (centerY + 5));//1

            inBoxPaint.setColor(Color.BLACK);
            canvas.drawArc(oval8, 0, 360, false, inBoxPaint);//○1


        }else if((lineList.size())==12){
            //12は丸と線の組み合わせ
            //句読点小文字用
            //ハイフン
            Log.d("ManualCanvasView", "丸と線");


            String len = "middle";
            if(lineList.get(4)==3)len = "long";

            final float eoLine = Parameter.getEoLine(len);
            SampleLine s4 = new SampleLine(new ArrayList<Float>(){{add((float)0);add((float)0);add(eoLine);add((float)0);}});


            float kaishiX = s4.slist.get(0);
            float kaishiY = s4.slist.get(1);
            float owariX = s4.slist.get(2);
            float owariY = s4.slist.get(3);
            float habaX = owariX - kaishiX;
            float habaY = owariY - kaishiY;
            int centerX=wid/2;
            int centerY=hei/2;

            //線の描画
            canvas.drawLine((centerX - habaX), (centerY - habaY), (centerX + habaX), (centerY + habaY), sampleLinePaint);//線の書込、数字は描画位置

            float maruBool1 = lineList.get(0);
            float maruBool2 = lineList.get(1);
            float enhaba = Parameter.inLine/2;


            //○の描画
            RectF oval8 = new RectF((centerX - habaX - enhaba*2), (centerY-enhaba), (centerX - habaX), (centerY+enhaba));//1
            RectF oval9 = new RectF((centerX + habaX), (centerY-enhaba), (centerX + habaX + enhaba*2), (centerY+enhaba));//2


            inBoxPaint.setColor(Color.BLACK);
            if(maruBool1==1){canvas.drawArc(oval8, 0, 360, false, inBoxPaint);}//○1
            if(maruBool2==1){canvas.drawArc(oval9, 0, 360, false, inBoxPaint);}//○2

        }else if((lineList.size())==13){
            //!?用
            //12は丸と線の組み合わせ
            //大文字小文字用
            Log.d("ManualCanvasView", "丸と線");
            final float eoLine = Parameter.getEoLine("long");
            SampleLine s4 = new SampleLine(new ArrayList<Float>(){{add((float)0);add((float)0);add((float)0);add(eoLine);}});


            float kaishiX = s4.slist.get(0);
            float kaishiY = s4.slist.get(1);
            float owariX = s4.slist.get(2);
            float owariY = s4.slist.get(3);
            float habaX = owariX - kaishiX;
            float habaY = owariY - kaishiY;
            int centerX=wid/2;
            int centerY=hei/2;

            //線の描画
            canvas.drawLine((centerX - habaX), (centerY - habaY), (centerX + habaX), (centerY + habaY), sampleLinePaint);//線の書込、数字は描画位置

            float maruBool1 = lineList.get(0);
            float maruBool2 = lineList.get(1);
            float enhaba = Parameter.outLine/2;


            //○の描画
            RectF oval8 = new RectF((centerX - enhaba), (centerY - habaY -enhaba*2), (centerX + enhaba), (centerY - habaY));//1
            RectF oval9 = new RectF((centerX - enhaba), (centerY + habaY), (centerX + enhaba), (centerY + habaY +enhaba*2));//2


            inBoxPaint.setColor(Color.BLACK);
            if(maruBool1==2){canvas.drawArc(oval8, 0, 360, false, inBoxPaint);}//○1
            if(maruBool2==2){canvas.drawArc(oval9, 0, 360, false, inBoxPaint);}//○2



        }else{
            //0・１・２・３の場合
            //曲線
            Log.d("ManualCanvasView", "曲線情報");

            // 塗りつぶしなしで描画
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);
            paint.setColor(Color.argb(255, 0, 0, 0));

            float s = lineList.get(0);
            float w = wid / 2;
            float h = hei / 2;
            RectF oval8 = new RectF((w-s), (h-s), (w+s), (h+s));
            canvas.drawArc(oval8, lineList.get(1), lineList.get(2), false, paint);

        }

    }




//これを書くとメニュー内のキャンバスで文字が書けるようになる。
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        float x = event.getX();
//        float y = event.getY();
//        switch(event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                path.moveTo(x, y);
//                invalidate();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                path.lineTo(x, y);
//                invalidate();
//                break;
//            case MotionEvent.ACTION_UP:
//                path.lineTo(x, y);
//                invalidate();
//                break;
//        }
//        return true;
//    }

}
