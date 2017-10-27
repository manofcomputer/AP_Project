
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Grid extends Pane {

    private double gridWidth;
    private double gridHeight;
    private Block[][] playfield;
    
    Color c=Color.GREEN;
    
    public Grid(double sceneWidth,double sceneHeight,double border, int n, int m) {
    	showGrid(sceneWidth,sceneHeight,border,n,m);
    }	
    public void showGrid(double sceneWidth,double sceneHeight,double border, int n, int m)
    {
    	gridWidth = (sceneWidth - border) / n;
    	gridHeight = (sceneHeight - border) / m;
    	playfield = new Block[n][m];
    	
         for( int i=0; i < n; i++) {
             for( int j=0; j < m; j++) {

                 // create node
                 Block node = new Block((i * gridWidth) + border, (j * gridHeight) + border, gridWidth, gridHeight,c,i,j);
                 // add node to group
                 getChildren().add(node);

                 // add to playfield for further reference using an array
                 playfield[i][j] = node;
             }
         }
         for( int i=0; i < n; i++)
         {
             for( int j=0; j < m; j++)
             {
            	 Button b=new Button();
            	 b.setTranslateX((i * gridWidth) + border+10);
            	 b.setTranslateY((j * gridHeight) + border+10);
            	 b.setPrefSize(gridWidth,gridHeight);
            	 Rectangle r=new Rectangle();
            	 r.setWidth(gridWidth);
            	 r.setHeight(gridHeight);
            	 b.setShape(r);
            	 b.setStyle("-fx-background-color: transparent;");
            	    
            	 b.setOnAction(new ClickEvent(playfield[i][j]));
            	 getChildren().add(b);
             }
         }
    }
	public double getGridWidth() {
		return gridWidth;
	}
	public void setGridWidth(double gridWidth) {
		this.gridWidth = gridWidth;
	}
	public double getGridHeight() {
		return gridHeight;
	}
	public void setGridHeight(double gridHeight) {
		this.gridHeight = gridHeight;
	}
    class	ClickEvent	implements	EventHandler<ActionEvent>	{
	
		Block block;
    	
    	public ClickEvent(Block block)
		{
			this.block=block;
		}
		@Override
		public void handle(ActionEvent	event)
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
