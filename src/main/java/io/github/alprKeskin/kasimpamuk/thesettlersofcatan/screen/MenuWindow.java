package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.screen;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.screen.gameboard.GameBoard;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lombok.Getter;

@Getter
public class MenuWindow {

    private final GridPane pane;
    private Scene registerScene;

    public MenuWindow() {
        this.pane = new GridPane();
        this.registerScene = new Scene(this.pane, 700, 700);
    }

    public void display() {

        initializeWindowConfigurations();

        createRegistrationForm();

        createRegistrationButton();


    }

    private void initializeWindowConfigurations() {
        this.pane.setAlignment(Pos.CENTER);
        this.pane.setPadding(new Insets(11, 12, 13, 14));
        this.pane.setHgap(5);
        this.pane.setVgap(5);
    }

    private void createRegistrationForm() {
        this.pane.add(new Label("Email:"), 0, 0);
        this.pane.add(new TextField(), 1, 0);
        this.pane.add(new Label("Password:"), 0, 1);
        this.pane.add(new TextField(), 1, 1);
    }

    private void createRegistrationButton() {
        Button btAdd = new Button("Add User");
        Button btPlay = new Button("Play");
        btPlay.setPrefWidth(100);
        btPlay.setPrefHeight(50);
        btPlay.setStyle("-fx-background-color: #FF0000; -fx-text-fill: #FFFFFF; -fx-font-size: 20px;");
        btPlay.setOnAction(e -> { // change scene
            GameBoard gameBoard = new GameBoard();
            gameBoard.display();
            Stage stage = (Stage) btAdd.getScene().getWindow();
            stage.setTitle("Catan Board");
            stage.setScene(gameBoard.getCatanScene());
        });
        this.pane.add(btAdd, 1, 2);
        this.pane.add(btPlay, 1, 3);
        GridPane.setHalignment(btAdd, HPos.RIGHT);
    }

    private void showStage() {
        Stage register_st = new Stage();
        register_st.setTitle("Register User");
        register_st.setScene(this.registerScene);
        register_st.show();
    }

}
