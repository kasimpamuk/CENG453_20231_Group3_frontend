package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.screen.gameboard.component;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BottomConsole {

	private ResourceBox resourceBox = new ResourceBox();
	private DiceBox diceBox = new DiceBox();

	public void initializeBottomConsole(BorderPane boardPane) {
		HBox bottomConsole = new HBox(20, resourceBox.getResource_box(), diceBox.getDiceBox());
		bottomConsole.setAlignment(Pos.CENTER);
		boardPane.setBottom(bottomConsole);
	}

}
