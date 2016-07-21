package com.example.ux21a.slipgestrues;

import android.graphics.Bitmap;
import android.util.Log;
import java.util.ArrayList;

/**
 * 傾きや斜辺の長さを計算するクラス
 */
public class CalcConsonant {
    private Triangle tri;

    public void setTri(Triangle tri){
        this.tri = tri;
    }
    public float calcZeroXPoint(float x,float y,float tilt){
        //三角関数を使用して子の切片を返す
        float zeroXPoint;//切片
        zeroXPoint = y - (x/tilt);
        return zeroXPoint;

    }

    public void calcZeroXPoint(){
        //三角関数を使用して切片を割り出す。
        //引数なしはtriオブジェクトに直接代入
        float zeroXPoint;//切片
//        zeroXPoint = tri.getCenterY() - (tri.getCenterX()*tri.getTiltParentnum());
        zeroXPoint = tri.getCenterY() - (tri.getCenterX()/tri.getTiltParentnum());
        System.out.println("ClacConsonant.calcZeroPoint(): " + "zeroXPoint = tri.getCenterY() - (tri.getCenterX()*tri.getTiltParentnum())");
        System.out.println("ClacConsonant.calcZeroPoint(): "+ zeroXPoint + " = " + tri.getCenterY() + " - (" + tri.getCenterX() + " * " + tri.getTiltParentnum() + ")");
        tri.setZeroXPointChild(zeroXPoint);
    }

    public float calcLength(float startX,float startY,float endX,float endY){
        //1次関数のxy2点の距離を計算する。
        //三角関数の「高さの2乗×底辺の2乗＝斜辺の２乗」を使っている。

        float whidth = startX>endX?(startX-endX):(endX-startX);
        float height = startY>endY?startY-endY:endY-startY;

        System.out.println("CalcConsonant: "+ "子底辺:" + Float.toString(whidth));
        System.out.println("CalcConsonant: "+ "子高さ:" + Float.toString(height));


        if(height!=0&&whidth!=0){//傾きがある
            double rutea = Math.pow((double)height, 2);//2乗
            double ruteb = Math.pow((double)whidth, 2);
            double rutec = rutea + ruteb;
            return (float)Math.sqrt(rutec);//ルートが数字に直る

        }else if(height==0&&whidth==0){
            System.out.println("幅のない入力、タップ");
            return 0;

        }else{
            System.out.println("え行・お行");
            float a = height>whidth?height:whidth;
            return a;
        }

    }

    public float calcTilt(float startX,float startY,float endX,float endY,boolean flg){
        //傾きを計算するメソッド
        float width = startX>endX?(startX-endX):(endX-startX);
        float height = startY>endY?startY-endY:endY-startY;
        float tilt = height/width;
        if(flg){
            tilt *= -1;
        }
        return tilt;
    }

    public void calcTilt(){
        //傾きを計算するメソッド
        float startX = tri.getAlx().get(0);
        float endX =  tri.getAlx().get(tri.getAlx().size()-1);
        float startY = tri.getAly().get(0);
        float endY =  tri.getAly().get(tri.getAly().size()-1);

        float width = startX>endX?(startX-endX):(endX-startX);
        float height = startY>endY?startY-endY:endY-startY;
        float tilt = height/width;


        if(tri.getFlg()){
            tilt *= -1;
        }
        tri.setTiltParentnum(tilt);
    }

    public void calcCenterPoint(){
        //親三角の斜辺の中心を求める

        float xLengHarf = tri.getEdgeA()/2;//X軸なので底辺
        float yLengHarf = tri.getEdgeB()/2;//Yなので高さ

        //小さい方を返す
        float minX = tri.getAlx().get(0)<tri.getAlx().get(tri.getAlx().size()-1)?tri.getAlx().get(0):tri.getAlx().get(tri.getAlx().size()-1);
        float minY = tri.getAly().get(0)<tri.getAly().get(tri.getAly().size()-1)?tri.getAly().get(0):tri.getAly().get(tri.getAly().size()-1);

        tri.setCenterX(xLengHarf+minX);
        tri.setCenterY(yLengHarf+minY);

    }

    public boolean chackRightAngle(ArrayList<Float> alx,ArrayList<Float> aly){
        //右肩上がりか左肩上がりか調べる
        //右上がりだとtrueを返す
        if(alx.get(0)>alx.get(alx.size()-1)){
            //右から左に書いた
            if(aly.get(0)>aly.get(aly.size()-1)){
                return false;
            }else{
                return true;
            }
        }else{
            //左から右に書いた
            if(aly.get(0)>aly.get(aly.size()-1)){
                return true;
            }else{
                return false;
            }
        }

    }


    private float calcMinusY(float x,float a,float zeroPoint){
        //点xと傾きと切片から点Yを調べる

        float y = (a * x) + zeroPoint;
        Log.d("CalcConsonant_calcMinusY()","minusY=(a * x) + zeroPoint");
        Log.d("CalcConsonant_calcMinusY()",y+" = "+"("+a+" * "+x+") + "+zeroPoint);
        return y;
    }
    private float calcPlusY(float x,float tilt,float zeroPoint){
        //点xと傾きと切片から点Yを調べる
        float a = (tilt * x) + zeroPoint;
        Log.d("CalcConsonant_calcPlusY()","plusY=(tilt * x) + zeroPoint");
        Log.d("CalcConsonant_calcPlusY()",a+" = "+"("+tilt+" * "+x+") + "+zeroPoint);
        return a;
    }


    private float calcMinusX(float y,float tilt,float zeroPoint){
        //点Yと傾きと切片から点Xを調べる
        float a = (y-zeroPoint)/tilt;
        Log.d("CalcConsonant_calcMinusX()","minusX = (y-zeroPoint)/tilt");
        Log.d("CalcConsonant_calcMinusX()",a+" = "+"("+y+" - "+zeroPoint+") / "+tilt);
        return a;

    }

    private float calcPlusX(float y,float tilt,float zeroPoint){
        //点Yと傾きと切片から点Xを調べる
        float a = (y-zeroPoint)/tilt;
        Log.d("CalcConsonant_calcPlusX()","plusX = (y-zeroPoint)/tilt");
        Log.d("CalcConsonant_calcPlusX()",a+" = "+"("+y+" - "+zeroPoint+") / "+tilt);
        return a;
    }


    public float colorPointLocation(String row,int width,int height,Bitmap saveBitmap){
        //色のついた場所を探す
        //中心から色の付いている場所を探しその距離を返す。
        //引数は、row:未使用　width&height:調べる範囲 saveBitmap:調べるコントロール

        float centerX = tri.getCenterX();
        float centerY = tri.getCenterY();
        float tiltParent = tri.getTiltParentnum();

        float length  = 0;//距離の初期値
        float plusX;
        float minusX;
        float plusY;
        float minusY;


        float tiltChild = tri.getTiltChild();

        float zeroXPointChild = tri.getZeroXPointChild();
        tri.setZeroXPointChild(tri.getZeroXPointChild());

        //検索値変数を中央で初期化
        plusX  = centerX;
        minusX = centerX;
        plusY = centerY;
        minusY = centerY;
        Log.d("CalcConsonant_colorPointLocation()","ループにはいる 幅:"+width+"  縦:"+height+" 中心X:"+centerX+" 中心Y:"+centerY);

        boolean xFlg=true;
        if(tri.getEdgeA()<tri.getEdgeB()){
            Log.d("CalcConsonant_colorPointLocation()","xを＋１していくパターン");
        }else{
            Log.d("CalcConsonant_colorPointLocation()","yを＋１していくパターン");
            xFlg = false;
        }



        int i=0;
        boolean pulsFlg = true;
        boolean minusFlg = true;

        while((pulsFlg||minusFlg)&&xFlg){
            i+=1;
            Log.d("CalcSinse_ループ内1","plusX:"+plusX+" plusY:"+plusY+" minusX:"+minusX+" minusY:"+minusY);
            try {
                if (plusY < height &&plusY > 0 && plusX > 0 && plusX < width) {
                    int a = saveBitmap.getPixel((int) plusX, (int) plusY);
                    if (a == -16742400) {
                        length = calcLength( centerX, centerY, plusX, plusY);
                        break;
                    }
                }else{
                    //プラスの点はこれ以上計算する必要がない

                    if(pulsFlg) {
                        if (plusY >= height) {
                            System.out.println("plusY >= height");
                        }
                        if (plusY <= 0) {
                            System.out.println("plusY > 0");
                        }
                        if (plusX <= 0) {
                            System.out.println("plusX <= 0");
                        }

                        if (plusX >= width) {
                            System.out.println("plusX >= width");
                        }

                    }


                    pulsFlg = false;
                }

            }catch (java.lang.IllegalArgumentException e)
            {
                System.out.println("plusY = (tiltChild * plusX)+zeroXPoint;");
                System.out.println(plusY+" = ("+ tiltChild+" * "+ plusX+")+ "+zeroXPointChild+");");
                System.out.println("PLUSエラー plusX:"+plusX+"  plusY:"+plusY + " minusX:"+minusX+"  minusY:"+minusY);
                break;
            }

//            minusY = calcMinusY(minusX,tiltChild,zeroXPointChild);


            try {
                if (minusY > 0 && minusX > 0 && minusX < width && plusY < height) {
                    int a = saveBitmap.getPixel((int) minusX, (int) minusY);
                    if (a == -16742400) {
                        length = calcLength(centerX, centerY, minusX, minusY);
                        length *= -1;
                        break;
                    }
                }else{
                    //マイナスの点をこれ以上見る必要がない
                    if(minusFlg) {
                        if (minusY <= 0) {
                            System.out.println("minusY <= 0");
                        }
                        if (minusX <= 0) {
                            System.out.println("minusX <= 0");
                        }
                        if (minusX >= width) {
                            System.out.println("minusX >= width");
                        }

                        if (plusY >= height) {
                            System.out.println("plusY >= height");
                        }
                    }

                    minusFlg = false;

                }

            }catch (java.lang.IllegalArgumentException e)
            {
                System.out.println("minusY = (int) (tiltChild * minusX) + (int) zeroXPoint;");
                System.out.println(minusY+" = "+"( "+tiltChild+" * "+ minusX+" ) + "+zeroXPointChild+")");
                System.out.println("MINUSエラー plusX:"+plusX+"  plusY:"+plusY + " minusX:"+minusX+"  minusY:"+minusY);
                break;
            }

            plusX  = centerX+i;
            minusX = centerX-i;
//            plusY = calcPlusY(plusX,tiltChild,zeroXPointChild);

        }

        while((pulsFlg||minusFlg)&&xFlg==false){//yを＋１していくパターン
            i+=1;
            Log.d("CalcConsonant_ループ内3","plusX:"+plusX+" plusY:"+plusY+" minusX:"+minusX+" minusY:"+minusY);

            try {
                if (plusY < height &&plusY > 0 && plusX > 0 && plusX < width) {
                    int a = saveBitmap.getPixel((int) plusX, (int) plusY);
                    if (a == -16742400) {
                        System.out.println("「長さ計算」 " + "centerX:" + centerX + " centerY:" + centerY + " plusX:" + plusX + " plusY:" + plusY);
                        length = calcLength( centerX, centerY, plusX, plusY);
                        break;
                    }
                }else{
                    //プラスの点はこれ以上計算する必要がない
                    if(pulsFlg) {
                        if (plusY >= height) {
                            System.out.println("plusY >= height");
                        }
                        if (plusY <= 0) {
                            System.out.println("plusY > 0");
                        }
                        if (plusX <= 0) {
                            System.out.println("plusX <= 0");
                        }
                        if (plusX >= width) {
                            System.out.println("plusX >= width");
                        }

                    }
                    pulsFlg = false;
                }

            }catch (java.lang.IllegalArgumentException e)
            {

                System.out.println("plusY = (tiltChild * plusX)+zeroXPoint;");
                System.out.println(plusY+" = ("+ tiltChild+" * "+ plusX+")+ "+zeroXPointChild+");");

                System.out.println("PLUSエラー plusX:"+plusX+"  plusY:"+plusY + " minusX:"+minusX+"  minusY:"+minusY);
                break;
            }


//            Log.d("CalcConsonant_4計算式","minusX = calcMinusX(minusY,tiltChild,zeroXPointChild)");
//            minusX = calcMinusX(minusY,tiltChild,zeroXPointChild);
//            Log.d("CalcConsonant_4計算式",minusX+" = calcMinusX("+minusY+","+tiltChild+","+zeroXPointChild+")");


            Log.d("CalcConsonant_ループ内4","plusX:"+plusX+" plusY:"+plusY+" minusX:"+minusX+" minusY:"+minusY);

            try {
                if (minusY > 0 && minusX > 0 && minusX < width && plusY < height) {
                    int a = saveBitmap.getPixel((int) minusX, (int) minusY);
                    if (a == -16742400) {
                        length = calcLength(centerX, centerY, minusX, minusY);
                        length *= -1;
                        break;
                    }
                }else{
                    //マイナスの点をこれ以上見る必要がない
                    if(minusFlg) {
                        if (minusY <= 0) {
                            System.out.println("minusY <= 0");
                        }
                        if (minusX <= 0) {
                            System.out.println("minusX <= 0");
                        }
                        if (minusX >= width) {
                            System.out.println("minusX >= width");
                        }

                        if (plusY >= height) {
                            System.out.println("plusY >= height");
                        }
                    }

                    minusFlg = false;

                }

            }catch (java.lang.IllegalArgumentException e)
            {
                System.out.println("minusY = (int) (tiltChild * minusX) + (int) zeroXPoint;");
                System.out.println(minusY+" = "+"( "+tiltChild+" * "+ minusX+" ) + "+zeroXPointChild+")");
                System.out.println("MINUSエラー plusX:"+plusX+"  plusY:"+plusY + " minusX:"+minusX+"  minusY:"+minusY);
                break;
            }

            plusY  = centerY+i;
            minusY = centerY-i;
//            plusX = calcPlusX(plusY,tiltChild,zeroXPointChild);
            Log.d("CalcConsonant_3計算","plusX = calcPlusX(plusY,tiltChild,zeroXPointChild);");
            Log.d("CalcConsonant_3計算",Float.toString(plusX)+" = calcPlusX("+Float.toString(plusY)+","+Float.toString(tiltChild)+","+Float.toString(zeroXPointChild)+");");


        }
        Log.d("CalcConsonant","確認ループ"+Integer.toString(i)+"回");
        Log.d("CalcConsonant", "plusX:" + Float.toString(plusX) + " plusY:" + Float.toString(plusY) + " minusX:" + Float.toString(minusX) + " minusY:" + Float.toString(minusY));

        tri.setCurveLength(length);
        return length;
    }
}
