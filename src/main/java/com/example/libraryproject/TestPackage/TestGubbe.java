package com.example.libraryproject.TestPackage;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TestGubbe extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Group root = new Group();

        Rectangle back = new Rectangle(640,330);
        back.setFill(Color.DEEPSKYBLUE);

        Circle bottom = new Circle(320,280,60); //Skriv in kordinater och storlek
        bottom.setStroke(Color.WHITE);
        bottom.setStrokeWidth(10);
        bottom.setFill(Color.WHITE);

        Circle middle = new Circle(320,190,40); //Skriv in kordinater och storlek
        middle.setStroke(Color.WHITE);
        middle.setStrokeWidth(10);
        middle.setFill(Color.WHITE);

        Circle top = new Circle(320,125,25); //Skriv in kordinater och storlek
        top.setStroke(Color.WHITE);
        top.setStrokeWidth(10);
        top.setFill(Color.WHITE);

        Circle button1 = new Circle(320,190,3); //Skriv in kordinater och storlek
        button1.setStroke(Color.BLACK);
        button1.setStrokeWidth(2);
        button1.setFill(Color.BLACK);

        Circle button2 = new Circle(320,210,3); //Skriv in kordinater och storlek
        button2.setStroke(Color.BLACK);
        button2.setStrokeWidth(2);
        button2.setFill(Color.BLACK);

        Circle button3 = new Circle(320,170,3); //Skriv in kordinater och storlek
        button3.setStroke(Color.BLACK);
        button3.setStrokeWidth(2);
        button3.setFill(Color.BLACK);

        Circle eye1 = new Circle(310,125,4); //Skriv in kordinater och storlek
        eye1.setStroke(Color.BLACK);
        eye1.setStrokeWidth(1);
        eye1.setFill(Color.BLACK);

        Circle eye2 = new Circle(330,125,4); //Skriv in kordinater och storlek
        eye2.setStroke(Color.BLACK);
        eye1.setStrokeWidth(1);
        eye1.setFill(Color.BLACK);

        Line smile = new Line(305,137, 335,137);
        smile.setFill(Color.BLACK);
        smile.setStrokeWidth(2);

        Circle sun = new Circle(550,60,50); //Skriv in kordinater och storlek
        sun.setStroke(Color.YELLOW);
        sun.setStrokeWidth(5);
        sun.setFill(Color.YELLOW);

        //Hoppas det fungerar

        root.getChildren().addAll(back,bottom,middle,top,button1,button2,button3,eye1,eye2,smile,sun);

        Scene scene = new Scene(root, 640, 480);
        primaryStage.setTitle("Snowman");
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}


