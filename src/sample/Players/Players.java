package sample.Players;

import java.io.*;
import java.util.ArrayList;


public abstract class Players {

    int moveCost,targetCost;
    int gold,steps,stepsCount,goldSpent;
    int indexI,indexJ;
    int matrixM,matrixN;

    FileWriter writer;
    File logFile;

    public abstract void selectTarget(int matrix[][],int targetCost,int indexI,int indexJ);
    public abstract void log(Players player);

    public int getStepsCount() {
        return stepsCount;
    }

    public int getGoldSpent() {
        return goldSpent;
    }

    public int calcDistance(int x1,int x2,int y1,int y2)
    {
        int distance = (int) Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
        return distance;
    }


    //public abstract void findGoldPosition(int goldAmount,int matrix[][]);

    /*public void searchGolds(int mat[][], int m, int n, int x)
    {

        // set indexes for
        // bottom left element
        int i = m - 1, j = 0;
        while (i >= 0 && j < n)
        {
            if (mat[i][j] == x){
                goldI.add(i);
                goldJ.add(j);
                break;
            }

            if (mat[i][j] > x){
                i--;
            } else {
                j++;
            }
        }

    }*/

}


