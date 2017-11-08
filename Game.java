import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game {
	
	private double sceneWidth;
    private double sceneHeight;
    
    private double border=25;
    
    protected static int n;
    protected static int m;
    
    private int number_players;
    private static Grid grid;
    
    protected static Player[] players;
	public Game(double sceneWidth,double sceneHeight,int n,int m,int number_players,Player[] players)
	{
		this.sceneWidth=sceneWidth;
		this.sceneHeight=sceneHeight;
		this.n=n;
		this.m=m;
		this.number_players=number_players;
		this.players = players;
	}
	public void start(Grid g) throws Exception {
		Stage primaryStage = new Stage();
		VBox root=new VBox();
		Menu options = new Menu("Options");
		options.setStyle("-fx-background-color: #6ab708");
		Menu Undo = new Menu("Undo");
		//Undo.setOnAction();
		Undo.setStyle("-fx-background-color: #6ab708");
		options.getItems().addAll(new MenuItem("Restart"),new MenuItem("Menu"));
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(options,Undo);
		menuBar.setStyle("-fx-background-color: transparent");
		root.getChildren().add(menuBar);
		root.setStyle("-fx-background-color: #1e252a");
		if(g==null) {
			grid=new Grid(sceneWidth,sceneHeight,border,n,m,number_players);
			grid.createGrid(sceneWidth, sceneHeight, border, n, m);
		}
		else {
			grid = g;
			grid.initialise();
			grid.resumeGame(sceneWidth, sceneHeight, border, n, m);
		}
		root.getChildren().add(grid);
		
		//Initializing players 
		/*b1.setOnAction(e -> {
			root.getChildren().removeAll(b1,b2);
			number_players=3;
			players=new Player[3];
			players[0]=new Player(1,0,0);
			players[1]=new Player(0,1,0);
			players[2]=new Player(0,0,1);
			grid=new Grid(sceneWidth,sceneHeight,border,n,m,number_players);
			grid.createGrid(sceneWidth, sceneHeight, border, n, m);
			root.getChildren().add(grid);
		});
		
		b2.setOnAction(e -> {
			root.getChildren().removeAll(b1,b2);
			ObjectInputStream in = null;
			try
			{
				try {
					in=new ObjectInputStream(new FileInputStream("out.txt"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					grid= (Grid) in.readObject();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			finally
			{
				try {
					in.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			players=new Player[3];
			players[0]=new Player(1,0,0);
			players[1]=new Player(0,1,0);
			players[2]=new Player(0,0,1);
			grid.initialise();
			root.getChildren().add(grid);
			grid.resumeGame(sceneWidth, sceneHeight, border, n, m);
			
		});
		*/
		Scene scene = new Scene(root,sceneWidth+border,sceneHeight+border+35,Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	public static void serialize()
	{
		ObjectOutputStream out	=	null;	
		ObjectOutputStream outplay = null;
		try {
			try {
				out=new ObjectOutputStream(new FileOutputStream("out.txt"));
				outplay=new ObjectOutputStream(new FileOutputStream("outplay.txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				out.writeObject(grid);
				outplay.writeObject(players);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		finally
		{
			try {
				out.close();
				outplay.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
