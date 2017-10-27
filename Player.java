
import javafx.scene.paint.Color;

public class Player {
	private Color color;
	private boolean isAlive;
	public Player(Color c)
	{
		color=c;
		isAlive=false;
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
}
