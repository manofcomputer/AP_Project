import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Cube extends Pane {
	Rectangle upper,lower;
	Line ul,ur,ll,lr;

	/**
	 * Constructor for cube object
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param c
	 */
	public Cube(double x, double y, double width, double height,Color c)
	{
		 upper = new Rectangle(x,y,width,height);
	     upper.setFill(null);
	     upper.setStroke(c);
	     lower = new Rectangle(x+10,y+10,width,height);
	     lower.setFill(null);
	     lower.setStroke(c);
	
	     //Draw the line connecting them
	     ul = new Line(x,y,x+10,y+10);
	     ul.setStroke(c);
	     ur = new Line(x+width,y+height, x+width+10,y+height+10);
	     ur.setStroke(c);
	     ll = new Line(x,y+height, x+10,y+height+10);
	     ll.setStroke(c);
	     lr = new Line(x+width,y,x+width+10,y+10);
	     lr.setStroke(c);
	     
	     this.getChildren().addAll(upper,lower,ll,lr,ul,ur);
	}

	/**
	 * Change color of cube object
	 * @param c
	 */
	public void changeColor(Color c)
	{
		upper.setStroke(c);
		lower.setStroke(c);
		ul.setStroke(c);
		ur.setStroke(c);
		ll.setStroke(c);
		lr.setStroke(c);
	}
}
