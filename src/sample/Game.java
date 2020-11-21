package sample;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import sample.Players.*;

import java.util.ArrayList;
import java.util.Random;

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

    ArrayList<Label> goldLabels;

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
    int points[] = {5, 10, 15, 20};

    //Players
    A A;
    B B;
    C C;
    D D;


    public void getSettings(int m, int n, int goldPerc, int gold, int stepsSetting, int movementCostA, int targetingCostA
            , int movementCostB, int targetingCostB, int movementCostC, int targetingCostC, int movementCostD, int targetingCostD) {
        //Oyun degerleri
        this.m = m;
        this.n = n;

        //Players
        A = new A(movementCostA, targetingCostA, stepsSetting, gold, 0, 0, m, n);
        B = new B(movementCostB, targetingCostB, stepsSetting, gold, 0, 5, m, n);
        C = new C(movementCostC, targetingCostC, stepsSetting, gold, 0, 15, m, n);
        D = new D(movementCostD, targetingCostD, stepsSetting, gold, 0, 10, m, n);

        //Setting first positions for players


        //Oyun Tahtasi
        this.gameBoard = new int[m][n];
        goldLabels = new ArrayList<>(m * n);
        goldCounter();
        setGameBoard();

        A.selectTarget(gameBoard,targetingCostA,A.getIndexI(),A.getIndexJ());
        B.selectTarget(gameBoard,targetingCostB,B.getIndexI(),B.getIndexJ());
        C.selectTarget(gameBoard,targetingCostC,C.getIndexI(),C.getIndexJ());
        D.selectTarget(gameBoard,targetingCostD,D.getIndexI(),D.getIndexJ(),A,B,C);


        updateGameBoard();

        /*for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                System.out.print(gameBoard[i][j]+" ");
            }
            System.out.println();
        }*/

        for (int i = 0; i < m; i++) {

            this.board.getRowConstraints().add(new RowConstraints(30));

        }
        for (int j = 0; j < n; j++) {

            this.board.getColumnConstraints().add(new ColumnConstraints(30));

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

    public void setGameBoard() {
        Random random = new Random();
        int index1, index2, pointIndex, counter = 0;
        int  indexCount = 0, secretCounter = 0;
        //Creating labels to show matrix values on gridpane
        for (int i = 0; i < m * n; i++) {

            goldLabels.add(new Label("1"));
            goldLabels.get(i).setAlignment(Pos.CENTER);
        }

        //Initializing first values for gameBoard
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                gameBoard[i][j] = 1;
            }
        }

        //Initializing gold positions
        while (counter < randomGoldCounter) {

            //random position for golds
            index1 = random.nextInt(m);
            index2 = random.nextInt(n);

            if ((index1 != A.getIndexI() && index2 != A.getIndexJ() &&
                    index1 != B.getIndexI() && index2 != B.getIndexJ() &&
                    index1 != C.getIndexI() && index2 != C.getIndexJ() && index1 != D.getIndexI() && index2 != D.getIndexJ())
            ) {
                //random value for golds
                pointIndex = random.nextInt(4);
                gameBoard[index1][index2] = points[pointIndex];
                counter++;
            }

        }

        //Set the value of secret golds to 50
        while (secretCounter < randomSecretGoldCounter) {

            //random position for golds
            index1 = random.nextInt(m);
            index2 = random.nextInt(n);

            if (gameBoard[index1][index2] != 1) {

                gameBoard[index1][index2] = 50;
                secretCounter++;
            }

        }


        //Showing matrix values on gridpane
        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {
                if (gameBoard[i][j] == 50 || gameBoard[i][j] != 1) {
                    goldLabels.get(indexCount).setText(Integer.toString(gameBoard[i][j]));
                    board.add(goldLabels.get(indexCount), j, i);
                    board.setAlignment(Pos.CENTER);
                    indexCount++;
                } else if ((i == A.getIndexI() && j == A.getIndexJ())) {
                    goldLabels.get(indexCount).setText("A");
                    board.add(goldLabels.get(indexCount), j, i);
                    indexCount++;
                } else if ((i == B.getIndexI() && j == B.getIndexJ())) {
                    goldLabels.get(indexCount).setText("B");
                    board.add(goldLabels.get(indexCount), j, i);
                    indexCount++;
                } else if ((i == C.getIndexI() && j == C.getIndexJ())) {
                    goldLabels.get(indexCount).setText("C");
                    board.add(goldLabels.get(indexCount), j, i);
                    indexCount++;
                } else if ((i == D.getIndexI() && j == D.getIndexJ())) {
                    goldLabels.get(indexCount).setText("D");
                    board.add(goldLabels.get(indexCount), j, i);
                    indexCount++;
                } else {
                    indexCount++;
                }
            }
        }

    }

    public void updateGameBoard(){

        int  indexCount = 0;
        //Showing matrix values on gridpane
        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {
                if (gameBoard[i][j] == 50 || gameBoard[i][j] != 1) {
                    goldLabels.get(indexCount).setText(Integer.toString(gameBoard[i][j]));
                    board.setAlignment(Pos.CENTER);
                    indexCount++;
                } else if(i == A.getIndexI() && j == A.getIndexJ()){
                    goldLabels.get(indexCount).setText("A");
                    board.setAlignment(Pos.CENTER);
                    indexCount++;
                } else if(i == B.getIndexI() && j == B.getIndexJ()){
                    goldLabels.get(indexCount).setText("B");
                    board.setAlignment(Pos.CENTER);
                    indexCount++;
                } else if(i == C.getIndexI() && j == C.getIndexJ()){
                    goldLabels.get(indexCount).setText("C");
                    board.setAlignment(Pos.CENTER);
                    indexCount++;
                } else if(i == D.getIndexI() && j == D.getIndexJ()){
                    goldLabels.get(indexCount).setText("D");
                    board.setAlignment(Pos.CENTER);
                    indexCount++;
                } else {
                    indexCount++;
                }
            }
        }

    }

    public boolean tableGoldChecker(boolean var) {
        int i;
        int j;
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                if (gameBoard[i][j] != 1) {
                    var = true;
                } else {
                    var = false;
                }
            }
        }
        return var;
    }

    public boolean playerGoldChecker(boolean param, int goldA, int goldB, int goldC, int goldD) {

        if (goldA == 0 && goldB == 0 && goldC == 0 && goldD == 0) {
            param = false;
        } else {
            param = true;
        }
        return param;
    }

    public void startGame() {

        boolean param = true;
        int goldForA = gold;
        int goldForB = gold;
        int goldForC = gold;
        int goldForD = gold;

        playerGoldChecker(param, goldForA, goldForB, goldForC, goldForD);
        tableGoldChecker(param);

        while (param == true) {

            playerGoldChecker(param, goldForA, goldForB, goldForC, goldForD);
            tableGoldChecker(param);
        }
    }
}
