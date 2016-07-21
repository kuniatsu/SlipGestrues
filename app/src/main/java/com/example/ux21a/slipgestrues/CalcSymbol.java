package com.example.ux21a.slipgestrues;

import java.util.ArrayList;

/**
 * 濁音・半濁音・小文字を判定する
 */
public class CalcSymbol {

    private Triangle tri;
    public void setTri(Triangle tri){
        this.tri = tri;
    }



      public Boolean plumpStart(String row,Triangle tri){
        //先端が丸まっているかを判定する         plumpまるまるという意味
        boolean flg = false;
        float len = 10;//必要な幅　※pxなので要修正
        float sx;
        float sy;

        int minXindex = tri.getMinX();
        int minYindex = tri.getMinY();
        int maxXindex = tri.getMaxX();
        int maxYindex = tri.getMaxY();

        //XとYの座標情報を持ってくる
        ArrayList<Float> x = tri.getAlx();
        ArrayList<Float> y = tri.getAly();

        //Xが最小値の座標
        float minXX = x.get(minXindex);
        float minXY = y.get(minXindex);
        //Xが最大値の座標
        float maxXX = x.get(maxXindex);
        float maxXY = y.get(maxXindex);

        //Yが最小値の座標
        float minYY = y.get(minYindex);
        float minYX = x.get(minYindex);

        //Yが最大値の座標
        float maxYY = y.get(maxYindex);
        float maxYX = x.get(maxYindex);

        //Xの始点と終点
        float originX = x.get(0);
        float terminalX = x.get(x.size()-1);

        //Yの始点と終点
        float originY = y.get(0);
        float terminalY = y.get(y.size()-1);


        if((originX >= terminalX) && (originY >= terminalY)){
            //右下から左上への線
            sx = originX + len;
            sy = originY + len;

            if((sx < maxYX && sy < maxYY) || (sx < maxXX && sy < maxXY)){
                //①sx < maxYX　始点＋設定幅よりも一番下の点が右にある
                //①sy < maxYY　始点＋設定幅よりも一番下の点が下にある
                //②sx < maxXX　始点＋設定幅よりも一番右の点が右にある
                //②sy < maxXY　始点＋設定幅よりも一番右の点が下にある
                return true;
            }
        }else if((originX >= terminalX) && (originY <= terminalY)){
            sx = originX + len;
            sy = originY - len;

            if((sx < minYX && sy > minYY) || (sx > maxXX && sy > maxXY)){
                return true;
            }else{
                return false;
            }
        }else if((originX <= terminalX) && (originY <= terminalY)){
            sx = originX - len;
            sy = originY - len;

            if((sx > minYX && sy > minYY) || (sx > minXX && sy > minXY)){
                return true;
            }
        }else if((originX <= terminalX) && (originY >= terminalY)){
            sx = originX - len;
            sy = originY + len;

            if((sx > maxYX && sy < maxYY) || (sx > minXX && sy < minXY)){
                return true;
            }
        }else if(row.equals("お") && (originX >= terminalX)){

            sx = originX + len;
            if(sx > maxXX){
                return true;
            }
        }else if(row.equals("お") && (originX <= terminalX)){
            sx =originX - len;

            if(sx < minXX){
                return true;
            }
        }else if(row.equals("え") && (originY >= terminalY)){
            sy = originY - len;

            if(sy < maxYY){
                return true;
            }
        }else if(row.equals("え") && (originY <= terminalY)){
            sy = originY - len;

            if(sy > minYY){
                return true;
            }
        }
        return false;
    }




    public Boolean plumpEnd(String row,Triangle tri){
        //末端が丸まっているかを判定する
        //作成開始日20160211
        boolean flg = false;
        float len = 10;//必要な幅　※pxなので要修正
        float sx;
        float sy;

        int minXindex = tri.getMinX();
        int minYindex = tri.getMinY();
        int maxXindex = tri.getMaxX();
        int maxYindex = tri.getMaxY();

        //XとYの座標４点それぞれの情報を持ってくる
        ArrayList<Float> x = tri.getAlx();
        ArrayList<Float> y = tri.getAly();
        float minXX = x.get(minXindex);//もっとも左のx座標
        float minXY = y.get(minXindex);//もっとも左のy座標
        float maxXX = x.get(maxXindex);//もっとも右のx座標
        float maxXY = y.get(maxXindex);//もっとも右のy座標
        float minYY = y.get(minYindex);//もっとも上のy座標
        float minYX = x.get(minYindex);//もっとも上のx座標
        float maxYY = y.get(maxYindex);//もっとも下のy座標
        float maxYX = x.get(maxYindex);//もっとも下のx座標

        //Xの始点と終点
        float originX = x.get(0);
        float terminalX = x.get(x.size()-1);
        //Yの始点と終点
        float originY = y.get(0);
        float terminalY = y.get(y.size()-1);


        if((originX >= terminalX) && (originY >= terminalY)){
            //右下から左上への線
            sx = terminalX - len;//ケツのx点より設定幅分左の点
            sy = terminalY - len;//ケツのy点より設定幅分上の点

            if((sx > minYX && sy > minYY) || (sx < minXX && sy < minXY)){
                //sx > minYX もっとも上の点は終点よりも設定幅分以上左
                //sy > minYY もっとも上の点は終点よりも設定幅分以上上
                //sx < minXX もっとも左の点は終点よりも設定幅分以上左
                //sy < minXY もっとも左の点は終点よりも設定幅分以上上
                return true;
            }
        }else if((originX >= terminalX) && (originY <= terminalY)){
            //右上から左下への線
            sx = terminalX - len;//もっとも左の点＋設定幅
            sy = terminalY + len;//もっとも下の点＋設定幅
            if((sx > maxYX && sy < maxYY) || (sx > minXX && sy < minXY)){
                //sx > maxYX もっとも下の点は終点よりも設定幅分以上左
                //sy < maxYY もっとも下の点は終点よりも設定幅分以上下
                //sx > minXX もっとも左の点は終点よりも設定幅分以上左
                //sy < minXY もっとも左の点は終点よりも設定幅分以上下
                return true;
            }
        }else if((originX <= terminalX) && (originY <= terminalY)){
            //左上から右下へ
            sx = terminalX + len;//もっとも右の点＋設定幅
            sy = terminalY + len;//もっとも下の点＋設定幅
            if((sx < maxYX && sy < maxYY) || (sx < maxXX && sy < maxXY)){
                return true;
            }
        }else if((originX <= terminalX) && (originY >= terminalY)){
            //左下から右上への線
            sx = terminalX + len;//もっとも右の点＋設定幅分右
            sy = terminalY - len;//もっとも上の点＋設定幅分上
            if((sx < minYX && sy > minYY) || (sx < maxXX && sy > maxXY)){
                return true;
            }
        }else if(row.equals("お") && (originX >= terminalX)){
            //右から左への横線
            sx = terminalX - len;//終点＋設定幅分左
            if(sx < minXX){
                return true;
            }
        }else if(row.equals("お") && (originX <= terminalX)){
            //左から右への線
            sx = terminalX + len;//終点＋設定幅分右
            if(sx < maxXX){
                return true;
            }
        }else if(row.equals("え") && (originY >= terminalY)){
            //下から上への縦線
            sy = terminalY - len;//
            if(sy > minYY){
                return true;
            }
        }else if(row.equals("え") && (originY <= terminalY)){
            //上から下への縦線
            sy = terminalY + len;
            if(sy < minYY){
                return true;
            }
        }
        return false;
    }








    public String smallChange(String text){
        //指定の文字を小文字に変える
        if(text=="あ"){
            return "ぁ";
        }else if(text=="い"){
            return "ぃ";
        }else if(text=="う"){
            return "ぅ";
        }else if(text=="え"){
            return "ぇ";
        }else if(text=="お"){
            return "ぉ";
        }else if(text=="や"){
            return "ゃ";
        }else if(text=="ゆ"){
            return "ゅ";
        }else if(text=="よ"){
            return "ょ";
        }else if(text=="つ"){
            return "っ";
        }else if(text=="｜"){
            return "？";
        }





        return text;
    }


    public String plosiveChange(String text){
        //文字を濁音に変える
        Plosive plosive = new Plosive();
        String value = plosive.plosiveChange(text);
        return value;
    }

    public String herfplosiveChange(String text){
        //文字を半濁音に変える
        Plosive plosive = new Plosive();
        String value = plosive.hrefPlosiveChange(text);
        return value;
    }







}
