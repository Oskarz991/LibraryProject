package com.example.libraryproject.LibProject;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

    public class Scenery extends Application {

        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) {

            Group layout = new Group();
            GridPane pane = new GridPane();
            pane.setAlignment(Pos.TOP_LEFT);
            pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.4));
            pane.setHgap(10);
            pane.setVgap(10);

            //Logga in som bibliotikarie
            pane.add(new Label("Login as Librarian"),0,0);
            pane.add(new Label("Name"), 0, 1);
            final TextField insertName = new TextField();
            pane.add(insertName, 0, 2);

            pane.add(new Label("ID"), 1, 1);
            final TextField insertId = new TextField();
            pane.add(insertId, 1, 2);

            Button loginButtonB = new Button("Login");
            pane.add(loginButtonB, 2, 1);
            GridPane.setHalignment(loginButtonB, HPos.LEFT);

            Line breakline = new Line(220, 145, 0, 145);
            breakline.setStrokeWidth(1.5);
            breakline.setFill(Color.BLACK);

            //Logga in som medelm
            pane.add(new Label("Login as member"),0,4);
            pane.add(new Label("Name"), 0, 5);
            final TextField insertNamee = new TextField();
            pane.add(insertNamee, 0, 6);

            pane.add(new Label("ID"), 1, 5);
            final TextField insertIdd = new TextField();
            pane.add(insertIdd, 1, 6);

            Button loginButton = new Button("Login");
            pane.add(loginButton, 2, 5);
            GridPane.setHalignment(loginButtonB ,HPos.LEFT);

        /*    pane.add(new Label("ID"), 1, 1);
            final TextField insertId = new TextField();
            pane.add(insertId, 1, 2);

         */

            layout.getChildren().addAll(breakline,pane);
            Scene scene = new Scene(layout, 640, 480);
            primaryStage.setTitle("Snowman");
            primaryStage.setScene(scene);
            primaryStage.show();


        }
    }