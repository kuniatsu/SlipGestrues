package com.example.ux21a.slipgestrues.JudgementVowel;

import android.util.Log;

import com.example.ux21a.slipgestrues.JudgementVowel.Columm;

/**
 * Created by ux21a on 2015/03/15.
 */
public class EColumm extends Columm {


    public String mojiReturn(float slope,float cenlen) {

        Log.d("EColumm", "slope:" + slope);
        Log.d("EColumm", "cenlen:" + cenlen);

        if(slope>HI_SLOPE){//長いの
            if(cenlen<LOW_CENTERLENGTH&&cenlen>-LOW_CENTERLENGTH) {//直線
                return "｜";
            }else if(cenlen>LOW_CENTERLENGTH) {
                return "め";
            }else if(cenlen<-LOW_CENTERLENGTH){
                return "へ";
            }
        }else if(slope<LOW_SLOPE){//短い
            if(cenlen<LOW_CENTERLENGTH&&cenlen>-LOW_CENTERLENGTH) {
                return "て";
            }else if(cenlen>HI_CENTERLENGTH) {
                    return String.valueOf('"');
            }else if(cenlen<-HI_CENTERLENGTH){
                    return String.valueOf("'");
            }else if(cenlen>LOW_CENTERLENGTH) {
                return "れ";
            }else if(cenlen<-LOW_CENTERLENGTH){
                return "せ";
            }
        }else{//中位
            if(cenlen<LOW_CENTERLENGTH&&cenlen>-LOW_CENTERLENGTH) {//直線
                return "え";
            }else if(cenlen>LOW_CENTERLENGTH) {
                return "ね";
            }else if(cenlen<-LOW_CENTERLENGTH){
                return "け";
            }
        }


        return "";

    }

}
