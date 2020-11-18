package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    //Oyun ayarlari
    @FXML TextField matrixM,matrixN;
    @FXML TextField goldPercentage;
    @FXML TextField goldAmount;
    @FXML TextField stepsPerMovement;
    //Oyuncu ayarlari
    //A
    @FXML TextField moveCostA;
    @FXML TextField targetCostA;
    //B
    @FXML TextField moveCostB;
    @FXML TextField targetCostB;
    //C
    @FXML TextField moveCostC;
    @FXML TextField targetCostC;
    //D
    @FXML TextField moveCostD;
    @FXML TextField targetCostD;

    //Oyun degerleri
    int m,n;
    int goldPerc;
    int gold;
    int stepsSetting;
    //Oyuncu degerleri
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


    public void setNewSettings(){
        Alert alert;
        try {
            //Oyun ayarlari
            m = Integer.parseInt(matrixM.getText());
            n = Integer.parseInt(matrixN.getText());
            goldPerc = Integer.parseInt(goldPercentage.getText());
            gold = Integer.parseInt(goldAmount.getText());
            stepsSetting = Integer.parseInt(stepsPerMovement.getText());
            //Oyuncu Ayarlari
            //A
            movementCostA = Integer.parseInt(moveCostA.getText());
            targetingCostA = Integer.parseInt(targetCostA.getText());
            //B
            movementCostB = Integer.parseInt(moveCostB.getText());
            targetingCostB = Integer.parseInt(targetCostB.getText());
            //C
            movementCostC = Integer.parseInt(moveCostC.getText());
            targetingCostC = Integer.parseInt(targetCostC.getText());
            //D
            movementCostD = Integer.parseInt(moveCostD.getText());
            targetingCostD = Integer.parseInt(targetCostD.getText());

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ayarlar Hakkında");
            alert.setHeaderText(null);
            alert.setContentText("Değişiklikler Kaydedildi!");

            alert.showAndWait();

        } catch (Exception e) {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ayarlar Hakkında");
            alert.setHeaderText(null);
            alert.setContentText("Bir veya birden fazla değeri eksik girdiniz.Girdiğiniz değerleri lütfen kontrol edin ve tekrar deneyin");

            alert.showAndWait();
        }

    }

    public void defaultSettings(){

        // erdem özer

        //Oyun ayarlari
        m = 20;
        n = 20;
        goldPerc = 20;
        gold = 200;
        stepsSetting = 3;
        //Oyuncu ayarlari
        //A
        movementCostA = 5;
        targetingCostA = 5;
        //B
        movementCostB = 5;
        targetingCostB = 10;
        //C
        movementCostC = 5;
        targetingCostC = 15;
        //D
        movementCostD = 5;
        targetingCostD = 20;

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ayarlar Hakkında");
        alert.setHeaderText(null);
        alert.setContentText("Varsayılan ayarlar seçildi.");
        //System.out.println(gold + targetingCostD);

        alert.showAndWait();

    }

    //game.fxml e gecis
    public void startGame(ActionEvent event)throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("game.fxml"));
        Parent game = loader.load();
        Scene gameScene = new Scene(game);
        //Asagida gecis yapmak istedigimiz sayfanin controllerinin methodunu kullanarak bilgileri aktardik.
        Game controller = loader.getController();
        controller.getSettings(m,n,goldPerc,gold,stepsSetting,movementCostA,targetingCostA
        ,movementCostB,targetingCostB,movementCostC,targetingCostC,movementCostD,targetingCostD);
        //
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(gameScene);
        window.show();

    }

}
