package com.example.ux21a.slipgestrues.JudgementVowel;

import android.util.Log;

import com.example.ux21a.slipgestrues.JudgementVowel.Columm;

/**
 * Created by ux21a on 2015/03/15.
 */
public class OColumm extends Columm {

    public String mojiReturn(float slope,float cenlen){

        System.out.println("OColumm:基準値 " + " HI_SLOPE:" + HI_SLOPE +" LOW_SLOPE:"+LOW_SLOPE);
        System.out.println("OColumm: " + "slope:" + slope);
        System.out.println("OColumm: " + "cenlen:" + cenlen);

        if(slope>HI_SLOPE){//長いの
            if(cenlen<LOW_CENTERLENGTH&&cenlen>-LOW_CENTERLENGTH) {//直線
                return "よ";
            }else if(cenlen>LOW_CENTERLENGTH) {
                return "ほ";
            }else if(cenlen<-LOW_CENTERLENGTH){
                return "も";
            }
        }else if(slope<LOW_SLOPE){//短いの
            System.out.println("OColumm：  "+"短い流さ:slope" + Float.toString(slope) + " 曲がり具合cenlen:" + Float.toString(cenlen));

            if(cenlen<LOW_CENTERLENGTH && cenlen>-LOW_CENTERLENGTH) {
                return "と";
            }else if(cenlen>HI_CENTERLENGTH){
                return "わ";
            }else if(cenlen<(HI_CENTERLENGTH*(-1))){
                return "を";
            }else if(cenlen>LOW_CENTERLENGTH) {
                return "そ";
            }else if(cenlen<-LOW_CENTERLENGTH){
                return "ろ";
            }
        }else{
            Log.d("OColumm：", "中間の長さ");
            if(cenlen<LOW_CENTERLENGTH&&cenlen>-LOW_CENTERLENGTH) {
                Log.d("OColumm：","お");
                return "お";
            }else if(cenlen>LOW_CENTERLENGTH) {
                return "こ";
            }else if(cenlen<-LOW_CENTERLENGTH){
                return "の";
            }
        }
        return "";
    }

}
