package io.github.alprKeskin.kasimpamuk.thesettlersofcatan;

import javafx.application.Application;
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

        BorderPane bpane = new BorderPane();
        bpane.setTop(new CustomPane("Top"));
        bpane.setRight(new CustomPane("Right"));
        bpane.setBottom(new CustomPane("Bottom"));
        bpane.setLeft(new CustomPane("Left"));
        bpane.setCenter(new CustomPane("Center"));

        Scene scene2 = new Scene(bpane);
        Stage stage2 = new Stage();
        stage2.setTitle("ShowBorderPane");
        stage2.setScene(scene2);
        stage2.show();

    }

    class CustomPane extends StackPane {
        public CustomPane(String title) {
            getChildren().add(new Label(title));
            setStyle("-fx-border-color: red");
            setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        }
    }

    // You may add other JavaFX-specific methods or components here
}
