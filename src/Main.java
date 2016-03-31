package sample;

import application.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        ActionBar actionBar = new ActionBar("Traitment sur les arbres");
        DrawerLayout drawerLayout = new DrawerLayout();
        drawerLayout.getChildren().add(FXMLLoader.load(getClass().getClassLoader().getResource("drawerLayout.fxml")));
        Color white = Color.WHITE;
        actionBar.addTab(new TabTitle(new MaterialText("Arbre Rouge Noire", white)), FXMLLoader.load(getClass().getClassLoader().getResource("arn.fxml")));
        actionBar.addTab(new TabTitle(new MaterialText("Arbre AVL", white)), FXMLLoader.load(getClass().getClassLoader().getResource("aavl.fxml")));
        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(50));
        MaterialText materialText = new MaterialText("Page en construction !", Color.RED);
        materialText.setFont(new Font(20));
        ImageView im = new ImageView(new Image("inProgress.gif"));
        im.setFitWidth(300);
        im.setFitHeight(300);
        vBox.getChildren().add(materialText);
        vBox.getChildren().add(im);

        actionBar.addTab(new TabTitle(new MaterialText("Arbre 2 3 4", white)), vBox);
        MaterialRootLayout root = new MaterialRootLayout(actionBar, drawerLayout);
        primaryStage.setTitle("Traitment sur les arbres");
        primaryStage.getIcons().add(new Image("logo.jpg"));
        primaryStage.setScene(new Scene(root, 1000, 650));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}