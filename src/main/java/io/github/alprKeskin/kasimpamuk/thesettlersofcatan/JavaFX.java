package io.github.alprKeskin.kasimpamuk.thesettlersofcatan;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;

public class JavaFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        Button btn = new Button("Click me!");
        btn.setStyle("-fx-background-color: orange; -fx-text-fill: purple;");
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 200);

        primaryStage.setTitle("JavaFX Application");
        primaryStage.setScene(scene);
        primaryStage.show();

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11, 12, 13, 14));
        pane.setHgap(5);
        pane.setVgap(5);

        pane.add(new Label("Email:"), 0, 0);
        pane.add(new TextField(), 1, 0);
        pane.add(new Label("Password:"), 0, 1);
        pane.add(new TextField(), 1, 1);
        Button btAdd = new Button("Add User");
        pane.add(btAdd, 1, 2);
        GridPane.setHalignment(btAdd, HPos.RIGHT);


        Stage stage = new Stage();
        stage.setTitle("Register User");
        stage.setScene(new Scene(pane, 300, 200));
        stage.show();

        MyPolygon polygon = new MyPolygon();



        Scene scene2 = new Scene(polygon, 200, 200);
        Stage stage2 = new Stage();
        stage2.setTitle("Polygon");
        stage2.setScene(scene2);
        stage2.show();


    }

    class MyPolygon extends Pane {
        private void paint() {
            Polygon polygon = new Polygon();
            polygon.setFill(Color.GOLD);
            polygon.setStroke(Color.BLACK);
            ObservableList<Double> list = polygon.getPoints();

            double centerX = getWidth() / 2, centerY = getHeight() / 2;
            double radius = Math.min(getWidth(), getHeight()) * 0.4;

            for (int i = 0; i < 6; i++) {
                list.add(centerX + radius * Math.cos(2 * i * Math.PI / 6));
                list.add(centerY - radius * Math.sin(2 * i * Math.PI / 6));
            }

            getChildren().clear();
            getChildren().add(polygon);

        }

        @Override
        public void setWidth(double width) {
            super.setWidth(width);
            paint();
        }

        @Override
        public void setHeight(double height) {
            super.setHeight(height);
            paint();
        }
    }

    // You may add other JavaFX-specific methods or components here
}
