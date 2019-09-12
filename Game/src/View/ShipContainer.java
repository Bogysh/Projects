package View;

import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class ShipContainer extends VBox {

    private ImageView circleCheckImg;
    private ImageView shipCheckImg;
    private String check = "/View/Resources/blue_boxTick.png";
    private String notCheck = "/View/Resources/grey_boxTick.png";

    private Ship ship;

    private boolean choosen;

    public ShipContainer(Ship s) {
        setWidth(300);
        setHeight(300);
        picker(s);
    }

    public void picker(Ship ship) {
        {
            circleCheckImg = new ImageView(notCheck);
            shipCheckImg = new ImageView(ship.getShip());
            this.ship = ship;
            choosen = false;
            this.setAlignment(Pos.CENTER);
            setSpacing(20);
            this.getChildren().add(circleCheckImg);
            this.getChildren().add(shipCheckImg);
        }
    }


    public Ship getShip() {
        return ship;
    }

    ;

    public boolean getCircleChoosen() {
        return choosen;
    }

    public void setCircleChoosen(boolean choosen) {
        this.choosen = choosen;
        String ImgToSet = choosen ? check : notCheck;
        circleCheckImg.setImage(new Image(ImgToSet));
    }


}
