package com.example.libraryproject.LibProject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

    public class Scenery extends Application {

        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) {

            primaryStage.setTitle("Library");
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.TOP_LEFT);
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(25, 25, 25, 25));

            Text login_as_a_librarian = new Text("Login as a Librarian");
            login_as_a_librarian.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            grid.add(login_as_a_librarian, 0, 0, 2, 1);

            Label userName = new Label("User Name:");
            grid.add(userName, 0, 1);

            TextField userTextField = new TextField();
            grid.add(userTextField, 1, 1);

            Label pw = new Label("Password:");
            grid.add(pw, 0, 2);

            PasswordField pwBox = new PasswordField();
            grid.add(pwBox, 1, 2);

            Button btn = new Button("Sign in");
            HBox hbBtn = new HBox(10);
            hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
            hbBtn.getChildren().add(btn);
            grid.add(hbBtn, 1, 4);

            final Text actiontarget = new Text();
            grid.add(actiontarget, 1, 6);

            btn.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent e) {
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("You are now logged in");
                }
            });

            Text searchBook = new Text("Search after book");
            grid.add(searchBook, 0, 0, 3, 3);

            Label bookName = new Label("Book name");
            grid.add(bookName, 3, 3);

            TextField bookNameText = new TextField();
            grid.add(bookNameText, 3, 1);

            Button btnn = new Button("Sign in");
            HBox hbBtnn = new HBox(10);
            hbBtnn.setAlignment(Pos.BOTTOM_RIGHT);
            hbBtnn.getChildren().add(btnn);
            grid.add(hbBtnn, 3, 4);

            final Text actiontargett = new Text();
            grid.add(actiontargett, 3, 6);

            btnn.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent e) {
                    actiontargett.setFill(Color.FIREBRICK);
                    actiontargett.setText("You are now logged in");
                }
            });

            Scene scene = new Scene(grid, 700, 500);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }