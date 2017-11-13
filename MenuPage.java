import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.text.Font;


public class MenuPage extends Application{
    Color black = Color.BLACK;
    Color white = Color.WHITE;
    Color grey = Color.GREY;
    final String IDLE_BUTTON_STYLE = "-fx-background-color: #555555;";
    final String HOVERED_BUTTON_STYLE = "-fx-background-color: black;";
    Scene menu,settings;
    String x="2 Player Game";
    private double sceneWidth = 400;
    private double sceneHeight = 600;
    static Color[] colors = new Color[8];

    {
        for (int i = 0; i < 8; i++)
            colors[i] = new Color(1,1,1,1);
        colors[0]=Color.RED;
        colors[1]=Color.GREEN;
        colors[2]=Color.BLUE;
        colors[3]=Color.ORANGE;
        colors[4]=Color.PINK;
        colors[5]=Color.YELLOW;
        colors[6]=Color.AQUA;
        colors[7]=Color.BROWN;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)  {
        primaryStage.setTitle("Chain Reaction");
        BorderPane borderPane = new BorderPane();

        VBox vBox = new VBox();

        Image image = new Image("file:///C:/Users/shree/IdeaProjects/ChainReaction/icons/Nuclear-Power-1.jpg");
        ImageView im = new ImageView(image);
        im.setFitWidth(100);
        im.setFitHeight(100);
        ImageView im2 = new ImageView(image);
        im2.setFitWidth(100);
        im2.setFitHeight(100);


        Label label1 = new Label("Chain Reaction");
        label1.setFont(Font.font ("Verdana", 40));;
        label1.setTextFill(white);


        HBox hBox = new HBox();
        hBox.getChildren().addAll(im,label1,im2);
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);

        vBox.getChildren().add(hBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: #1e252a");
        vBox.setPadding(new Insets(10,10,10,10));

        borderPane.setTop(vBox);

        HBox hBox1 = new HBox();
        hBox1.setSpacing(10);
        hBox1.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(3,3,3,3));

        Label label = new Label("Players");
        label.setFont(Font.font("Verdana",20));
        label.setTextFill(white);
        Label num = new Label("2");
        num.setFont(Font.font("Verdana",55));
        num.setTextFill(white);
        Button minus = new Button("-");
        minus.setStyle(IDLE_BUTTON_STYLE);
        minus.setOnAction(event -> {
            int val = Integer.parseInt(num.getText());
            if(val>2) {
                val--;
                num.setText(val+"");
            }

        });
        minus.setFont(Font.font("Verdana",20));
        minus.setOnMouseEntered(e -> minus.setStyle(HOVERED_BUTTON_STYLE));
        minus.setOnMouseExited(e -> minus.setStyle(IDLE_BUTTON_STYLE));
        minus.setTextFill(Color.WHITE);

        Button plus = new Button("+");
        plus.setStyle(IDLE_BUTTON_STYLE);
        plus.setFont(Font.font("Verdana",20));
        plus.setOnMouseEntered(e -> plus.setStyle(HOVERED_BUTTON_STYLE));
        plus.setOnMouseExited(e -> plus.setStyle(IDLE_BUTTON_STYLE));
        plus.setTextFill(Color.WHITE);
        plus.setOnAction(event -> {
            int val = Integer.parseInt(num.getText());
            if(val<8) {
                val++;
                num.setText(val+"");
            }

        });

        hBox1.getChildren().addAll(minus,num,plus);

        HBox hBox2 = new HBox();
        Button play = new Button("Play");
        play.setOnAction(e -> {
        	int number_players = Integer.parseInt(num.getText());
			Player[] players=new Player[number_players];
			for(int i = 0;i<number_players;i++) {
				players[i] = new Player(colors[i]);
			}
			Game game=new Game(sceneWidth,sceneHeight,6,9,number_players,players);
			try {
				game.start(null,primaryStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			});
        Button playHD = new Button("Play with HD Grid");
        playHD.setOnAction(e -> {
            int number_players = Integer.parseInt(num.getText());
            Player[] players=new Player[number_players];
            for(int i = 0;i<number_players;i++) {
                players[i] = new Player(colors[i]);
            }
            Game game=new Game(sceneWidth,sceneHeight,10,15,number_players,players);
            try {
                game.start(null,primaryStage);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        Button Resume = new Button("Resume");
        Resume.setOnAction(e -> {
			ObjectInputStream in = null;
			ObjectInputStream inplay = null;
			Grid grid = null;
			Player[] players = new Player[8];
			try
			{
				try {
					in=new ObjectInputStream(new FileInputStream("out.txt"));
					inplay=new ObjectInputStream(new FileInputStream("player.txt"));

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					grid= (Grid) in.readObject();
					players = (Player[])inplay.readObject();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			finally
			{
				try {
					in.close();
					inplay.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			for(int i = 0;i<players.length;i++) {
				players[i].initialise();
			}
			if(grid.n==6) {
				System.out.println(grid);
				Game game = new Game(sceneWidth,sceneHeight,6,9,grid.number_players,players);
				try {
					game.start(grid,primaryStage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else{
                System.out.println(grid);
                Game game = new Game(sceneWidth,sceneHeight,10,15,grid.number_players,players);
                try {
                    game.start(grid,primaryStage);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
		});
        play.setStyle(IDLE_BUTTON_STYLE);
        playHD.setStyle(IDLE_BUTTON_STYLE);
        Resume.setStyle(IDLE_BUTTON_STYLE);
        play.setTextFill(Color.WHITE);
        playHD.setTextFill(Color.WHITE);
        Resume.setTextFill(Color.WHITE);
        play.setOnMouseEntered(e -> play.setStyle(HOVERED_BUTTON_STYLE));
        play.setOnMouseExited(e -> play.setStyle(IDLE_BUTTON_STYLE));
        playHD.setOnMouseEntered(e -> playHD.setStyle(HOVERED_BUTTON_STYLE));
        playHD.setOnMouseExited(e -> playHD.setStyle(IDLE_BUTTON_STYLE));
        Resume.setOnMouseEntered(e -> Resume.setStyle(HOVERED_BUTTON_STYLE));
        Resume.setOnMouseExited(e -> Resume.setStyle(IDLE_BUTTON_STYLE));
        hBox2.setPadding(new Insets(10,5,10,5));
        hBox2.setSpacing(10);
        hBox2.getChildren().addAll(play,playHD,Resume);
        hBox2.setAlignment(Pos.CENTER);

        VBox vBox2 = new VBox();
        vBox2.getChildren().addAll(label,hBox1,hBox2);
        vBox2.setAlignment(Pos.TOP_CENTER);
        vBox2.setStyle("-fx-background-color: #1e252a");
        borderPane.setCenter(vBox2);

        Label instructions = new Label("The Objective of Chain Reactions is to take control of the bpards" +
                "by eliminating your opponents orbs\n\n"+
                " Players takes it in turns to place their orbs in a cell. Once a cell has reached critical mass the orbs explode" +
                " into surrounding cells adding extra orb and claiming the cell for the playera player may only place thier orb in a blank "
                +"cell or a cell that contains orbs of their own color. As soons a plaer loses all their orbs they are out of the game");
        instructions.setWrapText(true);
        instructions.setPadding(new Insets(10,10,10,10));
        instructions.setTextFill(white);
        vBox2.getChildren().add(instructions);


        VBox vBox3 = new VBox();
        Button configure = new Button("Player Settings");
        configure.setStyle(IDLE_BUTTON_STYLE);
        configure.setOnMouseEntered(e -> configure.setStyle(HOVERED_BUTTON_STYLE));
        configure.setOnMouseExited(e -> configure.setStyle(IDLE_BUTTON_STYLE));
        configure.setTextFill(Color.WHITE);

        configure.setOnAction(e-> Settings.display());
        vBox3.getChildren().add(configure);
        vBox3.setStyle("-fx-background-color: #1e252a");
        vBox3.setPadding(new Insets(10,10,10,10));
        vBox3.setAlignment(Pos.BOTTOM_RIGHT);
        borderPane.setBottom(vBox3);
        Settings s = new Settings();

        menu = new Scene(borderPane, 600, 600);

        primaryStage.setScene(menu);
        primaryStage.getIcons().add(new Image("file:///C:/Users/shree/IdeaProjects/ChainReaction/icons/icon.png"));
        primaryStage.setResizable(false);
        primaryStage.show();
        //Game.serialize("out.txt","player.txt");
    }

}
