package com.example.ux21a.slipgestrues;

import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * メニューのレイアウトを定義するクラス。
 */
public class LayoutMenu {

    LinearLayout ll;
    public LayoutMenu(Main main,String args_textTitle,String args_text1){
        TextView textTitle = makeTitle(main,args_textTitle);
        TextView text1 = makeText(main, args_text1);
//        ManualCanvasView mcv = new ManualCanvasView();



        ll = new LinearLayout(main);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(textTitle);
        ll.addView(text1);


    }

    public LinearLayout getLauout(){

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
