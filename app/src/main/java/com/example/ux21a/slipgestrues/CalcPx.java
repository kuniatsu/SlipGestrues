package com.example.ux21a.slipgestrues;

/**
 * Created by kuniatus on 16/01/14.
 * ピクセルをインチやセンチに直すクラス
 */
public class CalcPx {
    DisplayParameters dispara;

    public CalcPx(DisplayParameters dispara){
        this.dispara = dispara;
    }


    public float calcInch(float px){
        return (float)(px / dispara.getDpi());
    }
    public float calcCm(float px){
        return (float)((px / dispara.getDpi())*2.54);
    }
    public float oneCm(){
        //1cmを表すのに何pxかかるか
        return (float)(dispara.getDpi()/2.54);
    }

}
