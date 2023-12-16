package io.github.alprKeskin.kasimpamuk.thesettlersofcatan;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.SettlementCorner;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.Point;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.Tile;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.enums.Terrain;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.screen.RegisterWindow;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.enums.Terrain.*;

public class JavaFX extends Application {

    private List<SettlementCorner> settlementCorners = new ArrayList<SettlementCorner>();
    private List<Tile> tiles = new ArrayList<Tile>();
    private final List<Terrain> terrains = createTerrainList();
    private final List<Integer> terrainNumbers = createNumberList();

    private final RegisterWindow registerWindow = new RegisterWindow();

    private final Pane tileMap = new Pane();

    @Override
    public void start(Stage primaryStage) {
        // Register
        this.registerWindow.displayRegisterWindow();

        // Game Board
        int terrainIndex = 0;
        int numberIndex = 0;

        int height = 700;
        int width = 700;
        tileMap.setStyle("-fx-background-color: #87CEEB;");
        tileMap.setPrefSize(width, height);

        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #87CEEB;");
        borderPane.setCenter(tileMap);
        Scene catan_board = new Scene(borderPane, width, height);
        double boardCenterX = borderPane.getWidth()/2 ;
        double boardCenterY = borderPane.getHeight()/2;
        primaryStage.setTitle("Catan Board");
        primaryStage.setScene(catan_board);
        int size = 50;
        double v=Math.sqrt(3)/2.0;
        int number;
        int tileIndex = 0;


        // Create Even Tiles
        for(double y = -1; y < 2; y++) {
            double centerY= boardCenterY + y * size * 3;
            for (double x = -2; x < 3; x++) {
                if(((y == -1 || y == 1) && (x == -2 || x == 2))) continue;

                double centerX = boardCenterX + x * size * Math.sqrt(3);
                Terrain terrain;
                if(y == 0 && x == 0) {
                    terrain = DESERT;
                    Tile tile = new Tile(tileIndex++, new Point(centerX, centerY), terrain, 0, size);
                    this.tiles.add(tile);
                    tileMap.getChildren().add(tile.getHexagon());
                }
                else{
                    terrain = terrains.get(terrainIndex++);
                    number = this.terrainNumbers.get(numberIndex++);
                    Text numberText = new Text(String.valueOf(number));
                    numberText.setX(centerX - numberText.getBoundsInLocal().getWidth() / 2);
                    numberText.setY(centerY + numberText.getBoundsInLocal().getHeight() / 4);
                    Tile tile = new Tile(tileIndex++ , new Point(centerX, centerY), terrain, number, size);
                    this.tiles.add(tile);
                    tileMap.getChildren().add(tile.getHexagon());
                    tileMap.getChildren().add(numberText);
                }
            }
        }


        boardCenterX = boardCenterX -  size*v;
        double centerx, centery;
        // Create odd tiles
        for(int x=-1; x<3; x++){
            centerx= boardCenterX + x*size*Math.sqrt(3);
            for(int y=-1; y<2;y+=2){
                centery= boardCenterY + y*size*3/2;
                Terrain terrain = terrains.get(terrainIndex++);
                number = this.terrainNumbers.get(numberIndex++);
                Text numberText = new Text(String.valueOf(number));
                numberText.setX(centerx - numberText.getBoundsInLocal().getWidth() / 2);
                numberText.setY(centery + numberText.getBoundsInLocal().getHeight() / 4);
                Tile tile = new Tile(tileIndex++ , new Point(centerx, centery), terrain, number, size);
                this.tiles.add(tile);
                tileMap.getChildren().add(tile.getHexagon());
                tileMap.getChildren().add(numberText);
            }
        }

        createAllCornerButtons();



        HBox resource_box = new HBox(10);
        resource_box.setAlignment(Pos.CENTER);
        resource_box.setPadding(new Insets(10));
        resource_box.setStyle("-fx-background-color: #FFFD74;");
        VBox brickBox = createResourceBox("brick.png", 50, "Brick", 0, "#b7410e"); // Brownish color for brick
        VBox grainBox = createResourceBox("grain.png", 50, "Grain", 0, "#FFD700"); // Gold color for grain
        VBox lumberBox = createResourceBox("lumber.png", 50, "Lumber", 0, "#228B22"); // Green color for lumber
        VBox oreBox = createResourceBox("ore.png", 50, "Ore", 0, "#708090"); // Gray color for ore
        VBox woolBox = createResourceBox("wool.png", 50, "Wool", 0, "#B6C9A2"); // Beige color for wool

        resource_box.getChildren().addAll(brickBox, grainBox, lumberBox, oreBox, woolBox);

        HBox dice_box = new HBox(10);
        dice_box.setAlignment(Pos.CENTER);
        dice_box.setPadding(new Insets(10));
        dice_box.setStyle("-fx-background-color: #87CEEB;");
        dice1 = new ImageView(new Image("dice1.png"));
        dice2 = new ImageView(new Image("dice1.png"));
        dice1.setFitHeight(50);
        dice1.setFitWidth(50);
        dice2.setFitHeight(50);
        dice2.setFitWidth(50);
        dice_box.getChildren().addAll(dice1, dice2);

        // Roll dice button
        Button rollDiceButton = new Button("Roll Dice");
        rollDiceButton.setOnAction(e -> rollDice());

        // Bottom console
        HBox bottomConsole = new HBox(20, resource_box, dice_box, rollDiceButton);
        bottomConsole.setAlignment(Pos.CENTER);
        borderPane.setBottom(bottomConsole);

        primaryStage.show();
    }

    private SettlementCorner createSettlementButton(int id, Point point, List<Integer> adjacentTileIds) {
        return new SettlementCorner(id, point, adjacentTileIds);
    }

    private void createTileButtons(Tile tile, boolean isEvenRow, boolean isEvenColumn, int tileId) {
        if (tileId != -1) {

            Point cornerPoint;
            if (tileId == 11) {
                SettlementCorner settlementCorner = createSettlementButton(0, tile.getTopLeftCornerPoint(), getAdjacentTileIdsOfCorner(tile.getTopLeftCornerPoint()));
                this.tileMap.getChildren().add(settlementCorner.getButton());
                return;
            }
            if (tileId == 17) {
                // create only top right corner button
                SettlementCorner settlementCorner = createSettlementButton(0, tile.getTopRightCornerPoint(), getAdjacentTileIdsOfCorner(tile.getTopRightCornerPoint()));
                this.tileMap.getChildren().add(settlementCorner.getButton());
                return;
            }
            if (tileId == 12) {
                // create only bottom left corner button
                SettlementCorner settlementCorner = createSettlementButton(0, tile.getBottomLeftCornerPoint(), getAdjacentTileIdsOfCorner(tile.getBottomLeftCornerPoint()));
                this.tileMap.getChildren().add(settlementCorner.getButton());
                return;
            }
            if (tileId == 18) {
                // create only bottom right corner button
                SettlementCorner settlementCorner = createSettlementButton(0, tile.getBottomRightCornerPoint(), getAdjacentTileIdsOfCorner(tile.getBottomRightCornerPoint()));
                this.tileMap.getChildren().add(settlementCorner.getButton());
                return;
            }
            else {
                return;
            }

        }
        else if (isEvenRow) {
            if (isEvenColumn) {
                // create all corner buttons
                for (int i = 0; i < 6; i++) {
                    SettlementCorner settlementCorner = createSettlementButton(0, tile.getCorners().get(i), getAdjacentTileIdsOfCorner(tile.getCorners().get(i)));
                    this.tileMap.getChildren().add(settlementCorner.getButton());
                }
            }
            else {
                // create only top and bottom buttons
                Point topCornerPoint = tile.getTopCornerPoint();
                SettlementCorner settlementCorner = createSettlementButton(0, topCornerPoint, getAdjacentTileIdsOfCorner(topCornerPoint));
                this.tileMap.getChildren().add(settlementCorner.getButton());

                Point bottomCornerPoint = tile.getBottomCornerPoint();
                SettlementCorner settlementCorner1 = createSettlementButton(0, bottomCornerPoint, getAdjacentTileIdsOfCorner(bottomCornerPoint));
                this.tileMap.getChildren().add(settlementCorner1.getButton());
            }
            return;
        }
        else {
            return;
        }
    }


    private List<Integer> getAdjacentTileIdsOfCorner(Point point) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < this.tiles.size(); i++) {
            Tile tile = this.tiles.get(i);
            for (int j = 0; j < tile.getCorners().size(); j++) {
                if (point.equals(tile.getCorners().get(j))) {
                    result.add(tile.getId());
                }
                else {
                    continue;
                }
            }
        }
        return result;
    }

    private void createAllCornerButtons() {
        for (int i = 0; i < this.tiles.size(); i++) {
            int tileId = this.tiles.get(i).getId();
            if (tileId < 11) {
                if ( (tileId > 2) && (tileId < 8)) {
                    if (tileId % 2 != 0) {
                        // even column
                        createTileButtons(this.tiles.get(i), true, true, -1);
                    }
                    else {
                        // odd column
                        createTileButtons(this.tiles.get(i), true, false, -1);
                    }
                }
                else {
                    if (tileId % 2 == 0) {
                        // even column
                        createTileButtons(this.tiles.get(i), true, true, -1);
                    }
                    else {
                        // odd column
                        createTileButtons(this.tiles.get(i), true, false, -1);
                    }
                }
            }
            else {
                // odd rows
                createTileButtons(this.tiles.get(i), false, false, tileId);
            }
        }
    }

    private ArrayList<Terrain> createTerrainList() {
        ArrayList<Terrain> terrains = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            terrains.add(HILL); // 3 hills
            terrains.add(MOUNTAIN); // 3 mountains
        }
        for (int i = 0; i < 4; i++) {
            terrains.add(FOREST); // 4 forests
            terrains.add(FIELD); // 4 fields
            terrains.add(PASTURE); // 4 pastures
        }
        Collections.shuffle(terrains);
        return terrains;
    }
    private ArrayList<Integer> createNumberList() {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.addAll(Arrays.asList(2, 12)); // One each of 2, 3, 11, and 12
        for (int number : new int[]{3, 4, 5, 6, 8, 9, 10, 11}) { // Two each of 4, 5, 6, 8, 9, and 10
            numbers.add(number);
            numbers.add(number);
        }
        Collections.shuffle(numbers);
        return numbers;
    }
    private VBox createResourceBox(String imagePath, int size, String resourceName, int initialCount, String backgroundColor) {
        ImageView imageView = new ImageView(imagePath);
        imageView.setFitHeight(size);
        imageView.setFitWidth(size);

        Label countLabel = new Label(Integer.toString(initialCount));
        countLabel.setFont(new Font("Arial", 20)); // Increase the font size
        countLabel.setTextFill(Color.WHITE); // Set a text color that contrasts with the background
        countLabel.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-padding: 5;"); // Semi-transparent background for readability
        countLabel.setAlignment(Pos.CENTER); // Center align the text

        VBox box = new VBox(5, imageView, countLabel);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(10));
        box.setStyle("-fx-background-color: " + backgroundColor + "; -fx-border-color: black; -fx-border-width: 2;");

        return box;
    }
    private ImageView dice1, dice2;
    private void rollDice() {
        Random random = new Random();
        int diceValue1 = random.nextInt(6) + 1; // Dice values between 1 and 6
        int diceValue2 = random.nextInt(6) + 1;

        dice1.setImage(new Image("dice" + diceValue1 + ".png"));
        dice2.setImage(new Image("dice" + diceValue2 + ".png"));
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

    /*
    private Shape createRoad(Point corner1, Point corner2) {
        double roadWidth = 8; // Width of the road

        // Calculate the center point
        double centerX = (corner1.getX() + corner2.getX()) / 2;
        double centerY = (corner1.getY() + corner2.getY()) / 2;

        // Calculate the length of the road
        double length = Math.sqrt(Math.pow(corner2.getX() - corner1.getX(), 2) + Math.pow(corner2.getY() - corner1.getY(), 2));

        // Create a rectangle to represent the road
        Rectangle road = new Rectangle(centerX - roadWidth / 2, centerY - roadWidth / 2, roadWidth, length);
        road.setFill(Color.BLACK); // Set road color

        // Rotate the rectangle to align with the two corners
        double angle = Math.toDegrees(Math.atan2(corner2.getY() - corner1.getY(), corner2.getX() - corner1.getX()));
        road.setRotate(angle);

        return road;
    }
*/

}