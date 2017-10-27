
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;



public class Game extends Application {
	
	private double sceneWidth = 400;
    private double sceneHeight = 600;
    
    private double border=20;
    
    protected static int n = 6;
    protected static int m = 9;
    
    Grid grid;
    final String IDLE_BUTTON_STYLE = "-fx-background-color: #555555;";
    final String HOVERED_BUTTON_STYLE = "-fx-background-color: black;";
	
	public static void main(String[] args) {
        launch(args);
    }
	
	@Override
	public void start(Stage primaryStage) throws Exception {
        VBox vBox = new VBox();


        Menu options = new Menu("Options");
        Menu Undo = new Menu("Undo");
        options.getItems().addAll(new MenuItem("Restart"),new MenuItem("Menu"));
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(options,Undo);

        vBox.setStyle("-fx-background-color: #1e252a");
		Group root=new Group();
        vBox.getChildren().addAll(menuBar,root);
		grid=new Grid(sceneWidth,sceneHeight,border,n,m);
		root.getChildren().add(grid);
		Scene scene = new Scene( vBox, sceneWidth+border, sceneHeight+border+50,Color.BLACK);
        primaryStage.setScene( scene);
        primaryStage.getIcons().add(new Image("file:///C:/Users/shree/IdeaProjects/ChainReaction/icons/icon.png"));
        primaryStage.setTitle("GamePlay");
        primaryStage.show();
	}
	
}
