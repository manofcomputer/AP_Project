import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PlayerSettings {
    final static String IDLE_BUTTON_STYLE = "-fx-background-color: #555555;";
    final static String HOVERED_BUTTON_STYLE = "-fx-background-color: black;";
    static Stage primaryStage;
    static void display(String player){
        primaryStage = new Stage();
        primaryStage.setTitle("Player "+player);
        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.setPadding(new Insets(20,10,10,10));
        final ColorPicker colorPicker = new ColorPicker();

        Button SubmitExit = new Button("Submit & Exit");
        SubmitExit.setOnAction(event -> closeProgram());
        SubmitExit.setStyle(IDLE_BUTTON_STYLE);
        SubmitExit.setOnMouseEntered(e -> SubmitExit.setStyle(HOVERED_BUTTON_STYLE));
        SubmitExit.setOnMouseExited(e -> SubmitExit.setStyle(IDLE_BUTTON_STYLE));
        SubmitExit.setTextFill(Color.WHITE);

        vBox.getChildren().addAll(colorPicker,SubmitExit);
        Scene scene = new Scene(vBox,350,400);
        vBox.setStyle("-fx-background: #1e252a;");
        vBox.setAlignment(Pos.TOP_CENTER);

        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("file:///C:/Users/shree/IdeaProjects/ChainReaction/icons/icon.png"));
        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setOnCloseRequest(event -> closeProgram());
        primaryStage.show();
    }
    static void closeProgram(){
        primaryStage.close();
    }

}
