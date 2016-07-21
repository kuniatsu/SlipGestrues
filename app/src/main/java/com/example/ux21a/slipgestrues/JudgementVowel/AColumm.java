package com.example.ux21a.slipgestrues.JudgementVowel;

import android.util.Log;

/**
 * Created by ux21a on 2015/03/15.
 */
public class AColumm extends Columm {

    public String mojiReturn(float slope,float cenlen){

        Log.d("AColumm", "slope:" + slope);
        Log.d("AColumm","cenlen:"+cenlen);

        if(slope>HI_SLOPE){//長い
            Log.d("AColumm","長い");
            if(cenlen<LOW_CENTERLENGTH&&cenlen>-LOW_CENTERLENGTH) {
                Log.d("AColumm","や");
                return "や";
            }else if(cenlen>LOW_CENTERLENGTH) {
                return "は";
            }else if(cenlen<-LOW_CENTERLENGTH){
                return "ま";
            }
        }else if(slope<LOW_SLOPE){//短い
            Log.d("AColumm","短い");
            if(cenlen<LOW_CENTERLENGTH&&cenlen>-LOW_CENTERLENGTH) {
                Log.d("AColumm","た");
                return "た";
            }else if(cenlen>LOW_CENTERLENGTH) {
                return "さ";
            }else if(cenlen<-LOW_CENTERLENGTH){
                return "ら";
            }
        }else{
            Log.d("AColumm","普通の長さ");
            if(cenlen<LOW_CENTERLENGTH&&cenlen>-LOW_CENTERLENGTH) {
                Log.d("AColumm", "あ");
                return "あ";
            }else if(cenlen>LOW_CENTERLENGTH) {
                return "か";
            }else if(cenlen<-LOW_CENTERLENGTH){
                return "な";
            }
        }
        return "";
    }

}
