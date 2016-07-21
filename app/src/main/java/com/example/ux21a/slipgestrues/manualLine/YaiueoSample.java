package com.example.ux21a.slipgestrues.manualLine;

import com.example.ux21a.slipgestrues.Parameter;

import java.util.ArrayList;

/**
 * メニューのアイウエオを書くためのサンプル
 */
public class YaiueoSample {




    public static ArrayList<SampleLine> getlineList(){
        String len = "long";
        final float auLine = Parameter.getAuLine(len);
        final float[] iLine = Parameter.getiLine(len);
        final float eoLine = Parameter.getEoLine(len);

        ArrayList<SampleLine> slList = new ArrayList<>();


        //リストのリストにリストを代入して返す
        SampleLine s1 = new SampleLine(new ArrayList<Float>(){{add((float)0);add(auLine);add(auLine);add((float)0);}});
        s1.setMoji("や");
        slList.add(s1);

        SampleLine s3 = new SampleLine(new ArrayList<Float>(){{add((float)0);add((float)0);add(auLine);add(auLine);}});
        s3.setMoji("ゆ");
        slList.add(s3);

        SampleLine s5 = new SampleLine(new ArrayList<Float>(){{add((float)0);add((float)0);add(eoLine);add((float)0);}});
        s5.setMoji("よ");
        slList.add(s5);

        return slList;
    }



}
