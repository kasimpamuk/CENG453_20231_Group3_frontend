package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.gamescreen;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.restservice.CatanRestService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Random;

@Getter
@Setter
@Service
public class DiceBoxService {

    private ImageView dice1, dice2;
    private HBox diceBox;
    private Button button;
    private int diceValue1, diceValue2;

    public DiceBoxService() {
        createDiceBox();
        createRollDiceButton();
    }

    private void createDiceBox() {
        this.diceBox = new HBox(10);
        this.diceBox.setAlignment(Pos.CENTER);
        this.diceBox.setPadding(new Insets(10));
        this.diceBox.setStyle("-fx-background-color: #87CEEB;");

        this.dice1 = generateDice("dice1.png", 50, 50);
        this.dice2 = generateDice("dice1.png", 50, 50);

        this.diceBox.getChildren().addAll(dice1, dice2);

    }

    private void createRollDiceButton() {
        Button button = new Button("Roll Dice");
        button.setOnAction(e -> rollDice());
        this.diceBox.getChildren().add(button);
    }

    private void rollDice() {
        Random random = new Random();
        int diceValue1 = random.nextInt(6) + 1; // Dice values between 1 and 6
        int diceValue2 = random.nextInt(6) + 1;
        this.diceValue1 = diceValue1;
        this.diceValue2 = diceValue2;

        dice1.setImage(new Image("dice" + diceValue1 + ".png"));
        dice2.setImage(new Image("dice" + diceValue2 + ".png"));
    }

    private ImageView generateDice(String iconName, Integer height, Integer width) {
        ImageView dice = new ImageView(new Image(iconName));
        dice.setFitHeight(height);
        dice.setFitWidth(width);
        return dice;
    }
}
