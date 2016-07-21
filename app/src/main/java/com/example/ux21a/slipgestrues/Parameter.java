package com.example.ux21a.slipgestrues;

/**
 * 各種統一情報
 */
public  class Parameter {

    static float inLine;//内円の半径
    static float outLine;//外円の半径

    public static float getOutLine() {
        return outLine;
    }

    public static float getInLine() {
        return inLine;
    }

    public static void setOutLine(float outLine) {
        Parameter.outLine = outLine;
    }

    public static void setInLine(float inLine) {
        Parameter.inLine = inLine;
    }


    //ディベロッパーモードのON/OFF
    public static boolean devflg;


    //サンプル用のLine
    public static float getAuLine(String type){
        float leng=1;//0だとerrが割り算の無限ループに成るので
        if(type=="short"){
            leng = inLine * (float)0.9;
        }else if(type=="middle"){
            leng = outLine * (float)0.9;
        }else if(type=="long"){
            leng = outLine * (float)1.5;
        }
        leng *= leng;//三平方の定理より
        leng /=2;
        leng = (float)Math.sqrt(leng);




        return leng;
    }



    public static float getEoLine(String type){
        float leng=0;

        if(type=="short"){
            leng = inLine * (float)0.9;
        }else if(type=="middle"){
            leng = outLine * (float)0.9;
        }else if(type=="long"){
            leng = outLine * (float)1.5;
        }



        return leng;
    }


    public static float[] getiLine(String type){
        float leng=1;//0だとerrが無限ループに成るので
        float len[] = {0,0};

        if(type=="short"){
            leng = inLine * (float)0.7;
        }else if(type=="middle"){
            leng = outLine * (float)0.7;
        }else if(type=="long"){
            leng = outLine * (float)1.1;
        }
        leng *= leng;//三平方の定理より
        leng /=3;
        leng = (float)Math.sqrt(leng);

        len[0] = leng;
        len[1] = leng*2;




        return len;
    }




}
