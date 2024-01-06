package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.screen.gameboard.component;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.gamescreen.DiceBoxService;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.menu.MenuService;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Getter
@Setter
@Service
public class BottomConsole {

	@Autowired
	private MenuService menuService;

	private ResourceBox resourceBox = new ResourceBox();
	private DiceBoxService diceBoxService = new DiceBoxService();
	private Button menuButton = new Button("Menu");

	public void initializeBottomConsole(BorderPane boardPane) {
		menuButton.setPrefSize(60, 30);
		menuButton.setStyle("-fx-background-color: #FF0000; -fx-text-fill: #FFFFFF; -fx-font-size: 13px;");
		menuButton.setOnAction(e -> {
			// change scene
			Stage stage = (Stage) this.menuButton.getScene().getWindow();
			stage.setScene(this.menuService.getMenuScene());
			stage.setTitle("Main Menu");

		});
		ImageView waitGif = new ImageView("gif_timer.gif");
		waitGif.setFitHeight(50);
		waitGif.setFitWidth(50);
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		int wait = 1;
		if(wait == 1){
			vbox.getChildren().addAll(waitGif);
		}
		if(wait == 0){
			vbox.getChildren().removeAll(waitGif);
		}
		vbox.getChildren().addAll(menuButton);
		HBox bottomConsole = new HBox(20, resourceBox.getResource_box(), diceBoxService.getDiceBox(), vbox);
		bottomConsole.setAlignment(Pos.CENTER);
		boardPane.setBottom(bottomConsole);
	}

}
