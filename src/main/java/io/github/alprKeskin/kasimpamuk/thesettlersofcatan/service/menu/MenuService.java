package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.menu;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.screen.mainmanu.components.LeaderBoardBox;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.authentication.LoginService;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.authentication.RegisterService;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.gamescreen.GameScreenService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

	private final BorderPane pane = new BorderPane();

	@Getter
	private final Scene menuScene = new Scene(this.pane, 750, 750);

	private final VBox loginBox;
	private final VBox registerBox;

	private final LoginService loginService;
	private final RegisterService registerService;

	private final GameScreenService gameScreenService;

	@Autowired
	@Lazy
	public MenuService(LoginService loginService, RegisterService registerService, GameScreenService gameScreenService) {
		this.loginService = loginService;
		this.registerService = registerService;
		this.gameScreenService = gameScreenService;
		this.registerBox = this.registerService.createRegisterBox();
		this.loginBox = this.loginService.createLoginBox();
	}

	public void displayMenu() {
		this.pane.setPadding(new Insets(11, 12, 13, 14));
		this.pane.setStyle("-fx-background-color: #066098;");
		this.pane.setLeft(this.registerBox);
		this.pane.setRight(this.loginBox);
		Button btPlay = new Button("Play");
		btPlay.setPrefWidth(100);
		btPlay.setPrefHeight(50);
		btPlay.setStyle("-fx-background-color: #FF0000; -fx-text-fill: #FFFFFF; -fx-font-size: 20px;");
		btPlay.setOnAction(e -> {
			// change scene
			this.gameScreenService.displayGameScreen();
			Stage stage = (Stage) this.pane.getScene().getWindow();
			stage.setTitle("Catan Board");
			stage.setScene(gameScreenService.getCatanScene());
			//stage.setScene(new Scene(this.gameScreenService.getBoardPane(), 750, 750));
		});

		VBox vBox = new VBox();
		TitledPane centerPane = new TitledPane();

		// add catan logo
		ImageView catanLogo = new ImageView("catan_logo.png");
		catanLogo.setFitHeight(200);
		catanLogo.setFitWidth(200);
		catanLogo.setPreserveRatio(true);
		catanLogo.setSmooth(true);
		catanLogo.setCache(true);

		vBox.getChildren().add(catanLogo);

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