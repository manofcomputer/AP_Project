import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.util.Duration;

public class Block extends Group{
	
	private double x,y,width,height;
    private int critical_mass,current_mass;
    private int player_number;
    private Color color;
    private Sphere[] spheres=new Sphere[4];
    private int i,j;
    RotateTransition rt;
    
    public Block(double x, double y, double width, double height,Color color,int i,int j) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.color=color;
		this.i=i;
		this.j=j;
		current_mass=0;
		player_number=-1;
		
		critical_mass=4;
		if(i==0 || i==Game.n-1)
			critical_mass--;
		if(j==0 || j==Game.m-1)
			critical_mass--;
    }
    public void addMass(Block[][] playfield) throws InterruptedException
	{
		color=Game.players[player_number].getColor();
		if(current_mass<critical_mass-1)
		{
			Sphere s=new Sphere();
			s.setRadius(width/4);
			current_mass++;
			s.setTranslateX(x+(width/2)+((current_mass/2)*12.5));
			s.setTranslateY(y+(height/2)+((current_mass/3)*12.5));
		    PhongMaterial material = new PhongMaterial();
		    material.setDiffuseColor(color);
		    
		    s.setMaterial(material);
		    
		    spheres[current_mass-1]=s;
		    getChildren().add(s);
		    for(int i=0;i<current_mass;i++)
		    {
		    	Sphere sphere=spheres[i];
			    sphere.setMaterial(material);
		    }
		    
		    if(current_mass==critical_mass-1)
		    {
		    	rt = new RotateTransition(Duration.millis(1500),this);
			    rt.setByAngle(360);
			    rt.setCycleCount(Integer.MAX_VALUE);
			    rt.setAxis(this.getRotationAxis());
			    rt.setInterpolator(Interpolator.LINEAR);
			    rt.play();
		    }
		}
		else
		{
			rt.stop();
			explode(playfield);
		}
	}
	public void explode(Block[][] playfield) throws InterruptedException
	{	
		Sphere s=new Sphere();
		s.setRadius(width/4);
		s.setTranslateX(x+(width/2)+((current_mass/2)*12.5));
		s.setTranslateY(y+(height/2)+((current_mass/3)*12.5));
	    PhongMaterial material = new PhongMaterial();
	    material.setDiffuseColor(color);
	    s.setMaterial(material);
	    this.getChildren().add(s);
	    spheres[critical_mass-1]=s;
	    
		for(int k=0;k<critical_mass;k++)
		{
			this.getChildren().remove(spheres[k]);
			spheres[k]=null;
		}
		
		current_mass=0;
		
		if(i>0)
		{
			Block block=playfield[i-1][j];
			block.setPlayer_number(player_number);
			block.addMass(playfield);
		}
		if(j>0)
		{
			Block block=playfield[i][j-1];
			block.setPlayer_number(player_number);
			block.addMass(playfield);
		}
		if(i<Game.n-1)
		{
			Block block=playfield[i+1][j];
			block.setPlayer_number(player_number);
			block.addMass(playfield);
		}
		if(j<Game.m-1)
		{
			Block block=playfield[i][j+1];
			block.setPlayer_number(player_number);
			block.addMass(playfield);
		}
		
		if(current_mass==0)
		{
			player_number=-1;
		}
	}
	public int getCurrent_mass() {
		return current_mass;
	}
	public void setCurrent_mass(int current_mass) {
		this.current_mass = current_mass;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}

	public int getPlayer_number() {
		return player_number;
	}

	public void setPlayer_number(int player_number) {
		this.player_number = player_number;
	}
}
