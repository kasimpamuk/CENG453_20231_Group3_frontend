package io.github.alprKeskin.kasimpamuk.thesettlersofcatan;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.screen.gameboard.GameBoard;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.screen.mainmanu.MenuWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class JavaFX extends Application {

    public static final MenuWindow registerWindow = new MenuWindow();

    private final GameBoard gameBoard = new GameBoard();

    @Override
    public void start(Stage primaryStage) {
        registerWindow.display();
        //this.gameBoard.display();

        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(registerWindow.getRegisterScene());

        primaryStage.show();
    }

}