package com.example.libraryproject.LibProject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
//Funkar detta?
            VBox root = new VBox();
            root.setSpacing(5);
            root.setPadding(new Insets(5, 5, 10, 5));
            Label title = new Label("Library system");
            title.setFont(new Font("Fira Sans", 32));

            GridPane pane = new GridPane();
            pane.setAlignment(Pos.BASELINE_LEFT);
            pane.setHgap(5);
            pane.setVgap(5);

            Label lblBookTitle = new Label("Book title:");
            TextField name = new TextField();
            Label lblISPM = new Label("ISPM:");
            TextField utgvAr = new TextField();

            //Lägg till en ny title
            Label lblBildades = new Label("Name:");
            TextField bildades = new TextField();
            Label lblPersonalNr = new Label("Personal number:");
            TextField genre = new TextField();

            lblBildades.setVisible(false);
            bildades.setVisible(false);
            lblPersonalNr.setVisible(false);
            genre.setVisible(false);

            Button searchForBook = new Button("Search for book by title ");
            Button skapaArtist2 = new Button("Register new account");
            skapaArtist2.setVisible(false);

            Button insertAlbum = new Button("Search book ");
            Label albumTillad = new Label();
            Button insertArtist = new Button("Register account");
            insertArtist.setVisible(false);

            ComboBox<String> artister = new ComboBox<>();

            searchForBook.setOnAction(e-> {
                name.clear(); utgvAr.clear();
                searchForBook.setVisible(false); skapaArtist2.setVisible(true);
                insertAlbum.setVisible(false); insertArtist.setVisible(true);
                lblBookTitle.setVisible(true); name.setVisible(true);
                lblISPM.setVisible(false); utgvAr.setVisible(false);
                lblBildades.setVisible(true);bildades.setVisible(true);
                lblPersonalNr.setVisible(true);genre.setVisible(true);
            });

            skapaArtist2.setOnAction(e-> {
                name.clear(); bildades.clear(); genre.clear();
                searchForBook.setVisible(true); skapaArtist2.setVisible(false);
                insertAlbum.setVisible(true); insertArtist.setVisible(false);
                lblBookTitle.setVisible(true); name.setVisible(true);
                lblISPM.setVisible(true); utgvAr.setVisible(true);
                lblBildades.setVisible(false);bildades.setVisible(false);
                lblPersonalNr.setVisible(false);genre.setVisible(false);
            });

            pane.add(artister,0,0); pane.add(searchForBook,1,0); pane.add(skapaArtist2,1,0);
            pane.add(lblBookTitle,0,1); pane.add(name,1,1);
            pane.add(lblISPM,0,2); pane.add(utgvAr,1,2);
            pane.add(lblBildades,0,2); pane.add(bildades,1,2);
            pane.add(lblPersonalNr,0,3); pane.add(genre,1,3);
            pane.add(insertAlbum,0,4); pane.add(insertArtist,0,4);

            root.getChildren().addAll(title,albumTillad,pane);
            Scene scene = new Scene(root ,500,400);
            primaryStage.setTitle("Library");
            primaryStage.setScene(scene);
            primaryStage.show();


        }
}