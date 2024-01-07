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
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.Map;

import static io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui.enums.Resource.*;

@Service
public class ResourceBoxService {

	public HBox createHorizontalResourceBox() {
		return this.initializeResourceBox();
	}

	private HBox initializeResourceBox() {
		HBox resourceBox = new HBox(10);
		resourceBox.setAlignment(Pos.CENTER);
		resourceBox.setPadding(new Insets(10));
		resourceBox.setStyle("-fx-background-color: #FFFD74;");

		VBox brickCard = createResourceCard("brick.png", 50, BRICK, 3, "#b7410e"); // Brownish color for brick
		VBox grainCard = createResourceCard("grain.png", 50, GRAIN, 1, "#FFD700"); // Gold color for grain
		VBox lumberCard = createResourceCard("lumber.png", 50, LUMBER, 3, "#228B22"); // Green color for lumber
		VBox oreCard = createResourceCard("ore.png", 50, ORE, 0, "#708090"); // Gray color for ore
		VBox woolCard = createResourceCard("wool.png", 50, WOOL, 1, "#B6C9A2"); // Beige color for wool

		resourceBox.getChildren().addAll(brickCard, grainCard, lumberCard, oreCard, woolCard);
		return resourceBox;
	}

	private VBox createResourceCard(String imagePath, int size, Resource resource, int initialCount, String backgroundColor) {
		ImageView imageView = new ImageView(imagePath);
		imageView.setFitHeight(size);
		imageView.setFitWidth(size);

		Label countLabel = new Label(Integer.toString(initialCount));
		countLabel.setFont(new Font("Arial", 20)); // Increase the font size
		countLabel.setTextFill(Color.WHITE); // Set a text color that contrasts with the background
		countLabel.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-padding: 5;"); // Semi-transparent background for readability
		countLabel.setAlignment(Pos.CENTER); // Center align the text

		// Store the label in the map
		Map<Resource, Label> resourceLabelMap = new EnumMap<>(Resource.class);
		resourceLabelMap.put(resource, countLabel);

		VBox box = new VBox(5, imageView, countLabel);
		box.setAlignment(Pos.CENTER);
		box.setPadding(new Insets(10));
		box.setStyle("-fx-background-color: " + backgroundColor + "; -fx-border-color: black; -fx-border-width: 2;");

		return box;
	}

	public void adjustResourceCount(Map<Resource, Label> resourceLabelMap, Resource resource, int change) {
		Label label = resourceLabelMap.get(resource);
		if (label != null) {
			int currentCount = Integer.parseInt(label.getText());
			int newCount = currentCount + change;
			label.setText(Integer.toString(newCount));
		}
	}



}
