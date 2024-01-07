package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.gamescreen;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui.enums.Resource;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ResourceCard {
	private VBox resourceCard;
	private Resource resource;
	private ImageView resourceImage;
	private final int SIZE = 50;
	private Label countLabel;
	private int count;

	public ResourceCard(String imagePath, Resource resource, int initialCount) {
		this.count = initialCount;
		this.resource = resource;
		this.resourceImage = new ImageView(imagePath);
		this.countLabel = new Label(Integer.toString(initialCount));
	}

	public void createResourceCard() {
		Color backgroundColor = this.resource.getColor();
		this.resourceCard.setStyle("-fx-background-color: " + backgroundColor + "; -fx-border-color: black; -fx-border-width: 2;");
		this.resourceImage.setFitHeight(SIZE);
		this.resourceImage.setFitWidth(SIZE);

		countLabel.setFont(new Font("Arial", 20)); // Increase the font size
		countLabel.setTextFill(Color.WHITE); // Set a text color that contrasts with the background
		countLabel.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-padding: 5;"); // Semi-transparent background for readability
		countLabel.setAlignment(Pos.CENTER); // Center align the text

		this.resourceCard = new VBox(5, this.resourceImage, this.countLabel);
		this.resourceCard.setAlignment(Pos.CENTER);
		this.resourceCard.setPadding(new Insets(10));
		this.resourceCard.setStyle("-fx-background-color: " + backgroundColor + "; -fx-border-color: black; -fx-border-width: 2;");
	}

	public void increaseCount(int amount) {
		this.count += amount;
		this.countLabel.setText(Integer.toString(this.count));
	}

	public void decreaseCount(int amount) {
		this.count -= amount;
		this.countLabel.setText(Integer.toString(this.count));
	}

	public void setCount(int count) {
		this.count = count;
		this.countLabel.setText(Integer.toString(this.count));
	}


}
