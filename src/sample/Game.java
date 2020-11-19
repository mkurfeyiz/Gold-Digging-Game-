package sample;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
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


    public void getSettings(int m, int n, int goldPerc, int gold, int stepsSetting, int movementCostA, int targetingCostA
            , int movementCostB, int targetingCostB, int movementCostC, int targetingCostC, int movementCostD, int targetingCostD) {
        //Oyun degerleri
        this.m = m;
        this.n = n;
        //Oyun Tahtasi
        this.gameBoard = new int[m][n];
        goldLabels = new ArrayList<>(m * n);
        goldCounter();
        setGameBoard();
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
        int index1, index2, pointIndex, counter = 0, indexCount = 0;
        //Creating labels to show matrix values on gridpane
        for (int i = 0; i < m * n; i++) {

            goldLabels.add(new Label("0"));
        }

        //Initializing first values for gameBoard
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                gameBoard[i][j] = 0;
            }
        }

        //Initializing gold positions
        while (counter < randomGoldCounter) {

            //random position for golds
            index1 = random.nextInt(m);
            index2 = random.nextInt(n);

            if (((index1 != 0 && index2 != 0) && (index1 != 0 && index2 != n) && (index1 != m && index2 != 0) && (index1 != m && index2 != n))
            ) {
                //random value for golds
                pointIndex = random.nextInt(4);
                gameBoard[index1][index2] = points[pointIndex];
                counter++;
            }

        }
        counter = 0;
        //Set the value of secret golds to 1
        while (counter < randomSecretGoldCounter) {

            //random position for golds
            index1 = random.nextInt(m);
            index2 = random.nextInt(n);

            if (((index1 != 0 && index2 != 0) && (index1 != 0 && index2 != n) && (index1 != m && index2 != 0) && (index1 != m && index2 != n))
                    && gameBoard[index1][index2] != 0) {

                gameBoard[index1][index2] = 1;
                counter++;
            }

        }


        //Showing matrix values on gridpane
        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {
                if (gameBoard[i][j] == 1 || gameBoard[i][j] != 0) {
                    goldLabels.get(indexCount).setText(Integer.toString(gameBoard[i][j]));
                    board.add(goldLabels.get(indexCount), j, i);
                    board.setAlignment(Pos.CENTER);
                    indexCount++;
                } else if (((i == 0 && j == 0))) {
                    goldLabels.get(indexCount).setText("A");
                    board.add(goldLabels.get(indexCount), j, i);
                    indexCount++;
                } else if ((i == 0 && j == n - 1)) {
                    goldLabels.get(indexCount).setText("B");
                    board.add(goldLabels.get(indexCount), j, i);
                    indexCount++;
                } else if ((i == m - 1 && j == 0)) {
                    goldLabels.get(indexCount).setText("C");
                    board.add(goldLabels.get(indexCount), j, i);
                    indexCount++;
                } else if ((i == m - 1 && j == n - 1)) {
                    goldLabels.get(indexCount).setText("D");
                    board.add(goldLabels.get(indexCount), j, i);
                    indexCount++;
                } else {
                    indexCount++;
                }
            }
        }

        /*for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                System.out.print(gameBoard[i][j]+" ");
            }
            System.out.println();
        }*/

    }

    public void movementA() {

        int startColumnA = 0;
        int startRowA = 0;
        int var;
        int closestPoint[][];
        int i = 0, j = 0;

        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                if (gameBoard[i][j] != 0) {
                    System.out.println("selam");
                }
            }

        }
    }
}
