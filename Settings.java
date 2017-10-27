import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Settings {
    final static String IDLE_BUTTON_STYLE = "-fx-background-color: #555555;";
    final static String HOVERED_BUTTON_STYLE = "-fx-background-color: black;";

    public static void display(){
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Player Settings");
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(20,10,10,10));
        Button[] pp = new Button[8];
        for(int i =0 ;i<8;i++) {
            pp[i] = new Button("Player "+(i+1));
            pp[i].setTextFill(Color.WHITE);
            pp[i].setFont(Font.font("Verdana",20));
            pp[i].setPrefWidth(140);
            pp[i].setAlignment(Pos.CENTER);
            pp[i].setPadding(new Insets(5,10,5,10));
            pp[i].setStyle(IDLE_BUTTON_STYLE);
            vBox.getChildren().add(pp[i]);
        }
        pp[0].setOnMouseEntered(e -> pp[0].setStyle(HOVERED_BUTTON_STYLE));
        pp[0].setOnMouseExited(e -> pp[0].setStyle(IDLE_BUTTON_STYLE));
        pp[0].setOnMousePressed(
                e -> {
                    System.out.println("Hello World");
                });
        pp[0].setOnAction(e-> PlayerSettings.display("1"));
        pp[1].setOnMouseEntered(e -> pp[1].setStyle(HOVERED_BUTTON_STYLE));
        pp[1].setOnMouseExited(e -> pp[1].setStyle(IDLE_BUTTON_STYLE));
        pp[1].setOnMousePressed(
                e -> {
                    System.out.println("Hello World");
                });
        pp[1].setOnAction(e-> PlayerSettings.display("2"));
        pp[2].setOnMouseEntered(e -> pp[2].setStyle(HOVERED_BUTTON_STYLE));
        pp[2].setOnMouseExited(e -> pp[2].setStyle(IDLE_BUTTON_STYLE));
        pp[2].setOnMousePressed(
                e -> {
                    System.out.println("Hello World");
                });
        pp[2].setOnAction(e-> PlayerSettings.display("3"));
        pp[3].setOnMouseEntered(e -> pp[3].setStyle(HOVERED_BUTTON_STYLE));
        pp[3].setOnMouseExited(e -> pp[3].setStyle(IDLE_BUTTON_STYLE));
        pp[3].setOnMousePressed(
                e -> {
                    System.out.println("Hello World");
                });
        pp[3].setOnAction(e-> PlayerSettings.display("4"));
        pp[4].setOnMouseEntered(e -> pp[4].setStyle(HOVERED_BUTTON_STYLE));
        pp[4].setOnMouseExited(e -> pp[4].setStyle(IDLE_BUTTON_STYLE));
        pp[4].setOnMousePressed(
                e -> {
                    System.out.println("Hello World");
                });
        pp[4].setOnAction(e-> PlayerSettings.display("5"));
        pp[5].setOnMouseEntered(e -> pp[5].setStyle(HOVERED_BUTTON_STYLE));
        pp[5].setOnMouseExited(e -> pp[5].setStyle(IDLE_BUTTON_STYLE));
        pp[5].setOnMousePressed(
                e -> {
                    System.out.println("Hello World");
                });
        pp[5].setOnAction(e-> PlayerSettings.display("6"));
        pp[6].setStyle(IDLE_BUTTON_STYLE);
        pp[6].setOnMouseEntered(e -> pp[6].setStyle(HOVERED_BUTTON_STYLE));
        pp[6].setOnMouseExited(e -> pp[6].setStyle(IDLE_BUTTON_STYLE));
        pp[6].setOnMousePressed(
                e -> {
                    System.out.println("Hello World");
                });
        pp[6].setOnAction(e-> PlayerSettings.display("7"));
        pp[7].setOnMouseEntered(e -> pp[7].setStyle(HOVERED_BUTTON_STYLE));
        pp[7].setOnMouseExited(e -> pp[7].setStyle(IDLE_BUTTON_STYLE));
        pp[7].setOnMousePressed(
                e -> {
                    System.out.println("Hello World");
                });
        pp[7].setOnAction(e-> PlayerSettings.display("8"));
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
