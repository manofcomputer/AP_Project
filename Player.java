import java.io.Serializable;

import javafx.scene.paint.Color;

public class Player implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	transient private Color color;
	double c1,c2,c3;
	private boolean isAlive,hasPlayed;
	public void initialise()
	{
		color=new Color(c1,c2,c3,1);
	}
	public Player(double c1,double c2,double c3)
	{
		this.c1=c1;
		this.c2=c2;
		this.c3=c3;
		color=new Color(c1,c2,c3,1);
		isAlive=true;
		hasPlayed=false;
	}
	public Player(Color c)
	{
		this.c1=c.getRed();
		this.c2=c.getGreen();
		this.c3=c.getBlue();
		color=c;
		isAlive=true;
		hasPlayed=false;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(double c1,double c2,double c3) {
		this.c1=c1;
		this.c2=c2;
		this.c3=c3;
		this.color = new Color(c1,c2,c3,1);
	}
	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	public boolean hasPlayed() {
		return hasPlayed;
	}
	public void hasPlayed(boolean hasPlayed) {
		this.hasPlayed = hasPlayed;
	}
}
