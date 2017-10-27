
import javafx.animation.PathTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.util.Duration;

public class Block extends Pane {
	
	private double x,y,width,height;
    private Rectangle upper,lower;
    private Line ll,lr,ul,ur;
    private int critical_mass,current_mass;
    private int player_no;
    private Player[] players;
    private Color color;
    Sphere[] spheres=new Sphere[4];
    int i,j;
    
	public Block(double x, double y, double _width, double _height,Color c,int i,int j) {
		
		this.x=x;
		this.y=y;
		this.width=_width;
		this.height=_height;
		this.color=c;
		this.i=i;
		this.j=j;
		
		current_mass=0;
		
		critical_mass=4;
		if(i==0 || i==Game.n-1)
			critical_mass--;
		if(j==0 || j==Game.m-1)
			critical_mass--;
		
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
	    
        getChildren().addAll(upper,lower,ul,ur,ll,lr);
        
    }
	public void changeColor(Color c)
	{
		upper.setStroke(c);
		lower.setStroke(c);
		ul.setStroke(c);
		ur.setStroke(c);
		ll.setStroke(c);
		lr.setStroke(c);
	}
	public void addMass(Block[][] playfield) throws InterruptedException
	{
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
		}
		else
		{
			explode(playfield);
		}
	}
	public void explode(Block[][] playfield) throws InterruptedException
	{	
		Sphere s=new Sphere();
		s.setRadius(width/4);
		current_mass++;
		s.setTranslateX(x+(width/2)+((current_mass/2)*12.5));
		s.setTranslateY(y+(height/2)+((current_mass/3)*12.5));
	    PhongMaterial material = new PhongMaterial();
	    material.setDiffuseColor(color);
	    s.setMaterial(material);
	    this.getChildren().add(s);
	    spheres[critical_mass-1]=s;
	    int number_spheres=critical_mass-1;
	    
	    PathTransition t = null;
	    
	    if(i>0)
		{
			Sphere sphere=spheres[number_spheres];
			Block block=playfield[i-1][j];
			t=new PathTransition();
			t.setNode(sphere);
			t.setDuration(Duration.seconds(0.25));
			MoveTo moveTo = new MoveTo(sphere.getTranslateX(),sphere.getTranslateY());
			LineTo l1=new LineTo(block.x+(width/2),block.y+(height/2));
			Path p=new Path();
			p.getElements().add(moveTo);
			p.getElements().add(l1);
			t.setPath(p);
			t.play();
			number_spheres--;
		}
		if(j>0)
		{
			Sphere sphere=spheres[number_spheres];
			Block block=playfield[i][j-1];
			t=new PathTransition();
			t.setNode(sphere);
			t.setDuration(Duration.seconds(0.25));
			MoveTo moveTo = new MoveTo(sphere.getTranslateX(),sphere.getTranslateY());
			LineTo l1=new LineTo(block.x+(width/2),block.y+(height/2));;
			Path p=new Path();
			p.getElements().add(moveTo);
			p.getElements().add(l1);
			t.setPath(p);
			t.play();
			number_spheres--;
		}
		if(i<Game.n-1)
		{
			Sphere sphere=spheres[number_spheres];
			Block block=playfield[i+1][j];
			t=new PathTransition();
			t.setNode(sphere);
			t.setDuration(Duration.seconds(0.25));
			MoveTo moveTo = new MoveTo(sphere.getTranslateX(),sphere.getTranslateY());
			LineTo l1=new LineTo(block.x+(width/2),block.y+(height/2));;
			Path p=new Path();
			p.getElements().add(moveTo);
			p.getElements().add(l1);
			t.setPath(p);
			t.play();
			number_spheres--;
		}
		if(j<Game.m-1)
		{
			Sphere sphere=spheres[number_spheres];
			Block block=playfield[i][j+1];
			t=new PathTransition();
			t.setNode(sphere);
			t.setDuration(Duration.seconds(0.25));
			MoveTo moveTo = new MoveTo(sphere.getTranslateX(),sphere.getTranslateY());
			LineTo l1=new LineTo(block.x+(width/2),block.y+(height/2));;
			Path p=new Path();
			p.getElements().add(moveTo);
			p.getElements().add(l1);
			t.setPath(p);
			t.play();
			number_spheres--;
		}
		t.setOnFinished(e -> {
			try {
				explode1(playfield);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});		
	}
	public void explode1(Block[][] playfield) throws InterruptedException
	{
		for(int i=0;i<critical_mass;i++)
		{
			this.getChildren().remove(spheres[i]);
		}
		current_mass=0;
		
		if(i>0)
		{
			Block block=playfield[i-1][j];
			block.addMass(playfield);
		}
		if(j>0)
		{
			Block block=playfield[i][j-1];
			block.addMass(playfield);
		}
		if(i<Game.n-1)
		{
			Block block=playfield[i+1][j];
			block.addMass(playfield);
		}
		if(j<Game.m-1)
		{
			Block block=playfield[i][j+1];
			block.addMass(playfield);
		}
	}
	public int getCritical_mass() {
		return critical_mass;
	}
	public void setCritical_mass(int critical_mass) {
		this.critical_mass = critical_mass;
	}
	public int getCurrent_mass() {
		return current_mass;
	}
	public void setCurrent_mass(int current_mass) {
		this.current_mass = current_mass;
	}
	public int getPlayer_no() {
		return player_no;
	}
	public void setPlayer_no(int player_no) {
		this.player_no = player_no;
	}
}

