package com.example.ux21a.slipgestrues.manualLine;

import com.example.ux21a.slipgestrues.Parameter;

import java.util.ArrayList;

/**
 * メニューのアイウエオを書くためのサンプル
 */
public class RaiueoSample {

    public static ArrayList<SampleLine> getlineList(){
        ArrayList<SampleLine> slList = new ArrayList<>();

        //リストのリストにリストを代入して返す
        SampleLine s1 = new SampleLine(new ArrayList<Float>(){{add(Parameter.getInLine());add((float)150);add((float)180);}});
        s1.setMoji("ら");
        slList.add(s1);
        SampleLine s2 = new SampleLine(new ArrayList<Float>(){{add(Parameter.getInLine());add((float)320);add((float)180);}});
        s2.setMoji("り");
        slList.add(s2);

        SampleLine s3 = new SampleLine(new ArrayList<Float>(){{add(Parameter.getInLine());add((float)230);add((float)180);}});
        s3.setMoji("る");
        slList.add(s3);

        SampleLine s4 = new SampleLine(new ArrayList<Float>(){{add(Parameter.getInLine());add((float)270);add((float)180);}});
        s4.setMoji("れ");
        slList.add(s4);

        SampleLine s5 = new SampleLine(new ArrayList<Float>(){{add(Parameter.getInLine());add((float)180);add((float)180);}});
        s5.setMoji("ろ");
        slList.add(s5);

        return slList;
    }
}
