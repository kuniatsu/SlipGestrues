package com.example.ux21a.slipgestrues;

import java.util.ArrayList;

/**
 * 速記文字情報の値を保持する
 */
public class Triangle {

    private ArrayList<Float> alx;
    private ArrayList<Float> aly;
    private float sinA;
    private float sinB;
    private float edgeA;//底辺
    private float edgeB;//高さ
    private float edgeC;//斜辺
    private boolean flg;//trueは右肩上がり
    private int maxX;//インデックス！
    private int maxY;
    private int minX;
    private int minY;
    private float centerX;//斜辺の中心点
    private float centerY;

    private float tiltParentnum;//親の傾き
    private float curveLength;//曲がり具合

    private float tiltChild;//子の傾き
    private float zeroXPointChild;//子の切片

    private char straight;//縦か横が直線じゃないか


    public float getTiltChild(){return this.tiltChild;}
    public float getZeroXPointChild(){return this.zeroXPointChild;}

    public void setTiltChild(float tiltChild){
        this.tiltChild = tiltChild;
    }
    public void setZeroXPointChild(float zeroXPointChild){
        this.zeroXPointChild = zeroXPointChild;
    }

    public void setStraight(char straight){
        this.straight=straight;
    }

    public char getStraight(){
        return this.straight;
    }





    //    private char row = 'e';
    private String row = "e";

    public Triangle(){}
    public Triangle(ArrayList<Float> alx,ArrayList<Float> aly){
        this.alx=alx;
        this.aly=aly;
    }

    public Triangle(ArrayList<Float> alx,ArrayList<Float> aly,float sinA,float sinB,float edgeA,float edgeB,float edgeC,boolean flg){
        this.alx=alx;
        this.aly=aly;
        this.sinA=sinA;
        this.sinB=sinB;
        this.edgeA=edgeA;
        this.edgeB=edgeB;
        this.edgeC=edgeC;
        this.flg=flg;
    }


    public void setCurveLength(float curve){
        this.curveLength = curve;
    }

    public float getCurveLength(){
        return this.curveLength;
    }


    public ArrayList<Float> getAlx(){
        return this.alx;
    }
    public ArrayList<Float> getAly(){
        return this.aly;
    }

    public float getSinA(){
        return sinA;
    }
    public float getSinB(){
        return sinB;
    }
    public float getEdgeA(){
        return edgeA;
    }
    public float getEdgeB(){
        return edgeB;
    }
    public float getEdgeC(){
        return edgeC;
    }
    public boolean getFlg(){
        return flg;
    }
    public int getMaxX(){
        return maxX;
    }
    public int getMaxY(){
        return maxY;
    }
    public int getMinX(){
        return minX;
    }
    public int getMinY(){
        return minY;
    }

    public float getAlxLast(){
        return alx.get(alx.size()-1);
    }
    public float getAlyLast(){
        return aly.get(aly.size()-1);
    }






    public String getRow(){
        return row;
    }


    public void setSinA(float sinA){
        this.sinA = sinA;
    }



    public void setAlx(ArrayList<Float> alx){
        this.alx = alx;
    }

    public void setAly(ArrayList<Float> aly){
        this.alx = aly;
    }


    public void setSinB(float sinB){
        this.sinB=sinB;
    }
    public void setEdgeA(float edgeA){
        this.edgeA=edgeA;
    }
    public void setEdgeB(float edgeB){
        this.edgeB=edgeB;
    }
    public void setEdgeC(float edgeC){
        this.edgeC=edgeC;
    }
    public void setFlg(boolean flg){
        this.flg = flg;
    }
    public void setMaxX(int maxX){
        this.maxX = maxX;
    }
    public void setMaxY(int maxY){
        this.maxY = maxY;
    }
    public void setMinX(int minX){
        this.minX = minX;
    }
    public void setMinY(int minY){
        this.minY = minY;
    }
    public void setRow(String row){
        this.row = row;
    }

    public void setCenterX(float centerX){
        this.centerX = centerX;
    }
    public void setCenterY(float centerY){
        this.centerY = centerY;
    }

    public float getCenterX(){
        return centerX;
    }
    public float getCenterY(){
        return centerY;
    }

    public void setTiltParentnum(float tiltParentnum){
        this.tiltParentnum =tiltParentnum;
    }

    public float getTiltParentnum(){
        return tiltParentnum;
    }
}
