package com.example.ux21a.slipgestrues.manualLine;

import com.example.ux21a.slipgestrues.Parameter;

import java.util.ArrayList;

/**
 * メニューのアイウエオを書くためのサンプル
 */
public class WaiueoSample {

    public static ArrayList<SampleLine> getlineList(){
        ArrayList<SampleLine> slList = new ArrayList<>();

        final float auLine = Parameter.getAuLine("middle");
        final float[] iLine = Parameter.getiLine("middle");
        final float eoLine = Parameter.getEoLine("middle");


        SampleLine s2 = new SampleLine(new ArrayList<Float>(){{add(eoLine);add((float)0);add((float)0);add((float)(eoLine*1.5));add((float)0);add((float)0);add((float)0);add((float)0);}});
        s2.setMoji("わ");
        slList.add(s2);


        SampleLine s = new SampleLine(new ArrayList<Float>(){{add((float)0);add((float)(eoLine*1.5));add(eoLine);add((float)0);add((float)0);add((float)0);add((float)0);add((float)0);}});
        s.setMoji("を");
        slList.add(s);


        SampleLine s5 = new SampleLine(new ArrayList<Float>(){{add(Parameter.getInLine()/2);add((float)0);add((float)360);}});
        s5.setMoji("ん");
        slList.add(s5);

        return slList;
    }
}
