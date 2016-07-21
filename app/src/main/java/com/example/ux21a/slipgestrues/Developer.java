package com.example.ux21a.slipgestrues;

import android.widget.EditText;

/**
 * Created by kuniatus on 2016/06/23.
 */
public class Developer {
    
    public static EditText textarea;
    public static int countMsg = 0;
    public static void msg(String obj,String str){
        //logとuser出力を行う。
        System.out.println(obj+Integer.toString(countMsg)+": "+str);
        if(Parameter.devflg){
            String text = textarea.getText().toString();
            textarea.setText(text + str + "\n");
            countMsg++;
        }
    }
}
