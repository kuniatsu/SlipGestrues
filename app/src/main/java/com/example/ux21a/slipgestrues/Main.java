package com.example.ux21a.slipgestrues;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ux21a.slipgestrues.IMEpack.InputTransform;
import com.example.ux21a.slipgestrues.JudgementVowel.YahooApiAndroid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main extends ActionBarActivity {
    //イベントを追加するためのフィールド
    private TextView yosoku;
    private EditText textarea;
    private CanvasView cantes;

    private ChangeChar changechar;
    private int count=0;
    public void setCount(int count){
        this.count = count;
    }
    public int getCount(){
        return  this.count;
    }
    public LinearLayout yokoLl;//予測変換レイアウト
    private Handler handler;
    protected ScaleGestureDetector mScaleGestureDetector;//マルチタップ

    private boolean gesflg = false;//ジェスチャ判定用フラグ
    private boolean scaleflg = false;//ジェスチャ判定用フラグ
    private float scaleS;
    private float scaleE;


    //ime用のクラスを作成
    InputTransform it = new InputTransform();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slip_gestures);
        handler = new Handler();

//        mScaleGestureDetector = new ScaleGestureDetector(cantes, cantes);//マルチタップ


        //イベントの追加のための場所オブジェクトを取得
        yosoku=(TextView)findViewById(R.id.hello_world);
        textarea=(EditText)findViewById(R.id.edittext);
        Developer.textarea = textarea;//開発用
        cantes = (CanvasView)findViewById(R.id.cantesid);
        changechar = new ChangeChar();
        cantes.setOnTouchListener(m_touchListener);


        //機種情報を取得しパラメータークラスに設定
        CalcPx px = new CalcPx(new DisplayParameters(this));
        Parameter.setInLine((float)(px.oneCm()*0.5));
        Parameter.setOutLine((float) (px.oneCm() * 1));
        Parameter.devflg = false;




        //ジェスチャイベント
        cantes.setGesOGL(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }
            @Override
            public void onShowPress(MotionEvent e) {
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                //長押しイベント
                String text = yosoku.getText().toString();
                yosoku.setText(text + "。");
                gesflg=true;//ジェスチャフラグ：アップイベントを起こさせない
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });


        cantes.setGesODTL(new GestureDetector.OnDoubleTapListener(){
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                String text = yosoku.getText().toString();
                yosoku.setText(text + "、");
                return false;
            }//タップイベント
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                scaleflg=true;//スケール処理があった場合のフラグ
                String str1 = textarea.getText().toString()+yosoku.getText().toString();
                textarea.setText(str1+"\n");
                yosoku.setText("");

                return false;
            }//ダブルタップ
            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                return false;
            }
        });

        cantes.setScaleGes(this, new ScaleGestureDetector.OnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                System.out.println("onScale");
                return false;
            }
            @Override
            public boolean onScaleBegin(ScaleGestureDetector detector) {
                // TODO Auto-generated method stub
                Log.v("touch", "onScaleBegin");
                scaleflg=true;
                if(scaleS<=0) {
                    scaleS = detector.getCurrentSpan();
                }else {
                    scaleE = detector.getCurrentSpan();
                }
                return false;
            }

            @Override
            public void onScaleEnd(ScaleGestureDetector detector) {
                // TODO Auto-generated method stub
                Log.v("touch", "onScaleEnd");
            }
        });





        //キーボード非表示
        textarea.setOnClickListener(new View.OnClickListener() {//テキストエリアのクリックイベント
            @Override
            public void onClick(View v) {
                //キーボード非表示
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(),0);
            }
        });

        //入力テキスト表示(クリックしたらテキストエリアに反映)
        yosoku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = textarea.getText().toString()+yosoku.getText().toString();
                textarea.setText(str1);
                yosoku.setText("");
                yokoLl.removeAllViews();//予測変換削除
            }
        });

    }


    //タップフリック時に発生
    private View.OnTouchListener m_touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            float x = event.getX();
            float y = event.getY();
            String text = yosoku.getText().toString();
            int wid = cantes.getWidth();
            int hei = cantes.getHeight();



            //マルチタッチ
            int count = event.getPointerCount();//何本でタップか
            if(count >= 2) {
                cantes.getGesDetect().onTouchEvent(event);
                cantes.getPath().reset();//線を消す
                System.out.println("マルチタッチ");


                gesflg=true;
                return true;
            }
            //ジェスチャ
            cantes.getGes().onTouchEvent(event);
            //bitmap動作確認用
            View view = (View)v;
            Bitmap saveBitmap = null;
            view.setDrawingCacheEnabled(true);

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:	//タッチする
                    cantes.getPath().reset();//線を消す
                    cantes.getPath().moveTo(x, y);
                    cantes.invalidate();
                    changechar.clearAl();
                    if(Parameter.devflg)textarea.setText("");
                    changechar.addArrayList(x, y);
                    //devMsg("始点　x:" + Float.toString(x) + " y:" + Float.toString(y));
                    Developer.msg("Main","始点　x:" + Float.toString(x) + " y:" + Float.toString(y));

                    break;
                case MotionEvent.ACTION_MOVE:	//タッチしたまま動かす
                    cantes.getPath().lineTo(x, y);
                    cantes.invalidate();
                    changechar.addArrayList(x, y);
                    String beforTextStr = yosoku.getText().toString();
                    String areaTextStr = textarea.getText().toString();
                    if(changechar.countArraySize()>200) {
                        //線がやたら長い
                        Developer.msg("Main", changechar.countArraySize() + ":グシャッてる判定");
                        if (((changechar.countArraySize() % 10) == 0)&&(areaTextStr.length() + beforTextStr.length()) > 0) {
                            //ここで配列数を数えて以上に多かった場合はグシャッてるという事で予測変換あたりを消す。予測変換がない場合はメイン文字列を一文字づつ消していく
                            if (beforTextStr.length() > 0) {
                                //予測に文字が入っているなら
                                Developer.msg("Main", "予測を消そうと思う");
                                yokoLl.removeAllViews();//予測変換削除
                                if (beforTextStr.substring(beforTextStr.length() - 1, beforTextStr.length()).equals("、") && beforTextStr.length() >= 2) {
                                    //最後に点が入ってしまう場合があるのでその対策。
                                    beforTextStr = beforTextStr.substring(0, beforTextStr.length() - 2);
                                } else if (beforTextStr.length() == 1) {
                                    beforTextStr = "";
                                } else {
                                    beforTextStr = beforTextStr.substring(0, beforTextStr.length() - 1);
                                }
                                yosoku.setText(beforTextStr);
                            } else if (areaTextStr.length() > 0) {
                                //テキストエリアから一文字消す処理を書く
                                Developer.msg("Main", "一文字消そうと思う");
                                areaTextStr = areaTextStr.substring(0, areaTextStr.length() - 1);
                                textarea.setText(areaTextStr);
                            }else{
                                //ぐしゃりなので無視
                                break;
                            }
                        }
                    }

                    break;

                case MotionEvent.ACTION_UP:		//指を離す
                    if(changechar.countArraySize()>200){
                        //ぐしゃり無視
                        changechar.clearAl();
                        break;
                    }


                    if(gesflg){
                        //ジェスチャだった場合処理をやめる。
                        if(scaleflg){
                            //スケール処理があったか
                            System.out.println("スケール処理");
                            if((scaleS)<scaleE){
                                Log.d("touch","ピンチアウト");
                                textarea.setText(textarea.getText()+" ");//とりあえず、テキストエリアそのものにスペースを与える。


                            }else if((scaleS)>scaleE){
                                Log.d("touch","ピンチイン");
                                String beforText = yosoku.getText().toString();
                                String areaText = textarea.getText().toString();

                                System.out.println(beforText);
                                System.out.println(beforText.length());

                                if(beforText.length()>0){
                                    //予測に文字が入っているなら
                                    yokoLl.removeAllViews();//予測変換削除
                                    if(beforText.substring(beforText.length()-1,beforText.length()).equals("、")&&beforText.length()>=2){
                                        //最後に点が入ってしまう場合があるのでその対策。
                                        beforText=beforText.substring(0,beforText.length()-2);
                                    }else if(beforText.length()==1){
                                        beforText="";
                                    }else{
                                        beforText=beforText.substring(0,beforText.length()-1);
                                    }


                                    yosoku.setText(beforText);
                                    System.out.println(beforText);
                                }else{
                                    //テキストエリアから一文字消す処理を書く
                                    areaText = areaText.substring(0, areaText.length()-1);
                                    textarea.setText(areaText);
                                }
                            }
                        }
                        System.out.println("じぇすちゃなのでしょりやめます。");
                        cantes.getPath().reset();
                        gesflg=false;
                        scaleflg=false;
                        return true;
                    }

                    cantes.getPath().lineTo(x, y);
                    Developer.msg("Main", "終点　x:" + Float.toString(x) + " y:" + Float.toString(y));
                    cantes.invalidate();
                    changechar.addArrayList(x,y);
                    saveBitmap = Bitmap.createBitmap(view.getDrawingCache());
                    String sokki = changechar.getString(saveBitmap,wid,hei);
                    Developer.msg("Main", "入力結果: " + sokki);
                    System.out.println("----------------------------------");
                    yosoku.setText(text + sokki);
                    //予測変換
                    yokoLl = (LinearLayout)findViewById(R.id.linearlayout);
                    yokoLl.removeAllViews();//変換候補初期化
/*

                    /////////////////YahooのAPI使用の場合////////////////////////////////////////////////
                    Thread thread = new Thread(){
                        @Override
                        public void run(){

                            try{
                                //YahooのAPI使用の場合
                                final ArrayList<ArrayList<String>> dataList = YahooApiAndroid.quickType(Main.this, yosoku.getText().toString(), 0);
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {

                                        for (ArrayList<String> data : dataList) {
                                            String st = data.get(0);
                                            for (String text : data) {
                                                if (!text.equals("start")) {
                                                    //System.out.println(text+":"+st);
                                                    addStringTextData(text, st);
                                                }
                                            }
                                        }
                                    }
                                });
                            }catch(final IOException e){
                                //オフライン
                                handler.post(new Runnable(){
                                    @Override
                                    public void run(){
                                        Toast.makeText(Main.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        addStringTextData(yosoku.getText().toString(), yosoku.getText().toString());
                                    }
                                });
                            }
                        }
                    };
                    thread.start();
                    ///////////////////////////////////////////////////////////

*/
                    int testnum = 0;
                    ArrayList<String> lists = InputTransform.getTransform(yosoku.getText().toString());
                    for(String text2:lists){
                        addStringTextData(text2, "");
                    }

                    break;
            }
            return true;
        }
    };


    //テキスト追加
    private void addStringTextData(String text,String name){
        final QuickTypeBTN yokoBtn = new QuickTypeBTN(this);    //ボタン生成

        yokoBtn.setId(0);    //リソースID設定
        yokoBtn.setText(text);
        yokoLl.addView(yokoBtn);
        yokoBtn.setTitle(name);


        yokoBtn.setOnClickListener(new View.OnClickListener() {
            //クリックイベント
            @Override
            public void onClick(View v) {
                QuickTypeBTN vtnnn = (QuickTypeBTN)v;
                Log.d("Main", vtnnn.getText() + "が押されました。");
                String str1 = textarea.getText().toString()+vtnnn.getText().toString();
                textarea.setText(str1);

                yosoku.setText(changeString(yosoku.getText().toString(), vtnnn.getTitle(), ""));
                yokoLl.removeAllViews();
                yosoku.setText("");
            }
        });
    }


    public static String changeString(String test,String target,String swap){
        Pattern p = Pattern.compile(target);
        Matcher m = p.matcher(test);
        return m.replaceAll(swap);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //メニューなどのバー
        getMenuInflater().inflate(R.menu.menu_slip_gestures, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //メニューボタン押下時のイベント
        int id = item.getItemId();
        WriteMenu wm = new WriteMenu();
        boolean flg = wm.selectMenu(this,id);
        if(flg){
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void changeViewColor(int color){
        //canvasViewの色
        //cantes.background
        cantes.setBackgroundColor(color);
    }

    public TextView getYosoku(){return yosoku;}
    public EditText getTextarea(){return textarea;}
    public ChangeChar getChangechar(){return changechar;}

}
