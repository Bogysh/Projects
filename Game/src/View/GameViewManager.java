package View;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Random;


public class GameViewManager {

    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    private final int stageWidth = 600;
    private final int stageHeight = 800;

    private boolean leftKeyPressed;
    private boolean rightKeyPressed;
    private int angle;
    private AnimationTimer gameTimer;


    private Stage menuStage;
    private ImageView ship;

    private GridPane gridPanel1;
    private GridPane gridPanel2;
    private final String background_Path = "/View/Resources/blue_background.png";

    private final static String meteor_Brown_Path = "View/Resources/meteor_brown.png";
    private final static String meteor_Grey_Path = "View/Resources/meteor_grey.png";

    private ImageView[] brownMeteors;
    private ImageView[] greyMeteors;
    Random randomGenerator;

    private ImageView star;
    private infoLabel infobar;
    private ImageView[] life;
    private int lifeLeft;
    private int points;
    private String star_Path = "View/Resources/star_gold.png";

    private int ship_Radius = 30;
    private int star_Radius = 12;
    private int meteor_Radius = 20;


    public GameViewManager() {
        initializeStage();
        createKeyListeners();
        randomGenerator = new Random();

    }


    public void createKeyListeners() {
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.LEFT) {
                    leftKeyPressed = true;
                } else if (keyEvent.getCode() == KeyCode.RIGHT) {
                    rightKeyPressed = true;
                }
            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.LEFT) {
                    leftKeyPressed = false;
                } else if (keyEvent.getCode() == KeyCode.RIGHT) {
                    rightKeyPressed = false;
                }
            }
        });
    }

    public void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, stageWidth, stageHeight);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
    }

    public void createGame(Stage menuStage, Ship choosenShip) {
        this.menuStage = menuStage;
        this.menuStage.hide();
        createBackground();
        createShip(choosenShip);
        createGameElements(choosenShip);
        createGameLoop();
        gameStage.show();
    }

    private void createShip(Ship choosenShip) {
        ship = new ImageView(choosenShip.shipPath);
        ship.setLayoutX(stageWidth / 2);
        ship.setLayoutY(stageHeight - 90);
        gamePane.getChildren().add(ship);
    }

    private void createGameLoop() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                moveShip();
                moveBackground();
                moveDown();
                checkIfBelow();
                checkColision();
            }
        };
        gameTimer.start();
    }

    private void moveShip() {
        if (leftKeyPressed && !rightKeyPressed) {
            if (angle > -30) {
                angle -= 5;
            }
            ship.setRotate(angle);
            if (ship.getLayoutX() > -20) {
                ship.setLayoutX(ship.getLayoutX() - 3);
            }
        }
        if (!leftKeyPressed && rightKeyPressed) {
            if (angle < 30) {
                angle += 5;
            }
            ship.setRotate(angle);

            if (ship.getLayoutX() < 520) {
                ship.setLayoutX(ship.getLayoutX() + 3);
            }
        } else if (!leftKeyPressed && !rightKeyPressed) {
            if (angle < 0) {
                angle += 5;
            } else if (angle > 0) {
                angle -= 5;
            }

            ship.setRotate(angle);

        } else if (leftKeyPressed && rightKeyPressed) {
            if (angle < 0) {
                angle += 5;
            } else if (angle > 0) {
                angle -= 5;
            }
            ship.setRotate(angle);
        }
    }

    private void createBackground() {
        gridPanel1 = new GridPane();
        gridPanel2 = new GridPane();

        for (int i = 0; i < 12; i++) {


            ImageView backgroundImage1 = new ImageView(background_Path);
            ImageView backgroundImage2 = new ImageView(background_Path);
            GridPane.setConstraints(backgroundImage1, i % 3, i / 3);
            GridPane.setConstraints(backgroundImage2, i % 3, i / 3);
            gridPanel1.getChildren().add(backgroundImage1);
            gridPanel2.getChildren().add(backgroundImage2);
        }
        //gridPanel1.setLayoutY(0);
        gridPanel2.setLayoutY(-1024);
        gamePane.getChildren().addAll(gridPanel1, gridPanel2);

    }

    private void moveBackground() {
        gridPanel1.setLayoutY(gridPanel1.getLayoutY() + 0.5);
        gridPanel2.setLayoutY(gridPanel2.getLayoutY() + 0.5);

        if (gridPanel1.getLayoutY() >= 1024) {
            gridPanel1.setLayoutY(-1024);
        }
        if (gridPanel2.getLayoutY() >= 1024) {
            gridPanel2.setLayoutY(-1024);
        }
    }


    private void createGameElements(Ship ship) {
        lifeLeft = 2;
        star = new ImageView(star_Path);
        setMovingElements(star);
        gamePane.getChildren().add(star);
        infobar = new infoLabel("Points 00");
        infobar.setLayoutX(460);
        infobar.setLayoutY(20);
        gamePane.getChildren().add(infobar);
        life = new ImageView[3];

        for (int i = 0; i < life.length; i++) {
            life[i] = new ImageView(ship.getHealth());
            life[i].setLayoutX(455 + i * 40);
            life[i].setLayoutY(80);
            gamePane.getChildren().add(life[i]);
        }


        brownMeteors = new ImageView[3];
        greyMeteors = new ImageView[3];

        for (int i = 0; i < brownMeteors.length; i++) {
            brownMeteors[i] = new ImageView(meteor_Brown_Path);
            setMovingElements(brownMeteors[i]);
            gamePane.getChildren().add(brownMeteors[i]);
        }
        for (int i = 0; i < greyMeteors.length; i++) {
            greyMeteors[i] = new ImageView(meteor_Grey_Path);
            setMovingElements(greyMeteors[i]);
            gamePane.getChildren().add(greyMeteors[i]);
        }
    }

    private void moveDown() {
        star.setLayoutY(star.getLayoutY() + 8);
        star.setRotate(star.getRotate() + 4);


        for (int i = 0; i < brownMeteors.length; i++) {
            brownMeteors[i].setLayoutY(brownMeteors[i].getLayoutY() + 8);
            brownMeteors[i].setRotate(brownMeteors[i].getRotate() + 4);

        }

        for (int i = 0; i < greyMeteors.length; i++) {
            greyMeteors[i].setLayoutY(greyMeteors[i].getLayoutY() + 8);
            greyMeteors[i].setRotate(greyMeteors[i].getRotate() + 4);

        }
    }

    private void checkIfBelow() {
        if (star.getLayoutY() > 900)
            setMovingElements(star);


        for (int i = 0; i < greyMeteors.length; i++) {
            if (greyMeteors[i].getLayoutY() > 900)
                setMovingElements(greyMeteors[i]);

        }

        for (int i = 0; i < brownMeteors.length; i++) {
            if (brownMeteors[i].getLayoutY() > 900)
                setMovingElements(brownMeteors[i]);
        }


    }

    private void setMovingElements(ImageView img) {
        img.setLayoutX(randomGenerator.nextInt(370));
        img.setLayoutY(-(randomGenerator.nextInt(3000) + 600));
    }

    private void checkColision() {
        if (ship_Radius + star_Radius > calculateDistance(ship.getLayoutX()+ship_Radius/2, star.getLayoutX()+star_Radius/2, ship.getLayoutY(), star.getLayoutY())) {
            setMovingElements(star);
            points++;
        }
        String txt = "POINTS ";
        infobar.setText(txt + " " + points);


        for (int i = 0; i < greyMeteors.length; i++) {
            if (ship_Radius + meteor_Radius > calculateDistance(ship.getLayoutX()+ship_Radius/2, greyMeteors[i].getLayoutX()+meteor_Radius/2, ship.getLayoutY(), greyMeteors[i].getLayoutY())) {
                setMovingElements(greyMeteors[i]);
                removeLife();
            }
        }
        for (int i = 0; i < brownMeteors.length; i++) {
            if (ship_Radius + meteor_Radius > calculateDistance(ship.getLayoutX()+ship_Radius/2, brownMeteors[i].getLayoutX()+meteor_Radius/2, ship.getLayoutY(), brownMeteors[i].getLayoutY())) {
                setMovingElements(brownMeteors[i]);
                removeLife();
            }
        }
    }

    private void removeLife() {
        gamePane.getChildren().remove(life[lifeLeft]);
        lifeLeft--;

        if (lifeLeft < 0) {
            gameStage.close();
            gameTimer.stop();
            menuStage.show();
        }
    }

    private double calculateDistance(double x1, double x2, double y1, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}


