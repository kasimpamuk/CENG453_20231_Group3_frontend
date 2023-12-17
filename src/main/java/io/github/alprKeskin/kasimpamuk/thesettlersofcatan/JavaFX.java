package io.github.alprKeskin.kasimpamuk.thesettlersofcatan;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.screen.gameBoardWindow.GameBoardWindow;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.screen.RegisterWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class JavaFX extends Application {

    private final RegisterWindow registerWindow = new RegisterWindow();

    private final GameBoardWindow gameBoardWindow = new GameBoardWindow();

    @Override
    public void start(Stage primaryStage) {
        this.registerWindow.display();
        this.gameBoardWindow.display();

        primaryStage.setTitle("Catan Board");
        primaryStage.setScene(gameBoardWindow.getCatanScene());

        primaryStage.show();
    }

}