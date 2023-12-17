package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SettlementCorner {

    private int id;
    private Point location;
    private Button button;
    private List<Integer> adjacentTileIds;

    public SettlementCorner(int id, Point location, List<Integer> adjacentTileIds) {
        this.id = id;
        this.location = location;
        this.button = createCornerButton(location, (e) -> clickAction());
        this.adjacentTileIds = adjacentTileIds;
    }

    public void clickAction() {
        // TODO: Implement logic...
        for (int i = 0; i < adjacentTileIds.size(); i++) {
            System.out.println(adjacentTileIds.get(i));
        }
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
