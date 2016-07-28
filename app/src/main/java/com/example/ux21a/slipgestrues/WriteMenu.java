package com.example.ux21a.slipgestrues;

import android.app.AlertDialog;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ux21a.slipgestrues.manualLine.AiueoSample;
import com.example.ux21a.slipgestrues.manualLine.HaiueoSample;
import com.example.ux21a.slipgestrues.manualLine.KaiueoSample;
import com.example.ux21a.slipgestrues.manualLine.MaiueoSample;
import com.example.ux21a.slipgestrues.manualLine.NaiueoSample;
import com.example.ux21a.slipgestrues.manualLine.RaiueoSample;
import com.example.ux21a.slipgestrues.manualLine.SaiueoSample;
import com.example.ux21a.slipgestrues.manualLine.SampleLine;
import com.example.ux21a.slipgestrues.manualLine.TaiueoSample;
import com.example.ux21a.slipgestrues.manualLine.WaiueoSample;
import com.example.ux21a.slipgestrues.manualLine.YaiueoSample;

import java.util.ArrayList;
import java.util.List;

/**
 * メニューを定義しているクラス
 */
public class WriteMenu {

    public boolean selectMenu(Main main,int id){
        AlertDialog.Builder alert;
        if(id == R.id.inputAGyouManual){
            alert = makeAlert(main,"あ");
        }else if(id == R.id.inputKAGyouManual){
            alert = makeAlert(main,"か");
        }else if(id == R.id.inputSAGyouManual){
            alert = makeAlert(main,"さ");
        }else if(id == R.id.inputTAGyouManual){
            alert = makeAlert(main,"た");
        }else if(id == R.id.inputNAGyouManual){
            alert = makeAlert(main,"な");
        }else if(id == R.id.inputHAGyouManual){
            alert = makeAlert(main,"は");
        }else if(id == R.id.inputMAGyouManual){
            alert = makeAlert(main,"ま");
        }else if(id == R.id.inputYAGyouManual){
            alert = makeAlert(main,"や");
        }else if(id == R.id.inputRAGyouManual){
            alert = makeAlert(main,"ら");
        }else if(id == R.id.inputWAGyouManual){
            alert = makeAlert(main,"わ");
        }else if(id == R.id.inputlitteleManual){
            alert = makeAlertException(main, "ぁ");
        }else if(id == R.id.inputPlosiveManual){
            alert = makeAlertException(main, "濁音");
        }else if(id == R.id.inputHerfPlosiveManual){
            alert = makeAlertException(main, "半濁音");
        }else if(id == R.id.inputSymbolManual){
            alert = makeAlertException(main, "記号");
        }else if(id == R.id.inputDev){
            alert = makeAlertException(main, "ディベロッパー");
        }else{
            return false;
        }
        alert.show();
        return true;


        //
    }


    public void makeManual(){

    }



    public AlertDialog.Builder makeAlert(Main main,String cha){
        // アラートダイアログ作成.
        AlertDialog.Builder alert = new AlertDialog.Builder(main);

        // タイトル設定
        String title = cha + "行入力設定";
        alert.setTitle(title);

        ViewPager mViewPager = getSettingviewListAdapter(main,cha);
        alert.setView(mViewPager);
        alert.setPositiveButton("閉じる", null);

        return alert;
    }


    public AlertDialog.Builder makeAlertException(Main main,String cha){
        // アラートダイアログ作成.
        AlertDialog.Builder alert = new AlertDialog.Builder(main);
        List<View> viewList = new ArrayList<View>();



        //ページを設定する
        ViewPager mViewPager;
        ArrayList<SampleLine> samlist = new ArrayList<>();
        final float auLine = Parameter.getAuLine("middle");
        final float[] iLine = Parameter.getiLine("middle");
        final float eoLine = Parameter.getEoLine("middle");

        String setumei = "";
        String moji = "";


        // タイトル設定
        if(cha.equals("ぁ")){
            alert.setTitle("小文字の入力方法");
            SampleLine s1 = new SampleLine(
                    new ArrayList<Float>(){
                        {
                            //線と丸の値
                            add((float)1);//左の丸の有無
                            add((float)0);//右の丸の有無
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                        }
                    });
            s1.setMoji("ぁ");
            setumei = "小文字の書き方は書き始めを丸めます";

            TextView textTitle = makeTitle(main, setumei);

            //描画用の配列
            ManualCanvasView mcv = new ManualCanvasView(main,s1.slist);

            LinearLayout ll;
            ll = new LinearLayout(main);
            ll.setOrientation(LinearLayout.VERTICAL);
            ll.addView(textTitle);
            ll.addView(mcv);
            viewList.add(ll);
        }else if(cha.equals("濁音")){
            alert.setTitle("濁音の入力方法");
            SampleLine s1 = new SampleLine(
                    new ArrayList<Float>(){
                        {
                            //線と丸の値
                            add((float)0);//左の丸の有無
                            add((float)1);//右の丸の有無
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                        }
                    });
            s1.setMoji("ぃ");
            setumei = "濁音の書き方は書き終わりを丸めます";


            TextView textTitle = makeTitle(main, setumei);

            //描画用の配列
            ManualCanvasView mcv = new ManualCanvasView(main,s1.slist);

            LinearLayout ll;
            ll = new LinearLayout(main);
            ll.setOrientation(LinearLayout.VERTICAL);
            ll.addView(textTitle);
            ll.addView(mcv);
            viewList.add(ll);
        }else if(cha.equals("半濁音")){
            alert.setTitle("半濁音の入力方法");
            SampleLine s1 = new SampleLine(
                    new ArrayList<Float>(){
                        {
                            //線と丸の値
                            add((float)1);//左の丸の有無
                            add((float)1);//右の丸の有無
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                        }
                    });
            s1.setMoji("ぅ");
            setumei = "半濁音の書き方は書き始めと終わりを丸めます";
            TextView textTitle = makeTitle(main, setumei);

            //描画用の配列
            ManualCanvasView mcv = new ManualCanvasView(main,s1.slist);

            LinearLayout ll;
            ll = new LinearLayout(main);
            ll.setOrientation(LinearLayout.VERTICAL);
            ll.addView(textTitle);
            ll.addView(mcv);
            viewList.add(ll);
        }else if(cha.equals("記号")){
            alert.setTitle("記号の入力方法");
            setumei = "読点は画面をタップします";
            TextView textTitle = makeTitle(main, setumei);
            //描画用の配列

            //読点
            SampleLine s = new SampleLine(
                    new ArrayList<Float>(){
                        {
                            //線と丸の値
                            add((float)0);//上の丸の有無
                            add((float)0);//下の丸の有無
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                        }
                    });
            ManualCanvasView mcv = new ManualCanvasView(main,s.slist);
            LinearLayout ll;
            ll = new LinearLayout(main);
            ll.setOrientation(LinearLayout.VERTICAL);
            ll.addView(textTitle);
            ll.addView(mcv);
            viewList.add(ll);



            //句点
            ll = new LinearLayout(main);
            ll.setOrientation(LinearLayout.VERTICAL);
            ll.addView(makeTitle(main, "句点は画面を長押しします"));
            viewList.add(ll);

            //?
            SampleLine s2 = new SampleLine(
                    new ArrayList<Float>(){
                        {
                            //線と丸の値
                            add((float)2);//上の丸の有無
                            add((float)0);//下の丸の有無
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                        }
                    });
            //描画用の配列
            mcv = new ManualCanvasView(main,s2.slist);
            ll = new LinearLayout(main);
            ll.setOrientation(LinearLayout.VERTICAL);
            ll.addView(makeTitle(main, "?は書き始めを丸めてください"));
            ll.addView(mcv);
            viewList.add(ll);

            //?
            SampleLine s3 = new SampleLine(
                    new ArrayList<Float>(){
                        {
                            //線と丸の値
                            add((float)0);//上の丸の有無
                            add((float)2);//下の丸の有無
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                        }
                    });
            //描画用の配列
            mcv = new ManualCanvasView(main,s3.slist);
            ll = new LinearLayout(main);
            ll.setOrientation(LinearLayout.VERTICAL);
            ll.addView(makeTitle(main, "!は終わりを丸めてください"));
            ll.addView(mcv);
            viewList.add(ll);

            //?
            SampleLine s4 = new SampleLine(
                    new ArrayList<Float>(){
                        {
                            //線と丸の値
                            add((float)0);//左の丸の有無
                            add((float)1);//右の丸の有無
                            add((float)0);
                            add((float)0);
                            add((float)3);//長さをロングに
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                            add((float)0);
                        }
                    });
            //描画用の配列
            mcv = new ManualCanvasView(main,s4.slist);
            ll = new LinearLayout(main);
            ll.setOrientation(LinearLayout.VERTICAL);
            ll.addView(makeTitle(main, "-(ハイフン)は終わりを丸めてください"));
            ll.addView(mcv);
            viewList.add(ll);





        }else if(cha.equals("ディベロッパー")){
            alert.setTitle("ディベロッパーモード");

            boolean devflg = !Parameter.devflg;
            Parameter.devflg = devflg;//切り替え

            String onOff = devflg?"ON":"OFF";
            String onOff2 = !devflg?"ON":"OFF";

            setumei = "モードを"+ onOff +"にしました。";
            String setumei2 = "もう一度ディベロッパー画面を開くとモードは"+ onOff2 +"になります。";

            TextView textTitle = makeTitle(main, setumei);
            TextView textTitle2 = makeTitle(main, setumei2);

            LinearLayout ll;
            ll = new LinearLayout(main);
            ll.setOrientation(LinearLayout.VERTICAL);
            ll.addView(textTitle);
            ll.addView(textTitle2);
            viewList.add(ll);
            if(Parameter.devflg){
                main.changeViewColor(Color.GRAY);//Viewの色変更test
            }else{
                main.changeViewColor(0xffddeedd);//Viewの色変更test
                //初期化
                main.yokoLl.removeAllViews();
                main.getTextarea().setText("");
                main.getChangechar().clearAl();
                main.getYosoku().setText("");
            }

            System.out.println("WriteMenu:ディベロッパーモード"+onOff);

        }else if(cha.equals("SETTING")) {

        }
        // viewリストからアダプタを作成しViewPagerにセット
        MyPagerAdapter mpa = new MyPagerAdapter(viewList);
        mViewPager = new ViewPager(main);
        mViewPager.setAdapter(mpa);
        alert.setView(mViewPager);
        alert.setPositiveButton("閉じる", null);
        return alert;
    }






    public TextView makeTitle(Main main,String title){
        TextView textTitle = new TextView(main);
        textTitle.setText(title);
        textTitle.setTextColor(Color.RED);
        textTitle.setTextSize(20);
        return textTitle;
    }

    public TextView makeText(Main main,String text){
        TextView text1 = new TextView(main);
        text1.setText(text);
        return text1;
    }



    public List<View> makeViewList(Main main,ArrayList<SampleLine> samplelinelist) {
        //横並びのページを作るメソッド

        ArrayList<LinearLayout> lllist = new ArrayList<>();


        for (SampleLine sl:samplelinelist) {
            lllist.add(new LayoutMenu2(main,"「"+sl.getMoji()+"」の書き方",sl.slist).getLayout());
        }


        List<View> viewList = new ArrayList<View>();

        for (LinearLayout ll:lllist) {
            viewList.add(ll);
        }

        return viewList;
    }


    public ViewPager getSettingviewListAdapter(Main main,String cha) {
        //ページを設定する
        ViewPager mViewPager;


        //chaをarrayListに変える
        ArrayList<SampleLine> listlist = new ArrayList<>();
        listlist = serchDot(cha);


        // リストに作成したページを追加.
        List<View> viewList = makeViewList(main,listlist);

        // viewリストからアダプタを作成しViewPagerにセット
        MyPagerAdapter mpa = new MyPagerAdapter(viewList);
        mViewPager = new ViewPager(main);
        mViewPager.setAdapter(mpa);


        return mViewPager;
    }


    public ArrayList<SampleLine> serchDot(String cha){
        //chaで受け取ったひらがなの行を書くためのそれぞれのfloatListを返す
        ArrayList<SampleLine> sampleline = new ArrayList<>();

        switch (cha) {
            case "あ":
                sampleline = AiueoSample.getlineList();
                break;
            case "か":
                sampleline = KaiueoSample.getlineList();
                break;
            case "さ":
                sampleline = SaiueoSample.getlineList();
                break;
            case "た":
                sampleline = TaiueoSample.getlineList();
                break;
            case "な":
                sampleline = NaiueoSample.getlineList();
                break;
            case "は":
                sampleline = HaiueoSample.getlineList();
                break;
            case "ま":
                sampleline = MaiueoSample.getlineList();
                break;
            case "や":
                sampleline = YaiueoSample.getlineList();
                break;
            case "ら":
                sampleline = RaiueoSample.getlineList();
                break;
            case "わ":
                sampleline = WaiueoSample.getlineList();
                break;

            default:
                sampleline = AiueoSample.getlineList();

        }



        return sampleline;
    }






}
