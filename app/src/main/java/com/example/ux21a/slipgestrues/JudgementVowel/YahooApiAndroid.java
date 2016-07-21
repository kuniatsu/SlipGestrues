package com.example.ux21a.slipgestrues.JudgementVowel;

import android.content.Context;
import android.util.Xml;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by ux21a on 2015/03/15.
 */
public class YahooApiAndroid {
    private static final String URL = "http://jlp.yahooapis.jp/JIMService/V1/conversion?appid=*************";



    public static ArrayList<ArrayList<String>> quickType(Context context,String text,int num) throws IOException {
        //quickTypeは予測変換のこと
        //引数：textで渡された文字列をYahooAPIに送り、XMLを受け取り、XMLから文字列の取得する。
        //戻り値がArrayList<ArrayList<String>>となっているのは、送る文字列が文節ごとに区切られて文節ごとの予測変換が返るので文節ごとにしまうためこのような形にしている。


        //-----[httpクライアントの設定]
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(URL+text);
        ArrayList<ArrayList<String>> alsa = new ArrayList<ArrayList<String>>();
        ArrayList<String> al = new ArrayList<String>();
        al.add("start");

        try{
            HttpResponse res = client.execute(get);
            InputStream in = res.getEntity().getContent();
            //-----[パーサーの設定]
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(in, "UTF-8");
            int eventType = parser.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT)
            {
                switch(eventType)
                {
                    case XmlPullParser.START_TAG:
                        String tag = parser.getName();
//                        Log.d("Yahoo:tag ",tag);
                        int depth = parser.getDepth();
                        if(tag.equals("SegmentText")){
//                            System.out.println("SegmentText内");
                            alsa.add(al);
                            al = new ArrayList<String>();
                            String txt = parser.nextText();
                            al.add(txt);
                        }


                        if(tag.equals("Candidate")) {
//                            System.out.println("Candidate内");
                            String txt = parser.nextText();
//                           Log.d("Yahoo:txt ",txt);
                            al.add(txt);
                        }


                        break;
                }
                eventType = parser.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }


        alsa.add(al);

        return alsa;
    }
}
