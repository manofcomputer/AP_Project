import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class AlertPrompt extends Application{
    static final String IDLE_BUTTON_STYLE = "-fx-background-color: #555555;";
    static final String HOVERED_BUTTON_STYLE = "-fx-background-color: black;";
    @Override
    public void start(Stage primaryStage){
        //Stage primaryStage = new Stage();
        VBox vBox = new VBox();
        vBox.setStyle("-fx-background-color: #1e252a");
        Button play = new Button("Play Again?");
        Button menu = new Button("Back to Menu");


        play.setStyle(IDLE_BUTTON_STYLE);
        play.setFont(Font.font("Verdana",20));
        play.setOnMouseEntered(e -> play.setStyle(HOVERED_BUTTON_STYLE));
        play.setOnMouseExited(e -> play.setStyle(IDLE_BUTTON_STYLE));
        play.setTextFill(Color.WHITE);

        menu.setTextFill(Color.WHITE);
        menu.setStyle(IDLE_BUTTON_STYLE);
        menu.setFont(Font.font("Verdana",20));
        menu.setOnMouseEntered(e -> menu.setStyle(HOVERED_BUTTON_STYLE));
        menu.setOnMouseExited(e -> menu.setStyle(IDLE_BUTTON_STYLE));
        menu.setOnAction(event -> {
            //originalStage.close();
            primaryStage.close();
            new MenuPage().start(new Stage());
        });
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(3);
        vBox.getChildren().addAll(play,menu);
        Scene scene = new Scene(vBox,200,150);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("file:///C:/Users/shree/IdeaProjects/ChainReaction/icons/icon.png"));
        primaryStage.setResizable(false);
        //primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    public static void main(String[] args) {
            launch(args);
    }
}
