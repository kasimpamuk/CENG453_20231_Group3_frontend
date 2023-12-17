package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Slf4j
public class SettlementCorner {

    private int id;
    private Point location;
    private Button button;
    private List<Integer> adjacentTileIds;
    private final Pane pane;

    private final double HOUSE_IMAGE_SIZE = 20;
    private final String RED_HOUSE_IMAGE = "red-house.jpeg";

    public SettlementCorner(int id, Point location, List<Integer> adjacentTileIds, Pane pane) {
        this.id = id;
        this.location = location;
        this.button = createCornerButton(location, (e) -> clickAction());
        this.adjacentTileIds = adjacentTileIds;
        this.pane = pane;
    }

    public void clickAction() {
        // TODO: Implement logic...
        this.buildHouse();
        log.info("Adjacent tiles for the settlement: " + this.adjacentTileIds);
    }

    private void buildHouse() {
        Image houseImage = new Image(RED_HOUSE_IMAGE);
        ImageView houseView = new ImageView(houseImage);

        // Assuming the image is a square, adjust size as needed
        houseView.setFitHeight(HOUSE_IMAGE_SIZE);
        houseView.setFitWidth(HOUSE_IMAGE_SIZE);

        // Position the image at the corner, adjusting such that it's centered on the point
        houseView.setX(this.location.getX() - HOUSE_IMAGE_SIZE / 2);
        houseView.setY(this.location.getY() - HOUSE_IMAGE_SIZE / 2);

        this.pane.getChildren().add(houseView);
    }

    public void disableButton() {
        button.setDisable(true);
    }

    public void enableButton() {
        button.setDisable(false);
    }

    private Button createCornerButton(Point corner, EventHandler<ActionEvent> actionOnClick) {
        double buttonSize = 7;

        Button cornerButton = new Button("");
        cornerButton.setMinSize(buttonSize, buttonSize);
        cornerButton.setMaxSize(buttonSize, buttonSize);

        // Position the button at the corner
        cornerButton.setLayoutX(corner.getX() - buttonSize / 2);
        cornerButton.setLayoutY(corner.getY() - buttonSize / 2);

        // Initial style
        cornerButton.setStyle("-fx-background-radius: 10; " +
                "-fx-background-color: orange; " +
                "-fx-border-color: black; " +
                "-fx-border-width: 2; " +
                "-fx-cursor: hand;");

        // Hover effect - increase size
        cornerButton.setOnMouseEntered(e -> {
            cornerButton.setMinSize(buttonSize + 10, buttonSize + 10);
            cornerButton.setMaxSize(buttonSize + 10, buttonSize + 10);
            cornerButton.setLayoutX(cornerButton.getLayoutX() - 5);
            cornerButton.setLayoutY(cornerButton.getLayoutY() - 5);
            cornerButton.setStyle("-fx-background-radius: 15; " +
                    "-fx-background-color: #ff0000; " +
                    "-fx-border-color: black; " +
                    "-fx-border-width: 2; " +
                    "-fx-cursor: hand;");
        });

        // Hover effect - revert to original size
        cornerButton.setOnMouseExited(e -> {
            cornerButton.setMinSize(buttonSize, buttonSize);
            cornerButton.setMaxSize(buttonSize, buttonSize);
            cornerButton.setLayoutX(corner.getX() - buttonSize / 2);
            cornerButton.setLayoutY(corner.getY() - buttonSize / 2);
            cornerButton.setStyle("-fx-background-radius: 10; " +
                    "-fx-background-color: orange; " +
                    "-fx-border-color: black; " +
                    "-fx-border-width: 2; " +
                    "-fx-cursor: hand;");
        });

        // Set the action to be performed on click
        cornerButton.setOnAction(actionOnClick);

        return cornerButton;
    }
}
