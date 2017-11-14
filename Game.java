import java.io.*;

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

	private double border = 25;

	protected static int n;
	protected static int m;

	private int number_players;
	private static Grid grid;

	protected static Player[] players;
	private static Stage originalstage;

    /**
     * Constructor for game settings
     * @param sceneWidth
     * @param sceneHeight
     * @param n
     * @param m
     * @param number_players
     * @param players
     */
	public Game(double sceneWidth, double sceneHeight, int n, int m, int number_players, Player[] players) {
		this.sceneWidth = sceneWidth;
		this.sceneHeight = sceneHeight;
		this.n = n;
		this.m = m;
		this.number_players = number_players;
		this.players = players;
	}

    /**
     * This method starts the grid to play the game
     * put g= null if there is no resume game or put the resumed grid
     * enter primary stage for playing the grid in the current stage
     * @param g
     * @param primaryStage
     * @throws Exception
     */
	public void start(Grid g, Stage primaryStage) throws Exception {
		//Stage primaryStage = new Stage();
		originalstage = primaryStage;
		VBox root = new VBox();
		Menu options = new Menu("Options");
		options.setStyle("-fx-background-color: #6ab708");
		MenuItem restart = new MenuItem("Restart");
		restart.setOnAction(event -> this.restartGame(primaryStage));

		MenuItem menu = new MenuItem("Menu");
		menu.setOnAction(event -> {
			primaryStage.close();
			new MenuPage().start(new Stage());
		});

		MenuItem Undo = new MenuItem("Undo");
		Undo.setOnAction(event -> {
			ObjectInputStream in = null;
			ObjectInputStream inplay = null;
			Grid grid = null;
			Player[] players = new Player[8];
			try {
				try {
					in = new ObjectInputStream(new FileInputStream("undogrid.txt"));
					inplay = new ObjectInputStream(new FileInputStream("undoplayer.txt"));

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					grid = (Grid) in.readObject();
					players = (Player[]) inplay.readObject();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} finally {
				try {
					in.close();
					inplay.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			for (int i = 0; i < players.length; i++) {
				players[i].initialise();
			}
			if (grid.n == 6) {
				System.out.println(grid.n);
				Game game = new Game(sceneWidth, sceneHeight, 6, 9, grid.number_players, players);
				try {
					game.start(grid, primaryStage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				System.out.println(grid);
				Game game = new Game(sceneWidth, sceneHeight, 10, 15, grid.number_players, players);
				try {
					game.start(grid, primaryStage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			Game.serialize("out.txt","player.txt");
		});

		options.getItems().addAll(restart, menu, Undo);
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(options);
		menuBar.setStyle("-fx-background-color: transparent");
		root.getChildren().add(menuBar);
		root.setStyle("-fx-background-color: #1e252a");
		if (g == null) {
			grid = new Grid(sceneWidth, sceneHeight, border, n, m, number_players);
			grid.createGrid(sceneWidth, sceneHeight, border, n, m);
		} else {
			grid = g;
			grid.initialise();
			grid.resumeGame(sceneWidth, sceneHeight, border, n, m);
		}
		root.getChildren().add(grid);
		Scene scene = new Scene(root, sceneWidth + border, sceneHeight + border + 35, Color.BLACK);
		primaryStage.setScene(scene);
		primaryStage.setOnCloseRequest(event -> new MenuPage().start(new Stage()));
		primaryStage.show();
	}

	/**
	 * This function serailizes the Grid class object and Player array in the file
     * gridfile and playerfile
	 * @param gridfile
	 * @param playerfile
	 */
	public static void serialize(String gridfile,String playerfile) {
		ObjectOutputStream out = null;
		ObjectOutputStream outplay = null;
		try {
			try {
				out = new ObjectOutputStream(new FileOutputStream(gridfile));
				outplay = new ObjectOutputStream(new FileOutputStream(playerfile));
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
		} finally {
			try {
				out.close();
				outplay.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void closeStage(){
		originalstage.close();
	}
	public void restartGame(Stage primaryStage){
		for (int i = 0;i<players.length;i++){
			players[i].setAlive(true);
		}
		try {
			this.start(null, primaryStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Stage getOriginalstage() {
		return originalstage;
	}
}
