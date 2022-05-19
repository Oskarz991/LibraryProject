package com.example.libraryproject.LibProject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Scenery extends Application {

        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
//Funkar detta?
            VBox root = new VBox();
            root.setSpacing(5);
            root.setPadding(new Insets(5, 5, 10, 5));
            Label titleLibrarian = new Label("Login as a libriarian");
            Label titleMember = new Label("Login as a member");
            Label titleNewAccount = new Label("Register new account");
            titleLibrarian.setFont(new Font("Fira Sans", 32));
            titleMember.setFont(new Font("Fira Sans", 32));
            titleNewAccount.setFont(new Font("Fira Sans", 32));

            SplitPane splitPane = new SplitPane();

            VBox leftControl  = new VBox(new Label("Left Control"));
            VBox rightControl = new VBox(new Label("Right Control"));

            GridPane paneLeft = new GridPane();
            paneLeft.setAlignment(Pos.BASELINE_LEFT);
            paneLeft.setHgap(5);
            paneLeft.setVgap(5);

            GridPane paneRight = new GridPane();
            paneRight.setAlignment(Pos.BASELINE_LEFT);
            paneRight.setHgap(5);
            paneRight.setVgap(5);

            //Logga in som medlem
            Label nameMemberLbl = new Label("Name:");
            TextField nameMemberTxt = new TextField();
            Label idMemberLbl = new Label("ID-number:");
            TextField idMemberTxt = new TextField();

            //Skapa nytt konto
            Label surnameMemberLbl = new Label("Surname:");
            TextField surnameMemberTxt = new TextField();
            surnameMemberLbl.setVisible(false);
            Label personalNrMemberLbl = new Label("Personal Number:");
            TextField personalNrMemberTxt = new TextField();
            personalNrMemberLbl.setVisible(false);
            personalNrMemberTxt.setVisible(false);

            //Lägg till en ny title

            Button registerNewAccount = new Button("Register new account ");
            Button loginAsAMember = new Button("Login as a member");
            loginAsAMember.setVisible(false);

            Button loginBtn = new Button("Login ");
            Button registerBtn = new Button("Register account");
            registerBtn.setVisible(false);

            ComboBox<String> artister = new ComboBox<>();

            registerNewAccount.setOnAction(e-> {
                nameMemberTxt.clear(); idMemberTxt.clear();
                registerNewAccount.setVisible(false); loginAsAMember.setVisible(true);
                loginBtn.setVisible(false); registerBtn.setVisible(true);
                nameMemberTxt.setVisible(true); nameMemberTxt.setVisible(true);
                idMemberLbl.setVisible(false); idMemberTxt.setVisible(false);
                surnameMemberLbl.setVisible(true); surnameMemberTxt.setVisible(true);
                personalNrMemberLbl.setVisible(true); personalNrMemberTxt.setVisible(true);
            });

            loginAsAMember.setOnAction(e-> {
                nameMemberTxt.clear();
                registerNewAccount.setVisible(true); loginAsAMember.setVisible(false);
                loginBtn.setVisible(true); registerBtn.setVisible(false);
                nameMemberTxt.setVisible(true); nameMemberTxt.setVisible(true);
                idMemberLbl.setVisible(true); idMemberTxt.setVisible(true);
                surnameMemberLbl.setVisible(false); surnameMemberTxt.setVisible(false);
                personalNrMemberLbl.setVisible(false); personalNrMemberTxt.setVisible(false);

            });
            //Lägg till på vänster sida
            paneLeft.add(titleLibrarian,0,0);
            paneLeft.add(artister,0,1);


            //Lägg till på höger sida
            paneRight.add(titleMember,0,0);
            paneRight.add(loginAsAMember,0,1); paneRight.add(registerNewAccount,0,1);
            paneRight.add(nameMemberLbl,0,2); paneRight.add(nameMemberTxt,1,2);
            paneRight.add(idMemberLbl,0,3); paneRight.add(idMemberTxt,1,3);
            paneRight.add(personalNrMemberLbl,0,4); paneRight.add(personalNrMemberTxt,1,4);
            paneRight.add(surnameMemberLbl,0,3); paneRight.add(surnameMemberTxt,1,3);
            paneRight.add(loginBtn,1,6); paneRight.add(registerBtn,1,6);


            leftControl.getChildren().addAll(paneLeft);
            rightControl.getChildren().addAll(paneRight);

            splitPane.getItems().addAll(leftControl,rightControl);
            root.getChildren().addAll(splitPane);
            Scene scene = new Scene(root ,900,500);
            primaryStage.setTitle("Library");
            primaryStage.setScene(scene);
            primaryStage.show();


        }
}