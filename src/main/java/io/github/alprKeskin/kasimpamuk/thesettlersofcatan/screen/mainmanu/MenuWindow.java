package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.screen.mainmanu;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.screen.gameboard.GameBoard;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.screen.mainmanu.components.LeaderBoardBox;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.screen.mainmanu.components.LoginBox;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.screen.mainmanu.components.RegisterBox;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;
import org.w3c.dom.Text;

@Getter
public class MenuWindow {

    private final BorderPane pane;
    private final Scene registerScene;

    private LoginBox loginBox;
    private RegisterBox registerBox;

    public MenuWindow() {
        this.pane = new BorderPane();
        this.loginBox = new LoginBox();
        this.registerBox = new RegisterBox();
        this.registerScene = new Scene(this.pane, 750, 750);


    }

    public void display() {

        initializeWindowConfigurations();
        setScene();

    }

    private void initializeWindowConfigurations() {
        this.pane.setPadding(new Insets(11, 12, 13, 14));
        this.pane.setStyle("-fx-background-color: #066098;");

    }

    private void setScene() {
        this.pane.setLeft(registerBox.getRegisterBox());
        this.pane.setRight(loginBox.getLoginBox());
        Button btPlay = new Button("Play");
        btPlay.setPrefWidth(100);
        btPlay.setPrefHeight(50);
        btPlay.setStyle("-fx-background-color: #FF0000; -fx-text-fill: #FFFFFF; -fx-font-size: 20px;");
        btPlay.setOnAction(e -> { // change scene
            GameBoard gameBoard = new GameBoard();
            gameBoard.display();
            Stage stage = (Stage) this.pane.getScene().getWindow();
            stage.setTitle("Catan Board");
            stage.setScene(gameBoard.getCatanScene());
        });

        VBox vBox = new VBox();
        TitledPane centerPane = new TitledPane();

        centerPane.setText("Welcome to Catan");
        centerPane.setCollapsible(false);
        centerPane.setPrefWidth(500);
        centerPane.setPrefHeight(500);
        centerPane.setPadding(new Insets(10));
        centerPane.setStyle("-fx-background-color: #159fd7;");
        centerPane.setGraphic(new Label("Catan"));
        centerPane.setGraphicTextGap(10);
        centerPane.setContent(new Label("Welcome to Catan"));

        Pane leaderPane = new LeaderBoardBox().getLeaderBoardBox();

        vBox.getChildren().add(leaderPane);
        vBox.setAlignment(Pos.CENTER);
        centerPane.setContent(vBox);
        this.pane.setCenter(centerPane);
        this.pane.setBottom(btPlay);
        BorderPane.setAlignment(btPlay, Pos.CENTER);
    }

}
