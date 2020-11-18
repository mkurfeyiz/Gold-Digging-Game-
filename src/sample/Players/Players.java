package sample.Players;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Players {

    int moveCost,targetCost;
    int gold,steps,stepsCount,goldSpent;
    FileWriter writer;
    File logFile;

    public abstract void selectTarget(int moveCost,int targetCost);
    public abstract void log(Players player);

    public int getStepsCount() {
        return stepsCount;
    }

    public int getGoldSpent() {
        return goldSpent;
    }

}
