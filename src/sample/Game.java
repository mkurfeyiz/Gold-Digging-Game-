package sample;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
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

    Label goldLabels[][];

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

        //Players
        A = new A(movementCostA, targetingCostA, stepsSetting, gold, 0, 0, m, n);
        B = new B(movementCostB, targetingCostB, stepsSetting, gold, 0, this.n - 1, m, n);
        C = new C(movementCostC, targetingCostC, stepsSetting, gold, this.m - 1, 0, m, n);
        D = new D(movementCostD, targetingCostD, stepsSetting, gold, this.m - 1, this.n - 1, m, n);


        //Oyun Tahtasi
        this.gameBoard = new int[m][n];
        this.goldLabels = new Label[m][n];
        goldCounter();
        setGameBoard();

    }

    public void goldCounter() {
        randomGoldCounter = ((m * n) * 20) / 100;
        randomSecretGoldCounter = (randomGoldCounter * 10) / 100;

    }

    public void setGameBoard() {
        Random random = new Random();
        int index1, index2, pointIndex, counter = 0;
        int secretCounter = 0;

        //Creating labels to show matrix values on gridpane
        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                goldLabels[i][j] = new Label("1");

            }

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

            if (index1 != A.getIndexI() && index2 != A.getIndexJ() &&
                    index1 != B.getIndexI() && index2 != B.getIndexJ() &&
                    index1 != C.getIndexI() && index2 != C.getIndexJ() && index1 != D.getIndexI() && index2 != D.getIndexJ()) {
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

                if (gameBoard[i][j] != 1) {

                    goldLabels[i][j].setText(Integer.toString(gameBoard[i][j]));
                    board.add(goldLabels[i][j], j, i);

                } else if (i == A.getIndexI() && j == A.getIndexJ()) {

                    goldLabels[i][j].setText("A");
                    board.add(goldLabels[i][j], j, i);

                } else if (i == B.getIndexI() && j == B.getIndexJ()) {

                    goldLabels[i][j].setText("B");
                    board.add(goldLabels[i][j], j, i);

                } else if (i == C.getIndexI() && j == C.getIndexJ()) {

                    goldLabels[i][j].setText("C");
                    board.add(goldLabels[i][j], j, i);

                } else if (i == D.getIndexI() && j == D.getIndexJ()) {

                    goldLabels[i][j].setText("D");
                    board.add(goldLabels[i][j], j, i);

                }
            }
        }

    }

    public void updateGameBoard() {

        //Showing matrix values on gridpane
        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                if (gameBoard[i][j] != 1) {

                    goldLabels[i][j].setText(Integer.toString(gameBoard[i][j]));

                } else if ((i == A.getIndexI() && j == A.getIndexJ())) {

                    goldLabels[i][j].setText("A");

                } else if ((i == B.getIndexI() && j == B.getIndexJ())) {

                    goldLabels[i][j].setText("B");

                } else if ((i == C.getIndexI() && j == C.getIndexJ())) {

                    goldLabels[i][j].setText("C");

                } else if ((i == D.getIndexI() && j == D.getIndexJ())) {

                    goldLabels[i][j].setText("D");

                }
            }
        }

        /*for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                if (goldLabels[i][j].getText().matches("A") && i != A.getTargetI() && j != A.getTargetJ()) {

                    goldLabels[i][j].setText("");

                } else if (goldLabels[i][j].getText().matches("B") && i != B.getTargetI() && j != B.getTargetJ()) {

                    goldLabels[i][j].setText("");

                } else if (goldLabels[i][j].getText().matches("C") && i != C.getTargetI() && j != C.getTargetJ()) {

                    goldLabels[i][j].setText("");

                } else if (goldLabels[i][j].getText().matches("D") && i != D.getTargetI() && j != D.getTargetJ()) {

                    goldLabels[i][j].setText("");

                }

            }
        }*/

        goldA.setText(Integer.toString(A.getGold()));
        goldB.setText(Integer.toString(B.getGold()));
        goldC.setText(Integer.toString(C.getGold()));
        goldD.setText(Integer.toString(D.getGold()));

        stepsA.setText(Integer.toString(A.getStepsCount()));
        stepsB.setText(Integer.toString(B.getStepsCount()));
        stepsC.setText(Integer.toString(C.getStepsCount()));
        stepsD.setText(Integer.toString(D.getStepsCount()));

    }

    public boolean tableGoldChecker(boolean var) {
        int i;
        int j;
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                if (gameBoard[i][j] != 1) {
                    var = true;
                    break;
                } else {
                    var = false;
                }
            }

            if (var) {
                break;
            }
        }
        return var;
    }

    public boolean playerGoldChecker(boolean param, int goldA, int goldB, int goldC, int goldD) {

        if (goldA <= 0 && goldB <= 0 && goldC <= 0 && goldD <= 0) {
            param = false;
        } else {
            param = true;
        }
        return param;
    }

    public void startGame() {

        boolean param = true;

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (!playerGoldChecker(param, A.getGold(), B.getGold(), C.getGold(), D.getGold())) {
            System.out.println("----------------------");
            alert.setTitle("Oyun Sonu");
            alert.setHeaderText(null);
            alert.setContentText("Tüm oyuncuların altını bittiği için oyun sona erdi!");
            alert.showAndWait();
        } else if (!tableGoldChecker(param)) {
            System.out.println("----------------------");

            for (int i = 0; i < m; i++) {

                for (int j = 0; j < n; j++) {
                    System.out.print(gameBoard[i][j] + " ");
                }
                System.out.println();
            }

            alert.setTitle("Oyun Sonu");
            alert.setHeaderText(null);
            alert.setContentText("Oyun tahtasındaki altınlar bittiği için oyun sona erdi!");
            alert.showAndWait();
        } else {

            //Oyuncunun flagi false ise hedef sectir,true ise hareket ettir.Oyuncu hedefe ulasirsa flagi false yap.
            //A
            if (!A.getFlag() && A.getGold() > 0) {
                A.selectTarget(gameBoard, A.getTargetCost(), A.getIndexI(), A.getIndexJ());
            } else if (A.getGold() > 0 && A.getFlag()) {
                A.movement(gameBoard, B, C, D);
            } else {
                System.out.println("A oyuncusu elendi.");
            }
            //B
            if (!B.getFlag() && B.getGold() > 0) {
                B.selectTarget(gameBoard, B.getTargetCost(), B.getIndexI(), B.getIndexJ());
            } else if (B.getGold() > 0 && B.getFlag()) {
                B.movement(gameBoard, A, C, D);
            } else {
                System.out.println("B oyuncusu elendi.");
            }
            //C
            if (!C.getFlag() && C.getGold() > 0) {
                C.selectTarget(gameBoard, C.getTargetCost(), C.getIndexI(), C.getIndexJ());
            } else if (C.getGold() > 0 && C.getFlag()) {
                C.movement(gameBoard, A, B, D);
            } else {
                System.out.println("C oyuncusu elendi.");
            }
            //D
            if (!D.getFlag() && D.getGold() > 0) {
                D.selectTarget(gameBoard, D.getTargetCost(), D.getIndexI(), D.getIndexJ(), A, B, C);
            } else if (D.getGold() > 0 && D.getFlag()) {
                D.movement(gameBoard, A, B, C);
            } else {
                System.out.println("D oyuncusu elendi.");
            }

            A.log(A);
            B.log(B);
            C.log(C);
            D.log(D);

            updateGameBoard();

        }


    }
}
