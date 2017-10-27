import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RubiksCube extends Application
{
    static Node N;
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        //Draw two rectangles
        Rectangle upper = new Rectangle(100, 140, 100, 100);
        upper.setFill(null);
        upper.setStroke(Color.BLACK);
        Rectangle lower = new Rectangle(140, 100, 100, 100);
        lower.setFill(null);
        lower.setStroke(Color.BLACK);

        //Draw the line connecting them
        Line ul = new Line(100, 140, 140, 100);
        Line ur = new Line(240, 100, 200, 140);
        Line ll = new Line(140, 200, 100, 240);
        Line lr = new Line(240, 200, 200, 240);

        Sphere s=new Sphere();
        s.setRadius(25);
        s.setTranslateX(145);
        s.setTranslateY(190);

        Sphere s1=new Sphere();
        s1.setRadius(25);
        s1.setTranslateX(165);
        s1.setTranslateY(190);

        Rotate rt = new Rotate(0,5,5);
        s.getTransforms().add(rt);
        final Timeline rotationAnimation = new Timeline();
        rotationAnimation.getKeyFrames()
                .add(
                        new KeyFrame(
                                Duration.seconds(2),
                                new KeyValue(
                                        rt.angleProperty(),
                                        360
                                )
                        )
                );
        rotationAnimation.setCycleCount(Animation.INDEFINITE);
        rotationAnimation.play();


        Rotate rt1 = new Rotate(0,-5,-5);
        s1.getTransforms().add(rt1);
        final Timeline rotationAnimation1 = new Timeline();
        rotationAnimation1.getKeyFrames()
                .add(
                        new KeyFrame(
                                Duration.seconds(2),
                                new KeyValue(
                                        rt1.angleProperty(),
                                        360
                                )
                        )
                );
        rotationAnimation1.setCycleCount(Animation.INDEFINITE);
        rotationAnimation1.play();
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.RED);

        s.setMaterial(material);
        s1.setMaterial(material);

        pane.getChildren().addAll(upper, lower, ul, ur, ll, lr,s,s1);

        Scene scene = new Scene(pane, 400, 400);

        primaryStage.setTitle("Testing");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public double sphere2(Sphere s,Sphere s1){
        return (s.getTranslateX()+s1.getTranslateX())/2;
    }
    public static void main(String[] args) {
        launch(args);
    }

}