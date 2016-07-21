package com.example.ux21a.slipgestrues.manualLine;

import com.example.ux21a.slipgestrues.Parameter;

import java.util.ArrayList;

/**
 * メニューのアイウエオを書くためのサンプル
 */
public class TaiueoSample {




    public static ArrayList<SampleLine> getlineList(){
        String lineleng = "short";
        final float auLine = Parameter.getAuLine(lineleng);
        final float[] iLine = Parameter.getiLine(lineleng);
        final float eoLine = Parameter.getEoLine(lineleng);

        ArrayList<SampleLine> slList = new ArrayList<>();


        //リストのリストにリストを代入して返す
        SampleLine s1 = new SampleLine(new ArrayList<Float>(){{add((float)0);add(auLine);add(auLine);add((float)0);}});
        s1.setMoji("た");
        slList.add(s1);
        SampleLine s2 = new SampleLine(new ArrayList<Float>(){{add((float)0);add(iLine[1]);add(iLine[0]);add((float)0);}});
        s2.setMoji("ち");
        slList.add(s2);

        SampleLine s3 = new SampleLine(new ArrayList<Float>(){{add((float)0);add((float)0);add(auLine);add(auLine);}});
        s3.setMoji("つ");
        slList.add(s3);

        SampleLine s4 = new SampleLine(new ArrayList<Float>(){{add((float)0);add((float)0);add((float)0);add(eoLine);}});
        s4.setMoji("て");
        slList.add(s4);

        SampleLine s5 = new SampleLine(new ArrayList<Float>(){{add((float)0);add((float)0);add(eoLine);add((float)0);}});
        s5.setMoji("と");
        slList.add(s5);

        return slList;
    }



}
