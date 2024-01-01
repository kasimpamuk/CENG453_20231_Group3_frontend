package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@ToString
@Slf4j
public class Edge {

    private int id;
    private Point location1;
    private Point location2;

    private SettlementCorner settlementCorner1;
    private SettlementCorner settlementCorner2;

    private boolean isRoad;
    private Shape road;

    private final Pane pane;
    private Button button;

    public Edge(int id, Point location1, Point location2, boolean isRoad, Pane pane) {
        this.id = id;
        this.location1 = location1;
        this.location2 = location2;
        this.isRoad = isRoad;
        this.pane = pane;
        this.button = createRoadButton((e) -> clickAction());
    }
    public Edge(int id, Point location1, Point location2, Pane pane) {
        this.id = id;
        this.location1 = location1;
        this.location2 = location2;
        this.isRoad = false;
        this.pane = pane;
        this.button = createRoadButton((e) -> clickAction());
    }

    private void clickAction() {
        this.pane.getChildren().add(setRoad(Color.WHITE));
        pane.getChildren().remove(this.button);
        this.button = null;
        log.info("Road button clicked!");
    }

    private Button createRoadButton(EventHandler<ActionEvent> actionOnClick) {
        Point location = getMiddlePoint();
        double buttonSize = 7;

        Button roadButton = new Button("");
        roadButton.setMinSize(buttonSize, buttonSize);
        roadButton.setMaxSize(buttonSize, buttonSize);

        // Position the button at the corner
        roadButton.setLayoutX(location.getX() - buttonSize / 2);
        roadButton.setLayoutY(location.getY() - buttonSize / 2);

        // Initial style
        roadButton.setStyle("-fx-background-radius: 10; " +
                "-fx-background-color: orange; " +
                "-fx-border-color: black; " +
                "-fx-border-width: 2; " +
                "-fx-cursor: hand;");

        // Hover effect - increase size
        roadButton.setOnMouseEntered(e -> {
            roadButton.setMinSize(buttonSize + 10, buttonSize + 10);
            roadButton.setMaxSize(buttonSize + 10, buttonSize + 10);
            roadButton.setLayoutX(roadButton.getLayoutX() - 5);
            roadButton.setLayoutY(roadButton.getLayoutY() - 5);
            roadButton.setStyle("-fx-background-radius: 15; " +
                    "-fx-background-color: #ff0000; " +
                    "-fx-border-color: black; " +
                    "-fx-border-width: 2; " +
                    "-fx-cursor: hand;");
        });

        // Hover effect - revert to original size
        roadButton.setOnMouseExited(e -> {
            roadButton.setMinSize(buttonSize, buttonSize);
            roadButton.setMaxSize(buttonSize, buttonSize);
            roadButton.setLayoutX(location.getX() - buttonSize / 2);
            roadButton.setLayoutY(location.getY() - buttonSize / 2);
            roadButton.setStyle("-fx-background-radius: 10; " +
                    "-fx-background-color: orange; " +
                    "-fx-border-color: black; " +
                    "-fx-border-width: 2; " +
                    "-fx-cursor: hand;");
        });

        // Set the action to be performed on click
        roadButton.setOnAction(actionOnClick);

        return roadButton;
    }

    private Point getMiddlePoint() {
        return new Point( (this.location1.getX() + this.location2.getX()) / 2, (this.location1.getY() + this.location2.getY()) / 2);
    }

    public Shape setRoad(Color color) {
        this.isRoad = true;
        this.road = createRoad(color);
        return this.road;
    }

    private Shape createRoad(Color color) {
        double roadWidth = 8; // Width of the road
        double shrinkFactor = 10; // Distance to shrink the road from each corner

        // Calculate the direction vector from start to end
        double dx = this.location2.getX() - this.location1.getX();
        double dy = this.location2.getY() - this.location1.getY();
        double length = Math.sqrt(dx * dx + dy * dy);
        double unitDx = dx / length;
        double unitDy = dy / length;

        // Calculate new start and end points for the road
        double startX = this.location1.getX() + unitDx * shrinkFactor;
        double startY = this.location1.getY() + unitDy * shrinkFactor;
        double endX = this.location2.getX() - unitDx * shrinkFactor;
        double endY = this.location2.getY() - unitDy * shrinkFactor;

        // Create a line to represent the road
        Line road = new Line(startX, startY, endX, endY);
        road.setStrokeWidth(roadWidth);
        road.setStroke(color);

        return road;
    }

}
