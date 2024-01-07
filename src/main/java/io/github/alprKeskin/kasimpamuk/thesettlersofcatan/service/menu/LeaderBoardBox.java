package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.menu;

import javafx.scene.layout.GridPane;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
public class LeaderBoardBox {

        public GridPane createLeaderBoardBox() {
            GridPane leaderBoardBox = new GridPane();
            leaderBoardBox = new GridPane();
            leaderBoardBox.setHgap(10);
            leaderBoardBox.setVgap(10);
            leaderBoardBox.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
            leaderBoardBox.setStyle("-fx-background-color: #87CEEB;");


            leaderBoardBox.add(new javafx.scene.control.Label("Leaderboard"), 0, 0);

            return leaderBoardBox;
        }

}
