import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
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
		Group root=new Group();
		
		
		//Initializing players 
		int number_players=3;
		players=new Player[3];
		players[0]=new Player(Color.RED);
		players[1]=new Player(Color.GREEN);
		players[2]=new Player(Color.BLUE);
		grid=new Grid(sceneWidth,sceneHeight,border,n,m,number_players);
		
		
		root.getChildren().add(grid);
		Scene scene = new Scene(root,sceneWidth+border,sceneHeight+border,Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
}
