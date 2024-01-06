package io.github.alprKeskin.kasimpamuk.thesettlersofcatan;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.screen.gameboard.GameBoard;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.screen.mainmanu.MenuWindow;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.JavaFxService;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.menu.MenuService;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

public class JavaFX extends Application {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        applicationContext = new SpringApplicationBuilder(TheSettlersOfCatanApplication.class).run();
    }

    @Override
    public void start(Stage primaryStage) {
        applicationContext.publishEvent(new StageReadyEvent(primaryStage));
        JavaFxService javaFxService = applicationContext.getBean(JavaFxService.class);

        javaFxService.displayMenu();

        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(javaFxService.getMenuService().getMenuScene());

        primaryStage.show();
    }

    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }

    static class StageReadyEvent extends ApplicationEvent {
        public StageReadyEvent(Stage stage) {
            super(stage);
        }

        public Stage getStage() {
            return ((Stage) getSource());
        }
    }

}