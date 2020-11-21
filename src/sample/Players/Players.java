package sample.Players;

import java.io.*;


public abstract class Players {

    int moveCost,targetCost;
    int gold,steps,stepsCount,goldSpent;
    int indexI,indexJ;
    int matrixM,matrixN;
    int targetI,targetJ,targetDist;
    boolean flag;

    FileWriter writer;
    File logFile;

    public abstract void selectTarget(int matrix[][],int targetCost,int indexI,int indexJ);
    public abstract void log(Players player);

    public int getGold(){
        return this.gold;
    }

    public int getStepsCount() {
        return stepsCount;
    }

    public int getGoldSpent() {
        return goldSpent;
    }

    public double calcDistance(int x1,int y1,int x2,int y2)
    {
        double distance = Math.sqrt(Math.pow((x1-x2),2)+Math.pow((y1-y2),2));
        return distance;
    }

    public int getIndexI(){
        return this.indexI;
    }

    public int getIndexJ(){
        return this.indexJ;
    }

    public int getTargetI(){
        return this.targetI;
    }

    public int getTargetJ(){
        return this.targetJ;
    }

    public boolean getFlag(){
        return this.flag;
    }

    public int getMoveCost(){
        return  this.moveCost;
    }

    public int getTargetCost(){
        return this.targetCost;
    }

    public int getTargetDist(){
        return this.targetDist;
    }

}


