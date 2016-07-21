package com.example.ux21a.slipgestrues;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import java.util.ArrayList;

/**
 * キャンバスイベントなどを定義
 */


public class CanvasView extends View implements      GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {
    //フィールド
    private Paint paint;
    private Path path;
    private ArrayList<Float> alx = new ArrayList<Float>();
    private ArrayList<Float> aly = new ArrayList<Float>();

    //ジェスチャ用のもの
    private GestureDetector ges;
    private boolean gesFlg;
    private ScaleGestureDetector gesDetect = null;//ピンチイベント用
    private float scale;//ピンチイベント用


    //    //内側の四角
    private Paint inBoxPaint;
    private Rect inBoxRext;
    //    //外側の四角
    private Paint outBoxPaint;
    private Rect outBoxRext;




    //コンストラクター
    public CanvasView(Context context) {
        super(context);
    }
    public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        path = new Path();
        paint = new Paint();
        paint.setColor(0xFF008800);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(10);

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
    }


    //アクセサメソッド
    public void setAlx ( float x){
        alx.add(x);
    }
    public void setAly(float y) {
        aly.add(y);
    }
    public ArrayList<Float> getAlx() {
        return alx;
    }
    public ArrayList<Float> getAly() {
        return aly;
    }
    public Path getPath() {
        return this.path;
    }
    public void resetAlx() {
        alx = new ArrayList<Float>();
    }
    public void resetAly() {
        aly = new ArrayList<Float>();
    }
    public void resetAlxy() {
        alx = new ArrayList<Float>();
        aly = new ArrayList<Float>();
    }
    public void setGes(Context context){
        ges = new GestureDetector(context,this);
    }
    public void setGesOGL(Context context,GestureDetector.OnGestureListener ogl){
        ges = new GestureDetector(context,ogl);
    }
    public void setGesODTL(GestureDetector.OnDoubleTapListener odtl){
        ges.setOnDoubleTapListener(odtl);
    }
    public void setGesFlg(boolean gesFlg){this.gesFlg = gesFlg;}
    public GestureDetector getGes(){
        return ges;
    }
    public boolean getGesFlg(){return gesFlg;}

    public void setScaleGes(Context context,ScaleGestureDetector.OnScaleGestureListener osgl){
        gesDetect = new ScaleGestureDetector(context, osgl);
    }
    public ScaleGestureDetector getGesDetect(){
        return this.gesDetect;
    }



    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, paint);
        int wid = getWidth();
        int hei = getHeight();
        // 線で円を描く
        outBoxPaint.setStyle(Paint.Style.STROKE);
        outBoxPaint.setAntiAlias(true);
        canvas.drawCircle((wid/2), (hei/2), Parameter.inLine, outBoxPaint);//引数:中心点xのいち：中心点ｙの位置
        canvas.drawCircle((wid/2), (hei/2), Parameter.outLine, outBoxPaint);//引数:中心点xのいち：中心点ｙの位置
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.d("sample", "onSingleTapConfirmed()");
        Log.d("sample", "シングルタップ時に呼ばれる（ダブルタップ時は呼ばれない）");
        Log.d("CanvasTest2View","getGesFlg()=true");
        setGesFlg(true);
        Log.d("CanvasTest2View", "、");
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.d("sample", "onDoubleTap()");
        Log.d("sample", " ダブルタップ時に呼ばれる");
        Log.d("CanvasTest2View","getGesFlg()=true");
        setGesFlg(true);
        Log.d("CanvasTest2View", "\\n");
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        Log.d("sample", "onDoubleTapEvent()");
        Log.d("sample", "ダブルタップイベント時に呼ばれる。「押す、スクロール、離す」で呼び出される。");
        Log.d("CanvasTest2View","getGesFlg()=true");
        setGesFlg(true);
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d("sample", "onDown()");
        Log.d("sample", "押下時に呼ばれる");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d("sample", "onShowPress()");
        Log.d("sample", "プレス時に呼ばれる(onDownが先に呼ばれ、意味が異なる)");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d("sample", "onSingleTapUp()");
        Log.d("sample", "シングルアップ時に呼ばれる（ダブルタップ時も呼ばれる）");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d("sample", "onScroll()");
        Log.d("sample", "スクロール時に呼ばれる");
        Log.d("CanvasTest2View","getGesFlg()=false");
        setGesFlg(false);
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d("sample", "onLongPress()");
        Log.d("sample", "長押し時に呼ばれる");
        Log.d("CanvasTest2View","getGesFlg()=true");
        Log.d("CanvasTest2View", "。");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d("sample", "onFling()");
        Log.d("sample", "フリック時に呼ばれる");
        Log.d("CanvasTest2View","getGesFlg()=false");
        setGesFlg(false);
        return false;
    }


}
