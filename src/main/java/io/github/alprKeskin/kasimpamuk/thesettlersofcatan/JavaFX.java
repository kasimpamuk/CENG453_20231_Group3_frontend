package io.github.alprKeskin.kasimpamuk.thesettlersofcatan;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.screen.gameboard.GameBoard;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.screen.RegisterWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class JavaFX extends Application {

    private final RegisterWindow registerWindow = new RegisterWindow();

    private final GameBoard gameBoard = new GameBoard();

    @Override
    public void start(Stage primaryStage) {
        this.registerWindow.display();
        this.gameBoard.display();

        primaryStage.setTitle("Catan Board");
        primaryStage.setScene(gameBoard.getCatanScene());

        primaryStage.show();
    }

}