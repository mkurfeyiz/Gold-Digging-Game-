package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.Players.*;

import java.io.FileWriter;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Altın Toplama");
        primaryStage.setScene(new Scene(root, 960, 610));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        Players a = new A(2,2,3,200);
        Players b = new B(2,2,3,300);
        Players c = new C(2,2,3,400);
        Players d = new D(2,2,3,500);
        try {
            a.log(a);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
