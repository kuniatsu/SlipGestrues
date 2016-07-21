package com.example.ux21a.slipgestrues.JudgementVowel;

import com.example.ux21a.slipgestrues.Parameter;
import com.example.ux21a.slipgestrues.Triangle;

/**
 * Created by ux21a on 2015/03/15.
 */
public class Columm {

    float HI_SLOPE = Parameter.getOutLine()*2;
    float LOW_SLOPE = Parameter.getInLine()*2;
    float HI_CENTERLENGTH = Parameter.getOutLine();
    float LOW_CENTERLENGTH = Parameter.getInLine();

    public String changeTriangle(Triangle tri){

        //長さと曲がり具合で文字をを確認する。
        String moji = mojiReturn(tri.getEdgeC(),tri.getCurveLength());
        return moji;
    }
    public String changeTriangle(Triangle tri,float centerLength){

        //長さと曲がり具合で文字をを確認する。
        String moji = mojiReturn(tri.getEdgeC(),centerLength);

        return moji;
    }

    public Boolean straight(){
        return true;
    }
    public Boolean straight(float centerLength){
        if(centerLength>100){
            return false;
        }else {
            return true;
        }
    }
    public String mojiReturn(float slope){return "";}
    public String mojiReturn(float slope,Boolean slopeStyle){return "";}
    public String mojiReturn(float slope,float slopeStyle){return "";}
}
