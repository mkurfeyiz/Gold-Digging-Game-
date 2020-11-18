package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import sample.Players.*;

public class Game {

    //Oyuncu bilgileri
    //A
    @FXML
    Label goldA;
    @FXML
    Label stepsA;
    //B
    @FXML
    Label goldB;
    @FXML
    Label stepsB;
    //C
    @FXML
    Label goldC;
    @FXML
    Label stepsC;
    //D
    @FXML
    Label goldD;
    @FXML
    Label stepsD;
    //Oyun Tahtasi
    @FXML
    GridPane board;

    //Oyun ayarlarinin degerleri
    int gameBoard[][];
    int m, n;
    int goldPerc;
    int gold;
    int stepsSetting;
    //Oyuncu ayarlarinin degerleri
    //A
    int movementCostA;
    int targetingCostA;
    //B
    int movementCostB;
    int targetingCostB;
    //C
    int movementCostC;
    int targetingCostC;
    //D
    int movementCostD;
    int targetingCostD;

    // Golds
    int randomGoldCounter;
    int randomSecretGoldCounter;


    public void getSettings(int m, int n, int goldPerc, int gold, int stepsSetting, int movementCostA, int targetingCostA
            , int movementCostB, int targetingCostB, int movementCostC, int targetingCostC, int movementCostD, int targetingCostD) {
        //Oyun degerleri
        this.m = m;
        this.n = n;
        //Oyun Tahtasi
        this.gameBoard = new int[m][n];
        //RowConstraints rows = new RowConstraints();
        //rows.setPercentHeight(m);
        //ColumnConstraints cols = new ColumnConstraints();
        //cols.setPercentWidth(n);
        for (int i = 0; i < n - 15; i++) {
            this.board.getRowConstraints().add(new RowConstraints(m));
        }
        for (int j = 0; j < m - 15; j++) {

            this.board.getColumnConstraints().add(new ColumnConstraints(n));
        }


        this.goldPerc = goldPerc;
        this.gold = gold;
        this.stepsSetting = stepsSetting;
        //Oyuncu degerleri
        //A
        this.movementCostA = movementCostA;
        this.targetingCostA = targetingCostA;
        //B
        this.movementCostB = movementCostB;
        this.targetingCostB = targetingCostB;
        //C
        this.movementCostC = movementCostC;
        this.targetingCostC = targetingCostC;
        //D
        this.movementCostD = movementCostD;
        this.targetingCostD = targetingCostD;

        //Oyuncularin altin bilgisinin atanmasi
        //System.out.println(this.gold + " " +this.m);
        goldA.setText(Integer.toString(this.gold));
        goldB.setText(Integer.toString(this.gold));
        goldC.setText(Integer.toString(this.gold));
        goldD.setText(Integer.toString(this.gold));

    }

    public void goldCounter() {
        randomGoldCounter = ((m * n) * 20) / 100;
        randomSecretGoldCounter = (randomGoldCounter * 10) / 100;

    }

    public void goldSetter() {
        int points[] = {5, 10, 15, 20};
    }
}
