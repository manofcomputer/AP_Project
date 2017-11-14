import java.io.Serializable;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Sphere;
import javafx.util.Duration;

public class Block extends Group implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private double x,y,width,height;
    private int critical_mass,current_mass;
    private int player_number;
    transient private Color color;
    private double c1,c2,c3;
    transient private Sphere[] spheres;
    private int i,j;
    transient private RotateTransition rt;

	/**
	 * initialize block after it is resumed
	 */
	public void initialise()
    {
    	this.color=new Color(c1,c2,c3,1);
    	spheres=new Sphere[4];
    }

	/**
	 * Constructor to make block (1)
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param c1
	 * @param c2
	 * @param c3
	 * @param i
	 * @param j
	 */
    public Block(double x, double y, double width, double height,double c1,double c2,double c3,int i,int j) {
    	spheres=new Sphere[4];
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.c1=c1;
		this.c2=c2;
		this.c3=c3;
		this.color=new Color(c1,c2,c3,1);
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

	/**
	 * Add mass to block on the block array
	 * @param playfield
	 * @throws InterruptedException
	 */
    public void addMass(Block[][] playfield) throws InterruptedException
	{
		color=Game.players[player_number].getColor();
		c1=color.getRed();
		c2=color.getGreen();
		c3=color.getBlue();
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
		    if(current_mass==2 && critical_mass==4)
		    {
		    	rt = new RotateTransition(Duration.millis(5000),this);
			    rt.setByAngle(360);
			    rt.setCycleCount(Integer.MAX_VALUE);
			    rt.setAxis(this.getRotationAxis());
			    rt.setInterpolator(Interpolator.LINEAR);
			    rt.play();
		    }
		}
		else
		{
			explode(playfield);
			
		}
	}

	/**
	 * Explode block (1)
	 * @param playfield
	 * @throws InterruptedException
	 */
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
	    int number_spheres=critical_mass-1;


	    /*rt.stop();
	    ParallelTransition pt = new ParallelTransition();
	    
	    if(i>0)
		{
			Sphere sphere=spheres[number_spheres];
			Block block=playfield[i-1][j];
			PathTransition t=new PathTransition();
			t.setNode(sphere);
			t.setDuration(Duration.seconds(0.25));
			MoveTo moveTo = new MoveTo(sphere.getTranslateX(),sphere.getTranslateY());
			LineTo l1=new LineTo(block.x+(width/2),block.y+(height/2));
			Path p=new Path();
			p.getElements().add(moveTo);
			p.getElements().add(l1);
			t.setPath(p);
			//t.play();
			pt.getChildren().add(t);
			number_spheres--;
		}
		if(j>0)
		{
			Sphere sphere=spheres[number_spheres];
			Block block=playfield[i][j-1];
			PathTransition t=new PathTransition();
			t.setNode(sphere);
			t.setDuration(Duration.seconds(0.25));
			MoveTo moveTo = new MoveTo(sphere.getTranslateX(),sphere.getTranslateY());
			LineTo l1=new LineTo(block.x+(width/2),block.y+(height/2));;
			Path p=new Path();
			p.getElements().add(moveTo);
			p.getElements().add(l1);
			t.setPath(p);
			//t.play();
			pt.getChildren().add(t);
			number_spheres--;
		}
		if(i<Game.n-1)
		{
			Sphere sphere=spheres[number_spheres];
			Block block=playfield[i+1][j];
			PathTransition t=new PathTransition();
			t.setNode(sphere);
			t.setDuration(Duration.seconds(0.25));
			MoveTo moveTo = new MoveTo(sphere.getTranslateX(),sphere.getTranslateY());
			LineTo l1=new LineTo(block.x+(width/2),block.y+(height/2));;
			Path p=new Path();
			p.getElements().add(moveTo);
			p.getElements().add(l1);
			t.setPath(p);
			//t.play();
			pt.getChildren().add(t);
			number_spheres--;
		}
		if(j<Game.m-1)
		{
			Sphere sphere=spheres[number_spheres];
			Block block=playfield[i][j+1];
			PathTransition t=new PathTransition();
			t.setNode(sphere);
			t.setDuration(Duration.seconds(0.25));
			MoveTo moveTo = new MoveTo(sphere.getTranslateX(),sphere.getTranslateY());
			LineTo l1=new LineTo(block.x+(width/2),block.y+(height/2));;
			Path p=new Path();
			p.getElements().add(moveTo);
			p.getElements().add(l1);
			t.setPath(p);
			//t.play();
			pt.getChildren().add(t);
			number_spheres--;
		}
		pt.play();
		pt.setOnFinished(e -> {
			try {
				explode1(playfield);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});*/
		explode1(playfield);
	}

	/**
	 * explode other blocks near the grid
	 * @param playfield
	 * @throws InterruptedException
	 */
	public void explode1(Block[][] playfield) throws InterruptedException
	{
		for(int k=0;k<critical_mass;k++)
		{
			this.getChildren().remove(spheres[k]);
			spheres[k]=null;
		}
		
		current_mass=0;
		int player_num=player_number;
		player_number=-1;
		
		
		if(i>0)
		{
			Block block=playfield[i-1][j];
			block.setPlayer_number(player_num);
			block.addMass(playfield);
		}
		if(j>0)
		{
			Block block=playfield[i][j-1];
			block.setPlayer_number(player_num);
			block.addMass(playfield);
		}
		if(i<Game.n-1)
		{
			Block block=playfield[i+1][j];
			block.setPlayer_number(player_num);
			block.addMass(playfield);
		}
		if(j<Game.m-1)
		{
			Block block=playfield[i][j+1];
			block.setPlayer_number(player_num);
			block.addMass(playfield);
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
