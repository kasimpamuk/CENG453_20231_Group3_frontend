package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.screen.gameboard.component;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.dto.response.InitialResponseDTO;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.restservice.CatanRestService;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

@Getter
@Setter
@Service
public class DiceBox {

    private ImageView dice1, dice2;
    private HBox diceBox;
    private Button button;

    private CatanRestService catanRestService = new CatanRestService();

    public DiceBox() {
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

        dice1.setImage(new Image("dice" + diceValue1 + ".png"));
        dice2.setImage(new Image("dice" + diceValue2 + ".png"));
        
        // TEST
        startPollingInANewThread();
        // TEST
    }

    private ImageView generateDice(String iconName, Integer height, Integer width) {
        ImageView dice = new ImageView(new Image(iconName));
        dice.setFitHeight(height);
        dice.setFitWidth(width);
        return dice;
    }

    private void startPollingInANewThread() {
        Task<InitialResponseDTO> pollingTask = new Task<>() {
            @Override
            protected InitialResponseDTO call() throws Exception {
                return catanRestService.sendGetRequestByPolling(new URI("http://localhost:8080/api/catan/test-get"));
            }
        };

        pollingTask.setOnSucceeded(event -> {
            // This will be executed on the JavaFX Application Thread
            InitialResponseDTO response = pollingTask.getValue();
            // Update your UI based on the response
            this.diceBox.setStyle("-fx-background-color: #97DEFB;"); // TODO: Do not forget to delete
            System.out.println("YEPPPPP!");
        });

        pollingTask.setOnFailed(event -> {
            // Handle any exceptions
            Throwable e = pollingTask.getException();
            e.printStackTrace();
            // Update UI to reflect the error
        });

        // Start the task on a new thread
        new Thread(pollingTask).start();
    }

}
