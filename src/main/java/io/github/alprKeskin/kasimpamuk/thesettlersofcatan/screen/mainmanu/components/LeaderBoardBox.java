package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.screen.mainmanu.components;

import javafx.scene.layout.GridPane;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeaderBoardBox {
        GridPane leaderBoardBox;

        public LeaderBoardBox() {
            createLeaderBoardBox();
        }

        private void createLeaderBoardBox() {
            this.leaderBoardBox = new GridPane();
            this.leaderBoardBox.setHgap(10);
            this.leaderBoardBox.setVgap(10);
            this.leaderBoardBox.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
            this.leaderBoardBox.setStyle("-fx-background-color: #87CEEB;");


            this.leaderBoardBox.add(new javafx.scene.control.Label("Leaderboard"), 0, 0);



        }
}
