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

            VBox root = new VBox();
            root.setSpacing(5);
            root.setPadding(new Insets(5, 5, 10, 5));
            Label title = new Label("MusikDB Insättare");
            title.setFont(new Font("Fira Sans", 32));

            GridPane pane = new GridPane();
            pane.setAlignment(Pos.BASELINE_LEFT);
            pane.setHgap(5);
            pane.setVgap(5);

            Label lblName = new Label("Namn:");
            TextField name = new TextField();
            Label lblUtgvAr = new Label("Utgivningsår:");
            TextField utgvAr = new TextField();

            Label lblBildades = new Label("Bildades:");
            TextField bildades = new TextField();
            Label lblGenre = new Label("Genre:");
            TextField genre = new TextField();

            lblBildades.setVisible(false);
            bildades.setVisible(false);
            lblGenre.setVisible(false);
            genre.setVisible(false);

            Button skapaArtist = new Button("Skapa ny artist ");
            Button skapaArtist2 = new Button("Skapa ny album ");
            skapaArtist2.setVisible(false);

            Button insertAlbum = new Button("Sätt in ");
            Label albumTillad = new Label();
            Button insertArtist = new Button("Lägg till ");
            insertArtist.setVisible(false);

            ComboBox<String> artister = new ComboBox<>();

            skapaArtist.setOnAction(e-> {
                name.clear(); utgvAr.clear();
                skapaArtist.setVisible(false); skapaArtist2.setVisible(true);
                insertAlbum.setVisible(false); insertArtist.setVisible(true);
                lblName.setVisible(true); name.setVisible(true);
                lblUtgvAr.setVisible(false); utgvAr.setVisible(false);
                lblBildades.setVisible(true);bildades.setVisible(true);
                lblGenre.setVisible(true);genre.setVisible(true);
            });

            skapaArtist2.setOnAction(e-> {
                name.clear(); bildades.clear(); genre.clear();
                skapaArtist.setVisible(true); skapaArtist2.setVisible(false);
                insertAlbum.setVisible(true); insertArtist.setVisible(false);
                lblName.setVisible(true); name.setVisible(true);
                lblUtgvAr.setVisible(true); utgvAr.setVisible(true);
                lblBildades.setVisible(false);bildades.setVisible(false);
                lblGenre.setVisible(false);genre.setVisible(false);
            });

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("Driver loaded");
            } catch (ClassNotFoundException ex) {
                System.out.println("Driver did not load");
            }

            pane.add(artister,0,0); pane.add(skapaArtist,1,0); pane.add(skapaArtist2,1,0);
            pane.add(lblName,0,1); pane.add(name,1,1);
            pane.add(lblUtgvAr,0,2); pane.add(utgvAr,1,2);
            pane.add(lblBildades,0,2); pane.add(bildades,1,2);
            pane.add(lblGenre,0,3); pane.add(genre,1,3);
            pane.add(insertAlbum,0,4); pane.add(insertArtist,0,4);



            root.getChildren().addAll(title,albumTillad,pane);
            Scene scene = new Scene(root ,500,400);
            primaryStage.setTitle("Insättning");
            primaryStage.setScene(scene);
            primaryStage.show();


        }
}