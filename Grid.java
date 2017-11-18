import java.io.File;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Grid extends Pane implements Serializable {

    /**
	 * 
	 */
	static String musicFile = "click.mp3";     // For example
	static Media sound;
	static MediaPlayer mediaPlayer;

	{
		try {
			sound = new Media(new File(musicFile).toURI().toString());
			mediaPlayer = new MediaPlayer(sound);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	private static final long serialVersionUID = 1L;
	private double block_width;
    private double block_height;
    Block[][] playfield; // For the main blocks of the cube in which spheres are added
    transient private Cube[][] cubes; // For the structure of the cube
    transient private Button[][] buttons; // The buttons which make the cubes clickable 
    private int player_number; // The current player who is playing
    int n,m; // The grid is n x m
    transient private Color color; // Color of the current player
    private double c1,c2,c3;
    private int remaining_players;
    private int count=0; // For counting that each player has played at least one turn
    int number_players; // Total number of players

	/**
	 * initalize to be called on when using resume game to
	 * reintialize the grid
	 */
    public void initialise()
    {
    	color=new Color(c1,c2,c3,1);
    	cubes=null;
    	buttons=null;
    	for(int i=0;i<n;i++)
    	{
    		for(int j=0;j<m;j++)
    		{
    			playfield[i][j].initialise();
    		}
    	}
    }

    /**
     * Empty Grid object constructor
     */
    public Grid() {
    }

    /**
     * Grid Constructor with given specification as parameters
     * @param sceneWidth
     * @param sceneHeight
     * @param border
     * @param n
     * @param m
     * @param number_players
     */
    public Grid(double sceneWidth,double sceneHeight,double border, int n, int m,int number_players) {
    	player_number=0;
    	color=Game.players[0].getColor();
    	this.c1=color.getRed();
    	this.c2=color.getGreen();
    	this.c3=color.getBlue();
    	this.n=n;
    	this.m=m;
    	this.number_players=number_players;
    	this.remaining_players=number_players;
    	//createGrid(sceneWidth,sceneHeight,border,n,m);
    }

    /**
     * create grid with given parameters
     * @param sceneWidth
     * @param sceneHeight
     * @param border
     * @param n
     * @param m
     */
    public void createGrid(double sceneWidth,double sceneHeight,double border, int n, int m)
    {
    	block_width = (sceneWidth - border) / n;
    	block_height = (sceneHeight - border) / m;
    	playfield = new Block[n][m];
    	cubes=new Cube[n][m];
    	buttons=new Button[n][m];
    	
        for( int i=0; i < n; i++)
        {
            for( int j=0; j < m; j++)
            {
            	 double x=(i * block_width) + border;
            	 double y=(j * block_height) + border;
            	 double width=block_width;
            	 double height=block_height;
            	 
            	 Cube cube=new Cube(x,y,width,height,color);
            	 getChildren().add(cube);
            	 cubes[i][j]=cube; // For future referencing to change color of Grid
            	 
                 Block node = new Block(x,y,width,height,c1,c2,c3,i,j);
                 getChildren().add(node);
                 playfield[i][j] = node;
                 
            }
         }
        for( int i=0; i < n; i++)
        {
            for( int j=0; j < m; j++)
            {
            	Button b=new Button();
            	b.setTranslateX((i * block_width) + border+10);
            	b.setTranslateY((j * block_height) + border+10);
            	b.setPrefSize(block_width,block_height);
            	Rectangle r=new Rectangle();
            	r.setWidth(block_width);
           	 	r.setHeight(block_height);
           	 	b.setShape(r);
           	 	b.setStyle("-fx-background-color: transparent;");
           	    
           	 	b.setOnAction(new ClickEvent(playfield[i][j]));
           	 	getChildren().add(b);
           	 	buttons[i][j]=b;
            }
        }
    }

    /**
     * make grid with resumed parameters
     * @param sceneWidth
     * @param sceneHeight
     * @param border
     * @param n
     * @param m
     */
    public void resumeGame(double sceneWidth,double sceneHeight,double border, int n, int m)
    {
    	block_width = (sceneWidth - border) / n;
    	block_height = (sceneHeight - border) / m;
    	cubes=new Cube[n][m];
    	buttons=new Button[n][m];
    	
        for( int i=0; i < n; i++)
        {
            for( int j=0; j < m; j++)
            {
            	 double x=(i * block_width) + border;
            	 double y=(j * block_height) + border;
            	 double width=block_width;
            	 double height=block_height;
            	 
            	 Cube cube=new Cube(x,y,width,height,color);
            	 this.getChildren().add(cube);
            	 cubes[i][j]=cube; // For future referencing to change color of Grid  
            	 
            	 Block node=playfield[i][j];
            	 getChildren().add(node);
            }
         }
        for( int i=0; i < n; i++)
        {
            for( int j=0; j < m; j++)
            {
            	Button b=new Button();
            	b.setTranslateX((i * block_width) + border+10);
            	b.setTranslateY((j * block_height) + border+10);
            	b.setPrefSize(block_width,block_height);
            	Rectangle r=new Rectangle();
            	r.setWidth(block_width);
           	 	r.setHeight(block_height);
           	 	b.setShape(r);
           	 	b.setStyle("-fx-background-color: transparent;");
           	    
           	 	b.setOnAction(new ClickEvent(playfield[i][j]));
           	 	this.getChildren().add(b);
           	 	buttons[i][j]=b;
            }
        }
    	for(int i=0;i<n;i++)
    	{
    		for(int j=0;j<m;j++)
    		{
    			Block block=playfield[i][j];
    			int num_spheres=block.getCurrent_mass();
    			block.setCurrent_mass(0);
    			for(int k=0;k<num_spheres;k++)
    			{
    				try {
						block.addMass(playfield);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
    		}
    	}
    	for(int i=0;i<n;i++)
		{
			for(int j=0;j<m;j++)
			{
				if(playfield[i][j].getPlayer_number()==-1 || playfield[i][j].getPlayer_number()==player_number)
				{
					buttons[i][j].setDisable(false);
				}
				else
				{
					buttons[i][j].setDisable(true);
				}
			}
		}
    }

    /**
     * Change color of grid
     * @param c
     */
    public void changeColor(Color c)
    {
    	for(int i=0;i<n;i++)
    	{
    		for(int j=0;j<m;j++)
    		{
    			cubes[i][j].changeColor(c);
    		}
    	}
    }

    /**
     * Eventhandler for click on grid
     */
    class	ClickEvent	implements	EventHandler<ActionEvent>
    {
		Block block;
    	
		public ClickEvent(Block block)
		{
			this.block=block;
		}

        /**
         * handle function on click
         * @param event
         */
		@Override
		public void handle(ActionEvent	event)
		{
			mediaPlayer.play();
			mediaPlayer = new MediaPlayer(sound);
			Game.serialize("undogrid.txt","undoplayer.txt");
			try
			{
				block.setPlayer_number(player_number);
				block.addMass(playfield);		
				int[] player_count=new int[8];
				for(int i=0;i<n;i++)
				{
					for(int j=0;j<m;j++)
					{
						if(playfield[i][j].getPlayer_number()!=-1)
						{
							player_count[playfield[i][j].getPlayer_number()]++;
						}
					}
				}
				if(count<number_players)
				{
					count++;
				}
				else
				{
					remaining_players=0;
					for(int i=0;i<number_players;i++)
					{
						if(player_count[i]==0)
						{
							Game.players[i].setAlive(false);
						}
						else
						{
							remaining_players++;
						}
					}
					
					
					// Declaring the winner of the game / closing the game etc
					if(remaining_players==1)
					{
						for(int i=0;i<number_players;i++)
						{
							if(Game.players[i].isAlive()==true)
							{
								System.out.println("Player "+(int)(i+1)+" wins the game");
								TimeUnit.MILLISECONDS.sleep(200);
								Game.deleteAllFiles();
								AlertPrompt.start(new Stage(),"Player "+(int)(i+1)+" wins the game");
								break;
							}
						}
					}
					
					
				}
				player_number=(player_number+1)%number_players;
				while(Game.players[player_number].isAlive()!=true)
				{
					player_number=(player_number+1)%number_players;
				}
				for(int i=0;i<n;i++)
				{
					for(int j=0;j<m;j++)
					{
						if(playfield[i][j].getPlayer_number()==-1 || playfield[i][j].getPlayer_number()==player_number)
						{
							buttons[i][j].setDisable(false);
						}
						else
						{
							buttons[i][j].setDisable(true);
						}
					}
				}
				color=Game.players[player_number].getColor();
				c1=color.getRed();
		    	c2=color.getGreen();
		    	c3=color.getBlue();
				changeColor(color);
				if(remaining_players!=1) {
					Game.serialize("out.txt", "player.txt");
				}
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}	
    }
}
