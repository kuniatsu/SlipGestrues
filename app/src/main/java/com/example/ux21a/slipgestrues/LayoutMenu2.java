package com.example.ux21a.slipgestrues;

import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * メニューのレイアウトを定義するクラス。
 * 削除予定クラス
 */
public class LayoutMenu2 {

    LinearLayout ll;


    public LayoutMenu2(Main main, String args_textTitle,ArrayList<Float> linelist){
        TextView textTitle = makeTitle(main, args_textTitle);

        //描画用の配列
        ManualCanvasView mcv = new ManualCanvasView(main,linelist);



        ll = new LinearLayout(main);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(textTitle);
        ll.addView(mcv);

    }










    public LinearLayout getLayout(){

        return ll;
    }


    private TextView makeTitle(Main main,String title){
        TextView textTitle = new TextView(main);
        textTitle.setText(title);
        textTitle.setTextColor(Color.RED);
        textTitle.setTextSize(20);
        return textTitle;
    }

    private TextView makeText(Main main,String text){
        TextView text1 = new TextView(main);
        text1.setText(text);
        return text1;
    }


}
