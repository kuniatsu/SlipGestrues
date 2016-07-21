package com.example.ux21a.slipgestrues.JudgementVowel;

import android.util.Log;

import com.example.ux21a.slipgestrues.JudgementVowel.Columm;

/**
 * Created by ux21a on 2015/03/15.
 */
public class IColumm extends Columm {


    public String mojiReturn(float slope,float cenlen) {

        Log.d("IColumm", "slope:" + slope);
        Log.d("IColumm","cenlen:"+cenlen);

        if(slope>HI_SLOPE){//長い

            if(cenlen<LOW_CENTERLENGTH&&cenlen>-LOW_CENTERLENGTH) {//直線の場合
                return "/";
            }else if(cenlen>LOW_CENTERLENGTH) {
                return "み";
            }else if(cenlen<-LOW_CENTERLENGTH){
                return "ひ";
            }

        }else if(slope<LOW_SLOPE){//短い
            if(cenlen<LOW_CENTERLENGTH&&cenlen>-LOW_CENTERLENGTH) {//直線の場合
                return "ち";
            }else if(cenlen>LOW_CENTERLENGTH) {
                return "り";
            }else if(cenlen<(-LOW_CENTERLENGTH)){
                return "し";
            }

        }else{
            if(cenlen<LOW_CENTERLENGTH&&cenlen>-LOW_CENTERLENGTH) {//直線の場合
                return "い";
            }else if(cenlen>LOW_CENTERLENGTH) {
                return "に";
            }else if(cenlen<(-LOW_CENTERLENGTH)){
                return "き";
            }

        }

        return "";

    }


}
