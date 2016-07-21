package com.example.ux21a.slipgestrues;

import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;

/**
 * Created by kuniatus on 16/01/11.
 * 各機種の情報を保持するクラス
 * 画面情報
 *
 */
public class DisplayParameters {
    private float xdpi;
    private float ydpi;


    private float dpi;//1インチのピクセル数
    private float densityDpi;
    private float widthPixels;

    private float heightPixels;
    private float density;
    private float scaledDensity;
    private float width;
    private float height;
    private float dipIndex;
    private String dip;







    public DisplayParameters(Main main){

        Display display = main.getWindowManager().getDefaultDisplay();
        DisplayMetrics disp = main.getResources().getDisplayMetrics();
        this.xdpi = disp.xdpi;
        this.ydpi = disp.ydpi;
        this.dpi = main.getResources().getDisplayMetrics().densityDpi;

        Point point = new Point();
        // Display情報からサイズを取得する。
        display.getSize(point);

        this.widthPixels=disp.widthPixels;
        this.heightPixels=disp.heightPixels;
        this.density=disp.density;
        this.scaledDensity=disp.scaledDensity;
        this.width=point.x;
        this.height=point.y;
    }


    public float getXdpi() {
        return xdpi;
    }

    public float getYdpi() {
        return ydpi;
    }

    public float getDpi() {
        return dpi;
    }

    public float getWidthPixels() {
        return widthPixels;
    }


    public float getHeightPixels() {
        return heightPixels;
    }




//    ldpi    x0.75 4dp 3px
//    mdpi    x1    4dp 4px
//    hdpi    x1.5  4dp 6px
//    xhdpi   x2    4dp 8px
//    xxhdpi  x3    4dp 12px
//    xxxhdpi x4    4dp 16px


//        Log.v("disp.xdpi", String.valueOf(disp.xdpi));                         //480
//        Log.v("disp.ydpi",            String.valueOf(disp.ydpi));              //480
//        Log.v("densityDpi",            String.valueOf(dpi));                   //480
//        Log.v("disp.widthPixels",    String.valueOf(disp.widthPixels));        //1080
//        Log.v("disp.heightPixels",    String.valueOf(disp.heightPixels));      //1776
//        Log.v("disp.density",        String.valueOf(disp.density));            //3.0
//        Log.v("disp.scaledDensity",    String.valueOf(disp.scaledDensity));    //3.0
//        Log.v("point.width",            String.valueOf(point.x));               //1080
//        Log.v("point.height",            String.valueOf(point.y));              //1776






}
