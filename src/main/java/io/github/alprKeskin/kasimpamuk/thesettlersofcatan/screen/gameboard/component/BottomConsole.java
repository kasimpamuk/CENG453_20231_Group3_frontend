package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.screen.gameboard.component;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.JavaFX;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.screen.RegisterWindow;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BottomConsole {

	private ResourceBox resourceBox = new ResourceBox();
	private DiceBox diceBox = new DiceBox();
	private Button menuButton = new Button("Menu");

	public void initializeBottomConsole(BorderPane boardPane) {
		menuButton.setPrefSize(100, 50);
		menuButton.setStyle("-fx-background-color: #FF0000; -fx-text-fill: #FFFFFF; -fx-font-size: 20px;");
		menuButton.setOnAction(e -> {
			// change scene
			Stage stage = (Stage) this.menuButton.getScene().getWindow();
			stage.setTitle("Main Menu");
			//Scene scene = new Scene(stage.getScene().getRoot(), 700, 700);
			stage.setScene(JavaFX.registerWindow.getRegisterScene());

		});
		HBox bottomConsole = new HBox(20, resourceBox.getResource_box(), diceBox.getDiceBox(), menuButton);
		bottomConsole.setAlignment(Pos.CENTER);
		boardPane.setBottom(bottomConsole);
	}

}
