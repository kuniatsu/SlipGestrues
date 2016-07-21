package com.example.ux21a.slipgestrues;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;

import static java.lang.Math.abs;

/**
 * サインコサインタンジェントの計算
 */
public class CalcSinse {


    public Triangle tri;
    private Bitmap saveBitmap;
    private int width;//canvasの横幅
    private int height;//canvasの縦幅
    private CalcConsonant cc;
    private CalcSymbol csym;


    public CalcSinse(){
        this.cc = new CalcConsonant();
        this.csym = new CalcSymbol();
    }

    public void setSaveBitmap(Bitmap saveBitmap){
        this.saveBitmap = saveBitmap;
    }
    public void setWidth(int wid){
        this.width = wid;
    }
    public void setHeight(int hei){
        this.height = hei;
    }
    public void setTri(Triangle tri){
        this.tri = tri;
    }
    public Triangle getTri(){
        return this.tri;
    }




    public void calcTiltChild(){
        //子の傾きをだす。
        float tiltChild = (tri.getEdgeA()/tri.getEdgeB());
        tri.setTiltChild(tiltChild);
    }

    public Triangle makeTri(ArrayList<Float> alx,ArrayList<Float> aly){
        //triangleオブジェクトを作り値をセットする。
        this.tri = new Triangle(alx,aly);

        maxminIndex();//maxX,maxY,minX,minYをセット
        chackRightAngle();//flgをセット
        calcEdge();//edgeA,edgeB,edgeCをセット
        changSin(calcAngle());//sinA,sinBをセット
        calcCenterPoint();//中心点をセット
        calcTilt();//親の斜辺をセット
        calcZeroXPoint();//子の切片をセット！親の切片は使わないので最初から子
        calcTiltChild();//子の傾き


        Developer.msg("CalcSinse" , "底辺:" + Float.toString(tri.getEdgeA()));
        Developer.msg("CalcSinse" , "高さ:" + Float.toString(tri.getEdgeB()));
        Developer.msg("CalcSinse" , "斜辺:" + Float.toString(tri.getEdgeC()));
        Developer.msg("CalcSinse" , "中心点X:" + Float.toString(tri.getCenterX()) + "Y:" + Float.toString(tri.getCenterY()));
        Developer.msg("CalcSinse:", "傾き:" + Float.toString(tri.getTiltParentnum()));
        System.out.println("CalcSinse:  " + "子切片:" + Float.toString(tri.getZeroXPointChild()));
        System.out.println("CalcSinse:  " + "子傾き:" + Float.toString(tri.getTiltChild()));




        taisakuMayoko();//まっすぐ横を書くと高さも斜辺も0になってしまう。０だった場合斜辺に底辺を代入する
        String row = writeRow();
        colorPointLocation(row);
        Developer.msg("CalcSinse","曲がり具合：" + Float.toString(tri.getCurveLength()));
        return this.tri;
    }
    public void taisakuMayoko(){
        //まっすぐ横を書くと高さも斜辺も0になってしまう。０だった場合斜辺に底辺を代入する
        if(tri.getEdgeC()==0.0 &&tri.getEdgeB()==0.0){
            //斜辺が0のため代入
            tri.setEdgeC(tri.getEdgeA());
            System.out.println("CalcSinse: 斜辺値に底辺値を代入");
        }
    }



    public void chackRightAngle(){
        //右肩上がりか左肩上がりか調べる
        float min = tri.getAly().get(tri.getMinX());
        float max = tri.getAly().get(tri.getMaxX());

        Log.d("CalcSinse","indexMinX :" + tri.getMinX()+" MinX:"+tri.getAlx().get(tri.getMinX()));
        Log.d("CalcSinse","indexMaxX :" + tri.getMaxX()+" MaxX:"+tri.getAlx().get(tri.getMaxX()));
        Log.d("CalcSinse","indexMinY :" + tri.getMinY()+" MinY:"+tri.getAly().get(tri.getMinY()));
        Log.d("CalcSinse","indexMaxY :" + tri.getMaxY()+" MaxY:"+tri.getAly().get(tri.getMaxY()));

        if (min > max) {
            tri.setFlg(true);
        } else {
            tri.setFlg(false);
        }
    }

    public float calcAngle(){
        //斜辺のラジアン数を計算するメソッド
        float bottom =tri.getEdgeA();//底辺
        float hypotenuse =tri.getEdgeC();//斜辺
        float angle =bottom/hypotenuse;

        return angle;
    }

    public void maxminIndex(){
        //最も、右のX左のX上のY下のYを調べるメソッド
        //0番目で初期化
        int rightXindex = 0;
        int leftXindex = 0;
        int upYindex = 0;
        int downYindex = 0;

        for(int i = 1;i<tri.getAlx().size();i++){
            rightXindex=tri.getAlx().get(i)>tri.getAlx().get(rightXindex)?i:rightXindex;
            leftXindex=tri.getAlx().get(i)<tri.getAlx().get(leftXindex)?i:leftXindex;
            downYindex=tri.getAly().get(i)>tri.getAly().get(downYindex)?i:downYindex;
            upYindex=tri.getAly().get(i)<tri.getAly().get(upYindex)?i:upYindex;
        }
        tri.setMaxX(rightXindex);
        tri.setMinX(leftXindex);
        tri.setMaxY(downYindex);
        tri.setMinY(upYindex);

    }

    public void calcEdge(){
        //３辺の長さを求めるメソッド
        float startX = tri.getAlx().get(0);//始点のX
        float endX = tri.getAlx().get(tri.getAlx().size()-1);//終点のX
        float startY = tri.getAly().get(0);//始点のY
        float endY = tri.getAly().get(tri.getAly().size()-1);//終点のY
        boolean flg = tri.getFlg();

        if(startY==endY){
            //横に一直線
            Log.d("CalcSinse","横に一直線");
            tri.setStraight('x');
            tri.setEdgeB(0);//高さ
            if(startX<endX){//左から
                tri.setEdgeA(endX-startX);
            }else{//右から
                tri.setEdgeA(startX-endX);
            }
        }else if(startX==endX){
            //縦に一直線
            Log.d("CalcSinse","縦に一直線");
            tri.setStraight('y');
            tri.setEdgeA(0);//底
            if(startY>endY){
                //下から上
                tri.setEdgeB(startY-endY);
            }else{
                //上から下
                tri.setEdgeB(endY-startY);
            }
        }else if(startX<endX){
            //左から書いた場合
            tri.setEdgeA(endX-startX);//底
            if(flg){
                tri.setEdgeB(startY-endY);//高さ
            }else{
                tri.setEdgeB(endY-startY);
            }
        }else if(startX>endX){
            //右から書いた場合
            tri.setEdgeA(startX-endX);
            if(flg){
                tri.setEdgeB(endY-startY);
            }else{
                tri.setEdgeB(startY-endY);
            }
        }

        if(tri.getEdgeA()>0&&tri.getEdgeB()>0){//傾きがある
            double rutea = Math.pow((double)tri.getEdgeA(), 2);//2乗
            double ruteb = Math.pow((double)tri.getEdgeB(), 2);
            double rutec = rutea + ruteb;
            tri.setEdgeC((float)Math.sqrt(rutec));
        }else{
            tri.setEdgeC(0);
        }
    }

    public void changSin(float a){
        //ラジアンを角度にするメソッド
        double b =Math.asin((double)a);//アークサイン
        tri.setSinA((float)Math.toDegrees(b));//角度に直して代入
        tri.setSinB((float)90.0-(float)Math.toDegrees(b));
    }

    public String writeRow(){
        //あ・い・う・え・お行・記号を決める

        boolean flg = tri.getFlg();
        float sinA = tri.getSinA();
        tri.setRow("e"+String.valueOf(sinA));
        Log.d("CalcSinse","sinA"+Float.toString(sinA));
        String backString = "";//戻り値用

        float sx = tri.getAlx().get(0);
        float ex = tri.getAlxLast();
        float sy = tri.getAly().get(0);
        float ey = tri.getAlyLast();

        //直線を記号行にならないようにする。
        if(Float.toString(sinA)=="NaN"&&Float.toString(tri.getTiltChild())=="NaN"&&Float.toString(tri.getZeroXPointChild())=="NaN") {//タップの認識
            Log.d("CalcSinse","記号行");
            tri.setRow("記号");
        }else if(sx<(ex+10)&&sx>(ex-10)) {
            tri.setRow("え");
            backString = "え";
        }else if(sy<(ey+10)&&sy>(ey-10)){
            tri.setRow("お");
            backString ="お";
        }else if(Float.toString(sinA)=="NaN") {
            Log.d("CalcSinse","記号行");
            tri.setRow("記号");
        }else if(sinA<=15||tri.getAlx().get(0)==tri.getAlxLast()){
            tri.setRow("え");
            backString ="え";
        }else if(sinA>15&&sinA<=40&&flg){
            tri.setRow("い");
            backString ="い";
        }else if(sinA>40&&sinA<=75&&flg){
            tri.setRow("あ");
            backString ="あ";
        }else if(sinA>15&&sinA<=75&&!flg){
            tri.setRow("う");
            backString ="う";
        }else if(sinA>=75||tri.getAly().get(0)==tri.getAlyLast()){
            tri.setRow("お");
            backString ="お";
        }else{
            System.out.println("エラー");
            if(flg){System.out.println("flg:true");}else{System.out.println("flg:true");}
        }

        Log.d("CalcSinse",backString+":行");
        return backString;
    }

    public String checkN(String text,ArrayList<Float> alx,ArrayList<Float> aly){
        //「ん」を確認出力する
        float sx = alx.get(0);
        float ex = alx.get(alx.size()-1);
        float sex = abs(sx-ex);
        float sy = aly.get(0);
        float ey = aly.get(aly.size()-1);
        float sey = abs(sy-ey);

        float xLength = abs(alx.get(tri.getMaxX())-alx.get(tri.getMinX()));
        float yLength = abs(aly.get(tri.getMaxY())-aly.get(tri.getMinY()));
        if((sex<(xLength/3))&&(sey<(yLength/3))){

            if(alx.size()>3&&aly.size()>3){
                Log.d("cs:checkN","ん出力");
                return "ん";
            }
        }
        return text;
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public float calcZeroXPoint(float x,float y,float tilt){
        //点ｘ・点ｙ・傾きから切片を求める
        return cc.calcZeroXPoint(x, y, tilt);
    }
    public void calcZeroXPoint(){
        //点ｘ・点ｙ・傾きから切片を求める
        cc.setTri(tri);
        cc.calcZeroXPoint();
    }

    public float calcLength(float startX,float startY,float endX,float endY){
        //始点と終点の距離を求める
        return cc.calcLength(startX, startY, endX, endY);
    }

    public float calcTilt(float startX,float startY,float endX,float endY,boolean flg){
        //始点と終点と線の向きから傾きを求める。
        return cc.calcTilt(startX, startY, endX, endY, flg);
    }

    public void calcTilt(){
        //傾きを計算するメソッド
        cc.setTri(tri);
        cc.calcTilt();
    }

    public void calcCenterPoint(){
        //親三角の斜辺の中心を求める
        cc.setTri(tri);
        cc.calcCenterPoint();
    }

    public boolean chackRightAngle(ArrayList<Float> alx,ArrayList<Float> aly){
        //右肩上がりか左肩上がりか調べる
        return cc.chackRightAngle(alx,aly);
    }


    public float colorPointLocation(String row){
        //色のついた場所を探す
        return cc.colorPointLocation(row, width, height, saveBitmap);
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public Boolean smallString(String row,Triangle tri){
        //小文字（先端の丸まり）
        return csym.plumpStart(row, tri);
    }

    public Boolean plosiveString(String row,Triangle tri){
        //濁音（末端の丸まり）
        return csym.plumpEnd(row, tri);
    }



    public String smallChange(String text){
        //入力文字の小文字を返す
        return csym.smallChange(text);
    }

    public String plosiveChange(String text){
        //入力文字の濁音
        return csym.plosiveChange(text);
    }

    public String herfplosiveChange(String text){
        //入力文字の半濁音
        return csym.herfplosiveChange(text);
    }







}
