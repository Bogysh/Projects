package View;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;


public class MenuSubscenes extends SubScene {

    private final static String Background_PATH = "/View/Resources/blue_panel.png";
    private final static String FONT_PATH = "/View/Resources/kenvector_future.ttf";
    //static AnchorPane root = new AnchorPane();
    private boolean hide;


    public MenuSubscenes() {
        super(new AnchorPane(), 500, 400);
        BackgroundImage bgImage = new BackgroundImage(new Image(Background_PATH, 500, 400, false, true), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);

        AnchorPane root = (AnchorPane) this.getRoot();
        root.setBackground(new Background(bgImage));

        setLayoutY(250);
        setLayoutX(1024);
        hide = true;
    }


    public void moveSubscene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.5));
        transition.setNode(this);

        if (hide) {
            transition.setToX(-600);
            hide = false;
        } else {
            transition.setToX(0);
            hide = true;
        }
        transition.play();
    }

    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }


}
