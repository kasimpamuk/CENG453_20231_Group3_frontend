package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.gamescreen;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.menu.MenuService;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
public class BottomConsoleService {

	private final ImageView waitGif = createWaitGif();
	private VBox vbox;
	private final MenuService menuService;
	private final ResourceBoxService resourceBoxService;
	private final DiceBoxService diceBoxService;
	private final Button menuButton = new Button("Menu");

	@Autowired
	public BottomConsoleService(MenuService menuService, ResourceBoxService resourceBoxService, DiceBoxService diceBoxService) {
		this.menuService = menuService;
		this.resourceBoxService = resourceBoxService;
		this.diceBoxService = diceBoxService;
	}

	public HBox createBottomConsole() {
		menuButton.setPrefSize(60, 30);
		menuButton.setStyle("-fx-background-color: #FF0000; -fx-text-fill: #FFFFFF; -fx-font-size: 13px;");
		menuButton.setOnAction(e -> {
			// change scene
			Stage stage = (Stage) this.menuButton.getScene().getWindow();
			stage.setScene(this.menuService.getMenuScene());
			stage.setTitle("Main Menu");

		});

		vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(menuButton);

		showWaitingIcon(vbox);

		HBox bottomConsole = new HBox(20, this.resourceBoxService.createHorizontalResourceBox(), diceBoxService.getDiceBox(), vbox);
		bottomConsole.setAlignment(Pos.CENTER);

		return bottomConsole;
	}

	public void showWaitingIcon(VBox vBox) {
		vBox.getChildren().addAll(waitGif);
	}

	public void disappearWaitingIcon(VBox vBox) {
		vBox.getChildren().removeAll(waitGif);
	}

	private ImageView createWaitGif() {
		ImageView waitGif = new ImageView("gif_timer.gif");
		waitGif.setFitHeight(50);
		waitGif.setFitWidth(50);
		return waitGif;
	}

	public void disableDiceButton() {
		this.diceBoxService.getButton().setDisable(true);
	}

	public void enableDiceButton() {
		this.diceBoxService.getButton().setDisable(false);
	}

}
