import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;

public class Game extends Application implements Serializable{
	public final long serialUIDVersion = 11L;
	
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
		players[0]=new Player(new Color(1,1,1,1));
		players[1]=new Player(Color.GREEN);
		players[2]=new Player(Color.BLUE);
		grid=new Grid(sceneWidth,sceneHeight,border,n,m,number_players);

		try {
			deserialize();
		}
		catch (Exception e){
			System.out.println("No resume game");
		}

		Menu options = new Menu("Options");
		Menu Undo = new Menu("Undo");
		options.getItems().addAll(new MenuItem("Restart"),new MenuItem("Menu"));
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(options,Undo);
		
		
		vBox.getChildren().addAll(menuBar,grid);
		vBox.setStyle("-fx-background-color: #1e252a");
		Scene scene = new Scene(vBox,sceneWidth+border,sceneHeight+border+25,Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(event -> {
			try {
				closeProgram();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
        primaryStage.show();
	}
	public void closeProgram() throws IOException{
		serialize();

	}
	public void serialize() throws IOException{
		ObjectOutputStream out = null;
		try{
			out = new ObjectOutputStream(
					new FileOutputStream("out.txt")
			);
			out.writeObject(grid);
		}
		finally {
			out.close();
		}
	}
	public void deserialize() throws IOException,ClassNotFoundException{
		ObjectInput in = null;
		try{
			in = new ObjectInputStream(
					new FileInputStream("out.txt")
			);
			grid = (Grid) in.readObject();
		}
		finally {
			in.close();
		}
	}
}
