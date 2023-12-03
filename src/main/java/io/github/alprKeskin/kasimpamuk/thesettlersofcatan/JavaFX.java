package io.github.alprKeskin.kasimpamuk.thesettlersofcatan;

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

public class JavaFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        Button btn = new Button("Click me!");
        btn.setStyle("-fx-background-color: orange; -fx-text-fill: purple;");
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 200);

        primaryStage.setTitle("JavaFX Application");
        primaryStage.setScene(scene);
        primaryStage.show();

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

/*
        int height = 400;
        int width = 400;
        AnchorPane tileMap = new AnchorPane();
        Scene content = new Scene(tileMap, width, height);
        Stage stage3 = new Stage();
        stage3.setScene(content);
        double size = 50,v=Math.sqrt(3)/2.0;
        for(double y=50;y<width;y+=size*Math.sqrt(3)) // x and y are interchanged
        {
            for(double x=50,dy=y;x<height;x+=(3.0/2.0)*size)
            {
                Polygon tile = new Polygon();
                tile.getPoints().addAll(new Double[]{
                        dy,x,
                        dy,x+size,
                        dy+size*v,x+size*(3.0/2.0),
                        dy+size*Math.sqrt(3),x+size,
                        dy+size*Math.sqrt(3),x,
                       dy+size*v, x-(size/2.0)
                });
                tile.setFill(Color.GOLD);
                tile.setStrokeWidth(2);
                tile.setStroke(Color.BLACK );
                tileMap.getChildren().add(tile);
                dy = dy==y ? dy+size*v : y;
            }
        }
        stage3.show();

*/


        int height = 500;
        int width = 500;
        Stage board = new Stage();
        AnchorPane tileMap = new AnchorPane();
        Scene catan_board = new Scene(tileMap, width, height);
        double board_centerx = tileMap.getWidth()/2;
        double board_centery = tileMap.getHeight()/2;
        board.setTitle("Catan Board");
        board.setScene(catan_board);
        double size = 50,v=Math.sqrt(3)/2.0;
        for(double y=-1;y<2;y++)
        {
            double centery= board_centery + y*size*3;

            double centerx= board_centerx;

            for(double x=-2;x<3;x++)
            {
                if(((y==-1 || y==1) && (x==-2 || x==2)))
                    continue;

                centerx=board_centerx+x*size*Math.sqrt(3);
                Polygon tile = new Polygon();
                tile.getPoints().addAll(new Double[]{
                        centerx-v*size,centery+size/2,
                        centerx-v*size,centery-size/2,
                        centerx,centery-size,
                        centerx+v*size,centery-size/2,
                        centerx+v*size,centery+size/2,
                        centerx,centery+size
                });
                tile.setFill(Color.GOLD);
                tile.setStrokeWidth(2);
                tile.setStroke(Color.BLACK );
                tileMap.getChildren().add(tile);
            }
        }
        board_centerx = board_centerx -  size*v;
        double centerx, centery;
        for(int x=-1; x<3; x++){
            centerx= board_centerx + x*size*Math.sqrt(3);
            for(int y=-1; y<2;y+=2){
                centery= board_centery + y*size*3/2;
                Polygon tile = new Polygon();
                tile.getPoints().addAll(new Double[]{
                        centerx-v*size,centery+size/2,
                        centerx-v*size,centery-size/2,
                        centerx,centery-size,
                        centerx+v*size,centery-size/2,
                        centerx+v*size,centery+size/2,
                        centerx,centery+size
                });
                tile.setFill(Color.GOLD);
                tile.setStrokeWidth(2);
                tile.setStroke(Color.BLACK );
                tileMap.getChildren().add(tile);

            }
        }
        board.show();

    }

}


/*
    class MyPolygon extends Pane {
        private void paint(double size) {
            Polygon polygon = new Polygon();
            polygon.setFill(Color.GOLD);
            polygon.setStroke(Color.BLACK);
            ObservableList<Double> list = polygon.getPoints();

            double centerX = getWidth() / 2, centerY = getHeight() / 2;
            double radius = Math.min(getWidth(), getHeight()) * 0.4;

            double[] points = new double[12];
            for (int i = 0; i < 6; i++) {
                points[i * 2] = size * Math.cos(i * 2 * Math.PI / 6);
                points[i * 2 + 1] = size * Math.sin(i * 2 * Math.PI / 6);
                list.add(points[i * 2]);
                list.add(points[i * 2 + 1]);
            }
            getChildren().clear();
            getChildren().add(polygon);

        }

        @Override
        public void setWidth(double width) {
            super.setWidth(width);
            paint(20.0);
        }

        @Override
        public void setHeight(double height) {
            super.setHeight(height);
            paint(20.0);
        }
    }
    */