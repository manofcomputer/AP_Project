import javafx.scene.paint.Color;

import java.io.Serializable;

public class Player implements Serializable{
	private Color color;
	private boolean isAlive,hasPlayed;
	public Player(Color c)
	{
		color=c;
		isAlive=true;
		hasPlayed=false;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color c) {
		this.color = c;
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
