package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.gamescreen;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui.enums.Resource;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.Map;

import static io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui.enums.Resource.*;

@Service
@Getter
public class ResourceBoxService {

	private ResourceCard brickCard;
	private ResourceCard grainCard;
	private ResourceCard lumberCard;
	private ResourceCard oreCard;
	private ResourceCard woolCard;

	public HBox createHorizontalResourceBox() {
		return this.initializeResourceBox();
	}

	private HBox initializeResourceBox() {
		HBox resourceBox = new HBox(10);
		resourceBox.setAlignment(Pos.CENTER);
		resourceBox.setPadding(new Insets(10));
		resourceBox.setStyle("-fx-background-color: #FFFD74;");

		this.brickCard = createResourceCard("brick.png", BRICK, 3); // Brownish color for brick
		this.grainCard = createResourceCard("grain.png", GRAIN, 1); // Gold color for grain
		this.lumberCard = createResourceCard("lumber.png", LUMBER, 3); // Green color for lumber
		this.oreCard = createResourceCard("ore.png", ORE, 0); // Gray color for ore
		this.woolCard = createResourceCard("wool.png", WOOL, 1); // Beige color for wool

		resourceBox.getChildren().addAll(brickCard.getResourceCard(), grainCard.getResourceCard(), lumberCard.getResourceCard(), oreCard.getResourceCard(), woolCard.getResourceCard());
		return resourceBox;
	}

	private ResourceCard createResourceCard(String imagePath, Resource resource, int initialCount) {
		ResourceCard resourceCard = new ResourceCard(imagePath, resource, initialCount);
		resourceCard.createResourceCard();

		return resourceCard;
	}




}
