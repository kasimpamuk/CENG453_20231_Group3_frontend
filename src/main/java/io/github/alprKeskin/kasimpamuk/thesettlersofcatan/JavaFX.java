package io.github.alprKeskin.kasimpamuk.thesettlersofcatan;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.Tile;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class JavaFX extends Application {
    private ArrayList<String> createTerrainList() {
        ArrayList<String> terrains = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            terrains.add("hills"); // 3 hills
            terrains.add("mountains"); // 3 mountains
        }
        for (int i = 0; i < 4; i++) {
            terrains.add("forest"); // 4 forests
            terrains.add("fields"); // 4 fields
            terrains.add("pasture"); // 4 pastures
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
    @Override
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11, 12, 13, 14));
        pane.setHgap(5);
        pane.setVgap(5);

        pane.add(new Label("Email:"), 0, 0);
        pane.add(new TextField(), 1, 0);
        pane.add(new Label("Password:"), 0, 1);
        pane.add(new TextField(), 1, 1);
        Button btAdd = new Button("Add User");
        pane.add(btAdd, 1, 2);
        GridPane.setHalignment(btAdd, HPos.RIGHT);


        Stage register_st = new Stage();
        register_st.setTitle("Register User");
        register_st.setScene(new Scene(pane, 300, 200));
        register_st.show();


        ArrayList<String> terrains = createTerrainList();
        ArrayList<Integer> numbers = createNumberList();
        int terrainIndex = 0;
        int numberIndex = 0;

        int height = 700;
        int width = 700;
        BorderPane tileMap = new BorderPane();
        tileMap.setStyle("-fx-background-color: #87CEEB;");
        Scene catan_board = new Scene(tileMap, width, height);
        double board_centerx = tileMap.getWidth()/2;
        double board_centery = tileMap.getHeight()/2;
        primaryStage.setTitle("Catan Board");
        primaryStage.setScene(catan_board);
        int size = 50;
        double v=Math.sqrt(3)/2.0;
        int number;
        int tileIndex = 0;



        for(double y=-1;y<2;y++)
        {
            double centery= board_centery + y*size*3;
            for(double x=-2;x<3;x++)
            {
                if(((y==-1 || y==1) && (x==-2 || x==2)))
                    continue;

                double centerx=board_centerx+x*size*Math.sqrt(3);
                String terrain;
                if(y==0 && x==0) {
                    terrain = "desert";
                    Tile tile = new Tile(tileIndex++ , centerx, centery, terrain, 0, size);
                    tileMap.getChildren().add(tile.getHexagon());
                }
                else{
                    terrain = terrains.get(terrainIndex++);
                    number = numbers.get(numberIndex++);
                    Text numberText = new Text(String.valueOf(number));
                    numberText.setX(centerx - numberText.getBoundsInLocal().getWidth() / 2);
                    numberText.setY(centery + numberText.getBoundsInLocal().getHeight() / 4);
                    Tile tile = new Tile(tileIndex++ , centerx, centery, terrain, number, size);
                    tileMap.getChildren().add(tile.getHexagon());
                    tileMap.getChildren().add(numberText);
                }
            }
        }
        board_centerx = board_centerx -  size*v;
        double centerx, centery;
        for(int x=-1; x<3; x++){
            centerx= board_centerx + x*size*Math.sqrt(3);
            for(int y=-1; y<2;y+=2){
                centery= board_centery + y*size*3/2;
                String terrain = terrains.get(terrainIndex++);
                number = numbers.get(numberIndex++);
                Text numberText = new Text(String.valueOf(number));
                numberText.setX(centerx - numberText.getBoundsInLocal().getWidth() / 2);
                numberText.setY(centery + numberText.getBoundsInLocal().getHeight() / 4);
                Tile tile = new Tile(tileIndex++ , centerx, centery, terrain, number, size);
                tileMap.getChildren().add(tile.getHexagon());
                tileMap.getChildren().add(numberText);

            }
        }

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

        // Dice box setup
        HBox dice_box = new HBox(10);
        dice_box.setAlignment(Pos.CENTER);
        dice_box.setPadding(new Insets(10));
        dice_box.setStyle("-fx-background-color: #87CEEB;");
        ImageView dice1 = new ImageView("dice.png");
        dice1.setFitHeight(50);
        dice1.setFitWidth(50);
        ImageView dice2 = new ImageView("dice.png");
        dice2.setFitHeight(50);
        dice2.setFitWidth(50);
        dice_box.getChildren().addAll(dice1, dice2);

        // Bottom console containing both resource box and dice box side by side
        HBox bottomConsole = new HBox(20, resource_box, dice_box);
        bottomConsole.setAlignment(Pos.CENTER);


        tileMap.setBottom(bottomConsole);




        primaryStage.show();
    }
}