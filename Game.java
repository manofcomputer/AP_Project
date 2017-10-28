import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game extends Application {
	
	private double sceneWidth = 400;
    private double sceneHeight = 600;
    
    private double border=20;
    
    protected static int n = 6;
    protected static int m = 9;
    
    private Grid grid;
    
    protected static Player[] players;
	
	public static void main(String[] args) {
        launch(args);
    }
	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox vBox=new VBox();
		
		
		//Initializing players 
		int number_players=3;
		players=new Player[number_players];
		players[0]=new Player(Color.RED);
		players[1]=new Player(Color.GREEN);
		players[2]=new Player(Color.BLUE);
		grid=new Grid(sceneWidth,sceneHeight,border,n,m,number_players);

		Menu options = new Menu("Options");
		Menu Undo = new Menu("Undo");
		options.getItems().addAll(new MenuItem("Restart"),new MenuItem("Menu"));
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(options,Undo);
		
		
		vBox.getChildren().addAll(menuBar,grid);
		vBox.setStyle("-fx-background-color: #1e252a");
		Scene scene = new Scene(vBox,sceneWidth+border,sceneHeight+border+25,Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
}
