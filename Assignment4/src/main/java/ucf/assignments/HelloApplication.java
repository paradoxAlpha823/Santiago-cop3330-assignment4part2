/*
 *  UCF COP3330 Fall 2021 Assignment 4 Part 2 Solution
 *  Copyright 2021 Diego Santiago
 */

//Warning, please do not try to resize the window

package ucf.assignments;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;


import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/assignment4/itempage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("ToDo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}