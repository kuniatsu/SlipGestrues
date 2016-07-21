package com.example.ux21a.slipgestrues;

import android.content.Context;
import android.widget.Button;

/**
 * Created by ux21a on 2015/03/15.
 */
public class QuickTypeBTN extends Button {
    private String title;
    public QuickTypeBTN(Context context) {
        super(context);
    }

    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
        System.out.println("格納済み:"+title);
    }
}
