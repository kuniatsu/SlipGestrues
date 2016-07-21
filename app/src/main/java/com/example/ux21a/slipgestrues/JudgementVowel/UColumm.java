package com.example.ux21a.slipgestrues.JudgementVowel;

import android.util.Log;

import com.example.ux21a.slipgestrues.JudgementVowel.Columm;

/**
 * Created by ux21a on 2015/03/15.
 */
public class UColumm extends Columm {
    public String mojiReturn(float slope,float cenlen){

        Log.d("UColumm", "slope:" + slope);
        Log.d("UColumm", "cenlen:" + cenlen);

        if(slope>HI_SLOPE){//長いの
            if(cenlen<LOW_CENTERLENGTH&&cenlen>-LOW_CENTERLENGTH) {//直線
                return "ゆ";
            }else if(cenlen>LOW_CENTERLENGTH) {
                return "む";
            }else if(cenlen<-LOW_CENTERLENGTH){
                return "ふ";
            }

        }else if(slope<LOW_SLOPE){//短いの
            if(cenlen<LOW_CENTERLENGTH&&cenlen>-LOW_CENTERLENGTH) {
                return "つ";
            }else if(cenlen>LOW_CENTERLENGTH) {
                return "す";
            }else if(cenlen<-LOW_CENTERLENGTH){
                return "る";
            }

        }else{
            if(cenlen<LOW_CENTERLENGTH&&cenlen>-LOW_CENTERLENGTH) {
                return "う";
            }else if(cenlen>LOW_CENTERLENGTH) {
                return "く";
            }else if(cenlen<-LOW_CENTERLENGTH){
                return "ぬ";
            }

        }

        return "";
    }

}
