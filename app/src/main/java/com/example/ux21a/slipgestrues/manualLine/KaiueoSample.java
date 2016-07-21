package com.example.ux21a.slipgestrues.manualLine;

import com.example.ux21a.slipgestrues.Parameter;

import java.util.ArrayList;

/**
 * メニューのアイウエオを書くためのサンプル
 */
public class KaiueoSample {

    public static ArrayList<SampleLine> getlineList(){
        ArrayList<SampleLine> slList = new ArrayList<>();

        //リストのリストにリストを代入して返す
        SampleLine s1 = new SampleLine(new ArrayList<Float>(){{add(Parameter.getOutLine());add((float)330);add((float)180);}});
        s1.setMoji("か");
        slList.add(s1);
        SampleLine s2 = new SampleLine(new ArrayList<Float>(){{add(Parameter.getOutLine());add((float)140);add((float)180);}});
        s2.setMoji("き");
        slList.add(s2);

        SampleLine s3 = new SampleLine(new ArrayList<Float>(){{add(Parameter.getOutLine());add((float)50);add((float)180);}});
        s3.setMoji("く");
        slList.add(s3);

        SampleLine s4 = new SampleLine(new ArrayList<Float>(){{add(Parameter.getOutLine());add((float)90);add((float)180);}});
        s4.setMoji("け");
        slList.add(s4);

        SampleLine s5 = new SampleLine(new ArrayList<Float>(){{add(Parameter.getOutLine());add((float)0);add((float)180);}});
        s5.setMoji("こ");
        slList.add(s5);

        return slList;
    }
}
