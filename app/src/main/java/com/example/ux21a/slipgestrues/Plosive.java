package com.example.ux21a.slipgestrues;

/**
 * Created by ux21a on 2015/03/15.
 * 濁音と半濁音の変換を行うクラス
 */
public class Plosive {
    private final String[] plosiveArray = {"-","！","が","ぎ","ぐ","げ","ご","ざ","じ","ず","ぜ","ぞ","だ","ぢ","づ","で","ど","ば","び","ぶ","べ","ぼ"};
    private final String[] naturalArray = {"や","｜","か","き","く","け","こ","さ","し","す","せ","そ","た","ち","つ","て","と","は","ひ","ふ","へ","ほ"};
    private final String[][] plosive2DArray = {{"が","ぎ","ぐ","げ","ご"},
            {"ざ","じ","ず","ぜ","ぞ"},
            {"だ","ぢ","づ","で","ど"},
            {"ば","び","ぶ","べ","ぼ"}};

    private final String[][] natural2DArray = {{"か","き","く","け","こ"},
            {"さ","し","す","せ","そ"},
            {"た","ち","つ","て","と"},
            {"は","ひ","ふ","へ","ほ"}};

    private final String[] hrefPlosiveArray = {"ぱ","ぴ","ぷ","ぺ","ぽ"};
    private final String[] hrefNaturalArray = {"は","ひ","ふ","へ","ほ"};


    //アクセサメソッド
    public String[] getPlosiveArray(){
        return this.plosiveArray;
    }
    public String[][] getPlosive2DArray(){
        return this.plosive2DArray;
    }
    public String[] getHrefPlosiveArray(){
        return this.hrefPlosiveArray;
    }
    public String getPlosive(int num){
        return plosiveArray[num];
    }
    public String get2DPlosive(int rownum,int colnum){
        return plosive2DArray[rownum][colnum];
    }
    public String getHrefPlosive(int num){
        return hrefPlosiveArray[num];
    }


    public String plosiveChange(String chr){
        //引数の文字を濁音に変える
        String str="";
        for(int i=0;i<plosiveArray.length;i++){
            if(chr.equals(naturalArray[i]))str=plosiveArray[i];
        }
        return str;
    }

    public String hrefPlosiveChange(String chr){
        //引数の文字を半濁音に変える
        String str="";
        for(int i=0;i<hrefPlosiveArray.length;i++){
            if(chr.equals(hrefNaturalArray[i]))str=hrefPlosiveArray[i];
        }
        return str;
    }
}
