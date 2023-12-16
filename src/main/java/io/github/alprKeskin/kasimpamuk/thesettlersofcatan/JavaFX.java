package io.github.alprKeskin.kasimpamuk.thesettlersofcatan;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.BuildingCorner;
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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.enums.Terrain.*;

public class JavaFX extends Application {

    private List<BuildingCorner> buildingCorners = new ArrayList<BuildingCorner>();
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
                    System.out.println(tile.getId() + " des => number: " + tile.getNumber());
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
                    System.out.println(tile.getId() + " for1 => number: " + tile.getNumber());
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
                System.out.println(tile.getId() + " for 2 => number: " + tile.getNumber());
                tileMap.getChildren().add(tile.getHexagon());
                tileMap.getChildren().add(numberText);
            }
        }

        createAllCornerButtons();



        HBox resource_box = new HBox(10);
        resource_box.setAlignment(Pos.CENTER);
        resource_box.setPadding(new Insets(10));
        resource_box.setStyle("-fx-background-color: #FFFD74;");
        VBox brickBox = createResourceBox("brick.png", 50, "Brick", 0);
        VBox grainBox = createResourceBox("grain.png", 50, "Grain", 0);
        VBox lumberBox = createResourceBox("lumber.png", 50, "Lumber", 0);
        VBox oreBox = createResourceBox("ore.png", 50, "Ore", 0);
        VBox woolBox = createResourceBox("wool.png", 50, "Wool", 0);
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

    private void createSettlementButton() {
        // code...
    }

    private void createTileButtons(Tile tile, boolean isEvenRow, boolean isEvenColumn, int tileId) {
        if (tileId != -1) {

            Point cornerPoint;
            if (tileId == 12) {
                // create only top left corner button
                cornerPoint = tile.getTopLeftCornerPoint();
                Button cornerButton = createCornerButton(cornerPoint, e -> rollDice());
                this.tileMap.getChildren().add(cornerButton);
                return;
            }
            if (tileId == 18) {
                // create only top right corner button
                cornerPoint = tile.getTopRightCornerPoint();
                Button cornerButton = createCornerButton(cornerPoint, e -> rollDice());
                this.tileMap.getChildren().add(cornerButton);
                return;
            }
            if (tileId == 11) {
                // create only bottom left corner button
                cornerPoint = tile.getBottomLeftCornerPoint();
                Button cornerButton = createCornerButton(cornerPoint, e -> rollDice());
                this.tileMap.getChildren().add(cornerButton);
                return;
            }
            if (tileId == 17) {
                // create only bottom right corner button
                cornerPoint = tile.getBottomRightCornerPoint();
                Button cornerButton = createCornerButton(cornerPoint, e -> rollDice());
                this.tileMap.getChildren().add(cornerButton);
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
                    Point cornerPoint = tile.getCorners().get(i);
                    Button cornerButton = createCornerButton(cornerPoint, e -> rollDice());
                    this.tileMap.getChildren().add(cornerButton);
                }
            }
            else {
                // create only top and bottom buttons
                Point topCornerPoint = tile.getTopCornerPoint();
                Button topCornerButton = createCornerButton(topCornerPoint, e -> rollDice());
                this.tileMap.getChildren().add(topCornerButton);

                Point bottomCornerPoint = tile.getBottomCornerPoint();
                Button bottomCornerButton = createCornerButton(bottomCornerPoint, e -> rollDice());
                this.tileMap.getChildren().add(bottomCornerButton);
            }
            return;
        }
        else {
            return;
        }
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
    private VBox createResourceBox(String imagePath, int size, String resourceName, int initialCount) {
        ImageView imageView = new ImageView(imagePath);
        imageView.setFitHeight(size);
        imageView.setFitWidth(size);

        Label countLabel = new Label(Integer.toString(initialCount));
        VBox box = new VBox(5, imageView, countLabel);
        box.setAlignment(Pos.CENTER);

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
                "-fx-background-color: #3cfcd6; " +
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
                    "-fx-background-color: #3cfcd6; " +
                    "-fx-border-color: black; " +
                    "-fx-border-width: 2; " +
                    "-fx-cursor: hand;");
        });

        // Set the action to be performed on click
        cornerButton.setOnAction(actionOnClick);

        return cornerButton;
    }

}