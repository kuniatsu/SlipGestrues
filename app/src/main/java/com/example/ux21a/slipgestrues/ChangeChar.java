package com.example.ux21a.slipgestrues;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.ux21a.slipgestrues.JudgementVowel.AColumm;
import com.example.ux21a.slipgestrues.JudgementVowel.EColumm;
import com.example.ux21a.slipgestrues.JudgementVowel.IColumm;
import com.example.ux21a.slipgestrues.JudgementVowel.OColumm;
import com.example.ux21a.slipgestrues.JudgementVowel.UColumm;

import java.util.ArrayList;

/**
 * あいうえおのどれに当たるかを分ける
 * 小文字・濁音・半濁音を調べる
 */
public class ChangeChar {

    private ArrayList<Float> x;
    private ArrayList<Float> y;

    //コンストラクタ
    public ChangeChar(){
        x = new ArrayList<Float>();
        y = new ArrayList<Float>();
    }
    public ChangeChar(ArrayList<Float> x,ArrayList<Float> y){
        this.x = x;
        this.y = y;
    }



    //アクセサメソッド
    public void setX(ArrayList<Float> x){
        this.x = x;
    }
    public void setY(ArrayList<Float> y){
        this.y = y;
    }
    public ArrayList<Float> getX(){
        return x;
    }
    public ArrayList<Float> getY(){
        return y;
    }


    public void addArrayList(float x,float y){
        //フィールドのArrayListに値を代入する
        this.x.add(x);
        this.y.add(y);
    }
    public void clearAl(){
        x.clear();
        y.clear();
    }
    public String getString(Bitmap saveBitmap,int wid,int hei) {
        //キャンバスに描かれた線を見て文字を返す。

        CalcSinse cs = new CalcSinse();
        cs.setSaveBitmap(saveBitmap);
        cs.setHeight(hei);
        cs.setWidth(wid);
        Triangle tr = cs.makeTri(x, y);
        String sokkimoji = String.valueOf(tr.getRow());
        sokkimoji = cs.checkN(sokkimoji,x,y);//[ん]なら吐く

        System.out.println("ChangeChar: "+sokkimoji+"クラスを読みに行く");
        //ポリモフィズムを意識して書き直す
        switch (sokkimoji) {
            case "あ":
                AColumm ac = new AColumm();
                sokkimoji = ac.changeTriangle(tr);
                break;
            case "い":
                IColumm ic = new IColumm();
                sokkimoji = ic.changeTriangle(tr);
                break;
            case "う":
                UColumm uc = new UColumm();
                sokkimoji = uc.changeTriangle(tr);
                break;
            case "え":
                EColumm ec = new EColumm();
                sokkimoji = ec.changeTriangle(tr);
                break;
            case "お":
                OColumm oc = new OColumm();
                sokkimoji = oc.changeTriangle(tr);
                break;
            case "記号":
                sokkimoji = "";
        }



        if(cs.smallString(sokkimoji,tr)&&cs.plosiveString(sokkimoji, tr)){
            //前も後ろも丸まっている
            Log.d("ChangeChar", "両端丸まってるよ(半濁音)");
            sokkimoji = cs.herfplosiveChange(sokkimoji);
        }else if(cs.smallString(sokkimoji,tr)){ //先端が丸まっているかを調べる
            Log.d("ChangeChar", "先端丸まってるよ(小文字)");
            sokkimoji = cs.smallChange(sokkimoji);
        }else if(cs.plosiveString(sokkimoji, tr)){ //末端が丸まっているかを調べる
            Log.d("ChangeChar", "末端丸まってるよ(濁音)");
            sokkimoji = cs.plosiveChange(sokkimoji);
        }


        //アレイリストの初期化
        x.clear();
        y.clear();
        return sokkimoji;
    }



    public int countArraySize(){
        return x.size();
    }

}
