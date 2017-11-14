import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Settings {
    final static String IDLE_BUTTON_STYLE = "-fx-background-color: #555555;";
    final static String HOVERED_BUTTON_STYLE = "-fx-background-color: black;";

    /**
     * Displays player settings with color picker option
     */
    public static void display(){
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Player Settings");
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(20,10,10,10));
        Label[] pp = new Label[8];
        ColorPicker[] colorPickers = new ColorPicker[8];
        for(int i = 0;i<8;i++) {
            colorPickers[i] = new ColorPicker();
            colorPickers[i].setStyle("-fx-background-color: white");
        }
        colorPickers[0].setValue(Color.RED);
        colorPickers[1].setValue(Color.GREEN);
        colorPickers[2].setValue(Color.BLUE);
        colorPickers[3].setValue(Color.ORANGE);
        colorPickers[4].setValue(Color.PINK);
        colorPickers[5].setValue(Color.YELLOW);
        colorPickers[6].setValue(Color.AQUA);
        colorPickers[7].setValue(Color.BROWN);
        colorPickers[7].setStyle("-fx-background-color: white");
        for(int i =0 ;i<8;i++) {
            pp[i] = new Label("Player "+(i+1));
            pp[i].setTextFill(Color.WHITE);
            pp[i].setFont(Font.font("Verdana",20));
            pp[i].setPrefWidth(140);
            pp[i].setAlignment(Pos.CENTER);
            pp[i].setPadding(new Insets(5,10,5,10));
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            hBox.setSpacing(2);

            hBox.getChildren().addAll(pp[i],colorPickers[i]);
            vBox.getChildren().addAll(hBox);
        }
        colorPickers[0].setOnAction(event -> {
            MenuPage.colors[0] = colorPickers[0].getValue();
        });
        colorPickers[1].setOnAction(event -> {
            MenuPage.colors[1] = colorPickers[1].getValue();
        });
        colorPickers[2].setOnAction(event -> {
            MenuPage.colors[2] = colorPickers[2].getValue();
        });
        colorPickers[3].setOnAction(event -> {
            MenuPage.colors[3] = colorPickers[3].getValue();
        });
        colorPickers[4].setOnAction(event -> {
            MenuPage.colors[4] = colorPickers[4].getValue();
        });
        colorPickers[5].setOnAction(event -> {
            MenuPage.colors[5] = colorPickers[5].getValue();
        });
        colorPickers[6].setOnAction(event -> {
        	MenuPage.colors[6] = colorPickers[6].getValue();
        });
        colorPickers[7].setOnAction(event -> {
        	MenuPage.colors[7] = colorPickers[7].getValue();
        });

        vBox.setStyle("-fx-background-color: #1e252a");
        vBox.setAlignment(Pos.TOP_CENTER);
        Scene scene = new Scene(vBox,350,400);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("file:///C:/Users/shree/IdeaProjects/ChainReaction/icons/icon.png"));
        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();


    }
}
